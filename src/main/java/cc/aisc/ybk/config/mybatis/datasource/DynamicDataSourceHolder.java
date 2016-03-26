package cc.aisc.ybk.config.mybatis.datasource;

import cc.aisc.ybk.commons.costant.Constant;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sjf on 16-3-18.
 */
public class DynamicDataSourceHolder{

    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicDataSourceHolder.class);

    private static final ThreadLocal<String> dbLookupKey = new ThreadLocal<>();

    private static List<String> masterDataSourceKeys = new ArrayList<>();
    private static List<String> slaveDataSourceKeys = new ArrayList<>();

    static {
        masterDataSourceKeys.add("default");
        slaveDataSourceKeys.add("ds1");
        slaveDataSourceKeys.add("ds2");
    }

    /**
     * 标记选取slave数据源
     */
    public static void markSlave(String ... args) {
        if (dbLookupKey.get() != null) {
            // 从现在的策略来看,不允许标记两次,直接抛异常,优于早发现问题
            LOGGER.error("当前已有选取数据源,不允许覆盖,已选数据源key:  {}", dbLookupKey.get());
            throw new IllegalArgumentException("当前已有选取数据源,不允许覆盖,已选数据源key:" + dbLookupKey.get());
        }
        String dataSourceKey = args.length > 0 ? args[0] : selectFromSlave();
        setDataSource(dataSourceKey);
    }

    /**
     * 标记选取master数据源
     */
    public static void markMaster(String ... args) {
        if (dbLookupKey.get() != null) {
            // 从现在的策略来看,不允许标记两次,直接抛异常,优于早发现问题
            LOGGER.error("当前已有选取数据源,不允许覆盖,已选数据源key:  {}", dbLookupKey.get());
            throw new IllegalArgumentException("当前已有选取数据源,不允许覆盖,已选数据源key:" + dbLookupKey.get());
        }
        String dataSourceKey = args.length > 0 ? args[0] : selectFromMaster();
        setDataSource(dataSourceKey);
    }

    /**
     * 删除己标记选取的数据源
     */
    public static void markRemove() {
        dbLookupKey.remove();
    }
    /**
     * 是否已经绑定datasource
     * 绑定：true
     * 没绑定：false
     * @return
     */
    public boolean hasBindedDataSourse(){
        return dbLookupKey.get().length() != 0;
    }

    private static String selectFromSlave() {
        if (dbLookupKey == null) {
            LOGGER.info("提供可选取slave数据源：{},将自动切换从主master选取数据源", slaveDataSourceKeys);
            return selectFromMaster();
        } else {
            return slaveDataSourceKeys.get(RandomUtils.nextInt(0, slaveDataSourceKeys.size()));
        }
    }

    private static String selectFromMaster() {
        return masterDataSourceKeys.get(RandomUtils.nextInt(0, masterDataSourceKeys.size()));
    }

    private static void setDataSource(String dataSourceKey) {
        LOGGER.info("dbLookupKey set datasource keys:{}", dataSourceKey);
        dbLookupKey.set(dataSourceKey);
    }

    public static String getDataSource(){
        String db = dbLookupKey.get();
        if (db == null){
            db = Constant.DEFAULT_DATASOURCE_NAME;
        }
        return db;
        /*return dbLookupKey.get();*/
    }

    public static boolean contains(String key){
        return masterDataSourceKeys.contains(key) || slaveDataSourceKeys.contains(key);

    }

}
