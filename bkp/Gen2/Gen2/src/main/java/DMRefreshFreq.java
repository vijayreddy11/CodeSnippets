import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

public class DMRefreshFreq {
    public static void main(String[] args) throws Exception {
        DMRefreshFreq dmRefreshFreq =new DMRefreshFreq();

        String data_movement_ids = "hj";
        String refreshfreq="nk";


        dmRefreshFreq.init(data_movement_ids,refreshfreq);

    }
    private void init(String data_movement_ids, String refreshfreq) throws Exception {

        STGroup stGroup = new STGroupFile("src\\main\\resources\\queries.stg");
        String updateDM = stGroup.getInstanceOf("updateDM")
                .add("data_movement_ids",data_movement_ids)
                .add("refreshfreq",refreshfreq)
                .render();
        System.out.println(updateDM);
        //initDB();
    }
}
