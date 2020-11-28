import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

import static javax.swing.UIManager.put;

public class MyCSVUtils {
    //CSV文件分隔符
    private static final String NEW_LINE_SEPARATOR = "\n" ;

    //CSV文件头
    private static final String [] FILE_HEADER = { "username" , "password"};

    public List<User> writeCsvFile(List<User> userList, String fileName) {
        FileWriter fileWriter = null ;
        CSVPrinter csvFilePrinter = null ;
        //创建 CSVFormat
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);
        try {
            //初始化FileWriter
            fileWriter = new FileWriter(fileName);
            //初始化 CSVPrinter
            csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);
            //创建CSV文件头
//            csvFilePrinter.printRecord(FILE_HEADER);

            // 遍历List写入CSV
            for (User user : userList) {
                List<String> userDataRecord = new ArrayList<String>();
                userDataRecord.add(user.getUsername());
                userDataRecord.add(user.getPassword());
                csvFilePrinter.printRecord(userDataRecord);
            }
            System.out.println( "CSV文件创建成功~~~" );

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
                csvFilePrinter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return userList;
    }

    public List<User> readCsvFile(List<User> userList, String fileName) {
        FileReader fileReader = null ;
        CSVParser csvFileParser = null ;
        //创建CSVFormat（header mapping）
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(FILE_HEADER);
        try {
            //初始化FileReader object
            fileReader = new FileReader(fileName);
            //初始化 CSVParser object
            csvFileParser = new CSVParser(fileReader, csvFileFormat);
            //CSV文件records
            List<CSVRecord> csvRecords = csvFileParser.getRecords();

            for ( int i = 1 ; i < csvRecords.size(); i++) {
                CSVRecord record = csvRecords.get(i);
                //创建用户对象填入数据
                User user = new User(record.get( "username" ), record.get( "password" ));
                userList.add(user);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
                csvFileParser.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return userList;
    }

}
