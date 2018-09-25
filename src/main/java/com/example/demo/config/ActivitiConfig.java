package com.example.demo.config;

import org.activiti.engine.*;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.activiti.spring.boot.AbstractProcessEngineAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;

/**
 * Created by liulanhua on 2018/9/25.
 */
@Configuration
@EnableTransactionManagement    //开启事物管理
public class ActivitiConfig extends AbstractProcessEngineAutoConfiguration {

    @Resource
    private DataSource dataSource;

    @Bean
    public StandaloneProcessEngineConfiguration processEngineConfiguration() throws IOException{
        StandaloneProcessEngineConfiguration configuration = new StandaloneProcessEngineConfiguration();
        configuration.setDataSource(dataSource);
        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        configuration.setAsyncExecutorActivate(false);
        configuration.setHistory("none");
        configuration.setDbIdentityUsed(false);
        configuration.setDbHistoryUsed(false);
        configuration.setActivityFontName("宋体");
        configuration.setLabelFontName("宋体");
        //configuration.setDeployers(resources);

        return configuration;

    }


    /*@Bean
    public SpringProcessEngineConfiguration processEngineConfiguration() throws IOException {
        SpringProcessEngineConfiguration springProcessEngineConfiguration = new SpringProcessEngineConfiguration();
        springProcessEngineConfiguration.setDataSource(dataSource);
        springProcessEngineConfiguration.setTransactionManager(transactionManager);
        springProcessEngineConfiguration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        springProcessEngineConfiguration.setHistory("none");
        springProcessEngineConfiguration.setDbIdentityUsed(false);
        springProcessEngineConfiguration.setDbHistoryUsed(false);
        springProcessEngineConfiguration.setActivityFontName("宋体");
        springProcessEngineConfiguration.setLabelFontName("宋体");
        //自动部署已有的流程文件
        org.springframework.core.io.Resource[] resources = new PathMatchingResourcePatternResolver().getResources(ResourceLoader.CLASSPATH_URL_PREFIX + "*.bpmn");
        springProcessEngineConfiguration.setDeploymentResources(resources);

        return springProcessEngineConfiguration;
    }*/


    @Bean
    public ProcessEngine processEngine()  throws IOException {
        return processEngineConfiguration().buildProcessEngine();
    }

    @Bean
    public RepositoryService repositoryService(ProcessEngine processEngine)  throws IOException {
        return processEngine.getRepositoryService();
    }

    @Bean
    public RuntimeService runtimeService(ProcessEngine processEngine)  throws IOException {
        return processEngine.getRuntimeService();
    }

    @Bean
    public TaskService taskService(ProcessEngine processEngine)  throws IOException {
        return processEngine.getTaskService();
    }

    @Bean
    public HistoryService historyService(ProcessEngine processEngine) throws IOException  {
        return processEngine.getHistoryService();
    }

    @Bean
    public ManagementService managementService(ProcessEngine processEngine)  throws IOException {
        return processEngine.getManagementService();
    }

    @Bean
    public IdentityService identityService(ProcessEngine processEngine) throws IOException  {
        return processEngine.getIdentityService();
    }

}
