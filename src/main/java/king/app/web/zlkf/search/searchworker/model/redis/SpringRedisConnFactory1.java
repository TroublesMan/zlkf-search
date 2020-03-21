/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.model.redis;

import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

/**
 *
 * @author king
 */
public class SpringRedisConnFactory1 extends LettuceConnectionFactory {
    
    public String host;
    
    public int database;
    
    public int port;
    
    public void connect(){
        this.setHostName(this.host);
        this.setDatabase(this.database);
        this.setPort(this.port);
    }
    
    public RedisConnection getConnection() {
        try{
            return super.getConnection();
        }catch( Exception ex ){
            return null;
        }
    }
    
}
