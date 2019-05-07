package com.hpedu.util.codeUtil;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 此类为日期功能类，不可实例化，全为静态变量（方法），直接通过DateUtil.get****调用
 */
public final class DateUtil {
    /**
     * 1秒钟的毫秒数
     */
    public static final int SECOND = 1000;
    /**
     * 1分钟的毫秒数
     */
    public static final int MINUTE = SECOND * 60;
    /**
     * 1小时的毫秒数
     */
    public static final int HOUR = MINUTE * 60;
    /**
     * 1天的毫秒数
     */
    public static final int DAY = HOUR * 24;
    /**
     * 1周的毫秒数
     */
    public static final int WEEK = DAY * 7;
    /**
     * 1年的毫秒数
     */
    public static final int YEAR = DAY * 365; // or 366 ???
    /**
     * 北京时间与GMT时差的毫秒数
     */
    public static final long GMT_BEIJING_TIME_OFFSET = HOUR * 8;
    static ThreadLocal<Map<String, DateFormat>> threadLocal = ThreadLocal.withInitial(() -> new HashMap<>());
    private static DateFormat ddMMyyyyFormat;
    private static DateFormat dateFormat;
    private static DateFormat datetimeFormat;
    private static DateFormat yMdhms;
    private static DateFormat yyyy_MM_dd;
    /**
     * 服务器与GMT时差的毫秒数 This is the time difference between GMT time and SERVER
     * time
     */
    private static long SERVER_TIME_OFFSET = HOUR;// * (new

    static {
        threadLocal.get().put("ddMMyyyyFormat", new SimpleDateFormat("dd/MM/yyyy"));
        threadLocal.get().put("dateFormat", DateFormat.getDateInstance(DateFormat.DEFAULT));
        threadLocal.get().put("datetimeFormat", DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT));
        threadLocal.get().put("yMdhms", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        threadLocal.get().put("yyyy_MM_dd", new SimpleDateFormat("yyyy-MM-dd"));

        ddMMyyyyFormat = threadLocal.get().get("ddMMyyyyFormat");
        dateFormat = threadLocal.get().get("dateFormat" );
        datetimeFormat = threadLocal.get().get("datetimeFormat" );
        yMdhms = threadLocal.get().get("yMdhms") ;
        yyyy_MM_dd = threadLocal.get().get("yyyy_MM_dd") ;
    }

    /**
     * private constructor
     */
    private DateUtil() {// prevent instantiation
    }

    /**
     * 返回日期的dd/MM/yyyy形式的String
     *
     * @param date
     * @return
     */
    public static String getDateDDMMYYYY(Date date) {
        return ddMMyyyyFormat.format(date);
    }

    /**
     * 返回date默认日期格式的String
     *
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        return dateFormat.format(date);
    }

    /**
     * 返回date默认日期时间格式的String
     *
     * @param date
     * @return
     */
    public static String formatDateTime(Date date) {
        return datetimeFormat.format(date);
    }

    /**
     * 格式化日期,格式化后可直接insert into [DB]
     *
     * @param date
     * @return
     */
    public static String dateToStr(Date date) {

        if (date == null)
            return "";
        return yMdhms.format(date);

    }

    /**
     * 格式化日期,格式化后可直接insert into [DB]
     *
     * @param date
     * @return
     */
    public static String To_yyyy_MM_dd(Date date) {

        if (date == null)
            return "";
        else {
            String str_Date = yyyy_MM_dd.format(date);
            return str_Date;
        }
    }

    /**
     * 格式化日期
     *
     * @param str
     * @return
     */
    public static Date strToDate(String str) {
        if (str == null)
            return null;
        DateFormat defaultDate = DateFormat.getDateInstance();
        Date date = null;
        try {
            date = defaultDate.parse(str);
        } catch (ParseException pe) {
            pe.printStackTrace();
        }
        return date;
    }

    /**
     * 返回当前时间，格式'yyyy-mm-dd HH:mm:ss' 可为orl插入当前时间
     *
     * @return
     */
    public static String getLocalDate() {
        java.util.Date dt = new java.util.Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        df.setTimeZone(TimeZone.getDefault());
        return df.format(dt);
    }

    /**
     * 设置日期
     *
     * @return
     */
    public static Date setDate(String s, String s1, String s2) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(s), Integer.parseInt(s1) - 1,
                Integer.parseInt(s2));
        return calendar.getTime();
    }

    /**
     * format:2003-11-07 当日期在数据库中是char是，这种格式可用于排序
     *
     * @param date
     * @return
     */
    public static String getYMD(Date date) {
        return yyyy_MM_dd.format(date);
    }

    public static String getYear(Date date) {
        // 2003
        Format formatter;
        formatter = new SimpleDateFormat("yyyy");
        return formatter.format(date);
    }

    public static String getMonth(Date date) {
        // 09
        Format formatter;
        formatter = new SimpleDateFormat("MM");
        return formatter.format(date);
    }

    public static String getDay(Date date) {
        // 02
        Format formatter;
        formatter = new SimpleDateFormat("dd");
        return formatter.format(date);
    }

    /**
     * 两个日期相差天数（d1-d2）
     *
     * @param d1
     * @param d2
     * @return
     */
    public static long comparedays(Date d1, Date d2) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d1);
        long time1 = cal.getTimeInMillis();
        cal.setTime(d2);
        long time2 = cal.getTimeInMillis();
        long between_days = (time1 - time2) / (1000 * 3600 * 24);
        return between_days;
    }

    /**
     * 增加日期
     *
     * @param date
     * @param i
     * @return
     */
    public static Date addDate(Date date, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(5, i);
        return calendar.getTime();
    }

    public static Date addMonth(Date date, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(2, i);
        return calendar.getTime();
    }

    public static Date addYear(Date date, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(1, i);
        return calendar.getTime();
    }

    /**
     * 判断当前日期是星期几
     *
     * @param date 日期
     * @return 1, 2, 3, 4, 5, 6, 7
     * @throws Exception
     */
    public static int dayForWeek(Date date) throws Exception {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int dayForWeek = 0;
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            dayForWeek = 7;
        } else {
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        }
        return dayForWeek;
    }

    /**
     * 判断当前日期是当前月的第几天
     *
     * @param date 日期
     * @return 1, 2, 3, 4, 5, 6, 7...
     * @throws Exception
     */
    public static int dayForMonth(Date date) throws Exception {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 判断当前日期是当年第几月份
     *
     * @param date 日期
     * @return 1, 2, 3, 4, 5, 6, 7...
     * @throws Exception
     */
    public static int MonthForYear(Date date) throws Exception {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MONTH) + 1;
    }

    public static String getToday() {
        Calendar cl = Calendar.getInstance();
        String month = "";
        String day = "";
        String today = "";
        String today2 = "";
        int m = cl.get(Calendar.MONTH) + 1;
        if (m < 10) {
            month = "0" + String.valueOf(m);
        } else {
            month = String.valueOf(m);
        }
        int d = cl.get(Calendar.DATE);
        if (d < 10) {
            day = "0" + String.valueOf(d);
        } else {
            day = String.valueOf(d);
        }
        today = cl.get(Calendar.YEAR) + month + day
                + cl.get(Calendar.HOUR_OF_DAY) + cl.get(Calendar.MINUTE)
                + cl.get(Calendar.SECOND);
        today2 = cl.get(Calendar.YEAR) + "-" + month + "-" + day + " "
                + cl.get(Calendar.HOUR_OF_DAY) + ":" + cl.get(Calendar.MINUTE)
                + ":" + cl.get(Calendar.SECOND);
        return today2;
    }

    /**
     * * 获取指定日期是星期几 参数为null时表示获取当前日期是星期几
     *
     * @param date
     * @return
     */
    public static String getWeekOfDate(Date date) {
        String[] weekOfDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekOfDays[w];
    }

    /**
     * 将日期文字转化为具体的日期
     *
     * @return
     */
    public static Date string2Date(String dateString, String formatString) {
        SimpleDateFormat formate = new SimpleDateFormat(formatString);
        Date date;
        try {
            date = formate.parse(dateString);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将日期转化为字符串
     *
     * @return
     */
    public static String Date2String(Date date, String formatString) {
        SimpleDateFormat formate = new SimpleDateFormat(formatString);
        return formate.format(date);
    }

    // 计算两个日期差多少天
    // format "yyyy-MM-dd"
    public String countDate(Date startDate, Date endDate, String format) {
        // 按照传入的格式生成一个simpledateformate对象
        SimpleDateFormat sd = new SimpleDateFormat(format);
        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
        long nh = 1000 * 60 * 60;// 一小时的毫秒数
        long nm = 1000 * 60;// 一分钟的毫秒数
        long ns = 1000;// 一秒钟的毫秒数long diff;try {
        // 获得两个时间的毫秒时间差异
        Long diff = endDate.getTime() - startDate.getTime();
        long day = diff / nd;// 计算差多少天
        if (day != 0) {
            return Long.toString(day) + "天前";
        }

        long hour = diff % nd / nh;// 计算差多少小时
        if (hour != 0) {
            return Long.toString(hour) + "小时前";
        }
        long min = diff % nd % nh / nm;// 计算差多少分钟
        if (min != 0) {
            return Long.toString(min) + "分钟";
        }
        long sec = diff % nd % nh % nm / ns;// 计算差多少秒//输出结果
        System.out.println("时间相差：" + day + "天" + hour + "小时" + min + "分钟" + sec
                + "秒。");
        return "刚刚";
    }
    // DateOptions()).serverHourOffset;
}
