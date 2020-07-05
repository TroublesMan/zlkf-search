/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.service.elasticsearch;

import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import king.app.web.zlkf.search.searchworker.model.bean.es.EntryItemEs;
import king.app.web.zlkf.search.searchworker.service.ElshBeanService;
import king.app.web.zlkf.search.searchworker.service.elasticsearch.comm.action.SearchResponseObj;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.WildcardQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
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
    
    @Autowired
    private ElshBeanService elshBeanService;
    
    /**
     * 根据 es 部分来进行查询结果
     * @param text
     * @param pageNum
     * @param pageSize
     * @return 
     */
    public List<EntryItemEs> searchEntryByText( String text , Integer pageNum , Integer pageSize) throws IOException{
        //先进性试探性的判断是否会进行抛出异常
        if( !eSearchService.isAlive() ){
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
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        
        //设置 index 与 type
        String esIndex = this.elshBeanService.getElshIndex(pageSize);
        String esType = this.elshBeanService.targetElshType(EntryItemEs.class);
        searchRequest.indices(esIndex).types(esType);
        
        int esFrom = pageNum * pageSize;
        int esSize = pageSize;
        
        searchSourceBuilder.from( esFrom );
        searchSourceBuilder.size( esSize );
        
        //下面对text 进行分析 
        int textLen = text.length();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        // 对应标题与介绍的信息
        BoolQueryBuilder titleBoolQuery = QueryBuilders.boolQuery();
        BoolQueryBuilder introductionBoolQuery = QueryBuilders.boolQuery();
        for( int index = 0 ; index < textLen ; index++ ){
            String currentTextChar = text.substring(index,index+1);
            titleBoolQuery.must(QueryBuilders.wildcardQuery("title", currentTextChar));
            introductionBoolQuery.must(QueryBuilders.wildcardQuery("introduction", currentTextChar));
        }
        //下面整合信息
        boolQueryBuilder.should(titleBoolQuery).should(introductionBoolQuery);
        searchSourceBuilder.query( boolQueryBuilder);
        
        
        searchRequest.source(searchSourceBuilder);
        
        List<EntryItemEs> entryItemEs = this.eSearchService.searchForList(searchRequest, EntryItemEs.class);
        
        return entryItemEs;
    }
    
    
}
