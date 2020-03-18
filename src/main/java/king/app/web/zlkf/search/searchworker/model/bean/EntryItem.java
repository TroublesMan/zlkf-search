/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.model.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author king
 */
@Entity
@Table(name = "entry_item")
public class EntryItem implements Serializable{

    @Id
    @Column(name="id")
    public Long id; 
    
    @Column(name="title")
    public String title = null; // title
    
    
    @Column(name = "introduction")
    public String introduction = null;

    
    @Column(name = "url")
    public String url = null;
    
    @Column( name="create_time")
    public Date createTime = null;
    
    
    @Column( name ="modify_time")
    public Date modifyTime = null; 
    
    public final static String KEY_ID_FIELD_STRING = "id";
    public final static String KEY_TITLE_FIELD_STRING = "title";
    public final static String KEY_INTRODUCTION_FIELD_STRING = "introduction";
    public final static String KEY_URL_FIELD_STRING = "url";

}
