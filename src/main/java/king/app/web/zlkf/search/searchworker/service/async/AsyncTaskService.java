/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.service.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author king
 */
@Service
public class AsyncTaskService {
    
    @Autowired
    private WebApplicationContext applicationContext;
    
    /**
     * asyncTask 一次任务状态之下进行的操作
     * @param task
     * @return 
     */
    public Boolean apply(AsyncTask task) {
        return task.apply( this.applicationContext );
    }

}
