package cc.aisc.ybk.content.base;

import cc.aisc.ybk.commons.costant.Constant;
import cc.aisc.ybk.commons.utils.CookieUtils;
import cc.aisc.ybk.commons.utils.StringUtils;
import org.springframework.web.context.ContextLoader;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Date;

/**
 * Created by sjf on 16-1-27.
 */
public abstract class BaseController extends CookieUtils {

    protected final String REDIRECT = "redirect:";
    protected final String FORWARD = "forward:";
    protected final String LASTACCESS = "lastAccess";
    protected final String LOGIN = "login";
    protected final String CURRENTUSER = "currentUser";
    protected final String USERID = "userId";
    protected final String INLINE = "inline";
    protected final String ATTACHMENT = "attachment";
    private final String DEFALULT_CONTENT_TYPE = "application/octet-stream";

    public String redirect(String url) {
        StringBuilder sb = new StringBuilder();
        sb.append(REDIRECT);
        sb.append(url);
        return sb.toString();
    }

    public String forward(String url) {
        StringBuilder sb = new StringBuilder();
        sb.append(FORWARD);
        sb.append(url);
        return sb.toString();
    }

    /**
     * 密码加密
     *
     * @param password
     * @return
     *
     */
    public String encrypt(String password) {
        return StringUtils.encrypt(password);
    }

    /**
     * 密码解密
     *
     * @param data
     * @return
     *
     */
    public String decrypt(String data) {
        return StringUtils.decrypt(data);
    }
    /**
     * 判断是否登录
     **/
    public boolean isLogin(HttpSession session) {
        if (session.getAttribute(LOGIN) != null
                && session.getAttribute(LOGIN).equals("true")) {
            return true;
        }
        return false;
    }

    /**
     * 设置登录
     **/
    public void setLoginState(HttpSession session, String userName,
                              String userId, Date date) {
        session.setAttribute(LOGIN, "true");
        session.setAttribute(CURRENTUSER, userName);
        session.setAttribute(USERID, userId);
        session.setAttribute(LASTACCESS, date);
    }

    /**
     * 设置退出
     **/
    public void setLogOutStatus(HttpSession session) {
        session.setAttribute(LOGIN, "false");
    }
    /**
     * 取出登录名
     **/
    public String getUserName(HttpSession session) {
        try {
            if (session.getAttribute(LOGIN).equals("true")) {
                return session.getAttribute(CURRENTUSER).toString();
            }
        } catch (NullPointerException e) {

        }
        return null;
    }

    /**
     * 取出登录名
     **/
    public String getUserID(HttpSession session) {
        try {
            if (session.getAttribute(LOGIN).equals("true")) {
                return session.getAttribute(USERID).toString();
            }
        } catch (NullPointerException e) {

        }
        return null;
    }
    /**
     * @param contentType
     *            输出指定文件的数据流(即文件下载或图片展示) contentType
     *            文件类型，如果为空用application/octet-stream
     * @param fileName
     *            文件名称，如果为空则使用系统随机命名，后缀为".file"
     * @param dispostition
     *            文件由浏览器打开还是操作系统打开，即"inline" "attachment"
     * @param content
     *            文件内容，如果为空，则不作处理
     * @param response
     *            客户端响应
     * */
    protected void download(String contentType, String fileName,
                            String dispostition, byte[] content, HttpServletResponse response)
            throws IOException {
        ServletResponse res = null;
        while (response instanceof HttpServletRequestWrapper) {
            res = ((HttpServletResponseWrapper) response).getResponse();
            if (res instanceof HttpServletResponse) {
                response = (HttpServletResponse) res;
            }
        }
        if (content != null && content.length > 0) {
            fileName = StringUtils.defaultIfEmpty(fileName,
                    "temp_" + String.valueOf(System.nanoTime()) + ".file");
            contentType = StringUtils.defaultIfEmpty(contentType,
                    DEFALULT_CONTENT_TYPE);
            if (!INLINE.equals(dispostition)
                    && !ATTACHMENT.equals(dispostition)) {
                dispostition = ATTACHMENT;
            }
            ServletOutputStream out = null;
            try {
                response.setContentType(contentType);
                response.setHeader("Content-Disposition", dispostition
                        + ";filename='"
                        + new String(fileName.getBytes("GBK"), "ISO-8859-1")//todo: utf-8
                        + "'");
                out = response.getOutputStream();
                out.write(content, 0, content.length);
                out.flush();
            } catch (Exception e) {

            } finally {
                if (out != null) {
                    out.close();
                }
            }
        }
    }

    public void downloadFile(String contentType, String fileName,
                             byte[] content, HttpServletResponse response) throws IOException {
        download(contentType, fileName, ATTACHMENT, content, response);
    }
    /**
     * 初始化web路径
     * */
    public void initWebRoot() {
        if (Constant.webRoot.equals("")) {
            Constant.webRoot = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/");
        }
    }

    public String getUploadedFilePath(HttpServletRequest request){
        if(!Constant.uploadedFilePath.equals("")){
            try{
                return request.getServletContext().getRealPath(Constant.uploadedFilePath);
            }catch  (Exception e){
                LOGGER.error(e.getMessage());
            }
        }
        return null;
    }

    public String getMappingFilePath(HttpServletRequest request){
        if(!Constant.mappingFilePath.equals("")){
            try{
                return request.getServletContext().getRealPath(Constant.mappingFilePath);
            }catch  (Exception e){
                LOGGER.error(e.getMessage());
            }
        }
        return null;
    }
    /**
     * 当用户没有登录时返回空
     **/
    /*public UserDetails getUserDetails() {
        UserDetails user = null;
        if (SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal() instanceof UserDetails) {
            user = (UserDetails) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal();
        }
        return user;
    }
    public Date parseDate(String s) {
        if (s == null)
            return new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date toDate;
        try {
            toDate = dateFormat.parse(s);
        } catch (ParseException e) {
            toDate = new Date();
            e.printStackTrace();
        }
        return toDate;
    }*/

}
