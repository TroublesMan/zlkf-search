/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * 该service 内拥有能调整程序内所有东西的开启与关闭
 * 
 * 以后，我们可以使用这个service 来控制与显示与程序运行状态相关的信息
 * 
 * @author king
 */
@Service
public class AppControlService {
    
    @Autowired
    private AppControlService self;
    
    private boolean isAllowAsync = false;
    
    private boolean isAllowDatabase = true;
    
    private boolean isAllowRedis = true; //是否允许运行 redis 运行
    
    private boolean isAllowElasticsearch = true;
    
    
    
    public boolean isAllowAsync(){
        return this.isAllowAsync;
    }
    
    public boolean setAllowAsync( boolean isAllowAsync ){
        this.isAllowAsync = isAllowAsync;
        return self.isAllowAsync();
    }
    
    //控制 MYSQL 的信息
    
    public boolean isAllowDatabase(){
        return this.isAllowDatabase;
    }
    
    public boolean setAllowDatabase( boolean allowDatabase ){
        this.isAllowDatabase = allowDatabase;
        return self.isAllowDatabase();
    }
    
    // redis
    
    public boolean isAllowRedis(){
        return this.isAllowRedis;
    }
    
    public boolean setAllowRedis( boolean allowRedis ){
        this.isAllowRedis = allowRedis;
        return self.isAllowRedis();
    }
    
    public boolean isAllowElasticsearch(){
        return this.isAllowElasticsearch;
    }
    
    public boolean setAllowElasticserch( boolean allowElasticserch ){
        this.isAllowElasticsearch = allowElasticserch;
        return self.isAllowElasticsearch();
    }
    
}
