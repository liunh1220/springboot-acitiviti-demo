package com.example.demo.util;

import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2018/5/21.
 */
public class EnhancedRestTemplate extends RestTemplate {

    private static final Logger logger = LoggerFactory.getLogger(EnhancedRestTemplate.class);

    public EnhancedRestTemplate() {
        super();
    }

    public EnhancedRestTemplate(ClientHttpRequestFactory requestFactory) {
        super(requestFactory);
    }

    public EnhancedRestTemplate(List<HttpMessageConverter<?>> messageConverters) {
        super(messageConverters);
    }

    @Override
    protected <T> T doExecute(URI url, HttpMethod method, RequestCallback requestCallback,
                              ResponseExtractor<T> responseExtractor) throws RestClientException {
        try {
            return super.doExecute(url, method, requestCallback, responseExtractor);
        } catch (Exception e) {
            return null;
        }
    }


    public static EnhancedRestTemplate assembleRestTemplate(
            MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter, OkHttpClient okHttpClient) {

        EnhancedRestTemplate restTemplate = createRestTemplate(okHttpClient);

        //自定义异常处理
        restTemplate.setErrorHandler(new RestTemplateErrorHandler());
        List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
        for(Iterator<HttpMessageConverter<?>> iterator = messageConverters.iterator(); iterator.hasNext();) {
            HttpMessageConverter<?> converter = iterator.next();
            if(converter instanceof MappingJackson2HttpMessageConverter) {
                iterator.remove();
            }
        }
        messageConverters.add(mappingJackson2HttpMessageConverter);
        return restTemplate;
    }

    private static EnhancedRestTemplate createRestTemplate(OkHttpClient okHttpClient) {
        EnhancedRestTemplate restTemplate = new EnhancedRestTemplate(new OkHttp3ClientHttpRequestFactory(okHttpClient));
//        restTemplate.getInterceptors().add(new LoggingRequestInterceptor());
        return restTemplate;
    }

}
