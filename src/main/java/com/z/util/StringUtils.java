package com.z.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符校验工具类
 * 
 * @Filename: StringUtils.java
 * @Version: 1.0
 * @Author: wuyu 吴宇
 * @Email: wuyu89630@163.com
 * 
 */
public class StringUtils {

    /**
     * 检查IP串是否合法
     * 
     * @param ips
     * @param limit
     *            分隔符
     * @return pass
     */
    public static boolean checkIps(String ips, String limit) {
        Pattern pattern = Pattern
            .compile("^((25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\\.){1,3}(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])?$");
        boolean pass = false;
        String[] ipArray = ips.split(limit, 0);
        for (String ip : ipArray) {
            if (ip != null && ip.length() > 0) {
                if (pattern.matcher(ip).matches()) {
                    pass = true;
                } else {
                    pass = false;
                    break;
                }
            }
        }
        return pass;
    }
    /**
     * 校验手机号码是否合法增加固话
     * 
     * @param mobile
     * @return boolean
     */
    public static boolean checkNewMobile(String mobile) {
        // 增加了对17开头手机号码的支持     
            Pattern pattern = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9])|(17[0-9]))\\d{8}$");
            Matcher matcher = pattern.matcher(mobile);            
            Pattern pattern1 = Pattern.compile("^(\\d{3,4}-\\d{7,8})$");
            Matcher matcher1 = pattern1.matcher(mobile);
            return matcher1.matches()||matcher.matches();      
    }

    /**
     * 校验手机号码是否合法
     * 
     * @param mobile
     * @return boolean
     */
    public static boolean checkMobile(String mobile) {
     // 增加了对17开头手机号码的支持
        Pattern pattern = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9])|(17[0-9]))\\d{8}$");
        Matcher matcher = pattern.matcher(mobile);
        return matcher.matches();

    }

    /**
     * 校验邮箱是否合法
     * 
     * @param email
     * @return boolean
     */
    public static boolean checkEmail(String email) {
        String checkemail = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(checkemail);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * 校验金额，小数点后面只能保留两位
     * 
     * @param money
     * @return boolean
     */
    public static boolean checkMoney(String money) {
        String checkMoney = "^(-)?(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){1,2})?$";
        Pattern pattern = Pattern.compile(checkMoney);
        Matcher matcher = pattern.matcher(money);
        return matcher.matches();
    }

    /**
     * 格式化日期时间
     * 
     * @param myDateTime
     *            （date类型）
     * @param isTime
     *            (是否格式化时间)true，false
     * @return date
     */
    public static String getDateTime(Date myDateTime, boolean isTime) {
        SimpleDateFormat sfDate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sfDateTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date = null;
        try {
            if (myDateTime != null) {
                if (isTime) {
                    date = sfDateTime.format(myDateTime);
                } else {
                    date = sfDate.format(myDateTime);
                }
            }
        } catch (Exception e) {
            return date;
        }
        return date;
    }

    /**
     * 获取时间戳加随机数的数字串
     * 
     * @return formatDate + random
     */
    public static String getRandomName() {
        DateFormat format = new SimpleDateFormat("yyMMddHHmmss");
        String formatDate = format.format(new Date());
        int random = new Random().nextInt(10000);
        return formatDate + random;
    }

    /**
     * 验证字符串时候为空
     * 
     * @author
     * @creationDate. 2010-12-3 下午04:47:52
     * @param str
     *            字符串
     * @return boolean
     */
    public static boolean isEmpty(String str) {
        return (str == null || str.equals("")) ? true : false;
    }

    /**
     * 验证字符串非空
     * 
     * @author
     * @creationDate. 2010-12-3 下午04:48:16
     * @param str
     *            字符串
     * @return boolean
     */
    public static boolean isNotEmpty(String str) {
        return (str == null || str.equals("")) ? false : true;
    }

    /**
     * 获得带中文的字符串长度
     * 
     * @author
     * @creationDate. 2010-11-2 上午11:36:30
     * @param str
     *            字符串
     * @return 字符串长度
     */
    public static long getChineseTextLen(String str) {
        if (isEmpty(str)) {
            return 0;
        }
        return str.replaceAll("[^\\x00-\\xff]", "00").length();
    }

    /**
     * 截取带中文的文本长度
     * 
     * @author
     * @creationDate. 2010-11-2 上午11:37:35
     * @param str
     *            字符串
     * @param len
     *            长度
     * @param ext
     *            截断后添加的标识(一般传省略号)
     * @return 字符串
     */
    public static String subChineseText(String str, int len, String ext) {
        if (isEmpty(str)) {
            return "";
        }
        if (getChineseTextLen(str) <= len) {
            return str;
        }
        String reg = "[^\\x00-\\xff]";
        int m = (int) Math.floor(len / 2);
        int length = str.length();
        int subLen = 0;
        for (int i = m; i < length; i++) {
            subLen = str.substring(0, i).replaceAll(reg, "00").length();
            if (subLen >= len) {
                StringBuffer result = new StringBuffer(str.substring(0, (subLen > len) ? i - 1 : i));
                if (isNotEmpty(ext)) {
                    result.append(ext);
                }
                return result.toString();
            }
        }
        return str;
    }

    /**
     * 文本转成全角字符串
     * 
     * @author
     * @creationDate. 2010-11-2 下午05:29:16
     * @param str
     *            待转换的字符串
     * @return 全角字符串
     */
    public static String text2sbcCase(String str) {
        if (isEmpty(str)) {
            return "";
        }
        char[] c = str.toCharArray();
        int len = c.length;
        for (int i = 0; i < len; i++) {
            if (c[i] == 32) {
                c[i] = (char) 12288;
                continue;
            }
            if (c[i] < 127) {
                c[i] = (char) (c[i] + 65248);
            }
        }
        return new String(c);
    }

    /**
     * 文本转成半角字符串
     * 
     * @author
     * @creationDate. 2010-11-2 下午05:28:31
     * @param str
     *            待转换的字符串
     * @return 半角字符串
     */
    public static String text2dbcCase(String str) {
        if (isEmpty(str)) {
            return "";
        }
        char[] c = str.toCharArray();
        int len = c.length;
        for (int i = 0; i < len; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375) {
                c[i] = (char) (c[i] - 65248);
            }
        }
        return new String(c);
    }

    /**
     * 过滤特殊字符 保留中文 数字 英文。
     * 
     * @param destFileName
     *            文件名称
     * @return destFileName
     */
    public static String getFileNameString(String destFileName) {
        if (isEmpty(destFileName)) {
            return "";
        }
        // 字符过滤
        String name = destFileName.substring(0, destFileName.lastIndexOf(".")).replaceAll(
            "[^0-9a-zA-Z_\\u4e00-\\u9fa5]", "");
        String type = destFileName.substring(destFileName.lastIndexOf("."));
        destFileName = name + type;
        return destFileName;
    }

    /**
     * 
     * @param srcPath
     * @return srcPath
     */
    public static String transferPath(String srcPath) {
        if (srcPath != null && !"".equals(srcPath)) {
            srcPath = srcPath.replace("\\", "/");
        }
        return srcPath;
    }

    /**
     * 判断是否为空
     * @param object
     * @return Boolean
     */
    public static Boolean checkIsEmpty(Object object) {
        if (object != null && !"".equals(object.toString())) {
            return false;
        }
        return true;
    }

    /**
     * 返回两位小数的字段
     * @param str
     * @return String
     */
    public static String fourUpSixInto(String str){
        if(!checkIsEmpty(str)){
            BigDecimal b1 = new BigDecimal(str);
            BigDecimal b2 = b1.setScale(2, BigDecimal.ROUND_HALF_EVEN);
            return b2.toString();
        }else{
            return "0";
        }
    }

    /**
     * 判断是否为数字，或者是否带两位小数
     * @param str
     * @return String
     */
    public static boolean isNumber(String str) {
        boolean isInt = Pattern.compile("^-?[1-9]\\d*$").matcher(str).find();
        boolean isDouble = Pattern.compile("^-?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0)$").matcher(str).find();
        return isInt || isDouble;
    }
}
