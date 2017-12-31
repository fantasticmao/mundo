package com.mundo;

import com.mundo.aop.TimeoutAspect;
import com.mundo.constant.Beans;
import com.mundo.util.SpringUtil;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

/**
 * MundoCoreAutoConfiguration
 *
 * @author maodh
 * @since 2017/11/15
 */
@Configuration
public class MundoCoreAutoConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(MundoCoreAutoConfiguration.class);

    @Bean
    @ConditionalOnMissingBean
    TimeoutAspect timeoutAspect() {
        return new TimeoutAspect();
    }

    @Bean(name = Beans.REST_DEFAULT_TEMPLATE)
    @ConditionalOnMissingBean(name = Beans.REST_DEFAULT_TEMPLATE)
    RestTemplate defaultRestTemplate() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        LOGGER.info("Instantiating core bean: defaultRestTemplate.");

        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
        SSLContext sslContext = SSLContextBuilder.create()
                .loadTrustMaterial(null, acceptingTrustStrategy)
                .build();
        SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext);
        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(sslConnectionSocketFactory)
                .setMaxConnTotal(500)
                .build();

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setReadTimeout(3_000);
        requestFactory.setConnectTimeout(3_000);
        requestFactory.setHttpClient(httpClient);
        return new RestTemplate(requestFactory);
    }

    @Bean(name = Beans.REST_ANSYC_TEMPLATE)
    @ConditionalOnMissingBean(name = Beans.REST_ANSYC_TEMPLATE)
    AsyncRestTemplate ansycRestTemplate() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        LOGGER.info("Instantiating core bean: ansycRestTemplate.");
        return new AsyncRestTemplate(null, defaultRestTemplate());
    }

    @Bean
    @ConditionalOnMissingBean
    SpringUtil springUtil() {
        return new SpringUtil();
    }

}
