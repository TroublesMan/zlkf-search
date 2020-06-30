/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.service.elasticsearch.comm.doc;

import king.app.web.zlkf.search.searchworker.service.elasticsearch.comm.ESearchResponseObj;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.rest.RestStatus;

/**
 *
 * @author king
 * @param <T>
 */
public class DocWriteResponseObj <T extends DocWriteResponse>  extends ESearchResponseObj<T>{
    
    public final String id;
    
    public final String index;
    
    public final String type;
    
    public final Long version;
    
    public final String result;
    
    public DocWriteResponseObj(T response) {
       super(response);
        
        if( response == null ){
            this.id = null;
            this.index = null;
            this.type = null;
            this.version = null;
            this.result = null;
            return;
        } 
        
        String id = response.getId();
        String index = response.getIndex();
        String type = response.getType();
        Long version = response.getVersion();
        String result = response.getResult().getLowercase();
        
        this.id = id;
        this.index = index;
        this.type = type;
        this.version = version;
        this.result = result;
    }
    
    /**
     * 当前的状态是否成功
     * @return 
     */
    public boolean statusSuccess(){
        if( this.originSearchResponse != null ){
            return false;
        }
        RestStatus restStatus = this.originSearchResponse.status();
        return restStatus.equals( RestStatus.OK ) || restStatus.equals( RestStatus.CREATED );
    }
    
}
