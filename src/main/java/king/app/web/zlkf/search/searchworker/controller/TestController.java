/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.controller;

import king.app.web.zlkf.search.searchworker.controller.model.jpa.EntryItemRepository;
import king.app.web.zlkf.search.searchworker.controller.rest.EntryItemController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author king
 */
@RestController
@RequestMapping("api/test")
public class TestController {
    
    @Autowired
    private EntryItemRepository entryItemRepository;
    
    private final static String HELLO_WORLD_STRING = "helloworld";
    
    @RequestMapping("helloworld")
    public String Helloworld(){
        return HELLO_WORLD_STRING;
    }
    
    @RequestMapping("entryItem/all")
    public Object entryItemAll(){
        return this.entryItemRepository.findAll();
    }
    
}
