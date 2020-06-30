/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.controller;

import king.app.web.zlkf.search.searchworker.model.bean.EntryItem;
import king.app.web.zlkf.search.searchworker.service.model.EntryItemService;
import king.app.web.zlkf.search.searchworker.service.obj.CreateEntryItemObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author king
 */
@RestController
@RequestMapping("api/insert")
public class InsertController {
    
    @Autowired
    private EntryItemService entryItemService;
    
    
    @RequestMapping("entry")
    public Object insertEntryItem( String title , String introduction ,  String url ){
        EntryItem entryItem = new EntryItem();
        entryItem.title = title;
        entryItem.introduction = introduction;
        entryItem.url = url;
        //下面生成对应的信息
        CreateEntryItemObj itemObj =  this.entryItemService.createEntryItem(entryItem);
        return itemObj;
    }
    
}
