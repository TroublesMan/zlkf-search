/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.service.elasticsearch.comm.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import king.app.web.zlkf.search.searchworker.service.elasticsearch.comm.ESearchResponseObj;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

/**
 * 目前 我们暂时只管理输出的信息
 * @author king
 */
public class SearchResponseObj extends ActionResponseObj<SearchResponse> {
    
    public final List<Map<String,Object>> list;
    
    public SearchResponseObj( SearchResponse response ){
        
        super(response);
        
        if( response == null ){
            this.list = null;
            return;
        }
        
        //甚至对应的信息
        SearchHits searchHits = response.getHits();
        SearchHit searchHitArr[] = searchHits.getHits();
        int searchHitArrLen = searchHitArr.length;
        this.list = new ArrayList<Map<String,Object>>(searchHitArrLen);
        
        for( SearchHit searchHitItem : searchHitArr ){
            //获取对应的信息
            String id = searchHitItem.getId();
            Float score = searchHitItem.getScore();
            Map<String,Object> searchHitMap = searchHitItem.getSourceAsMap();
            //然后输入对应的信息
            searchHitMap.put("id", id);
            searchHitMap.put("score", score);
            this.list.add(searchHitMap);
        }
        
        
        
        
    }
    
    
}
