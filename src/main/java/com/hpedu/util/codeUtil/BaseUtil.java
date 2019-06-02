package com.hpedu.util.codeUtil;

import com.hpedu.web.core.video.pojo.ContestVideo;
import com.hpedu.web.core.video.pojo.GeneralVideo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/*import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;*/

/**
 * @author 赖佳
 * @date 2016-09-21
 * @des 基础工具类
 */
public class BaseUtil {

    private static Logger log = BaseUtil.getLogger(BaseUtil.class);

    // 获取日志对象初始化
    public static Logger getLogger(Class<?> cls) {
        return LoggerFactory.getLogger(cls);
    }

    public static String getWpayMOney(String money) {
        try {
            BigDecimal big = new BigDecimal(money);
            big = big.multiply(new BigDecimal("100"));
            return String.valueOf(big.intValue());
        } catch (Exception e) {
            return "0";
        }
    }

    // 获取秒杀相关信息
    public static Map<String, Object> createKillInfo(String vid,
                                                     String vclassify,
                                                     String uid,
                                                     GeneralVideo g,
                                                     ContestVideo c) {
        String orderPrice;
        StringBuilder bodyDes = new StringBuilder();
        String originalPrice = "0";//原价
        String specialOfferPrice = "0";//秒杀价
        Boolean isSecKillPrice = false;//是否秒杀价
        String killStartTime = "";//秒杀开始时间
        String killEndTime = "";//秒杀截止时间

        Map<String, Object> killInfo = null;
        if ("0".equals(vclassify)) {//常规
            originalPrice = StringUtils.isBlank(g.getGmoney()) ? "0" : g.getGmoney();
            specialOfferPrice = StringUtils.isBlank(g.getKillMoney()) ? "0" : g.getKillMoney();
            isSecKillPrice = g.getIsKill() ==1 ? true:false;
            killStartTime = g.getKillStartTime();
            killEndTime = g.getKillEndTime();
            String gclass = g.getGclass();
            if (gclass == null
                    || gclass.indexOf("古诗") > -1 || gclass.indexOf("阅读") > -1
                    || gclass.indexOf("写作") > -1 || gclass.indexOf("流利英语") > -1
                    || gclass.indexOf("语法") > -1 || gclass.indexOf("其他") > -1) {
                gclass = "";
            }
            StringBuilder gname = new StringBuilder(g.getGname());
            if (gname.toString() != "" && gname.length() > 0) {
                gname.insert(0, "【");
                gname.append("】");
            }
            bodyDes.append(g.getGsbuject())
                    .append(gclass)
                    .append(g.getGclassify())
                    .append(gname.toString());
        } else {//竞赛
            originalPrice = StringUtils.isBlank(c.getCmoney()) ? "0" : c.getCmoney();
            specialOfferPrice = StringUtils.isBlank(c.getKillMoney()) ? "0" : c.getKillMoney();
            isSecKillPrice = c.getIsKill()==1 ? true:false ;
            killStartTime = c.getKillStartTime();
            killEndTime = c.getKillEndTime();
            String cname = c.getCname();
            if (!StringUtils.isBlank(cname)) {
                cname = "【" + cname + "】";
            }
            bodyDes.append(c.getCompetitionName())
                    .append(c.getCclass())
                    .append(c.getCclassify())
                    .append(cname);
        }
        killInfo = getKillInfo(killStartTime, killEndTime);
        if (isSecKillPrice) {//是秒杀活动
            orderPrice = (int)killInfo.get("timeType") == 1 ? specialOfferPrice : originalPrice;// 0 未开始,1进行中
        } else {
            orderPrice = originalPrice;
        }
        killInfo.put("orderPrice",orderPrice) ;
        killInfo.put("bodyDes",bodyDes.toString()) ;
        
        
        return killInfo;
    }

    // 获取秒杀相关信息
    public static Map<String, Object> getKillInfo(String killStartTime, String killEndTime) {
        Map<String, Object> map = new HashMap<String, Object>();
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date start = null;
        Date end = null;
        try {
            start = sim.parse(killStartTime);
        } catch (Exception e) {
            log.info("秒杀开始时间【" + killStartTime + "】转换异常:", e);
        }
        try {
            end = sim.parse(killEndTime);
        } catch (Exception e) {
            log.info("秒杀截止时间【" + killEndTime + "】转换异常:", e);
        }
        int type = 2;// 活动状态:0:未开始；1：进行中;2：已结束
        long totalTime = 0;// 单位毫秒
        if (start != null && end != null) {
            Date curr = new Date();
            Long eTime = end.getTime();
            if (curr.before(start)) {// 活动还没有开始
                totalTime = start.getTime() - curr.getTime();
                type = 0;
            } else if (start.before(curr) && curr.before(end)) {// 活动进行中
                totalTime = end.getTime() - curr.getTime();
                type = 1;
            }  

        }
        String showTimerText = getStringTime(totalTime);
        map.put("timeType", type);// 活动状态
        map.put("totalTime", totalTime);// 活动时长
        map.put("showTimerText", showTimerText);// 显示的文字（例如：2天10小时30分22秒）
        return map;

    }

    private static String getStringTime(long totalTime) {
        long day = totalTime / (24 * 60 * 60 * 1000);
        long hour = totalTime / (60 * 60 * 1000) - day * 24;
        long minute = totalTime / (60 * 1000) - day * 24 * 60 - hour * 60;
        long second = totalTime / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - minute * 60;
        // String
        // showTimerText=(day>0?day+"天":"")+(hour>0?hour+"小时":"")+(minute>0?minute+"分":"")+(second>0?second+"秒":"");
        return day + "天" + hour + "小时" + minute + "分" + second + "秒";
    }

    // 获取服务器路径
    public static String getServerPath( String dir, String filename, String uploadAbsolutePath) {
        return uploadAbsolutePath + "/" + dir + filename;
    }

    // 生成随机数
    public static String get_nonceStr() {
        String msg = "";
        for (int i = 1; i <= 32; i++) {
            String n = Math.floor(Math.random() * 16.0) + "";
            // n=n.substring(0,15);//16位
            msg += n;
            if ((i == 8) || (i == 12) || (i == 16) || (i == 20))
                msg += "";
        }
        return msg;
    }

    // 生成时间戳
    public static String get_timestamp() {
        String msg = Math.ceil(new Date().getTime() / 1000) + "";
        return msg;
    }

    // 千米转成英里 （1千米=0.6213712英里）
    public static Double getMiFromKm(Double km) {
        Double mi = 0d;
        if (km != null && (km + "").length() > 0) {
            mi = km * 0.6213712;
        }
        return BaseUtil.getTwoDecimal(mi);
    }

    // 获取字符串的物理长度
    public static Integer getStringLength(String str) {
        String regex = "[\u4e00-\u9fff]";// 中文字符
        String regex2 = "[\u3002\uff1b\uff0c\uff1a\u201c\u201d\uff08\uff09\u3001\uff1f\u300a\u300b]";// 中文标点
        int count = (" " + str + " ").split(regex).length - 1;
        int count2 = (" " + str + " ").split(regex2).length - 1;
        // System.out.println ("count:"+count+"==="+count2);
        Integer len = str.length() + count + count2;
        // System.out.println ("匹配的字符长度："+len);
        return len;
    }

    // 截取指定长度字符串
    public static String subStr4Byte(String str, int len) {
        int reInt = 0;
        String reStr = "";
        char[] tempChar = str.toCharArray();
        for (int kk = 0; (kk < tempChar.length && len > reInt); kk++) {
            String s1 = str.valueOf(tempChar[kk]);
            byte[] b = s1.getBytes();
            reInt += b.length;
            reStr += tempChar[kk];
        }
        return reStr;
    }

    // 数组转字符串
    public static String arrayToStr(String[] arr) {
        StringBuffer sb = new StringBuffer();
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                if (i > 0) {
                    sb.append(",");
                }
                sb.append(arr[i]);
            }
        }
        return sb.toString();
    }

    // 发送邮件
    /*
     * public static void sendEmail(String receiverEmail,String emailContent)
     * throws Exception{ // 配置发送邮件的环境属性 final Properties props = new
     * Properties();
     *
     * 可用的属性： mail.store.protocol / mail.transport.protocol / mail.host /
     * mail.user / mail.from
     *
     * // 表示SMTP发送邮件，需要进行身份验证 props.put("mail.smtp.auth", "true");
     * props.put("mail.smtp.host", "smtp.exmail.qq.com"); // 发件人的账号
     * props.put("mail.user", "dongchengcheng@sylhxj.cn"); // 访问SMTP服务时需要提供的密码
     * props.put("mail.password", "Dcc1234567890");
     *
     * // 构建授权信息，用于进行SMTP进行身份验证 Authenticator authenticator = new
     * Authenticator() {
     *
     * @Override protected PasswordAuthentication getPasswordAuthentication() {
     * // 用户名、密码 String userName = props.getProperty("mail.user"); String
     * password = props.getProperty("mail.password"); return new
     * PasswordAuthentication(userName, password); } }; // 使用环境属性和授权信息，创建邮件会话
     * Session mailSession = Session.getInstance(props, authenticator); //
     * 创建邮件消息 MimeMessage message = new MimeMessage(mailSession); // 设置发件人
     * InternetAddress form = new InternetAddress(
     * props.getProperty("mail.user")); message.setFrom(form);
     *
     * // 设置收件人 InternetAddress to = new InternetAddress(receiverEmail);
     * message.setRecipient(RecipientType.TO, to);
     *
     * // 设置邮件标题 message.setSubject("留学鹿");
     *
     * // 设置邮件的内容体 message.setContent(emailContent, "text/html;charset=UTF-8");
     * try { System.out.println(message.getContent()); } catch (IOException e) {
     * // TODO Auto-generated catch block e.printStackTrace(); } // 发送邮件
     * Transport.send(message); }
     */

    public static String SHA1(String decript) {
        try {
            MessageDigest digest = java.security.MessageDigest.getInstance("SHA-1");
            digest.update(decript.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            translateFromArrToHex(messageDigest, hexString);
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String SHA(String decript) {
        try {
            MessageDigest digest = java.security.MessageDigest.getInstance("SHA");
            digest.update(decript.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            translateFromArrToHex(messageDigest, hexString);
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static void translateFromArrToHex(byte[] messageDigest, StringBuffer hexString) {
        for (int i = 0; i < messageDigest.length; i++) {
            String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
            if (shaHex.length() < 2) {
                hexString.append(0);
            }
            hexString.append(shaHex);
        }
    }

    // 获取上下文
    public static ServletContext getApplication() {
        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
        ServletContext servletContext = webApplicationContext.getServletContext();
        return servletContext;
    }

    // 获取当天日期指定格式字符串
    public static String getCurrentDateStr(String formate) {
        Date ddate = new Date();
        return new SimpleDateFormat(formate).format(ddate).toString();
    }

    public static String doget_http_request(String url1) {
        StringBuffer readOneLineBuff = new StringBuffer();
        String content = "";
        try {
            URL url = new URL(url1);
            URLConnection conn = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            while ((line = reader.readLine()) != null) {
                readOneLineBuff.append(line);
            }
            content = readOneLineBuff.toString();
            reader.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return content;
    }

    // 获取properties文件的值
    public static String getPorpertites_pro(String key, String propertieName) throws IOException {
        propertieName = propertieName == null || propertieName.length() == 0 ? "application.yml" : propertieName;
        Properties pro = BaseUtil.readPorpertites(propertieName);
        return pro.getProperty(key);
    }

    // 设置session
    public static void setSession(HttpServletRequest request, String key, Object val) {
        request.getSession().setAttribute(key, val);
    }

    // 获取session
    public static Object getSession(HttpServletRequest request, String key) {
        return request.getSession().getAttribute(key);
    }

    // 阿拉伯数字数字转中文(0-10)
    public static String getChinaNumByALBNum(Integer num) {
        String ss = "";
        if (num < 0) {
            ss = "小于0";
        } else if (num > 10) {
            ss = "大于10";
        } else {
            String[] str = new String[]{"零", "一", "两", "三", "四", "五", "六", "七", "八", "九", "十"};
            ss = str[num];
        }

        return ss;
    }

    // 读取配置文件
    public static Properties readPorpertites(String properties) throws IOException {
        InputStream is = BaseUtil.class.getClassLoader().getResourceAsStream(properties);
        Properties pro = new Properties();
        pro.load(is);
        return pro;
    }

    // 字符串转成日期
    public static Date StringToDate(String str) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(str);
    }

    // 日期转成字符串
    public static String dateToString(Date date) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date).toString();
    }

    // 将数字保留两位
    public static Double getTwoDecimal(Object num) {
        DecimalFormat df = new DecimalFormat("#.00");
        String dd = df.format(num);
        return Double.parseDouble(dd);
    }

    // 将数字保留整数
    public static Integer getIntNum(Object num) {
        DecimalFormat df = new DecimalFormat("#");
        String dd = df.format(num);
        return Integer.parseInt(dd);
    }

    // 判断文件是否存在
    public static boolean judeFileExists(String path, boolean isDel, boolean iscreate) {
        File file = new File(path);
        boolean flag = false;
        if (file.exists()) {
            flag = true;
            if (isDel) {
                try {
                    file.delete();
                } catch (Exception e) {
                    log.error("删除文件失败:", e);

                }
            }
        } else {
            if (iscreate) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    log.error("创建文件失败:", e);

                }
            }
        }
        return flag;
    }

    // url访问（.net自带）
    public static String Urlconnection(String url1, String method) throws IOException {
        URL url = new URL(url1);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(method);
        connection.setDoOutput(true);
        connection.setRequestProperty("User-Agent", "directclient");
        StringBuffer backjsonBuffer = new StringBuffer();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            backjsonBuffer.append(inputLine);
        }
        in.close();
        String result = backjsonBuffer.toString();
        return result;
    }

    // 生成随机数
    public static String random(int n) {
        String tex = "";

        for (int i = 1; i <= n; i++) {
            tex += "" + (int) (Math.random() * 10);
        }
        return tex;
    }

}
