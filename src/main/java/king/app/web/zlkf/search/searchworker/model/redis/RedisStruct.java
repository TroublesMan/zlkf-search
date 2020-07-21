/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.model.redis;

import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

/**
 *
 * @author king
 */
public class RedisStruct {
    public  RedisTemplate template = null;
    public  ValueOperations valueOperations = null;
    public  HashOperations hashOperations = null;
    
    public RedisConnectionFactory connectionFactory = null;
    public boolean isConnectionRedis = false; 
    
    public static RedisStruct byTemplate ( RedisTemplate template ){
        RedisStruct redisStruct = new RedisStruct();
        redisStruct.template = template;
        //redisStruct.valueOperations = template.opsForValue();
       // redisStruct.hashOperations = template.opsForHash();
        
        if( !redisStruct.isConnectionRedis ){
            redisStruct.setConnectionFactory(template.getConnectionFactory());
        }
        
        return redisStruct;
    }
    
    public static RedisStruct byConnection( RedisConnectionFactory connectionFactory ){
        RedisTemplate template = new RedisTemplate();
        
        template.setConnectionFactory(connectionFactory);
        return byTemplate(template);
    }
    
    public void setConnectionFactory( RedisConnectionFactory connectionFactory ){
        if( connectionFactory != null ){
            
            RedisConnection redisConnection = connectionFactory.getConnection();
            if( redisConnection != null && !redisConnection.isClosed() ){
                this.isConnectionRedis = true;
                this.connectionFactory = connectionFactory;
            }
        }
    }
        
}
