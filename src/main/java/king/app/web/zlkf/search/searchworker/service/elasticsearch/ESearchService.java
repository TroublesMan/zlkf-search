/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.service.elasticsearch;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import king.app.web.zlkf.search.searchworker.model.bean.es.EntryItemEs;
import king.app.web.zlkf.search.searchworker.service.elasticsearch.comm.doc.DeleteResponseObj;
import king.app.web.zlkf.search.searchworker.service.elasticsearch.comm.ESearchResponseObj;
import king.app.web.zlkf.search.searchworker.service.elasticsearch.comm.action.GetResponseObj;
import king.app.web.zlkf.search.searchworker.service.elasticsearch.comm.doc.IndexResponseObj;
import king.app.web.zlkf.search.searchworker.service.elasticsearch.comm.action.SearchResponseObj;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author king
 */
@Service
public class ESearchService {
    
    @Autowired
    private ESearchLinkStruct linkStruct;
    
    @Autowired
    private RestClientBuilder builder;
    
    @Autowired
    private RestHighLevelClient elasticSearchCli;
    
    @Autowired
    private ESearchService self;
    
    public boolean isAlive(){
        return true;
    }
    
    /**
     * 根据对应的输出值
     * @param searchRequest
     * @return
     * @throws IOException 
     */
    public SearchResponseObj search( SearchRequest searchRequest ) throws IOException{
        SearchResponse response  = this.elasticSearchCli.search(searchRequest);
        SearchResponseObj responseObj = new SearchResponseObj(response);
        return responseObj;
    }
    /**
     * 整合对应的信息，之后默认整合成对应的信息
     * 
     * @param <T>
     * @param searchRequest
     * @param targetClass
     * @return 
     * @throws java.io.IOException 
     */
    public <T> List<T> searchForList( SearchRequest searchRequest ,Class<T> targetClass ) throws IOException{
        SearchResponseObj searchResponseObj = self.search(searchRequest);
        SearchResponse searchResponse = searchResponseObj.originSearchResponse;
        //得到对应的信息
        
        SearchHits searchHits = searchResponse.getHits();
        Long totalHits = searchHits.getTotalHits();
        // 得到对应的结果
        Iterator<SearchHit> iterator = searchHits.iterator();
        List<T> itemEsList = new ArrayList<T>(totalHits.intValue());
        //将内部的信息进行输出
        while( iterator.hasNext() ){
            //下面开始分析每一次的结果
            SearchHit searchHit = iterator.next();
            String hitString = searchHit.getSourceAsString();
            T item = JSONObject.parseObject(hitString, targetClass);
            itemEsList.add(item);
        }
        
        return itemEsList;
    }
    
    /**
     * 
     * @param indexRequest
     * @return
     * @throws IOException 
     */
    public IndexResponseObj saveOrUpdate( IndexRequest indexRequest ) throws IOException{
        IndexResponse indexResponse = this.elasticSearchCli.index(indexRequest);
        IndexResponseObj indexResponseObj = new IndexResponseObj( indexResponse );
        return indexResponseObj;
    }
    
    /**
     * 根据对应的参数直接生成对应的信息
     * @param index
     * @param type
     * @param id
     * @param obj
     * @return 
     */
    public IndexResponseObj saveOrUpdateByObj( String index ,String type , String id , Object obj ) throws IOException{
        
        IndexRequest indexRequest = new IndexRequest(index , type , id );
        //转化为对应的obj json 字符串
        String objJsonStr = JSONObject.toJSONString(obj);
        indexRequest.source(objJsonStr, XContentType.JSON);
        return this.saveOrUpdate(indexRequest);
    }
    
    public DeleteResponseObj delete( DeleteRequest deleteRequest) throws IOException{
        DeleteResponse deleteResponse = this.elasticSearchCli.delete(deleteRequest);
        DeleteResponseObj deleteResponseObj =  new DeleteResponseObj(deleteResponse);
        return deleteResponseObj;
    }
    
    public GetResponseObj get( GetRequest getRequest ) throws IOException{
        GetResponse getresponse = this.elasticSearchCli.get(getRequest);
        GetResponseObj getResponseObj = new GetResponseObj(getresponse);
        return getResponseObj;
    }
    
    
}
