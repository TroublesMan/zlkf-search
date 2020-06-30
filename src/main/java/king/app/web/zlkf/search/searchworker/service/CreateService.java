/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.service;

import king.app.web.zlkf.search.searchworker.service.model.EntryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author king
 */
@Service
public class CreateService {
    
    @Autowired
    private EntryItemService entryItemService; // 对应的 entryItem 的数据信息
    
    //插入的方式覆盖
    
}
