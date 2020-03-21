/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.service.model;

import king.app.web.zlkf.search.searchworker.model.redis.RedisStruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 *
 * @author king
 */
@Service
public class RedisService {
    
    @Autowired
    private RedisStruct redisStruct;
    
    @Autowired
    private TextFormatService textFormatService;
    
    @Autowired
    private RedisService self;
    
    public boolean set( Object key , Object val ){
        RedisStruct struct = this.redisStruct;
        if( !struct.isConnectionRedis ){
            return false;
        }
        struct.valueOperations.set(key, val);
        return true;
    }
    
    public boolean set( Object key , Object val , long offset ){
        RedisStruct struct = this.redisStruct;
        if( !struct.isConnectionRedis ){
            return false;
        }
        struct.valueOperations.set(key, val, offset);
        return true;
    }
    
    public <T> T get( Object key , Class<T> targetClass){
        RedisStruct struct = this.redisStruct;
        if( !struct.isConnectionRedis ){
            return null;
        }
        Object val = struct.valueOperations.get(key);
        if( val == null ){
            return null;
        }
        Object valClass = val.getClass();
        if( !valClass.equals(targetClass) ){
            return null;
        }
        return (T)val;
    }
}
