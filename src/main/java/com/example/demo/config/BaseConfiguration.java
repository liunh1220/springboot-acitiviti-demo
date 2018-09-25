package com.example.demo.config;

import com.example.demo.config.db.DynamicDattaSourceAspect;
import com.example.demo.constant.ApplicationConstant;
import com.example.demo.util.EnhancedRestTemplate;
import com.example.demo.util.JacksonUtils;
import com.example.demo.util.OkHttpUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static com.example.demo.constant.Constants.RAW_REST_TEMPLATE;


/**
 * Created by liulanhua on 2018/3/19.
 */
@ComponentScan({"com.example.demo"})
public class BaseConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars*")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }



    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        return JacksonUtils.mapper;
    }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        return new MappingJackson2HttpMessageConverter(objectMapper());
    }

    @Primary
    @Bean
    public okhttp3.OkHttpClient okHttpClient(ApplicationConstant applicationConstant){
        return OkHttpUtils.okHttpClientBuilder(applicationConstant).build();
    }

    @LoadBalanced
    @Bean
    @Primary
    public RestTemplate restTemplate(MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter,
                                     OkHttpClient okHttpClient) {
        return EnhancedRestTemplate.assembleRestTemplate(mappingJackson2HttpMessageConverter, okHttpClient);
    }

    @Bean(name = RAW_REST_TEMPLATE)
    public RestTemplate rawRestTemplate(MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter,
                                        OkHttpClient okHttpClient) {

        return EnhancedRestTemplate.assembleRestTemplate(mappingJackson2HttpMessageConverter, okHttpClient);
    }



    @Bean(name = "hostName")
    public String getHostName(){
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "mybatis-demo";
    }


    @Bean
    public DynamicDattaSourceAspect dynamicDattaSourceAspect(){
        return new DynamicDattaSourceAspect();
    }

}
