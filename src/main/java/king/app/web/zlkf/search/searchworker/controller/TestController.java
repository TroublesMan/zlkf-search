/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.controller;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.sql.DataSource;
import king.app.web.zlkf.search.searchworker.model.jpa.EntryItemRepository;
import king.app.web.zlkf.search.searchworker.service.model.EntryItemService;
import king.app.web.zlkf.search.searchworker.model.bean.EntryItem;
import king.app.web.zlkf.search.searchworker.model.bean.es.EntryItemEs;
import king.app.web.zlkf.search.searchworker.service.elasticsearch.ElshCreateService;
import king.app.web.zlkf.search.searchworker.service.elasticsearch.ESearchService;
import king.app.web.zlkf.search.searchworker.service.elasticsearch.ElshSearchService;
import king.app.web.zlkf.search.searchworker.service.elasticsearch.comm.ESearchResponseObj;
import king.app.web.zlkf.search.searchworker.service.elasticsearch.comm.action.SearchResponseObj;
import king.app.web.zlkf.search.searchworker.service.model.AnalyWdRdService;
import king.app.web.zlkf.search.searchworker.service.model.AnalyWdService;
import king.app.web.zlkf.search.searchworker.service.obj.CreateEntryItemObj;
import king.app.web.zlkf.search.searchworker.service.redis.RedisService;
import king.app.web.zlkf.search.searchworker.service.search.SearchService;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author king
 */
@RestController
@RequestMapping("api/test")
public class TestController {

    @Autowired
    private EntryItemRepository entryItemRepository;

    @Autowired
    private EntryItemService entryItemService;

    @Autowired
    private AnalyWdService analyService;

    private final static String HELLO_WORLD_STRING = "helloworld";

    @RequestMapping("helloworld")
    public String Helloworld() {
        return HELLO_WORLD_STRING;
    }

    @RequestMapping("entryItem/all")
    public Object entryItemAll() {
        return this.entryItemRepository.findAll();
    }

    /**
     * 根据对应的 text 进行搜索
     *
     * @param text
     * @return
     */
    @RequestMapping("entry/search")
    public Object entrySearchText(String text) {
        return this.entryItemService.searchByText(text, null, null);
    }

    @Autowired
    private DataSource datasource;

    //测试对应的DataSource
    @RequestMapping("datasource")
    public Object datasource() {
        return this.datasource.toString();
    }

    @Autowired
    private RedisService redis;

    @RequestMapping("redis/set/{key}/{val}")
    public Object redisSet(@PathVariable("key") String key, @PathVariable("val") String val) {
        EntryItem entry = new EntryItem();
        entry.title = val;
        this.redis.put(key, entry,Duration.ofDays(1));
        return entry;
    }

    @RequestMapping("redis/get/{key}")
    public Object redisGet(@PathVariable("key") String key) {
        Object result = this.redis.get(key, EntryItem.class);
        return result;
    }

    @RequestMapping("analy/wd")
    public Object analyWd(String str) {
        if (str == null) {
            return "this is a error ";
        }

        return this.analyService.analyWord(str);
    }

    @Autowired
    private AnalyWdRdService analyWordRecordService;

    @RequestMapping("insert/rd")
    public Object insert_rd(String str) {

        if (str == null) {
            return "this is a error ";
        }

        Set<String> strSet = this.analyService.analyWord(str);

        return this.analyWordRecordService.searchOrInsert(strSet);

    }

    @Autowired
    private RestHighLevelClient esClient;

    @Autowired
    private ESearchService eSearchService;

    @Resource(name = "origin_rest_template")
    private RestTemplate originRestTemplate;

    @RequestMapping("es/query/all")
    public Object es_query_all() throws IOException {
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("title", "title");
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("test");
        searchRequest.types("docu");

        sourceBuilder.query(matchQueryBuilder);
        searchRequest.source(sourceBuilder);

        SearchResponse searchResponse = this.esClient.search(searchRequest);

        SearchHits searchHits = searchResponse.getHits();
        //得到结果
        SearchHit searchHitArr[] = searchHits.getHits();
        StringBuilder builder = new StringBuilder();

        for (SearchHit searchHit : searchHitArr) {
            String sourceStr = searchHit.getSourceAsString();
            builder.append(sourceStr);
            builder.append("\n");
        }
        return builder.toString();
    }

    @RequestMapping("es/query/all/service")
    public Object es_query_all_service() {
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("title", "title");
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("test");
        searchRequest.types("docu");
        sourceBuilder.query(matchQueryBuilder);
        searchRequest.source(sourceBuilder);
        try {
            //输出对应的信息
            SearchResponseObj searchResponseObj = this.eSearchService.search(searchRequest);
            return searchResponseObj;
        } catch (IOException iOException) {
            return iOException.getMessage();
        }
    }

    @RequestMapping("es/query/insert/{id}")
    public Object es_query_insert_id(@PathVariable("id") String id) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("title", "this is a title insert have");
        map.put("content", "yes this is a title");
        IndexRequest indexRequest = new IndexRequest("test", "docu", id.toString());
        indexRequest = indexRequest.source(map, XContentType.JSON);
        try {
            Object obj = this.eSearchService.saveOrUpdate(indexRequest);
            return obj;
        } catch (IOException ioe) {
            return ioe.toString();
        }
    }

    @RequestMapping("es/query/del/{id}")
    public Object es_query_del_id(@PathVariable("id") String id) {
        DeleteRequest deleteRequest = new DeleteRequest("test", "docu", id);
        try {
            Object obj = this.eSearchService.delete(deleteRequest);
            return obj;
        } catch (Exception ex) {
            return ex.toString();
        }
    }

    @RequestMapping("es/query/get/{id}")
    public Object es_query_get_id(@PathVariable("id") String id) {
        GetRequest deleteRequest = new GetRequest("test", "docu", id);
        try {
            Object obj = this.eSearchService.get(deleteRequest);
            return obj;
        } catch (Exception ex) {
            return ex.toString();
        }
    }

    @RequestMapping("es/query/all/rest")
    public Object es_query_all_rest() {
        String httpStr = "http://193.112.51.74:9200/test/docu/2";
        String result = this.originRestTemplate.getForObject(httpStr, String.class);
        return result;
    }

    @Autowired
    private ElshCreateService elshCreateService;

    @RequestMapping("es/query/insert/entryItem/new")
    public Object es_query_insert_entryItem(String title, String introduction) {
        EntryItem entryItem = new EntryItem();
        this.entryItemService.newEntryItem(entryItem);
        entryItem.title = title;
        entryItem.introduction = introduction;
        entryItem.url = "http://www.baidu.com";
        CreateEntryItemObj createEntryItemObj = new CreateEntryItemObj(entryItem, null);
        EntryItemEs entryItemEs;
        try {
            entryItemEs = this.elshCreateService.createEntryItemEs(createEntryItemObj);
            return entryItemEs;
        } catch (Exception ex) {
            return "exception is :\t" + ex.toString();
        }
    }

    @Autowired
    private ElshSearchService elshSearchService;
    
    @Autowired
    private SearchService searchService;
    
    @RequestMapping("/es/search/t/ext")
    public Object es_query_search_entryItem( String text ) throws IOException{
        List<EntryItem> entryItemEs = this.searchService.searchEntryByText(text, 0,10);
        return entryItemEs;
    }
}
