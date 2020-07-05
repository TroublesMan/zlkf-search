/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.service.elasticsearch;

import king.app.web.zlkf.search.searchworker.model.bean.es.EntryItemEs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author king
 */
@Service
public class ElshDeleteService {
    
    //对应 es 对应的容器
    @Autowired
    private ESearchService eSearchService;
    
}
