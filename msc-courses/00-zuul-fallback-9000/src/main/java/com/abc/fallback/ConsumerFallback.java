package com.abc.fallback;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by sunbc on 2020-03-02
 */
@Component
public class ConsumerFallback implements FallbackProvider {
    @Override
    public String getRoute() {
        // 对指定的微服务进行降级
        //return "abcmsc-consumer-depart";
        // 指定对所有微服务进行降级
        return "*";
    }

    /**
     *
     * @param route 请求中的微服务名称
     * @param cause
     * @return
     */
    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        // 若微服务不是abcmsc-consumer-depart-8880，则不进行降级
//        if (!"abcmsc-consumer-depart-8880".equals(route)){
//            return null;
//        }
        // 仅对abcmsc-consumer-depart-8880进行降级
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.SERVICE_UNAVAILABLE;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return HttpStatus.SERVICE_UNAVAILABLE.value();
            }

            @Override
            public String getStatusText() throws IOException {
                return HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase();
            }

            @Override
            public void close() {
                // 写资源释放代码

            }

            @Override
            public InputStream getBody() throws IOException {
                String msg = "fallback:" + route;
                return new ByteArrayInputStream(msg.getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }
        };
    }
}
