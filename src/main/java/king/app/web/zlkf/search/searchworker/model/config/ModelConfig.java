/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.model.config;

import com.zaxxer.hikari.HikariDataSource;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author king
 */
@Configuration
public class ModelConfig {

    public final static String MYSQL_DRIVER_CLASS_NAME_STRING = "com.mysql.jdbc.Driver";
    
    @Bean
    public DataSource localDataSource(){
        HikariDataSource datasource = new HikariDataSource ();
        
        datasource.setDriverClassName(MYSQL_DRIVER_CLASS_NAME_STRING);
        datasource.setJdbcUrl( "jdbc:mysql://localhost:3306/zlkf_search?useUnicode=true&characterEncoding=UTF-8" );
        datasource.setUsername("root");
        datasource.setPassword("980621");
        
        return datasource;
    }
    
    
}
