package cn.enncy.swing.utils.scan;


import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Objects;

/**
 * //TODO
 * <br/>Created in 14:12 2021/4/14
 *
 * @author: enncy
 */
public class FileScanner {
    private String  packageName;
    /**
     * 开始扫描指定包下的类，并返回
     * packageName  -包名,例如  xx.xxx.xxx
     */
    public   Class[] scan(String packageName) throws URISyntaxException, ClassNotFoundException{
        this.packageName = packageName;

        URL url =  Thread.currentThread().getContextClassLoader().getResource(("\\"+packageName).replace('.', '\\'));
        assert url != null;
        File file = new File(url.toURI());

        return scanFile(file);
    }

    /**
     * 扫描文件，这里不使用递归，使用出栈入栈方式遍历
     */
    private   Class[] scanFile(File file) throws ClassNotFoundException {

        LinkedList<Class> classList = new LinkedList<Class>();
        LinkedList<File> fileList = new LinkedList<File>();
        fileList.push(file);

        while(!fileList.isEmpty()){
            File targetFile = fileList.pop();

            if(targetFile.isFile() && targetFile.getName().endsWith(".class")){
                //获取绝对路径
                String path = targetFile.getAbsolutePath();
                //获取包路径
                String packagePath = path.substring(path.indexOf(this.packageName.replace('.', '\\')),path.length());
                //处理包路径，变成包名的格式
                String className = packagePath.replace('\\', '.').replaceAll(".class", "");
                //添加class到集合中
                classList.push(Class.forName(className));



            }else if(targetFile.isDirectory()){
                if(targetFile.listFiles()!=null){
                    Collections.addAll(fileList, Objects.requireNonNull(targetFile.listFiles()));
                }
            }
        }
        return  classList.toArray(new Class[0]);
    }


}
