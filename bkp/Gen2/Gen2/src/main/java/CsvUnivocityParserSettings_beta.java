import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

public class CsvUnivocityParserSettings_beta {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream(new File("C:\\Users\\MT1061_Vijay\\OneDrive - Modak Analytics LLP\\Backup\\Gen2\\src\\main\\resources\\5bbe6c55-bce6-4edb-9d04-68edeb6bf7b1.csv"));
        DelimitedFileFormatUtil delimitedFileFormatUtil = new DelimitedFileFormatUtil();
        SemiStructuredUtils semiStructuredUtils = new SemiStructuredUtils();
//        String headerInfo = delimitedFileFormatUtil.getCSVFormat(inputStream);
//        System.out.println(headerInfo);

//        List<HashMap<String, Object>> mapList = semiStructuredUtils.getCSVColMetadata_commonsCSV(inputStream,"csv");
//        System.out.println(mapList);

        List<HashMap<String, Object>> listMap = semiStructuredUtils.getCSVAndTSVColMetadata(inputStream,"csv",true,10);
        System.out.println(listMap);
    }
}
