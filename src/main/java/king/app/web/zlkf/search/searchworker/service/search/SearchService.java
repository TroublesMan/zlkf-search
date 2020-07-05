/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.service.search;

import king.app.web.zlkf.search.searchworker.service.elasticsearch.ElshSearchService;
import java.util.List;
import king.app.web.zlkf.search.searchworker.model.bean.EntryItem;
import king.app.web.zlkf.search.searchworker.model.bean.es.EntryItemEs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        try {
            List<EntryItemEs> esEntryList = this.elshSearchService.searchEntryByText(text, pageNum, pageSize);
            //之后便根据对应的信息来得到对应的mysql信息
            if (esEntryList != null) {
                resultEntryItem = this.mysqlSearchService.searchEntryByEs(esEntryList);
                return resultEntryItem;
            }
            
        } catch (Exception ex) {
            System.out.println( ex.getMessage());
        }
        resultEntryItem = this.mysqlSearchService.searchEntryByText(text, Integer.SIZE, Integer.SIZE);
        return resultEntryItem;
    }
}
