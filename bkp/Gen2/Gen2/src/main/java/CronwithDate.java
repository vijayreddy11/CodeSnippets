import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class CronwithDate {
    public static void main(String[] args) {

        SimpleDateFormat formatter = new SimpleDateFormat("00 mm HH dd MM ? yyyy");
        Date date = new Date();
        System.out.println(formatter.format(date));

        java.util.Date UTCdate = new Date(System.currentTimeMillis()
                - TimeUnit.HOURS.toMillis(5)
                - TimeUnit.MINUTES.toMillis(30)
                + TimeUnit.MINUTES.toMillis(3));
        System.out.println(formatter.format(UTCdate));
        }
    }

