package com.example.demo.util;

import com.example.demo.base.ErrorCode;
import com.example.demo.constant.CommonErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * Created by Administrator on 2018/5/21.
 */
public class RestTemplateErrorHandler extends DefaultResponseErrorHandler {

    protected Logger logger = LoggerFactory.getLogger(RestTemplateErrorHandler.class);

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        Charset charset = getCharset(response);
        byte[] responseBody = getResponseBody(response);
        String responseText = null;
        if (responseBody != null && responseBody.length > 0) {
            responseText = new String(responseBody, charset).trim();
        }

        ErrorCode errorCode = CommonErrorCode.REQUEST_SERVICE_ERROR;
        /*ErrorInfo error = new ErrorInfo(errorCode.getCode(), "", errorCode.getMessage());
        if(StringUtils.isNotBlank(responseText)) {
            try {
                error = JsonUtils.json2Object(responseText, ErrorInfo.class);
            } catch (Exception ignore) {
            }
        }*/

        //throw new Exception(error, getHttpStatusCode(response).value());
    }

    @Override
    protected byte[] getResponseBody(ClientHttpResponse response) {
        try {
            InputStream responseBody = response.getBody();
            if (responseBody != null) {
                return FileCopyUtils.copyToByteArray(responseBody);
            }
        } catch (IOException ex) {
            // ignore
        }
        return new byte[0];
    }

    @Override
    protected Charset getCharset(ClientHttpResponse response) {
        HttpHeaders headers = response.getHeaders();
        MediaType contentType = headers.getContentType();
        return contentType != null ? contentType.getCharset() : Charset.forName("UTF-8");
    }

    protected HttpStatus getHttpStatusCode(ClientHttpResponse response) throws IOException {
        HttpStatus statusCode;
        try {
            statusCode = response.getStatusCode();
        } catch (IllegalArgumentException ex) {
            logger.warn("", ex);
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return statusCode;
    }

}
