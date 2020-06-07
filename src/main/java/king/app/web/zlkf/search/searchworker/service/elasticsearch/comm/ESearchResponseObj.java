/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.service.elasticsearch.comm;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;

/**
 *
 * @author king
 */
public class ESearchResponseObj<T> implements Serializable {
    
    @JsonIgnore
    public final T originSearchResponse;
    
    public ESearchResponseObj(T response ){
        this.originSearchResponse = response;
    }
    
}
