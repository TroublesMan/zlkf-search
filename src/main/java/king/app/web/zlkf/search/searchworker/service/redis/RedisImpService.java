/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.service.redis;

import java.time.Duration;
import king.app.web.zlkf.search.searchworker.model.bean.EntryItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author king
 */
@Service
public class RedisImpService {
    
    @Autowired
    private RedisService redisService;
    
    public void saveEntryItem( EntryItem entryItem ){
        //this.redisService.put(key, this, Duration.ZERO);
    }
    
}
