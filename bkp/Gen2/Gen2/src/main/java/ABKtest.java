import org.apache.commons.io.FileUtils;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ABKtest {
    public static void main(String[] args) throws IOException {
        //String jsonString = FileUtils.readFileToString(new File("C:\\Users\\MT1061_Vijay\\OneDrive - Modak Analytics LLP\\Backup\\Gen2\\src\\main\\resources\\test.json"));
        Map<String, Object> templateData = new HashMap<>();
                //JSONUtils.jsonToMap(jsonString);
        templateData.put("usubjid_checker",true);
        final STGroup stGroup = new STGroupFile("C:\\Users\\MT1061_Vijay\\OneDrive - Modak Analytics LLP\\Backup\\Gen2\\src\\main\\resources\\abk.stg",'$','$');



        ST listTemp = stGroup.getInstanceOf("listTempZ");
        listTemp.add("templateData",templateData);
        System.out.println(listTemp.render());
        //System.out.println(lst);
        //System.out.println(mapTemp.render());
//
//        ST listTemp2 = stGroup.getInstanceOf("listTemp2");
//        listTemp2.add("templateData",templateData);
//
//        System.out.println(listTemp.render());
//        System.out.println("\n=================================================================\n");
//        System.out.println(listTemp2.render());
//
//        ST tryy = stGroup.getInstanceOf("updateDM");
//        tryy.add("data_movement_ids","ff")
//                .add("refreshfreq","Ffr");
//        System.out.println(tryy.render());
    }
}
