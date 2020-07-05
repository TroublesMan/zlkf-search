/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.service.elasticsearch;

import java.io.IOException;
import king.app.web.zlkf.search.searchworker.model.bean.EntryItem;
import king.app.web.zlkf.search.searchworker.model.bean.es.EntryItemEs;
import king.app.web.zlkf.search.searchworker.service.ElshBeanService;
import king.app.web.zlkf.search.searchworker.service.elasticsearch.comm.doc.IndexResponseObj;
import king.app.web.zlkf.search.searchworker.service.obj.CreateEntryItemObj;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.rest.RestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author king
 */
@Service
public class ElshCreateService {

    @Autowired
    private ESearchService eSearchService;
    
    @Autowired
    private ElshBeanService elshBeanService;
    

    /**
     * 根据创造对应的createObj然后来进行得到对应的结果
     * @param createObj
     * @return
     * @throws IOException 
     */
    public EntryItemEs createEntryItemEs( CreateEntryItemObj createObj ) throws IOException{
        //先获取对应的信息值
        EntryItem entryItem = createObj.entryItem;
        //复制对应的信息
        EntryItemEs entryItemEs = new EntryItemEs();
        entryItemEs.clone(entryItem);
        //之后我们准备整合对应的信息
        
        String esIndex = this.elshBeanService.getElshIndex(entryItemEs);
        //对应后期会执行设置
        String esType = this.elshBeanService.targetElshType(entryItemEs.getClass());
        String esId = entryItemEs.id.toString();
        
        IndexResponseObj indexResponseObj = this.eSearchService.saveOrUpdateByObj( esIndex, esType , esId , entryItemEs );
        //之后将对应的信息转化为EntryItemEs
        
        IndexResponse indexResponse = indexResponseObj.originSearchResponse;
        //得到本次交互的结果
        RestStatus restStatus = indexResponse.status();
        //并且得知这次交互的结果是否为成功
        boolean isSuccess = restStatus.equals( RestStatus.OK ) || restStatus.equals( RestStatus.CREATED );
        return isSuccess ? entryItemEs : null;
    }

}
