/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.model.config;

import king.app.web.zlkf.search.searchworker.service.async.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextClosedEvent;

/**
 *
 * @author king
 */
@Configuration
public class AsyncConfiguration implements ApplicationListener<ContextClosedEvent> {
    
    @Autowired
    private AsyncService async;
    
    @Autowired
    public void start(){
        this.async.start();
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        boolean isAlive = this.async.isAlive();
        if( isAlive ){
            this.async.stop();
        }
    }
    
    
    
}
