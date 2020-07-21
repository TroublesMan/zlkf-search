/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.service.model;

import java.util.Date;
import king.app.web.zlkf.search.searchworker.model.bean.EntrySearchHistory;
import king.app.web.zlkf.search.searchworker.model.jpa.EntrySearchHisRepository;
import king.app.web.zlkf.search.searchworker.service.LogSerivce;
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
    private LogSerivce logService;
    
    @Autowired
    private EntrySearchHisRepository hisRepository;
    
    @Autowired
    private JdbcTemplate originJdbcTemplate;
    
    @Autowired
    private EntrySearchHisService self;
    
    /**
     * 新建 ， 查看是否对应的 信息是否为空 ， 倘若为空的话
     * @param content
     * @return 
     */
    public EntrySearchHistory writeSearchHis( String content ){
        
        // 这里是判断对应的参数是否符合标准的地方
        if( content == null || content.trim().equals("")){
            return null;
        }
        
        Long count = new Long(1);
        EntrySearchHistory history = this.hisRepository.getEntryHisByContent(content);
        Date current = new Date();
        if( history == null ){
            //创建新的 history 记录
            history = new EntrySearchHistory();
            history.id  = current.getTime();
            history.content = content;
            history.createTime = current.getTime();
            history.modifyTime = current.getTime();
            history.searchCount = count;
            
            history = this.hisRepository.save( history );
            
            return history;
        }
        
        history.modifyTime = current.getTime();
        Long currentCount = history.searchCount;
        
        history.searchCount = currentCount + count;
        this.logService.println( count + "\t" + history.searchCount);
        
        self.update( history );

        return history;
        
    }
    
    
    public EntrySearchHistory update( EntrySearchHistory history ){
        this.hisRepository.save(history);
        return history;
    }
    
}
