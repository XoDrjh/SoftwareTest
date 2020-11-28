import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MyCommonAppTest {

    @Test
    public void transFile2CSV() throws IOException {
        MyFileUtils myFileUtils = mock(MyFileUtils.class);
        MyCSVUtils myCSVUtils = mock(MyCSVUtils.class);
        String fileName = "transFile2CSVTest";
        List<User> userList = new ArrayList<User>();
        List<User> userList2 = new ArrayList<User>();
        userList.add(new User("xjh", "qiwjdalf"));
        userList.add(new User("xiaojh", "g324gsf"));
        userList.add(new User("xiaojiahao", "ajlsdf"));
        when(myFileUtils.writeFile(userList, fileName)).thenReturn(userList);
        when(myCSVUtils.writeCsvFile(userList, fileName)).thenReturn(userList);
        when(myFileUtils.readFile(userList2, fileName)).thenReturn(userList);
        when(myCSVUtils.readCsvFile(userList2, fileName)).thenReturn(userList);
        userList = myFileUtils.writeFile(userList, fileName);
        MyCommonApp.transFile2CSV(fileName);
        userList2 = myCSVUtils.readCsvFile(userList2, fileName);
        for (int i = 0; i < userList.size(); i ++) {
            assertEquals(userList.get(i).getUsername(), userList2.get(i).getUsername());
            assertEquals(userList.get(i).getPassword(), userList2.get(i).getPassword());
        }
    }

    @Test
    public void transCSV2File() throws IOException {
        MyFileUtils myFileUtils = mock(MyFileUtils.class);
        MyCSVUtils myCSVUtils = mock(MyCSVUtils.class);
        String fileName = "transCSV2FileTest";
        List<User> userList = new ArrayList<User>();
        List<User> userList2 = new ArrayList<User>();
        userList.add(new User("xjh", "qiwjdalf"));
        userList.add(new User("xiaojh", "g324gsf"));
        userList.add(new User("xiaojiahao", "ajlsdf"));
        when(myFileUtils.writeFile(userList, fileName)).thenReturn(userList);
        when(myCSVUtils.writeCsvFile(userList, fileName)).thenReturn(userList);
        when(myFileUtils.readFile(userList2, fileName)).thenReturn(userList);
        when(myCSVUtils.readCsvFile(userList2, fileName)).thenReturn(userList);
        userList = myCSVUtils.writeCsvFile(userList, fileName);
        MyCommonApp.transCSV2File(fileName);
        userList2 = myFileUtils.readFile(userList2, fileName);
        for (int i = 0; i < userList.size(); i ++) {
            assertEquals(userList.get(i).getUsername(), userList2.get(i).getUsername());
            assertEquals(userList.get(i).getPassword(), userList2.get(i).getPassword());
        }
    }
}