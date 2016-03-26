package cc.aisc.ybk.commons.costant;

/**
 * Created by sjf on 16-3-17.
 */
public class Constant {
    /**
     * 应用的根目录，由spring负责初始化
     * */

    public static final String[] JSONCONFIG_EXCLUDES = {"updatedAt", "createdAt", "createdBy", "updatedBy", "version", "level","parent"};
    public static String webRoot = "";

    public static final String UPLOAD_IMAGES_PATH = "upload/images/";
    public static final String mappingFilePath = "/resources/static/DataMapping";

    public static final Integer MAX_NUM_OF_EMPTY_ROWS = 5;

    public static final String GENDER_MALE = "男";
    public static final String GENDER_FEMALE = "女";
    public static final String MARRIED_TRUE = "已婚";
    public static final String MARRIED_FALSE = "未婚";
    public static final String EMPLOYED_TRUE = "在职";
    public static final String EMPLOYED_FALSE = "离职";

    public static final String [] BOOLEAN_TRUE = {"是", GENDER_MALE, MARRIED_TRUE, EMPLOYED_TRUE};
    public static final String [] BOOLEAN_FALSE = {"否"};



    public static final String DATE_TIME_FORMATTER = "yyyy-MM-dd HH:mm:ss";

    public static final String AUTH_USERNAME_PASSWORD_INVALID = "用户名/密码无效";
    public static final String AUTH_ACCOUNT_DISABLED = "账号已被禁用";
    public static final String AUTH_ACCOUNT_EXPIRED = "账号已过期";
    public static final String AUTH_ACCOUNT_LOCKED = "账号被锁定";
    public static final String AUTH_CRDDITAL_EXPIRED = "凭证已过期";

    public static final Integer USER_NOOB_EXPIRED_HOURS = 48;

    /*CAPTCHA*/
    public static final String CAPTCHA_SESSION_KEY = "Captcha_Session_Key";
    public static final int CAPTCHA_IMG_WIDTH = 160;
    public static final int CAPTCHA_IMG_HEIGHT = 60;
    public static final int CAPTCHA_MARGIN_LEFT = 10;
    public static final int CAPTCHA_MARGIN_RIGHT = 10;

    public static final String DEFAULT_DATASOURCE_NAME = "ds1";
    public static final String DEFAULT_DATASOURCE_TYPE = "com.alibaba.druid.pool.DruidDataSource";
    public static final String MASTER_DATASOURCE_PREFIX = "master.datasource.";
    public static final String SLAVE_DATASOURCE_PREFIX = "slave.datasource.";
    /*seek*/
    public static final int SEEK_PAGINATION_PAGESIZE = 8;
}
