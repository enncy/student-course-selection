package cn.enncy.scs.utils;


import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * //TODO
 * <br/>Created in 22:03 2021/5/1
 *
 * @author: enncy
 */
public class PropertiesUtils {
    //目标文件
    private File file;
    //目标路径
    private String path;
    //默认字符编码
    private String charset = "gbk";
    //文件的每一行
    private String[] lines;
    //注释符
    private static final String ANNOTATION_CHAR = "#";



    public PropertiesUtils(File file) throws IOException {
        this.file = file;
        this.lines = getFileLines(file);
    }

    public PropertiesUtils(String path) throws IOException {
        this.path = path;
        this.lines = getFileLines(path);
    }


    /*
     *  根据键获取值
     *
     * @param key  键
     * @return: java.lang.String
     */
    public String get(String key) {
        for (String line : lines) {
            if (hasKey(line, key)) {
                return getLineValue(line);
            }
        }
        return null;
    }


    /**
     * 添加配置
     *
     * @param key          键
     * @param value        值
     * @param descriptions 字段描述，可添加多个描述
     * @return: boolean
     */
    public boolean add(String key, String value, String... descriptions) {
        List<String> lineList = new ArrayList<>(Arrays.asList(lines));
        for (String description : descriptions) {
            lineList.add(ANNOTATION_CHAR + description);
        }
        lineList.add(key.trim() + "=" + value.trim());
        return commit(lineList.toArray(new String[0]));
    }


    /**
     * 设置键值对
     *
     * @param key   键
     * @param value 值
     * @return: boolean
     */
    public boolean set(String key, String value) {
        for (int i = 0; i < lines.length; i++) {
            if (hasKey(lines[i], key)) {
                lines[i] = key + "=" + value;
            }
        }
        return commit(lines);
    }

    /*
     *  删除键值对
     *
     * @param key  键
     * @return: boolean
     */
    public boolean delete(String key) {
        List<String> lineList = new ArrayList<>(Arrays.asList(lines));
        lineList.removeIf(line -> hasKey(line, key));
        return commit(lineList.toArray(new String[0]));
    }

    /*
     *  保存文件
     *
     * @param lines 文件每一行
     * @return: boolean
     */
    private boolean commit(String[] lines) {
        synchronized (this) {
            this.lines = lines;
            try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),charset))){
                StringBuilder data = new StringBuilder();
                for (String line : lines) {
                    data.append(line).append('\n');
                }
                bufferedWriter.write(data.toString());
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    /*
     *  获取一行中的 key 值
     *
     * @param line  目标行
     * @return: java.lang.String
     */
    private String getLineKey(String line) {
        return line.substring(0, line.indexOf('=')).trim();
    }

    /**
     * 获取一行中的 value 值
     *
     * @param line 目标行
     * @return: java.lang.String
     */

    private String getLineValue(String line) {
        return line.substring(line.indexOf('=') + 1).trim();
    }


    /**
     * 如果是键值对行(不以 # 开头，并且包含 = 符号)，则返回 true ，如果是注释行则返回 false。
     *
     * @return: boolean
     */
    private boolean isValidLine(String line) {
        return !line.trim().startsWith("#") && line.contains("=");
    }

    /**
     * 判断一行中是否有key 键
     *
     * @param line 目标行
     * @param key  目标键
     * @return: boolean
     */
    private boolean hasKey(String line, String key) {
        return isValidLine(line) && key.equals(getLineKey(line));
    }

    /**
     * 获取文件的每一行
     *
     * @param file 目标文件
     * @return: java.lang.String[]
     */
    private String[] getFileLines(File file) {

        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));){
            String line;
            List<String> lines = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
            return lines.toArray(new String[0]);
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取文件的每一行，如果路径不存在，则读取 resource 路径下的文件，如果resource路径不存在，则抛出IOException异常
     *
     * @param path 目标路径
     * @return: java.lang.String[]
     */
    private String[] getFileLines(String path) throws IOException {
        File file = new File(path);
        if (file.isFile()) {
            this.file = file;
            return getFileLines(file);
        } else {
            URL url = getClass().getClassLoader().getResource(path);
            if (url == null) {
                throw new FileNotFoundException("resource path is not found");
            }
            file = new File(url.getPath());
            if (file.isFile()) {
                this.file = file;
                return getFileLines(file);
            } else {
                throw new FileNotFoundException("properties file is not found");
            }
        }
    }


    public static PropertiesUtils getInstance(String path) {
        try{
            return new PropertiesUtils(path);
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public static PropertiesUtils getInstance(File file) {
        try{
            return new PropertiesUtils(file);
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }


    // Type conversion

    public boolean getBoolean(String key) {
        return Boolean.parseBoolean(get(key));
    }

    public int getInt(String key) {
        return Integer.parseInt(get(key));
    }

    public float getFloat(String key) {
        return Float.parseFloat(get(key));
    }

    public double getDouble(String key) {
        return Double.parseDouble(get(key));
    }

    public short getShort(String key) {
        return Short.parseShort(get(key));
    }

    public long getLong(String key) {
        return Long.parseLong(get(key));
    }

    //getter and setter

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String[] getLines() {
        return lines;
    }

    public void setLines(String[] lines) {
        this.lines = lines;
    }
}
