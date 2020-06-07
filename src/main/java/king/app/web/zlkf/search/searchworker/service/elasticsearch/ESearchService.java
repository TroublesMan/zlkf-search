/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.service.elasticsearch;

import java.io.IOException;
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
        indexRequest.source( obj );
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
