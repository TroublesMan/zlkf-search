/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.service.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author king
 */
@Configuration
public class ESearchConfiguration {
    
    // 在当前的版本，我们暂时使用对应的参数去设置对应的elasticSearch
    
    private final static String DEFAULT_ELASTICSEARCH_IP = "193.112.51.74";
    
    private final static Integer DEFAULT_ELASTICSEARCH_PORT = 9200;
    
    @Bean
    public ESearchLinkStruct settingESearchLinkStruct(){
        ESearchLinkStruct link = new ESearchLinkStruct( DEFAULT_ELASTICSEARCH_IP , DEFAULT_ELASTICSEARCH_PORT );
        return link;
    }
    
    @Bean
    public RestClientBuilder restClientBuilder( @Autowired ESearchLinkStruct link ){
        return RestClient.builder( link.httpHost );
    }
    
    @Bean
    public RestHighLevelClient highLevelClient(@Autowired RestClientBuilder builder ) {
        builder.setMaxRetryTimeoutMillis(60000);
        return new RestHighLevelClient(builder.build());
    }
    
    
}
