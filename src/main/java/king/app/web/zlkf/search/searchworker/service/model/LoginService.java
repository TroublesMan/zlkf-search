/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.service.model;

import java.util.Optional;
import king.app.web.zlkf.search.searchworker.model.bean.Login;
import king.app.web.zlkf.search.searchworker.model.bean.User;
import king.app.web.zlkf.search.searchworker.model.jpa.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
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
    
    @Autowired
    private BeanService beanService;
    
    public Login register( Login login ){
        Long current = System.currentTimeMillis();
        
        login.createTime = current;
        login.modifyTime = current;
        Long id = this.beanService.id( login , current );
        login.id = id;
        
        Login _login = this.loginRepository.saveAndFlush(login );
        boolean insertLoginBool =  _login  != null;
        if( insertLoginBool ){
            User user = new User();
            user.id = login.id;
            this.userService.insertUser(user);
        }
        
        return _login;
    }
    
    /**
     * 暂时将各式各样的登录方式总结在一起
     * @param login
     * @return 
     */
    public Login signIn( Login login ){
        Optional<Login> optional =  this.loginRepository.findOne(Example.of( login ));
        return optional.get();
    }
}
