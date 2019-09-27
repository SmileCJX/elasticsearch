package pers.caijx.elasticsearch.config;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Setting;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.Transport;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @ClassName ElasticSearchConfig
 * @Description: TODO
 * @Author Think
 * @Date 2019/9/27
 * @Version V1.0
 **/
@Configuration
@PropertySource(value = {"classpath:elasticsearch.properties"}) // 加载指定的配置，只支持properties
public class ElasticSearchConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticSearchConfig.class);

    @Value("${es.hostName}")
    private String hostName;

    @Value("${es.transport}")
    private Integer transport;

    @Value("${es.cluster.name}")
    private String clusterName;

    @Bean
    public TransportClient transportClient() {
        LOGGER.info("初始化开始");
        TransportClient client = null;
        try {
            TransportAddress transportAddress = new InetSocketTransportAddress(InetAddress.getByName(hostName),Integer.valueOf(transport));

            // 配置信息
            Settings esSetting = Settings.builder()
                                    .put("cluster.name",clusterName)
                                    .build();

            // 配置信息Settings自定义，下面设置为EMPTY
            client = new PreBuiltTransportClient(esSetting);

            client.addTransportAddress(transportAddress);

        } catch (UnknownHostException e) {
            LOGGER.error("elasticsearch TransportClient create error!!!",e);
        }
        LOGGER.info("初始化结束");
        return client;
    }
}
