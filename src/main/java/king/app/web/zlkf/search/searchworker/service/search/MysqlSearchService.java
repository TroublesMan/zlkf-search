/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.service.search;

import java.util.ArrayList;
import java.util.List;
import king.app.web.zlkf.search.searchworker.model.bean.EntryItem;
import king.app.web.zlkf.search.searchworker.model.bean.es.EntryItemEs;
import king.app.web.zlkf.search.searchworker.service.model.EntryItemService;
import king.app.web.zlkf.search.searchworker.service.model.EntryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author king
 */
@Service
public class MysqlSearchService {
    
    @Autowired
    private EntryItemService entryItemService ;
    
    public List<EntryItem> searchEntryByText( String text , Integer pageNum , Integer pageSize ){
        return this.entryItemService.searchByText(text, pageNum,pageSize);
    }
    
    /**
     * 根据es 来直接生成对应的 entry
     * @param entryItemEsList
     * @return 
     * PS. 其实这里应该引入对应的mysql信息，但是目前EntryItemEs 信息与 EntryItem 一样，因此也就无需麻烦，可以接着继续食用
     */
    public List<EntryItem> searchEntryByEs( List<EntryItemEs> entryItemEsList ){
        int esSize = entryItemEsList.size();
        List<EntryItem> entryItemList = new ArrayList<EntryItem>( esSize );
        for( EntryItemEs entryItemEs : entryItemEsList ){
            EntryItem entryItem = entryItemEs;
            entryItemList.add(entryItem);
        }
        return entryItemList;
    }
    
    
}
