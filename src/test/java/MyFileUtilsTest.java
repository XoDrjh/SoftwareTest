import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MyFileUtilsTest {

    @Test
    public void writeFile() throws IOException {
        MyFileUtils myFileUtils = new MyFileUtils();
        List<User> userList = new ArrayList<User>();
        String fileName = "wirteFileTest";
        userList.add(new User("xjh", "qiwjdalf"));
        userList.add(new User("xiaojh", "g324gsf"));
        userList.add(new User("xiaojiahao", "ajlsdf"));
        myFileUtils.writeFile(userList, fileName);
        // 接下来读取使用 JAVA 自带的库读取文件
        String usernameFile = "username" + fileName + ".txt";
        String passwordFile = "password" + fileName + ".txt";
        FileInputStream fip = new FileInputStream(usernameFile);
        FileInputStream fip2 = new FileInputStream(passwordFile);
        InputStreamReader reader = new InputStreamReader(fip, "UTF-8");
        InputStreamReader reader2 = new InputStreamReader(fip2, "UTF-8");
        StringBuffer sb = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();
        while(reader.ready() && reader2.ready()){
            //将读取的数据转化成char类型，加入StringBuffer对象sb里
            sb.append((char)reader.read());
            sb2.append((char)reader2.read());
        }
        //将sb对象内容转化成string类型，输出
        String string = sb.toString();
        String[] strArr = string.split("\r\n");
        String string2 = sb2.toString();
        String[] strArr2 = string2.split("\r\n");
        //关闭读取流
        reader.close();
        reader2.close();
        //关闭输入流，释放系统资源
        fip.close();
        fip2.close();
        for (int i = 0; i < 3; i ++) {
            assertEquals(userList.get(i).getUsername(), strArr[i]);
            assertEquals(userList.get(i).getPassword(), strArr2[i]);
        }
    }

    @Test
    public void readFile() throws IOException {
        String fileName = "readFileTest";
        String usernameFile = "username" + fileName + ".txt";
        String passwordFile = "password" + fileName + ".txt";
        //构建FileOutputStream对象,文件不存在会自动新建
        FileOutputStream fop = new FileOutputStream(usernameFile);
        FileOutputStream fop2 = new FileOutputStream(passwordFile);
        // 构建OutputStreamWriter对象,参数可以指定编码"UTF-8";不设置，默认为操作系统默认编码;
        OutputStreamWriter writer = new OutputStreamWriter(fop, "UTF-8");
        OutputStreamWriter writer2 = new OutputStreamWriter(fop2, "UTF-8");
        //写入缓冲区
        String[] strArr = {"xjh", "xiaojh", "xiaojiahao"};
        String[] strArr2 = {"123", "456", "789"};
        writer.append("xjh\r\nxiaojh\r\nxiaojiahao");
        writer2.append("123\r\n456\r\n789");
        // 关闭写入流,同时会把缓冲区内容写入文件
        writer.close();
        writer2.close();
        //关闭输出流，释放系统资源
        fop.close();
        fop2.close();

        MyFileUtils myFileUtils = new MyFileUtils();
        List<User> userList = new ArrayList<User>();
        myFileUtils.readFile(userList, fileName);

        for (int i = 0; i < 3; i ++) {
            assertEquals(userList.get(i).getUsername(), strArr[i]);
            assertEquals(userList.get(i).getPassword(), strArr2[i]);
        }
    }
}