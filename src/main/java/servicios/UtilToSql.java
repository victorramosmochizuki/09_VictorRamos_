package servicios;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class UtilToSql {

    public static java.sql.Timestamp convert(java.util.Date uDate) {
        Timestamp timestamp = new Timestamp(uDate.getTime());
        return timestamp;
    }
    
    public static java.sql.Date convertDate(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }
}
