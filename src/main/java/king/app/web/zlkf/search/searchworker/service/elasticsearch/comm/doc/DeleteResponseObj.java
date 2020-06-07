/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.service.elasticsearch.comm.doc;

import org.elasticsearch.action.delete.DeleteResponse;

/**
 *
 * @author king
 */
public class DeleteResponseObj extends DocWriteResponseObj<DeleteResponse>{
    
    
    public DeleteResponseObj(DeleteResponse response) {
        super(response);
    }
    
}
