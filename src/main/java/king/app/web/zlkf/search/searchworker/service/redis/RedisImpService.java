/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.service.redis;

import java.time.Duration;
import java.util.List;
import king.app.web.zlkf.search.searchworker.model.bean.EntryItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * 
 * @author king
 */
@Service
public class RedisImpService {
    
    //目前，我们将redis 缓存暂时只对应放在 持久层 
    
    @Autowired
    private RedisService redisService;
    
    private String saveEntryKey = "entry_item";
    
    private Duration saveEntryDuration = Duration.ofHours(3);
    
    private String byTextKey = "entry_search_text";
    
    private Duration byTextDuration = this.saveEntryDuration;
    
    /**
     * 
     * @param entryItem 
     * @return  
     */
    public boolean saveEntryItem( EntryItem entryItem ) {
        
        if( entryItem == null ){
            return false;
        } 
        
        this.redisService.put(this.saveEntryKey, entryItem.id.toString() ,  entryItem ,this.saveEntryDuration);
        return true;
    }
    
    /**
     * 根据单个id来直接获取数据
     * @param key
     * @return 
     */
    public EntryItem getEntryItem( String key ){
        
        return this.redisService.get( this.saveEntryKey , key , EntryItem.class);
    }
    
    public boolean saveEntryItemByText(String text , List<EntryItem> entryItemList ){
        
        return this.redisService.putList(this.byTextKey , text, entryItemList, this.byTextDuration);
    }
    
    public List<EntryItem> getEntryItemsByText( String text ){
        return this.redisService.getList(this.byTextKey, text, EntryItem.class);
    }
}
