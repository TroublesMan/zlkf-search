/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.service.elasticsearch.comm.action;

import king.app.web.zlkf.search.searchworker.service.elasticsearch.comm.ESearchResponseObj;
import org.elasticsearch.action.ActionResponse;

/**
 *
 * @author king
 */
public class ActionResponseObj<T extends ActionResponse> extends ESearchResponseObj<T> {
    
    public ActionResponseObj(T response) {
        super(response);
    }
    
}
