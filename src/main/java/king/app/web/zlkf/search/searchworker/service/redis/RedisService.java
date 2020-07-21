/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.service.redis;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.time.Duration;
import java.util.List;
import king.app.web.zlkf.search.searchworker.model.redis.RedisStruct;
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
    
    @Autowired
    private RedisStruct redisStruct;
    
    @Autowired
    private AppControlService appControlService;
    
    @Autowired
    private RedisService self;
    
    //组合redis 的key ， 因为一般来说，我们都会将对应的redis的 key放在
    
    
    /**
     * 当前redis的状态
     * @return 
     */
    public boolean currentRedisStatus(){
        boolean currentStatus = this.redisStruct.isConnectionRedis;
        return currentStatus;
    }
    
    public <T> boolean put( String key , T val , Duration duration ){
        RedisTemplate redisTemplate = this.redisStruct.template;
        ValueOperations<String,T> valueOperations =  redisTemplate.opsForValue();
        valueOperations.set(key, val);
        return true;
    }
    
    /**
     * 外加一个域的插入方式
     * 
     * 之所以外加这么一个方式
     * 主要便是希望让这个方法让 obj 与 hash 方法之前得到适配
     * 这样将来再改动之上我们可以轻松得到解决方案
     * 
     * @param <T>
     * @param scope
     * @param key
     * @param val
     * @param duration
     * @return 
     */
    public <T> boolean put( String scope , String key , T val ,Duration duration ){
        if( scope != null || key != null  ){
            return false;
        }
        String link_key = new StringBuilder().append(scope).append("_").append(key).toString();
        return self.put(link_key, val, duration);
    }
    
    public <T> T get( String key , Class<T> targetClass ){
        RedisTemplate redisTemplate = this.redisStruct.template;
        ValueOperations<String,T> valueOperations =  redisTemplate.opsForValue();
        return valueOperations.get(key);
    }
    
    public <T> T get( String scope , String key , Class<T> targetClass ){
        if( scope != null || key != null  ){
            return null;
        }
        String link_key = new StringBuilder().append(scope).append("_").append(key).toString();
        return self.get(link_key, targetClass);
    }
    
    //下面对 list 进行操作
    public <T> boolean putList(String scope , String key , List<T> itemList , Duration duration ){
        String jsonStr = JSONArray.toJSONString( itemList);
        return self.put(scope , key, jsonStr, duration);
    }
    
    public <T> List<T> getList( String scope , String key , Class<T> targetClass ){
         String jsonStr = self.get(scope , key , String.class);
         return JSONArray.parseArray(jsonStr, targetClass);
    }
    
}
