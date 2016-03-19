package cc.aisc.ybk.commons.utils;

import org.apache.commons.lang3.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import java.util.regex.Pattern;

/**
 * Html处理
 *
 */
public abstract class HtmlUtils {

    /**
     * 特殊字符转义，避免XSS
     * @param content
     * @return
     */
    public static String escapeXml(String content){
        return StringEscapeUtils.escapeXml11(content);
    }

    /**
     * 富文本内容处理返回纯文本
     * @param unsafe
     * @return
     */
    public static String cleanHtml(String unsafe){
        String clear = Jsoup.clean(unsafe, Whitelist.simpleText());
        return clear;
    }

    /**
     * 富文本内容处理返回安全文本
     * @param unsafe
     * @return
     */
    public static String safeHtml(String unsafe){
        String safe = Jsoup.clean(unsafe, Whitelist.basic());
        return safe;
    }

    /**
     * 去除HTML代码
     *
     * @param inputString
     * @return
     */
    public static String HtmltoText(String inputString) {
        String htmlStr = inputString; // 含HTML标签的字符串
        String textStr = "";
        Pattern p_script;
        java.util.regex.Matcher m_script;
        Pattern p_style;
        java.util.regex.Matcher m_style;
        Pattern p_html;
        java.util.regex.Matcher m_html;
        Pattern p_ba;
        java.util.regex.Matcher m_ba;

        try {
            String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
            // }
            String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
            // }
            String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
            String patternStr = "\\s+";

            p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
            m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll(""); // 过滤script标签

            p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
            m_style = p_style.matcher(htmlStr);
            htmlStr = m_style.replaceAll(""); // 过滤style标签

            p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
            m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll(""); // 过滤html标签

            p_ba = Pattern.compile(patternStr, Pattern.CASE_INSENSITIVE);
            m_ba = p_ba.matcher(htmlStr);
            htmlStr = m_ba.replaceAll(""); // 过滤空格

            textStr = htmlStr;

        } catch (Exception e) {
            System.err.println("Html2Text: " + e.getMessage());
        }
        return textStr;// 返回文本字符串
    }

    /**
     * 把页面的信息替换成我们想要的信息存放数据库里面
     *
     * @param sourcestr
     *            页面得到的信息
     * @return
     */
    public static String getHTMLToString(String sourcestr) {
        if (sourcestr == null) {
            return "";
        }
        sourcestr = sourcestr.replaceAll("\\x26", "&amp;");// &
        sourcestr = sourcestr.replaceAll("\\x3c", "&lt;");// <
        sourcestr = sourcestr.replaceAll("\\x3e", "&gt;");// >
        sourcestr = sourcestr.replaceAll("\\x09", "&nbsp;&nbsp;&nbsp;&nbsp;");// tab键
        sourcestr = sourcestr.replaceAll("\\x20", "&nbsp;");// 空格
        sourcestr = sourcestr.replaceAll("\\x22", "&quot;");// "

        sourcestr = sourcestr.replaceAll("\r\n", "<br>");// 回车换行
        sourcestr = sourcestr.replaceAll("\r", "<br>");// 回车
        sourcestr = sourcestr.replaceAll("\n", "<br>");// 换行
        return sourcestr;
    }

    /**
     * 把数据库里面的信息回显到页面上
     *
     * @param sourcestr
     *            数据库取得的信息
     * @return
     */
    public static String getStringToHTML(String sourcestr) {
        if (sourcestr == null) {
            return "";
        }
        sourcestr = sourcestr.replaceAll("&amp;", "\\x26");// &
        sourcestr = sourcestr.replaceAll("&lt;", "\\x3c");// <
        sourcestr = sourcestr.replaceAll("&gt;", "\\x3e");// >
        sourcestr = sourcestr.replaceAll("&nbsp;&nbsp;&nbsp;&nbsp;", "\\x09");// tab键
        sourcestr = sourcestr.replaceAll("&nbsp;", "\\x20");// 空格
        sourcestr = sourcestr.replaceAll("&quot;", "\\x22");// "

        sourcestr = sourcestr.replaceAll("<br>", "\r\n");// 回车换行
        sourcestr = sourcestr.replaceAll("<br>", "\r");// 回车
        sourcestr = sourcestr.replaceAll("<br>", "\n");// 换行
        return sourcestr;
    }

}
