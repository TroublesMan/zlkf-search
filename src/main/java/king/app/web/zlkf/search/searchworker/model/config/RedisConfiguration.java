/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.model.config;

import king.app.web.zlkf.search.searchworker.service.AppControlService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author king
 */
public class RedisConfiguration {
    
    @Autowired
    private AppControlService appControlService;
    
    /**
     * 运行对应的startRedis
     */
    @Autowired
    public void startRedis(){
        
    }
    
}
