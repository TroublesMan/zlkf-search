/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.controller;

import java.util.HashSet;
import java.util.Set;
import javax.sql.DataSource;
import king.app.web.zlkf.search.searchworker.model.jpa.EntryItemRepository;
import king.app.web.zlkf.search.searchworker.service.model.EntryItemService;
import king.app.web.zlkf.search.searchworker.controller.rest.EntryItemController;
import king.app.web.zlkf.search.searchworker.model.bean.AnalyWordRecord;
import king.app.web.zlkf.search.searchworker.model.bean.EntryItem;
import king.app.web.zlkf.search.searchworker.service.model.AnalyWdRdService;
import king.app.web.zlkf.search.searchworker.service.model.AnalyWdService;
import king.app.web.zlkf.search.searchworker.service.model.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author king
 */
@RestController
@RequestMapping("api/test")
public class TestController {
    
    @Autowired
    private EntryItemRepository entryItemRepository;
    
    @Autowired
    private EntryItemService entryItemService;
    
    @Autowired
    private AnalyWdService analyService;
    
    private final static String HELLO_WORLD_STRING = "helloworld";
    
    @RequestMapping("helloworld")
    public String Helloworld(){
        return HELLO_WORLD_STRING;
    }
    
    @RequestMapping("entryItem/all")
    public Object entryItemAll(){
        return this.entryItemRepository.findAll();
    }
    
    /**
     * 根据对应的 text 进行搜索
     * @param text
     * @return 
     */
    @RequestMapping("entry/search")
    public Object entrySearchText( String  text ){
        return this.entryItemService.searchByText(text , null , null);
    }
    
    @Autowired
    private DataSource datasource;
    
    //测试对应的DataSource
    @RequestMapping("datasource")
    public Object datasource(){
        return this.datasource.toString();
    }

    @Autowired
    private RedisService redis;
    
    @RequestMapping("redis/set/{key}/{val}")
    public Object redisSet(@PathVariable("key") String key , @PathVariable("val") String val ){
        EntryItem entry = new EntryItem();
        entry.title = val;
        this.redis.set(key, entry);
        return entry;
    }
    @RequestMapping("redis/get/{key}")
    public Object redisGet( @PathVariable("key") String key ){
        Object result = this.redis.get(key,EntryItem.class);
        return result;
    }
    
    @RequestMapping("analy/wd")
    public Object analyWd( String str ){
        if( str == null ){
            return "this is a error ";
        }
        
        return this.analyService.analyWord(str);
    }
    
    @Autowired
    private AnalyWdRdService analyWordRecordService;
    
    
    @RequestMapping("insert/rd")
    public Object insert_rd( String str ){
       
        if( str == null ){
            return "this is a error ";
        }
        
        Set<String> strSet  = this.analyService.analyWord(str);
        
        return this.analyWordRecordService.searchOrInsert(strSet);
        
    }
    
}
