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
public class AsyncHandlerService {
    
    @Autowired
    private AsyncContainerService asyncContainerService;
    
    @Autowired
    private AsyncHandlerService self;
    
    @Autowired
    private AsyncTaskService asyncTaskService;
    
    private final Integer MAX_TASK_RUN_COUNT = 3;
    
    /**
     * 
     * 该函数内的内容主要的意义：
     * 异步工作程序的逻辑代码
     * 
     */
    public void run(){
        //下面的信息，是一次便利时进行处理的信息的操作
        if( this.asyncContainerService.isEmpty() ){
            return ;
        }
        
        AsyncTask asyncTask = this.asyncContainerService.pop();
        System.out.println("开始执行任务");
        // 当前任务被执行的次数
        int currentTaskCount = 0;
        while ( !  self.apply(asyncTask) ) {
            //执行任务出现错误，则执行任务次数 + 1 ， 若当前次数超过三次，则重新进行操作
            if( ++currentTaskCount > MAX_TASK_RUN_COUNT ){
                //将结果存放到队列的最后 
                this.asyncContainerService.add(asyncTask);
                break;
            }
        }
    }
    
    /**
     * asyncTask 一次任务状态之下进行的操作
     * @param task
     * @return 
     */
    public Boolean apply(AsyncTask task) {
        return this.asyncTaskService.apply(task);
    }
    
}
