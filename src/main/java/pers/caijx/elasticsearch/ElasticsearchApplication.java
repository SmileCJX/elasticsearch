package pers.caijx.elasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

//public class ElasticsearchApplication extends SpringBootServletInitializer { // 打WAR包
//@EnableElasticsearchRepositories(basePackages = "pers.caijx.elasticsearch.repository")
@SpringBootApplication
public class ElasticsearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElasticsearchApplication.class, args);
    }

}
