/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.service.model;

import king.app.web.zlkf.search.searchworker.model.bean.Login;
import king.app.web.zlkf.search.searchworker.model.bean.User;
import king.app.web.zlkf.search.searchworker.model.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author king
 */
@Service
public class UserService{
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserService self;
    
    public boolean insertUser( User user ){
        User _user = this.userRepository.save(user);
        return _user != null;
    }
    
    public User newUser( Login login ){
        User user = new User();
        user.id = login.id;
        user.createTime = login.createTime;
        user.modifyTime = login.createTime;
        user.name = login.id.toString();
        return user;
    }

}
