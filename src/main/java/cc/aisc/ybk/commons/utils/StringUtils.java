package cc.aisc.ybk.commons.utils;

import cc.aisc.ybk.commons.costant.Constant;
import org.apache.commons.codec.binary.Base64;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * Created by sjf on 15-11-12.
 */
public abstract class StringUtils extends org.apache.commons.lang3.StringUtils{

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(StringUtils.class);

    private final static String PASSWORD_CRYPT_KEY = "gohome";
    private final static String PASSWORD_CRYPT_DES = "PASSWORD_CRYPT_DES";

    /**
     * 常用正则表达式：匹配非负整数（正整数 + 0）
     */
    public final static String regExp_integer_1 = "^\\d+$";

    /**
     * 常用正则表达式：匹配正整数
     */
    public final static String regExp_integer_2 = "^[0-9]*[1-9][0-9]*$";

    /**
     * 常用正则表达式：匹配非正整数（负整数  + 0）
     */
    public final static String regExp_integer_3 = "^((-\\d+) ?(0+))$";

    /**
     * 常用正则表达式：匹配负整数
     */
    public final static String regExp_integer_4 = "^-[0-9]*[1-9][0-9]*$";

    /**
     * 常用正则表达式：匹配整数
     */
    public final static String regExp_integer_5 = "^-?\\d+$";

    /**
     * 常用正则表达式：匹配非负浮点数（正浮点数 + 0）
     */
    public final static String regExp_float_1 = "^\\d+(\\.\\d+)?$";

    /**
     * 常用正则表达式：匹配正浮点数
     */
    public final static String regExp_float_2 = "^(([0-9]+\\.[0-9]*[1-9][0-9]*) ?([0-9]*[1-9][0-9]*\\.[0-9]+) ?([0-9]*[1-9][0-9]*))$";

    /**
     * 常用正则表达式：匹配非正浮点数（负浮点数 + 0）
     */
    public final static String regExp_float_3 = "^((-\\d+(\\.\\d+)?) ?(0+(\\.0+)?))$";

    /**
     * 常用正则表达式：匹配负浮点数
     */
    public final static String regExp_float_4 = "^(-(([0-9]+\\.[0-9]*[1-9][0-9]*) ?([0-9]*[1-9][0-9]*\\.[0-9]+) ?([0-9]*[1-9][0-9]*)))$";

    /**
     * 常用正则表达式：匹配浮点数
     */
    public final static String regExp_float_5 = "^(-?\\d+)(\\.\\d+)?$";

    /**
     * 常用正则表达式：匹配由26个英文字母组成的字符串
     */
    public final static String regExp_letter_1 = "^[A-Za-z]+$";

    /**
     * 常用正则表达式：匹配由26个英文字母的大写组成的字符串
     */
    public final static String regExp_letter_2 = "^[A-Z]+$";

    /**
     * 常用正则表达式：匹配由26个英文字母的小写组成的字符串
     */
    public final static String regExp_letter_3 = "^[a-z]+$";

    /**
     * 常用正则表达式：匹配由数字和26个英文字母组成的字符串
     */
    public final static String regExp_letter_4 = "^[A-Za-z0-9]+$";

    /**
     * 常用正则表达式：匹配由数字、26个英文字母或者下划线组成的字符串
     */
    public final static String regExp_letter_5 = "^\\w+$";

    /**
     * 常用正则表达式：匹配email地址
     */
    public final static String regExp_email = "^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$";
    /**
     * 常用正则表达式：腾讯QQ号, 腾讯QQ号从10000开始
     */
    public final static String regExp_qqmail = "^[1-9][0-9]{4,13}@[\\w-]+(\\.[\\w-]+)+$";

    /**
     * 常用正则表达式：匹配url
     */
    public final static String regExp_url_1 = "^[a-zA-z]+://(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*(\\?\\S*)?$";

    /**
     * 常用正则表达式：匹配url
     */
    public final static String regExp_url_2 = "[a-zA-z]+://[^\\s]*";

    /**
     * 常用正则表达式：匹配中文字符
     */
    public final static String regExp_chinese_1 = "[\\u4e00-\\u9fa5]";

    /**
     * 常用正则表达式：匹配双字节字符(包括汉字在内)
     */
    public final static String regExp_chinese_2 = "[^\\x00-\\xff]";

    /**
     * 常用正则表达式：匹配空行
     */
    public final static String regExp_line = "\\n[\\s ? ]*\\r";

    /**
     * 常用正则表达式：匹配HTML标记
     */
    public final static String regExp_html_1 = "/ <(.*)>.* <\\/\\1> ? <(.*) \\/>/";

    /**
     * 常用正则表达式：匹配首尾空格
     */
    public final static String regExp_startEndEmpty = "(^\\s*) ?(\\s*$)";

    /**
     * 常用正则表达式：匹配帐号是否合法(字母开头，允许5-16字节，允许字母数字下划线)
     */
    public final static String regExp_accountNumber = "^[a-zA-Z][a-zA-Z0-9_]{4,15}$";

    /**
     * 常用正则表达式：匹配国内电话号码，匹配形式如 0511-4405222 或 021-87888822
     * "^(\d{11})$|^(\d{3,5}[-]?\d{6,8})$"
     * "\\d{3}-\\d{8} ?\\d{4}-\\d{7}"
     */
    public final static String regExp_telephone = "^(\\d{11})$|^(\\d{3,5}[-]?\\d{6,8})$";
    /**
     * 常用正则表达式：匹配国内电话号码，匹配形式如 1xxxxxxxxxx
     */
    public final static String regExp_mobilephone = "^(\\+86)?1([\\d]{10})$";
    /**
     * 常用正则表达式：腾讯QQ号, 腾讯QQ号从10000开始
     */
    public final static String regExp_qq = "[1-9][0-9]{4,13}";

    /**
     * 常用正则表达式：匹配中国邮政编码
     */
    public final static String regExp_postbody = "[1-9]\\d{5}(?!\\d)";

    /**
     * 常用正则表达式：匹配身份证, 中国的身份证为15位或18位
     */
    public final static String regExp_idCard = "\\d{15} ?\\d{18}";

    /**
     * 常用正则表达式：IP
     */
    public final static String regExp_ip = "\\d+\\.\\d+\\.\\d+\\.\\d+";

    /**
     * 字符编码
     */
    public final static String encoding = "UTF-8";

    /**
     * 验证字符串是否匹配指定正则表达式
     * @param content
     * @param regExp
     * @return
     */
    public static boolean regExpVali(String content, String regExp){
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(content);
        return matcher.matches();
    }

    /**
     * double精度调整
     * @param doubleValue 需要调整的值123.454
     * @param format 目标样式".##"
     * @return
     */
    public static String decimalFormat(double doubleValue, String format){
        DecimalFormat myFormatter = new DecimalFormat(format);
        String formatValue = myFormatter.format(doubleValue);
        return formatValue;
    }

    /**
     * Url Base64编码
     *
     * @param data
     *            待编码数据
     * @return String 编码数据
     * @throws Exception
     */
    public static String encode(String data) throws Exception {
        // 执行编码
        byte[] b = Base64.encodeBase64URLSafe(data.getBytes(encoding));

        return new String(b, encoding);
    }

    /**
     * Url Base64解码
     *
     * @param data
     *            待解码数据
     * @return String 解码数据
     * @throws Exception
     */
    public static String decode(String data) throws Exception {
        // 执行解码
        byte[] b = Base64.decodeBase64(data.getBytes(encoding));

        return new String(b, encoding);
    }

    /**
     * URL编码（utf-8）
     *
     * @param source
     * @return
     */
    public static String urlEncode(String source) {
        String result = source;
        try {
            result = java.net.URLEncoder.encode(source, encoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据内容类型判断文件扩展名
     *
     * @param contentType
     *            内容类型
     * @return
     */
    public static String getFileExt(String contentType) {
        String fileExt = "";
        if ("image/jpeg".equals(contentType)){
            fileExt = ".jpg";

        } else if ("audio/mpeg".equals(contentType)){
            fileExt = ".mp3";

        } else if ("audio/amr".equals(contentType)){
            fileExt = ".amr";

        } else if ("video/mp4".equals(contentType)){
            fileExt = ".mp4";

        } else if ("video/mpeg4".equals(contentType)){
            fileExt = ".mp4";
        }

        return fileExt;
    }



    public final static Pattern referer_pattern = Pattern.compile("@([^@^\\s^:]{1,})([\\s\\:\\,\\;]{0,1})");//@.+?[\\s:]

    /**
     * 处理提到某人 @xxxx
     * @param msg  传入的文本内容
     * @param referers 传出被引用到的会员名单
     * @return 返回带有链接的文本内容
     */
	/*public static String userLinks(String contents, List<String> userReferers) {
	    StringBuilder html = new StringBuilder();
	    int lastIdx = 0;
	    Matcher matchr = referer_pattern.matcher(contents);
	    while (matchr.find()) {
	        String origion_str = matchr.group();
	        //System.out.println("-->"+origion_str);
	        String userName = origion_str.substring(1, origion_str.length()).trim();
	        //char ch = str.charAt(str.length()-1);
	        //if(ch == ':' || ch == ',' || ch == ';')
	        //  str = str.substring(0, str.length()-1);
	        //System.out.println(str);
	        html.append(contents.substring(lastIdx, matchr.start()));

	        User user = null;
			Object userObj = User.dao.cacheGet(userName);
			if (null != userObj) {
				user = (User) userObj;
			} else {
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("column", "username");
				String sql = SqlXmlUtil.getSql(User.sqlId_column, param, ConstantRender.sql_renderType_beetl);
				List<User> userList = User.dao.find(sql, userName);
				if (userList.size() == 1) {
					user = userList.get(0);
				}
			}

	        if(user != null){
	            html.append("<a href='http://www.xx.com/"+user.getStr("username")+"' class='referer' target='_blank'>@");
	            html.append(userName.trim());
	            html.append("</a> ");
	            if(userReferers != null && !userReferers.contains(user.getPKValue())){
	            	userReferers.add(user.getPKValue());
	            }
	        } else {
	            html.append(origion_str);
	        }
	        lastIdx = matchr.end();
	        //if(ch == ':' || ch == ',' || ch == ';')
	        //  html.append(ch);
	    }
	    html.append(contents.substring(lastIdx));
	    return html.toString();
	}*/

    /**
     * 首字母转小写
     * @param s
     * @return
     */
    public static String toLowerCaseFirstOne(String s) {
        if(Character.isLowerCase(s.charAt(0))){
            return s;
        } else {
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    /**
     * 首字母转大写
     * @param s
     * @return
     */
    public static String toUpperCaseFirstOne(String s) {
        if(Character.isUpperCase(s.charAt(0))){
            return s;
        } else {
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }




    /**
     * 加密
     *
     * @param src
     *            数据源
     * @param key
     *            密钥，长度必须是8的倍数
     * @return 返回加密后的数据
     * @throws Exception
     */
    public static byte[] encrypt(byte[] src, byte[] key) throws Exception {
        // DES算法要求有一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // 从原始密匙数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
        // 创建一个密匙工厂，然后用它把DESKeySpec转换成
        // 一个SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(PASSWORD_CRYPT_DES);
        SecretKey securekey = keyFactory.generateSecret(dks);
        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(PASSWORD_CRYPT_DES);
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
        // 现在，获取数据并加密
        // 正式执行加密操作
        return cipher.doFinal(src);
    }

    /**
     * 解密
     *
     * @param src
     *            数据源
     * @param key
     *            密钥，长度必须是8的倍数
     * @return 返回解密后的原始数据
     * @throws Exception
     */
    public static byte[] decrypt(byte[] src, byte[] key) throws Exception {
        // DES算法要求有一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // 从原始密匙数据创建一个DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
        // 创建一个密匙工厂，然后用它把DESKeySpec对象转换成
        // 一个SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(PASSWORD_CRYPT_DES);
        SecretKey securekey = keyFactory.generateSecret(dks);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(PASSWORD_CRYPT_DES);
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
        // 现在，获取数据并解密
        // 正式执行解密操作
        return cipher.doFinal(src);
    }
    /**
     * 密码解密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public final static String decrypt(String data) {
        try {
            return new String(decrypt(hex2byte(data.getBytes()),
                    PASSWORD_CRYPT_KEY.getBytes()));
        } catch (Exception e) {
        }
        return null;
    }
    /**
     * 密码加密
     *
     * @param password
     * @return
     * @throws Exception
     */
    public final static String encrypt(String password) {
        try {
            return byte2hex(encrypt(password.getBytes(),
                    PASSWORD_CRYPT_KEY.getBytes()));
        } catch (Exception e) {
        }
        return null;
    }
    /**
     * 二行制转字符串
     *
     * @param b
     * @return
     */
    public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
        }
        return hs.toUpperCase();
    }

    public static byte[] hex2byte(byte[] b) {
        if ((b.length % 2) != 0)
            throw new IllegalArgumentException("长度不是偶数");
        byte[] b2 = new byte[b.length / 2];
        for (int n = 0; n < b.length; n += 2) {
            String item = new String(b, n, 2);
            b2[n / 2] = (byte) Integer.parseInt(item, 16);
        }
        return b2;
    }
    /**
     * 如果字符串为空返回默认
     *
     * @param str
     *            待检验字符串
     * @param defaultStr
     *            默认字符串
     **/
    public static String defaultIfEmpty(String str, String defaultStr) {
        return isEmpty(str) ? defaultStr : str;
    }


    public static String formatDate(Date date) {
        if(null != date) {
            DateTime dt = new DateTime(date);
            return dt.toString(Constant.DATE_TIME_FORMATTER);
        }
        return "date cannot be null.";
    }


    public static String textToMD5L32(String plainText){
        String result = null;
        //首先判断是否为空
        if(StringUtils.isBlank(plainText)){
            return null;
        }
        try{
            //首先进行实例化和初始化
            MessageDigest md = MessageDigest.getInstance("MD5");
            //得到一个操作系统默认的字节编码格式的字节数组
            byte[] btInput = plainText.getBytes();
            //对得到的字节数组进行处理
            md.update(btInput);
            //进行哈希计算并返回结果
            byte[] btResult = md.digest();
            //进行哈希计算后得到的数据的长度
            StringBuffer sb = new StringBuffer();
            for(byte b : btResult){
                int bt = b&0xff;
                if(bt<16){
                    sb.append(0);
                }
                sb.append(Integer.toHexString(bt));
            }
            result = sb.toString();
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 2.对文本进行32位MD5大写加密
     * @param plainText 要进行加密的文本
     * @return 加密后的内容
     */
    public static String textToMD5U32(String plainText){
        if(StringUtils.isBlank(plainText)){
            return null;
        }
        String result = textToMD5L32(plainText);
        return result.toUpperCase();
    }

}
