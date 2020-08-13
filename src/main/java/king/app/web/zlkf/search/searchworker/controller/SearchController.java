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
    
    @RequestMapping("text/count")
    public Object countByText( String text ){
        return this.searchService.countByText(text);
    }
    
    /**
     * 根据对方用户输入的 input 字符串来进行的 提示操作
     * PS:这里可能需要与前端的缓存机制互相配合，
     * 来构建较强的可用性操作
     * @param text
     * @return 
     */
    @RequestMapping("text/recommend")
    public Object recommend( String text ){
       return this.searchService.recommend(text);
    }
    
}
