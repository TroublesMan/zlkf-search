/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.model.service;

import java.util.Date;
import king.app.web.zlkf.search.searchworker.model.bean.EntrySearchHistory;
import king.app.web.zlkf.search.searchworker.model.jpa.EntrySearchHisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 *
 * @author king
 */
@Service
public class EntrySearchHisService {
    
    @Autowired
    private EntrySearchHisRepository hisRepository;
    
    @Autowired
    private JdbcTemplate originJdbcTemplate;
    
    /**
     * 新建 ， 查看是否对应的 信息是否为空 ， 倘若为空的话
     * @param content
     * @return 
     */
    public EntrySearchHistory writeSearchHis( String content ){
        Long count = new Long(1);
        EntrySearchHistory history = this.hisRepository.getEntryHisByContent(content);
        if( history == null ){
            //创建新的 history 记录
            Date current = new Date();
            history = new EntrySearchHistory();
            history.id  = current.getTime();
            history.content = content;
            history.createTime = current;
            history.searchCount = count;
            history = this.hisRepository.save( history );
            
            return history;
        }
        
        Long currentCount = history.searchCount;
        
        history.searchCount = currentCount + count;
        
        this.updateHisCountById( history.id, currentCount ,  history.searchCount);

        return history;
        
    }
    
    
    public int updateHisCountById( Long id  , Long currentCount , Long searchCount ){
        String sql = "UPDATE entry_search_history esh SET esh.search_count = ? WHERE esh.id= ? AND esh.search_count = ?";
        int updateNumber =  this.originJdbcTemplate.update(sql, searchCount , id , currentCount);
        return updateNumber;
    }
    
}
