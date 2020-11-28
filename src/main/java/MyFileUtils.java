import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyFileUtils {
    public void writeFile(List<User> userList, String fileName) throws IOException {
        List<String> usernameList = new ArrayList<String>();
        List<String> passwordList = new ArrayList<String>();
        String usernameFile = "username" + fileName + ".txt";
        String passwordFile = "password" + fileName + ".txt";

        for (User user : userList) {
            usernameList.add(user.getUsername());
            passwordList.add(user.getPassword());
        }
        // 分两个文件写，用户名文件和密码文件
        FileUtils.writeLines(new File(usernameFile), usernameList, false);
        FileUtils.writeLines(new File(passwordFile), passwordList, false);
        System.out.println( "TXT文件创建成功~~~" );
    }

    public void readFile(List<User> userList, String fileName) throws IOException {
        List<String> usernameList;
        List<String> passwordList;
        String usernameFile = "username" + fileName + ".txt";
        String passwordFile = "password" + fileName + ".txt";
        // 分两个文件读，用户名文件和密码文件
        usernameList = FileUtils.readLines(new File(usernameFile), "UTF-8");
        passwordList = FileUtils.readLines(new File(passwordFile), "UTF-8");

        for (int i = 0; i < usernameList.size(); i ++) {
            userList.add(new User(usernameList.get(i), passwordList.get(i)));
        }
    }
}