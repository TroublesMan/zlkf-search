/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.service.search;

import java.util.List;
import king.app.web.zlkf.search.searchworker.model.bean.EntryItem;
import king.app.web.zlkf.search.searchworker.service.elasticsearch.ESearchService;
import org.elasticsearch.action.search.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 负责es 实际业务的 service层
 * @author king
 */
@Service
public class ElshSearchService {
    
    @Autowired
    private ESearchService eSearchService;
    
    /**
     * 根据 es 部分来进行查询结果
     * @param text
     * @param pageNum
     * @param pageSize
     * @return 
     */
    public List<EntryItem> searchEntryByText( String text , Integer pageNum , Integer pageSize){
        //先进性试探性的判断是否会进行抛出异常
        if( eSearchService.isAlive() ){
            return null;
        }
        /**
         * 之后进行查询 .. 查询的主要逻辑很简单 
         * 主要便是根据目标的 搜索文本，(可能分词，可能不分词）
         * 可能需要的元素：
         * 1.标题
         * 2.内容
         * 3.时间
         * 4.点击量
         * 5.用户反馈（可能暂时不管）
         * 来进行操作
         * 接下来就是要学习，如何在es内设置我们的信息其对应的元素占比量等等
         * 
        **/
        SearchRequest searchRequest = new SearchRequest();
        return null;
        
    }
    
    
}
