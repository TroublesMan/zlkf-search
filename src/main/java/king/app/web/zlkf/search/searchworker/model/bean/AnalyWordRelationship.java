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
import king.app.web.zlkf.search.searchworker.model.bean.anno.TableIdentifier;

/**
 *
 * @author king
 */
@Entity
@Table(name = "analy_word_relationship")
@TableIdentifier( identifier = 104)
public class AnalyWordRelationship implements Serializable {

    @Id
    @Column(name = "id")
    public Long id;

    @Column(name = "analy_word_record_id")
    public Long analyWordRecordId = null;

    @Column(name = "entry_item_id")
    public Long entryItemId = null;

    @Column(name = "create_time")
    public Long createTime = null;

    @Column(name = "modify_time")
    public Long modifyTime = null;
}
