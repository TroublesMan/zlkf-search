/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.model.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author king
 */
@Entity
@Table(name = "login")
public class Login {
    
    @Id
    public Long id;
    
    @Column(name="account")
    public String account;
    
    @Column(name="pwd")
    public String pwd;
    
    @Column(name="create_time")
    public Long createTime;
    
    @Column(name="modify_time")
    public Long modifyTime;
    
    
}
