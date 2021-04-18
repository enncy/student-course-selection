package cn.enncy.io;


import java.io.*;
import java.net.URI;

/**
 * //TODO
 * <br/>Created in 18:34 2021/4/18
 *
 * @author: enncy
 */
public class FileContentReader{

    public static String getFileContent(File file) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String content = "";
        String line = "";
        while ((line = bufferedReader.readLine())!=null){
            content += line + '\n';
        }
        return content;
    }

    public static String getFileContent(URI url) throws IOException {
        return getFileContent(new File(url));
    }

}
