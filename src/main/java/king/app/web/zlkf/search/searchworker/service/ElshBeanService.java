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
    
    public String getElshIndex( Object targetObject ){
        return this.currentServerIndex;
    }
    
    public String targetElshType( Object targetObject ){
        Class targetClass = targetObject.getClass();
        return targetClass.toString();
    }
    
    
    
}
