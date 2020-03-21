/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.controller;

import king.app.web.zlkf.search.searchworker.model.bean.Login;
import king.app.web.zlkf.search.searchworker.service.model.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author king
 */
@RestController
@RequestMapping("api/login")
public class LoginController {
    
    @Autowired
    private LoginService loginService;
    
    @RequestMapping("register")
    public Object register( Login login ){
        return this.loginService.register(login);
    }
}
