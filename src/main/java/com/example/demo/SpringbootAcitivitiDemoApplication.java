package com.example.demo;

import com.example.demo.config.ActivitiConfig;
import com.example.demo.config.BaseConfiguration;
import com.example.demo.config.db.DynamicDataSourceRegister;
import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@Import({BaseConfiguration.class, DynamicDataSourceRegister.class, ActivitiConfig.class})
public class SpringbootAcitivitiDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootAcitivitiDemoApplication.class, args);
    }
}
