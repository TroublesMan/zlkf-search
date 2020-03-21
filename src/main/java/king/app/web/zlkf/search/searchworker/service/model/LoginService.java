/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.service.model;

import king.app.web.zlkf.search.searchworker.model.bean.Login;
import king.app.web.zlkf.search.searchworker.model.bean.User;
import king.app.web.zlkf.search.searchworker.model.jpa.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 *
 * @author king
 */
@Service
public class LoginService {
    
    @Autowired
    private LoginRepository loginRepository;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    private UserService userService;
    
    public Login register( Login login ){
        Long current = System.currentTimeMillis();
        
        login.createTime = current;
        login.modifyTime = current;
        
        Login _login = this.loginRepository.save( login );
        
        boolean insertLoginBool = _login != null;
        
        if( insertLoginBool ){
            User user = new User();
            user.id = login.id;
            this.userService.insertUser(user);
        }
        return _login;
    }
}
