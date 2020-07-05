/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.service;

import javax.transaction.Transactional;
import king.app.web.zlkf.search.searchworker.model.bean.EntryItem;
import king.app.web.zlkf.search.searchworker.model.bean.es.EntryItemEs;
import king.app.web.zlkf.search.searchworker.service.elasticsearch.ElshCreateService;
import king.app.web.zlkf.search.searchworker.service.model.EntryItemService;
import king.app.web.zlkf.search.searchworker.service.obj.CreateEntryItemObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 *
 * @author king
 */
@Service
public class CreateService {

    @Autowired
    private EntryItemService entryItemService; // 对应的 entryItem 的数据信息

    @Autowired
    private ElshCreateService elshCreateService;

    /**
     *
     * @param entryItem
     * @return
     * PS. 需要注意的逻辑， 本次操作最重要的便是，将数据插入到mysql 。
     * 若其他的出现错误，那么本次的操作并不会出现错误。但是会记录下这个操作。
     * 之后再进行一次操作。
     */
    @Transactional( rollbackOn = {Exception.class})
    public EntryItem createEntryItem(EntryItem entryItem) {
        try {
            
            CreateEntryItemObj createEntryObj = this.entryItemService.createEntryItem(entryItem);
            EntryItemEs entryItemEs = this.elshCreateService.createEntryItemEs(createEntryObj);
            //得到返回的结果
            return createEntryObj.entryItem;
        } catch (Exception ioe) {
            //如果出现错误，则需要直接回滚。
            System.out.println( );
            //目前若，出现错误，则直接回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return null;
        }
    }

}
