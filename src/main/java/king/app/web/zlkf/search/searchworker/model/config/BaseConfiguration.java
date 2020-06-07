/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.model.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author king
 */
@Configuration
public class BaseConfiguration {
    
    @Bean(name = "origin_rest_template")
    public RestTemplate originRestTemplate(){
        return new RestTemplate();
    }
    
}
