package com.electricity.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jiaowei
 * @since 2024/11/18 14:45
 */
@Configuration
public class ElasticSearchConfig {
     public static final RequestOptions COMMON_OPTIONS;
    static {
        RequestOptions.Builder builder = RequestOptions.DEFAULT.toBuilder();
        //addHeader();
        //setHttpAsyncResponseCounsumerFactory
        COMMON_OPTIONS = builder.build();
    }

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        RestClientBuilder restClientBuilder = RestClient.builder(new HttpHost("192.168.119.135", 9200, "http"));
        RestHighLevelClient client = new RestHighLevelClient(restClientBuilder);
        return client;
    }
}
