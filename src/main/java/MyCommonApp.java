import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MyCommonApp {
    public static void transFile2CSV(String fileName) throws IOException {
        MyFileUtils myFileUtils = new MyFileUtils();
        MyCSVUtils myCSVUtils = new MyCSVUtils();
        List<User> userList = new ArrayList<User>();
        userList = myFileUtils.readFile(userList, fileName);
        myCSVUtils.writeCsvFile(userList, fileName);
    }

    public static void transCSV2File(String fileName) throws IOException {
        MyFileUtils myFileUtils = new MyFileUtils();
        MyCSVUtils myCSVUtils = new MyCSVUtils();
        List<User> userList = new ArrayList<User>();
        userList = myCSVUtils.readCsvFile(userList, fileName);
        myFileUtils.writeFile(userList, fileName);
    }

    public static void inputUser2File() throws IOException {
        MyFileUtils myFileUtils = new MyFileUtils();
        List<User> userList = new ArrayList<User>();
        Scanner scanner = new Scanner(System.in);
        String str;
        while(!(str = scanner.nextLine()).equals("#")) {
            String[] strArr = str.split(" ");
            userList.add(new User(strArr[0], strArr[1]));
        }
        str = scanner.nextLine();
        myFileUtils.writeFile(userList, str);
        transFile2CSV(str);
        scanner.close();
    }

    public static void main(String[] args) throws IOException {
        inputUser2File();
    }
}
