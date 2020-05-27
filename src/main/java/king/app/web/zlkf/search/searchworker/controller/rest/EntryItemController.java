/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.controller.rest;

import java.util.Date;
import king.app.web.zlkf.search.searchworker.model.bean.EntryItem;
import king.app.web.zlkf.search.searchworker.service.model.EntryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author king
 */
@RestController
@RequestMapping("api/entry")
public class EntryItemController {
    
    @Autowired
    private EntryItemService entryItemService;
    
    @RequestMapping("search")
    public Object searchText( String text , Integer pageNum , Integer pageSize ){
        return this.entryItemService.searchByText(text , pageNum , pageSize );
    }
    
    /**
     * 目前仅仅为开放如此一个的接口，并不提供实际的操作能力，仅仅证明能做到这样的操作
     * @param title
     * @param url
     * @param introduction
     * @return 
     */
    @RequestMapping("insert/self")
    public Object insertSelf( String title , String url , String introduction ){
        
        EntryItem entryItem = new EntryItem();
        entryItem.title = title;
        entryItem.url = url;
        entryItem.introduction = introduction;
        
        //首先生成id 号
        EntryItem newEntryItem = this.entryItemService.newEntryItem(entryItem);
        return newEntryItem;
    }
}
