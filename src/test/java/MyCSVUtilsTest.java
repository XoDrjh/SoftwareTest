import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MyCSVUtilsTest {

    @Test
    public void writeCsvFile() throws IOException {
        MyCSVUtils myCSVUtils = new MyCSVUtils();
        List<User> userList = new ArrayList<User>();
        String fileName = "writeCsvFileTest";
        userList.add(new User("xjh", "qiwjdalf"));
        userList.add(new User("xiaojh", "g324gsf"));
        userList.add(new User("xiaojiahao", "ajlsdf"));
        myCSVUtils.writeCsvFile(userList, fileName);
        // 接下来读取使用 JAVA 自带的库读取文件
        FileInputStream fip = new FileInputStream(fileName);
        InputStreamReader reader = new InputStreamReader(fip, "UTF-8");
        StringBuffer sb = new StringBuffer();
        while(reader.ready()){
            //将读取的数据转化成char类型，加入StringBuffer对象sb里
            sb.append((char)reader.read());
        }
        //将sb对象内容转化成string类型，输出
        String string = sb.toString();
        String[] strArr = string.split(",|\n");
        //关闭读取流
        reader.close();
        //关闭输入流，释放系统资源
        fip.close();
        assertEquals("username", strArr[0]);
        assertEquals("password", strArr[1]);
        for (int i = 0; i < 3; i ++) {
            assertEquals(userList.get(i).getUsername(), strArr[2 * i + 2]);
            assertEquals(userList.get(i).getPassword(), strArr[2 * i + 3]);
        }
    }

    @Test
    public void readCsvFile() throws IOException {
        String fileName = "readCsvFileTest";
        //构建FileOutputStream对象,文件不存在会自动新建
        FileOutputStream fop = new FileOutputStream(fileName);
        // 构建OutputStreamWriter对象,参数可以指定编码"UTF-8";不设置，默认为操作系统默认编码;
        OutputStreamWriter writer = new OutputStreamWriter(fop, "UTF-8");
        //写入缓冲区
        String[] strArr = {"xjh", "123", "xiaojh", "456", "xiaojiahao", "789"};
        writer.append("username,password\nxjh,123\nxiaojh,456\nxiaojiahao,789");
        // 关闭写入流,同时会把缓冲区内容写入文件
        writer.close();
        //关闭输出流，释放系统资源
        fop.close();

        MyCSVUtils myCSVUtils = new MyCSVUtils();
        List<User> userList = new ArrayList<User>();
        myCSVUtils.readCsvFile(userList, fileName);

        for (int i = 0; i < 3; i ++) {
            assertEquals(userList.get(i).getUsername(), strArr[2 * i]);
            assertEquals(userList.get(i).getPassword(), strArr[2 * i + 1]);
        }
    }
}