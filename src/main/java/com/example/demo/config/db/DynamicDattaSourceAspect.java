package com.example.demo.config.db;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by liulanhua on 2018/9/21.
 */
@Order(-1)//保证在@Transactional之前执行
@Component
@Aspect
public class DynamicDattaSourceAspect{

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * 改变数据源
     * @param joinPoint
     * @param targetDataSource
     */
    @Before("@annotation(targetDataSource)")
    public void changeDataSource(JoinPoint joinPoint, TargetDataSource targetDataSource) {
        String dbid = targetDataSource.value();
        if (!DynamicDataSourceContextHolder.isContainsDataSource(dbid)) {
            //joinPoint.getSignature() ：获取连接点的方法签名对象
            logger.error("数据源 " + dbid + " 不存在使用默认的数据源 -> " + joinPoint.getSignature());
        } else {
            logger.debug("使用数据源：" + dbid);
            DynamicDataSourceContextHolder.setDataSourceType(dbid);
        }
    }


    @After("@annotation(targetDataSource)")
    public void clearDataSource(JoinPoint joinPoint, TargetDataSource targetDataSource) {
        logger.debug("清除数据源 " + targetDataSource.value() + " !");
        DynamicDataSourceContextHolder.clearDataSourceType();
    }


    /*@Bean
    public SpringProcessEngineConfiguration springProcessEngineConfiguration(
            PlatformTransactionManager transactionManager,
            SpringAsyncExecutor springAsyncExecutor) throws IOException {

        return baseSpringProcessEngineConfiguration(
                dataSourceActiviti(),
                transactionManager,
                springAsyncExecutor);
    }*/
}
