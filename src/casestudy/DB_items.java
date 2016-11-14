package casestudy;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class DB_items {

    public static String gettimestamp() {

        //現在日時取得

        Calendar c = Calendar.getInstance();


        //フォーマットを指定

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");

        System.out.println(sdf.format(c.getTime()));

        return sdf.format(c.getTime());
    }
}
