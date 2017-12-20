package com.ldeng.sbsaml;

import com.github.ulisesbocchio.spring.boot.security.saml.configurer.ServiceProviderBuilder;
import com.github.ulisesbocchio.spring.boot.security.saml.configurer.ServiceProviderConfigurerAdapter;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyServiceProviderConfig extends ServiceProviderConfigurerAdapter{

    @Override
    public void configure(ServiceProviderBuilder serviceProvider) throws Exception {
        serviceProvider
                .metadataGenerator()
                .entityBaseURL("http://localhost:8081")
                .entityId("localhost-demo")
                .and()
                .sso()
                .defaultSuccessURL("/home")
                .idpSelectionPageURL("/idpselection")
                .and()
                .logout()
                .defaultTargetURL("/")
                .and()
                .metadataManager()
                .metadataLocations("classpath:/idp-ssocircle.xml")
                .refreshCheckInterval(0)
                .and()
                .extendedMetadata()
                .idpDiscoveryEnabled(true)
                .and()
                .keyManager()
                .privateKeyDERLocation("classpath:/localhost.key.der")
                .publicKeyPEMLocation("classpath:/localhost.cert")
                .and()
                .samlContextProviderLb()
                .scheme("http")
                .contextPath("/")
                .serverName("localhost")
                .serverPort(8081)
                .includeServerPortInRequestURL(true);
    }
}
