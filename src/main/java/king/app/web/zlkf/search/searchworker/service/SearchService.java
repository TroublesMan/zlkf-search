/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.service;

import java.util.List;
import king.app.web.zlkf.search.searchworker.model.bean.EntryItem;
import king.app.web.zlkf.search.searchworker.service.model.EntryItemService;
import king.app.web.zlkf.search.searchworker.service.model.EntryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author king
 */
@Service
public class SearchService {
    
    @Autowired
    private EntryItemService entryItemService ;
    
    public List<EntryItem> searchByText( String text , Integer pageNum , Integer pageSize ){
        return this.entryItemService.searchByText(text, Integer.SIZE, Integer.SIZE);
    }
    
    
}
