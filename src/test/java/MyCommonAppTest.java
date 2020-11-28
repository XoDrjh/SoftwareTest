import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MyCommonAppTest {

    @Test
    public void transFile2CSV() throws IOException {
        MyFileUtils myFileUtils = new MyFileUtils();
        MyCSVUtils myCSVUtils = new MyCSVUtils();
        String fileName = "transFile2CSVTest";
        List<User> userList = new ArrayList<User>();
        List<User> userList2 = new ArrayList<User>();
        userList.add(new User("xjh", "qiwjdalf"));
        userList.add(new User("xiaojh", "g324gsf"));
        userList.add(new User("xiaojiahao", "ajlsdf"));
        myFileUtils.writeFile(userList, fileName);
        MyCommonApp.transFile2CSV(fileName);
        myCSVUtils.readCsvFile(userList2, fileName);
        for (int i = 0; i < userList.size(); i ++) {
            assertEquals(userList.get(i).getUsername(), userList2.get(i).getUsername());
            assertEquals(userList.get(i).getPassword(), userList2.get(i).getPassword());
        }
    }

    @Test
    public void transCSV2File() throws IOException {
        MyFileUtils myFileUtils = new MyFileUtils();
        MyCSVUtils myCSVUtils = new MyCSVUtils();
        String fileName = "transCSV2FileTest";
        List<User> userList = new ArrayList<User>();
        List<User> userList2 = new ArrayList<User>();
        userList.add(new User("xjh", "qiwjdalf"));
        userList.add(new User("xiaojh", "g324gsf"));
        userList.add(new User("xiaojiahao", "ajlsdf"));
        myCSVUtils.writeCsvFile(userList, fileName);
        MyCommonApp.transCSV2File(fileName);
        myFileUtils.readFile(userList2, fileName);
        for (int i = 0; i < userList.size(); i ++) {
            assertEquals(userList.get(i).getUsername(), userList2.get(i).getUsername());
            assertEquals(userList.get(i).getPassword(), userList2.get(i).getPassword());
        }
    }


}