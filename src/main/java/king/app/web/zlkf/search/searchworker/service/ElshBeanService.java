/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.service;

import org.springframework.stereotype.Service;

/**
 * 描述对应的关系
 * @author king
 */
@Service
public class ElshBeanService {
    
    private final String currentServerIndex = "zlkf_search";
    
    private final String elshType = null;
    
    private final static String ENTRY_ITEM_ES_TYPE  = "enI";
    
    public String getElshIndex( Object targetObject ){
        return this.currentServerIndex;
    }
    
    public String targetElshType( Class esClass){
        return ENTRY_ITEM_ES_TYPE;
    }
    
    
    
}
