package pers.caijx.elasticsearch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName RfcConfig
 * @Description: 解决请求出现RFC 7230 and RFC3986的错误
 * @Author Think
 * @Date 2019/9/29
 * @Version V1.0
 **/
@Configuration
public class RfcConfig {

    @Bean
    public Integer setRfc() {
        // RFC 3986保留字符：! * ’ ( ) ; : @ & = + $ , / ? # [ ]
        // 指定jre系统属性，允许特殊符号，如{}做入参，其他符号按需添加。见tomcat的HttpParser源码
        System.setProperty("tomcat.util.http.parser.HttpParser.requestTargetAllow", "|{}[]");
        return 0;
    }
}
