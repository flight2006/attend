package com.hdu.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Flight on 2015/7/28.
 */
public class DateUtils {
    private SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public Timestamp converIntoNormal(Date old) {
        String dateStr = sdf.format(old);
        Timestamp eld = Timestamp.valueOf(dateStr);
        return eld;
    }
}
