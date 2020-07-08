/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.service.async;

import king.app.web.zlkf.search.searchworker.service.AppControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author king
 */
@Service
public class AsyncService extends Thread {

    @Autowired
    private AsyncContainerService asyncContainer;

    @Autowired
    private AsyncHandlerService asyncHandlerService;
    
    @Autowired
    private AppControlService appControlService;

    @Autowired
    private AsyncService self;

    //是否打开异步状态的开关标识符
    private boolean asyncSwitch = false;

    /**
     * 判断当前的异步状态按钮是否已经打开
     *
     * @return
     */
    public boolean isAllowAsync() {
        return this.appControlService.isAllowAsync();
    }

    /**
     * 将对应的处理信息
     *
     * @param task
     */
    public void async( AsyncTask task ) {

        Boolean isAllow = self.isAllowAsync();
        
        if (isAllow) {
            //将对应的信息来进行存储信息
            this.asyncContainer.add(task);
        } else {
            //若不允许，则直接运行程序
            this.asyncHandlerService.apply(task);
        }
        
    }

    @Override
    public void run() {
        try {
            while (true) {
                this.asyncHandlerService.run();
                Thread.sleep(1000L);
            }
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
            interrupted();
        }
    }
    
    @Override
    public void start(){
        Boolean isAllow = self.isAllowAsync();
        //只有isAllow 为 ture， 则才可以直接运行
        if( isAllow ){
            super.start();
        }
    }

}
