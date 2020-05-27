/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.model.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author king
 */
@Entity
@Table(name = "analy_word_record")
public class AnalyWordRecord implements Serializable {
    
    @Id
    @Column(name="id")
    public Long id; 
    
    @Column(name="word")
    public String word = null; // word
    
    @Column(name="create_time")
    public Long createTime = null;
    
    @Column( name = "modify_time")
    public Long modifyTime = null;
    
    
}
