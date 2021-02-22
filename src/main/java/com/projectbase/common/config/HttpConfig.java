package com.projectbase.common.config;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.Ssl;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置支持SpringBoot支持https时，在同时支持http
 */
@Configuration
public class HttpConfig {

    @Bean
    public TomcatServletWebServerFactory servletContainer(){
        TomcatServletWebServerFactory tomcatServletWebServerFactory=new TomcatServletWebServerFactory(){
            /**
             * 该方法的作用：http请求时会自动跳转成https请求
             * @param context
             */
            @Override
            protected void postProcessContext(Context context){
                //指定安全约束
                SecurityConstraint securityConstraint=new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection=new SecurityCollection();
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };
        tomcatServletWebServerFactory.addAdditionalTomcatConnectors(createHttpConnector());
        return tomcatServletWebServerFactory;
    }

    /**
     * 创建Http连接器
     * @return
     */
    private Connector createHttpConnector(){
        Connector connector=new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        //http端口
        connector.setPort(8080);
        connector.setSecure(false);
        //https端口
        connector.setRedirectPort(8443);
        return connector;
    }

    /**
     * 编程方式实现springboot的https
     * 不在配置文件配置，就写配置类
     * @return
     */
    /*@Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> containerCustomizer(){
            return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
                @Override
                public void customize(ConfigurableWebServerFactory factory) {
                    Ssl ssl=new Ssl();
                    //服务器私钥和证书
                    String path = this.getClass().getClassLoader().getResource("server.pkcs12").getPath();
                    ssl.setKeyStore(path);
                    ssl.setKeyStorePassword("123456");
                    ssl.setKeyStoreType("pkcs12");
                    factory.setSsl(ssl);
                    factory.setPort(8443);
                }
            };
    }*/
}
