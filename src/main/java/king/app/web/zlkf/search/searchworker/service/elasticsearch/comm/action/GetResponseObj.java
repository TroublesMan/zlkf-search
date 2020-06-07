/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.service.elasticsearch.comm.action;

import java.util.Map;
import org.elasticsearch.action.get.GetResponse;

/**
 *
 * @author king
 */
public class GetResponseObj extends ActionResponseObj<GetResponse> {
    
    public final Map<String,Object> obj;
    
    public final String id;
    
    public final String index;
    
    public final String type;
    
    public final Long version;
    
    
    public GetResponseObj(GetResponse response) {
        super(response);
        
        if( response == null ){
            this.id = null;
            this.index = null;
            this.type = null;
            this.version = null;
            this.obj = null;
            
            return;
        } 
        
        String id = response.getId();
        String index = response.getIndex();
        String type = response.getType();
        Long version = response.getVersion();
        
        this.id = id;
        this.index = index;
        this.type = type;
        this.version = version;
        
        this.obj = response.getSourceAsMap();
    }
    
}
