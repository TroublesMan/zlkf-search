/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.service.redis;

import java.time.Duration;
import king.app.web.zlkf.search.searchworker.service.AppControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

/**
 *
 * @author king
 */
@Service
public class RedisService {
    
    @Autowired(required = false)
    private RedisTemplate redisTemplate;
    
    @Autowired
    private AppControlService appControlService;
    
    
    public void put( String key , Object val , Duration duration ){
        ValueOperations<String,Object> valueOperations =  this.redisTemplate.opsForValue();
        valueOperations.set(key, val);
    }
    
    
    
}
