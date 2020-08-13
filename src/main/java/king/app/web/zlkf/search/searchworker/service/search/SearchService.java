/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.service.search;

import king.app.web.zlkf.search.searchworker.service.elasticsearch.ElshSearchService;
import java.util.List;
import king.app.web.zlkf.search.searchworker.model.bean.EntryItem;
import king.app.web.zlkf.search.searchworker.model.bean.EntrySearchHistory;
import king.app.web.zlkf.search.searchworker.model.bean.es.EntryItemEs;
import king.app.web.zlkf.search.searchworker.service.async.AsyncService;
import king.app.web.zlkf.search.searchworker.service.async.AsyncTask;
import king.app.web.zlkf.search.searchworker.service.model.EntrySearchHisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author king
 */
@Service
public class SearchService {

    @Autowired
    private MysqlSearchService mysqlSearchService;

    @Autowired
    private ElshSearchService elshSearchService;

    @Autowired
    private EntrySearchHisService entrySearchHisService;

    @Autowired
    private AsyncService asyncService;

    /**
     * 默认使用 es 来得到结果 。 若 es 出现问题 ， 则使用 mysql 进行操作问题。
     *
     * @param text
     * @param pageNum
     * @param pageSize
     * @return
     */
    public List<EntryItem> searchEntryByText(String text, Integer pageNum, Integer pageSize) {
        List<EntryItem> resultEntryItem = null;
        List<EntryItemEs> esEntryList = null;
        try {
            esEntryList = this.elshSearchService.searchEntryByText(text, pageNum, pageSize);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        //之后便根据对应的信息来得到对应的mysql信息
        if (esEntryList != null) {
            resultEntryItem = this.mysqlSearchService.searchEntryByEs(esEntryList);
        } else {
            resultEntryItem = this.mysqlSearchService.searchEntryByText(text, pageNum, pageSize);
        }
        this.asyncService.async(new AsyncTask() {

            @Override
            public Boolean apply(WebApplicationContext t) {
                EntrySearchHistory entrySearchHistory = entrySearchHisService.writeSearchHis(text);
                return entrySearchHistory != null;
            }
            
        });

        //将搜索文本进行保存，当然，这里的输入信息，应该调整为对应的异步处理状态
        return resultEntryItem;
    }
    
    /**
     * 暂时只由MYSQL 数据持久层来进行操作
     * @param text
     * @return 
     */
    public Long countByText( String text ){
        return this.mysqlSearchService.countByText(text);
    }
    
    /**
     * 根据用户当前输入的信息，然后返回推荐语句
     * @param text
     * @return 
     */
    public List<String> recommend( String text ){
        return this.mysqlSearchService.searchByTextInput(text);
    }
}
