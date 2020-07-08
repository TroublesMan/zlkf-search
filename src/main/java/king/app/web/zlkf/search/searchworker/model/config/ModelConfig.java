/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.model.config;

import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import king.app.web.zlkf.search.searchworker.model.redis.RedisStruct;
import king.app.web.zlkf.search.searchworker.model.redis.SpringRedisConnFactory1;
import king.app.web.zlkf.search.searchworker.service.AppControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 *
 * @author king
 */
@Configuration
public class ModelConfig {

    public final static String MYSQL_DRIVER_CLASS_NAME_STRING = "com.mysql.jdbc.Driver";
    
    @Autowired
    private AppControlService appControlService;
    

    @Bean
    public DataSource localDataSource(){
        
        boolean isAllowDatabase = this.appControlService.isAllowDatabase();
        //若isAllowDatabase 为 false
        if( !isAllowDatabase ){
            return null;
        }
        
        HikariDataSource datasource = new HikariDataSource ();
        //添加对应的 数据库的基本配置
        datasource.setDriverClassName(MYSQL_DRIVER_CLASS_NAME_STRING);
        datasource.setJdbcUrl( "jdbc:mysql://localhost:3306/zlkf_search?useUnicode=true&characterEncoding=UTF-8" );
        datasource.setUsername("root");
        datasource.setPassword("980621");
        return datasource;
    }

    
    /**
     * 
     * @return 
     */
    @Bean
    public RedisConnectionFactory redisConnection(){
        String host = "localhost";
        int port = 6379;
        
        SpringRedisConnFactory1 connectionFactory = new SpringRedisConnFactory1();
        connectionFactory.database = 1;
        connectionFactory.host = host;
        connectionFactory.port = port;
        connectionFactory.connect();
        return connectionFactory;
    }
    
    @Autowired(required = false)
    private RedisConnectionFactory redisConnectionFactory = null;
    
    @Bean
    public RedisStruct originRedisStruct( ){
        RedisStruct origin = RedisStruct.byConnection(redisConnectionFactory);
        return origin;
    }
    
    @Bean
    public RedisTemplate redisTemplate( RedisStruct redisStruct ){
        return redisStruct.template;
    }
}
