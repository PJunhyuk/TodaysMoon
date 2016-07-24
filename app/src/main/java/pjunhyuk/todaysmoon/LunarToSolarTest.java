package pjunhyuk.todaysmoon;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.ibm.icu.util.ChineseCalendar;

/**
 * Created by YonseiSIT15_PJH_DT on 2016-07-24.
 */
public class LunarToSolarTest {
    public static String getDateByString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    public static String convertLunarToSolar(String date) {
        ChineseCalendar cc = new ChineseCalendar();
        Calendar cal = Calendar.getInstance();

        cc.set(ChineseCalendar.EXTENDED_YEAR, Integer.parseInt(date.substring(0, 4)) + 2637);
        cc.set(ChineseCalendar.MONTH, Integer.parseInt(date.substring(4, 6)) - 1);
        cc.set(ChineseCalendar.DAY_OF_MONTH, Integer.parseInt(date.substring(6)));

        cal.setTimeInMillis(cc.getTimeInMillis());
        return getDateByString(cal.getTime());
    }

    public static String convertSolarToLunar(String date) {
        ChineseCalendar cc = new ChineseCalendar();
        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.YEAR, Integer.parseInt(date.substring(0, 4)));
        cal.set(Calendar.MONTH, Integer.parseInt(date.substring(4, 6)) - 1);
        cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date.substring(6)));

        cc.setTimeInMillis(cal.getTimeInMillis());

        int y = cc.get(ChineseCalendar.EXTENDED_YEAR) - 2637;
        int m = cc.get(ChineseCalendar.MONTH) + 1;
        int d = cc.get(ChineseCalendar.DAY_OF_MONTH);

        StringBuffer ret = new StringBuffer();
        ret.append(String.format("%04d", y)).append("-");
        ret.append(String.format("%02d", m)).append("-");
        ret.append(String.format("%02d" ,d));

        return ret.toString();
    }
}