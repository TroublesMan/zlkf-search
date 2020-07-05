/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.controller;

import king.app.web.zlkf.search.searchworker.service.elasticsearch.ElshSearchService;
import king.app.web.zlkf.search.searchworker.service.search.MysqlSearchService;
import king.app.web.zlkf.search.searchworker.service.search.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author king
 */
@RestController
@RequestMapping("api/search")
public class SearchController {
    
    @Autowired
    private SearchService searchService;
    
    @RequestMapping("text")
    public Object searchByText( String text , Integer pageNum , Integer pageSize ){
        return this.searchService.searchEntryByText(text, pageNum, pageSize);
    }
    
}
