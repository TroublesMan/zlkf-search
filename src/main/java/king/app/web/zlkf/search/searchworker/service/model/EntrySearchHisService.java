/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.service.model;

import java.util.Date;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import king.app.web.zlkf.search.searchworker.model.bean.EntrySearchHistory;
import king.app.web.zlkf.search.searchworker.model.jpa.EntrySearchHisRepository;
import king.app.web.zlkf.search.searchworker.service.LogSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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
    
    /**
     * 让目标用户根据当前的信息 ， 来找到对应的最有可能需要的数据
     * @param text
     * @return 
     */
    public List<EntrySearchHistory> searchByTextInput( String text ){
        Page<EntrySearchHistory> page = this.hisRepository.findAll(new Specification<EntrySearchHistory>() {
            @Override
            public Predicate toPredicate(Root<EntrySearchHistory> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                return cb.like( root.get("content"), "%" + text + "%");
            }
        }, new PageRequest(0, 10,Sort.by(Sort.Order.desc("search_count"))));
        
        List<EntrySearchHistory> resList = page.getContent();
        return resList;
    }
    
    
    public EntrySearchHistory update( EntrySearchHistory history ){
        this.hisRepository.save(history);
        return history;
    }
    
}
