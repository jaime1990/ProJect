package com.leohulabb.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.math.RoundingMode;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by js on 2015/8/13.
 */
public class DataUtils
{
    /**
     * 所有手机号码和固话的正则表达式。
     */
    private final static String REGEX_CHINA_MOBILE_ALL = "^(0?1[358]\\d{9})$|^((0(10|2[1-3]|[3-9]\\d{2}))?[1-9]\\d{6,7})$";
    /**
     * 身份证的正则表达式。
     */
    private final static String REGEX_IDCARD = "^(\\d{18}$|^\\d{17}(\\d|X|x))$";

    /**
     * 判断是否是邮箱
     */
    public static boolean isEmail(String str) {
        Pattern p = Pattern.compile("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+");
        Matcher m = p.matcher(str);
        if (!m.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 判断是否是纯数字
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    //判断字母的大小写
    public static boolean isUpperOrLower(String str)
    {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isUpperCase(c)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 得到二个日期间的间隔秒数
     */
    public static long getTwoDay(String sj1, String sj2) {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        long day = 0;
        try {
            java.util.Date date = myFormatter.parse(sj1);
            java.util.Date mydate = myFormatter.parse(sj2);
            day = date.getTime() - mydate.getTime();
        } catch (Exception e) {
            return -1;
        }
        return day;
    }

    public static boolean isDouble(String value) {
        try {
            Double.parseDouble(value);
            if (value.contains("."))
                return true;
            return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static int GetYearFromDate(String time) {
        Date date = null;

        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date.getYear() + 1900;
    }

    public static String getFormatTime(Long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date(time);
        String timeStr = null;
        try {
            timeStr = simpleDateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return timeStr;
    }

    /**
     * 判断对象是否为空 若为空则默认为""
     */
    public static String cs(String string) {
        if (string != null) {
            return string;
        }
        return "";
    }

    /**
     * 判断对象是否为空
     */
    public static boolean isNull(Object object) {
        if (object == null)
            return true;
        if (object instanceof String) {
            return ((String) object).isEmpty();
        }
        return false;
    }

    //计算百分比
    public static String getAccuracy(double num, double total)
    {
        DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();
        //可以设置精确几位小数
        df.setMaximumFractionDigits(2);
        //模式 例如四舍五入
        df.setRoundingMode(RoundingMode.HALF_UP);
        double accuracy_num = num / total * 100;
        return df.format(accuracy_num) + "%";
    }

    //排序
    public static int[] sort(int[] args) {
        for (int i = 0; i < args.length - 1; i++) {
            for (int j = i + 1; j < args.length; j++) {
                int temp;
                if (args[i] > args[j]) {
                    temp = args[j];
                    args[j] = args[i];
                    args[i] = temp;
                }
            }
        }
        return args;
    }


    public static void setShareBoolean(Context context, String key, boolean value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static boolean getShareBoolean(Context context, String key) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getBoolean(key, false);
    }

    public static void setSharePre(Context context, String key, String value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getSharePre(Context context, String key) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(key, null);
    }

    public static String md5(String str) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            md5.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        byte[] encodedValue = md5.digest();
        int j = encodedValue.length;
        char finalValue[] = new char[j * 2];
        int k = 0;
        for (int i = 0; i < j; i++) {
            byte encoded = encodedValue[i];
            finalValue[k++] = hexDigits[encoded >> 4 & 0xf];
            finalValue[k++] = hexDigits[encoded & 0xf];
        }

        return new String(finalValue);
    }

    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static boolean isNetConnected(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeInfo = manager.getActiveNetworkInfo();
        return activeInfo != null ? true : false;
    }

    public static String getCurrentTime(int delay) {
        Calendar c = Calendar.getInstance();
        int curYear = c.get(Calendar.YEAR);
        int curMonth = c.get(Calendar.MONTH) + 1;
        int curDate = c.get(Calendar.DAY_OF_MONTH);
        int curHour = c.get(Calendar.HOUR_OF_DAY) + delay;
        int curMinute = c.get(Calendar.MINUTE);
        int curSecond = c.get(Calendar.SECOND);

        String time = curYear + "-" + curMonth + "-" + curDate + " " + curHour + ":" + curMinute;
        return time;
    }


    public static String getVersionName(Context context) {
        String versionName = "";
        int versioncode = 0;
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            versioncode = pi.versionCode;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versionName;
    }

    public static String getDeviceInfo() {
        return android.os.Build.MANUFACTURER + "_" + android.os.Build.MODEL + "_"
                + android.os.Build.VERSION.SDK + "_"
                + android.os.Build.VERSION.RELEASE;
    }

    public static Boolean isRightData(String input) {
        if (input != null && !"".equals(input)) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否是身份证
     *
     * @param str
     * @return
     */
    public static boolean isIdCard(String str) {
        return str != null && str.matches(REGEX_IDCARD);
    }

    /**
     * 判断是否是手机号码
     *
     * @param str
     * @return
     */
    public static boolean isPhoneNumber(String str) {
        return str != null && str.matches(REGEX_CHINA_MOBILE_ALL);
    }

    /**
     * 手机号码加密
     *
     * @retrue String
     */
    public static String encodePhone(String phone) {
        String result = "";
            if (phone.length() == 11) {
                String before = phone.substring(0, 3);
                String after = phone.substring(7, phone.length());
                result = before + "****" + after;
            }else{
                result = phone;
            }
        return result;
    }

    /**
     * 时间格式转化
     */
    public static Date getStringFromDate(String time) {
        Date date = null;

        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
