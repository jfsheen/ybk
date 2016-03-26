package cc.aisc.ybk.config.mybatis.datasource;

import cc.aisc.ybk.config.mybatis.datasource.annotation.TargetDataSource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by sjf on 16-3-21.
 */
@Aspect
//@Order(-1)// 保证该AOP在@Transactional之前执行
@Component
public class DynamicDataSourceAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

    @Pointcut("@annotation(cc.aisc.ybk.config.mybatis.datasource.annotation.TargetDataSource)")
    public void targetDataSource(){}

    @Pointcut("execution(* cc.aisc.ybk.content.service.*.*(..))")
    public void exec(){}

    @Pointcut("execution(* cc.aisc.ybk.content.*.findBy*(..))")
    public void findBy(){}

    @Before("targetDataSource() && @annotation(ds)")
    public void changeDataSource(JoinPoint point, TargetDataSource ds) throws Throwable {
        LOGGER.debug("======before pointcut targetDataSource()=======");
        String dsId = ds.value();
        if (!DynamicDataSourceHolder.contains(dsId)) {
            LOGGER.error("DataSource [{}] NOT exists, using default DataSource-> {}", ds.value(), point.getSignature());
        } else {
            LOGGER.debug("Use DataSource : {} -> {}", ds.value(), point.getSignature());
            DynamicDataSourceHolder.markSlave(ds.value());
        }
    }

    @After("targetDataSource()  && @annotation(ds)")
    public void restoreDataSource(JoinPoint point, TargetDataSource ds) throws Throwable{
        LOGGER.debug("Revert DataSource : {} -> {}", ds.value(), point.getSignature());
        DynamicDataSourceHolder.markRemove();
        LOGGER.debug("======before pointcut targetDataSource()=======");
    }

}
