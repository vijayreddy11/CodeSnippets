import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeStampUtils {

    public static String changeToTimeStamp(Long unixSecondsd) {
        Date date = new Date(unixSecondsd);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return new String(sdf.format(date));
        } catch (Exception ex) {
            return null;
        }
    }

    public static String changeToTimestamp(String date) {
        Date date1 = new Date(date);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return new String(sdf.format(date1));
        } catch (Exception ex) {
            return null;
        }
    }

    public static Timestamp getTimestamp(String dateString) {
        try {
            Date date = new Date(dateString);
            Timestamp ts = new Timestamp(date.getTime());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return Timestamp.valueOf(formatter.format(ts));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
