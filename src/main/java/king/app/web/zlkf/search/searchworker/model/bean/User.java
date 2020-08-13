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
import king.app.web.zlkf.search.searchworker.model.bean.anno.TableIdentifier;

/**
 *
 * @author king
 */
@Entity
@Table(name = "user")
@TableIdentifier( identifier = 100)
public class User {
    
    @Id
    public Long id;
    
    @Column(name="name")
    public String name;
    
    @Column(name="create_time")
    public Long createTime;
    
    @Column( name="modify_time")
    public Long modifyTime;
    
}
