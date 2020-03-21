/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.model.redis;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisClusterConnection;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConnection;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

/**
 *
 * @author king
 */
public class SpringRedisConnFactory implements RedisConnectionFactory{
    
    private LettuceConnectionFactory coreConnectionFactory = null;
    
    public String host;
    
    public int database;
    
    public int port;
    
    /**
     * 进行连接
     */
    public void connect(){
        this.coreConnectionFactory = new LettuceConnectionFactory();
        this.coreConnectionFactory.setHostName(this.host);
        this.coreConnectionFactory.setDatabase(this.database);
        this.coreConnectionFactory.setPort(this.port);
    }

    @Override
    public RedisConnection getConnection() {
        if( this.coreConnectionFactory != null ){
            try {
                RedisConnection  redisConnection = this.coreConnectionFactory.getConnection();
                return redisConnection;
            } catch (Exception e) {
            }
        }
        return null;
    }

    @Override
    public RedisClusterConnection getClusterConnection() {
        return this.coreConnectionFactory != null ? this.coreConnectionFactory.getClusterConnection() : null;
    }

    @Override
    public boolean getConvertPipelineAndTxResults() {
         return this.coreConnectionFactory != null ? this.coreConnectionFactory.getConvertPipelineAndTxResults(): null;
    }

    @Override
    public RedisSentinelConnection getSentinelConnection() {
        return this.coreConnectionFactory != null ? this.coreConnectionFactory.getSentinelConnection(): null;
    }

    @Override
    public DataAccessException translateExceptionIfPossible(RuntimeException ex) {
         return this.coreConnectionFactory != null ? this.coreConnectionFactory.translateExceptionIfPossible(ex): null;
    }
    
    
}
