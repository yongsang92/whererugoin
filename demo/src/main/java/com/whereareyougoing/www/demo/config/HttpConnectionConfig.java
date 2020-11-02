package com.whereareyougoing.www.demo.config;

import org.springframework.context.annotation.Configuration;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/* RestTemplate 은 기본적으로 conneciton pool을 사용하지 않기 때문에 매 요청마다 handshake를 수행한다. 
이를 방지하기 위해 다음과 같은 설정을 추가한 Custom RestTemplate을 빈으로 등록하여 사용하는걸 추천한다.
*/
@Configuration
public class HttpConnectionConfig {
    @Bean
    public RestTemplate getCustomRestTemplate() {
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectTimeout(2000);
        httpRequestFactory.setReadTimeout(3000);
        CloseableHttpClient httpClient = HttpClientBuilder.create().setMaxConnTotal(200).setMaxConnPerRoute(20).build();
        httpRequestFactory.setHttpClient(httpClient);
        return new RestTemplate(httpRequestFactory);
    }
}
