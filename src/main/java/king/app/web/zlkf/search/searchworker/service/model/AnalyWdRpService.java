/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.service.model;

import java.util.Date;
import king.app.web.zlkf.search.searchworker.model.bean.AnalyWordRecord;
import king.app.web.zlkf.search.searchworker.model.bean.AnalyWordRelationship;
import king.app.web.zlkf.search.searchworker.model.bean.EntryItem;
import king.app.web.zlkf.search.searchworker.model.jpa.AnalyWdRdRpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author king
 */
@Service
public class AnalyWdRpService {
    
    @Autowired
    private AnalyWdRdRpRepository repository;
    
    /**
     * 
     * @param record
     * @param entryItem
     * @return 
     */
    public AnalyWordRelationship newObj( AnalyWordRecord record , EntryItem entryItem){
        AnalyWordRelationship relationship = new AnalyWordRelationship();
        
        Date date = new Date();
        Long current = date.getTime();
        
        relationship.id = current;
        relationship.createTime = current;
        relationship.modifyTime = current ;
        
        relationship.analyWordRecordId = record.id;
        relationship.entryItemId = entryItem.id;
        
        AnalyWordRelationship newRelationship = this.repository.save(relationship);
        
        return newRelationship;
    }
    
}
