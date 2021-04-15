package cn.enncy.swing.utils;


import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * //TODO
 * <br/>Created in 13:46 2021/4/14
 * 只要是文件都行， setting.ini 或 setting.properties 都是可以使用这个类的，格式一定要key=value 。注释要用#
 *
 * @author: enncy
 */


public class PropertiesUtil {

    public String url = "bin/setting.ini";//设置默认的读取文件

    Map<Object, Object> map = new HashMap<>();


    PropertiesUtil() {
        read();
    }

    public PropertiesUtil(String url) {
        this.url = url;
        File file = new File(url);

        read(url);
    }

    //更新数据
    public void upDate(String key, String valuse) {

        String data = setReadFileListener(new ReadFileListener() {
            @Override
            String setLine(String line) {
                if (!line.equals("")) {
                    String data = "";
                    String string[] = line.replaceFirst("=", "#=#").split("#=#");
                    if (string.length != 0) {
                        String keystr = string[0].replaceAll("\\s*", "");
                        if (keystr.equals(key) && !line.contains("#")) {
                            data += key + "=" + valuse + "\n";
                        } else {
                            data += line + "\n";
                        }
                    }
                    return data;
                } else return "";
            }
        });
        saveData(data);

    }

    //读取默认的url配置文件
    public void read() {
        read(url);
    }


    public void read(String url) {
        setReadFileListener(new ReadFileListener() {
            @Override
            String setLine(String line) {
                if (line.contains("#")) line = line.split("#")[0];//不读取注释
                if (!line.equals("")) {
                    String string[] = line.replaceFirst("=", "#=#").split("#=#");//替换等号为特殊字符串#=#，然后分割（这里任意都行，只要不存在歧义）
                    if (string != null) {
                        map.put(string[0], string[1]);
                    }
                }
                return "";
            }
        });

    }

    //删除数据
    public void delete(String key) {
        String data = setReadFileListener(new ReadFileListener() {
            @Override
            String setLine(String line) {
                if (!line.equals("")) {
                    String data = "";
                    String string[] = line.replaceFirst("=", "#=#").split("#=#");
                    if (string.length != 0) {
                        String keystr = string[0].replaceAll("\\s*", "");
                        if (keystr.equals(key)) ;
                        else data += line + "\n";
                    }
                    return data;
                } else return "";
            }
        });
        saveData(data);
    }

    //添加数据
    public void add(String key, String value) {
        File file = new File(url);
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write("\n" + key + "=" + value);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //设置文件读取的监听器，每读取一行数据，返回一次回调函数
    private String setReadFileListener(ReadFileListener readFileListener) {
        BufferedReader reader = null;
        try {

            reader = new BufferedReader(new FileReader(new File(url)));
            String tempstr = "";
            String data = "";
            while ((tempstr = reader.readLine()) != null) {
                data += readFileListener.setLine(tempstr);

            }
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private void saveData(String data) {
        try {
            FileWriter writer = new FileWriter(url);
            // 向文件写入内容
            writer.write(data);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //修改完成后，重新读取，刷新数据
        read();
    }


    public String getString(String key) {
        return String.valueOf(map.get(key));
    }

    public int getInt(String key) {
        return Integer.valueOf(String.valueOf(map.get(key)).replaceAll("\\s*", ""));
    }

    public double getDouble(String key) {
        return Double.valueOf(String.valueOf(map.get(key)).replaceAll("\\s*", ""));
    }

    public boolean getBoolean(String key) {
        return Boolean.valueOf(String.valueOf(map.get(key)).replaceAll("\\s*", ""));
    }

    public Object get(Object key) {
        return map.get(key);
    }

    public String[] getStrings(String key) {
        return getString(key).split(",");
    }

    public int[] getInts(String key) {
        return StringToInt(getString(key).split(","));
    }

    public Object[] getObjects(String key) {
        return StringToObject(getString(key).split(","));
    }

    public int[] StringToInt(String[] arrs) {

        int[] ints = new int[arrs.length];

        for (int i = 0; i < arrs.length; i++) {

            ints[i] = Integer.parseInt(arrs[i]);

        }

        return ints;

    }

    public Object[] StringToObject(String[] arrs) {

        Object[] ints = new Object[arrs.length];

        for (int i = 0; i < arrs.length; i++) {

            ints[i] = (Object) arrs[i];

        }

        return ints;

    }


}

abstract class ReadFileListener {
    String setLine(String line) {
        return "";
    }

    ;
}


