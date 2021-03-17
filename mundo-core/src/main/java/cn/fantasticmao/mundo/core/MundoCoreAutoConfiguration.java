package cn.fantasticmao.mundo.core;

import cn.fantasticmao.mundo.core.aop.AssertFalseAspect;
import cn.fantasticmao.mundo.core.aop.PrintArgsAspect;
import cn.fantasticmao.mundo.core.aop.TimeoutAspect;
import cn.fantasticmao.mundo.core.util.SpringUtil;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

/**
 * MundoCoreAutoConfiguration
 *
 * @author maodh
 * @since 2017/11/15
 */
@Configuration
public class MundoCoreAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    AssertFalseAspect assertFalseAspect() {
        return new AssertFalseAspect();
    }

    @Bean
    @ConditionalOnMissingBean
    PrintArgsAspect printArgsAspect() {
        return new PrintArgsAspect();
    }

    @Bean
    @ConditionalOnMissingBean
    TimeoutAspect timeoutAspect() {
        return new TimeoutAspect();
    }

    @Bean
    @ConditionalOnMissingBean
    SpringUtil springUtil() {
        return new SpringUtil();
    }

    @Bean
    @ConditionalOnMissingBean
    CloseableHttpClient httpClient() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslContext = SSLContextBuilder.create()
            .loadTrustMaterial(null, (X509Certificate[] chain, String authType) -> true)
            .build();
        RequestConfig requestConfig = RequestConfig.custom()
            .setConnectTimeout((int) TimeUnit.SECONDS.toMillis(30))
            .setSocketTimeout((int) TimeUnit.SECONDS.toMillis(30))
            .build();
        return HttpClients.custom()
            .setSSLContext(sslContext)
            .setDefaultRequestConfig(requestConfig)
            .build();
    }

}
