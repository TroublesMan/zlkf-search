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
import king.app.web.zlkf.search.searchworker.model.bean.EntryItem;
import king.app.web.zlkf.search.searchworker.model.jpa.EntryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 *
 * @author king
 */
@Service
public class EntryItemService {
    
    @Autowired
    private EntryItemRepository entryItemRepository;
    
    @Autowired
    private JdbcUtilService jdbcUtils;
    
    @Autowired
    private EntrySearchHisService entrySearchHisService;
    
    /**
     *  根据对应的一个字符串进行搜索出对应的长度
     * @param text
     * @param pageNum
     * @param pageSize
     * @return 
     */
    public List<EntryItem> searchByText( String text  , Integer pageNum , Integer pageSize ){
        String pattenText = this.jdbcUtils.buildLikeStr( text );
        
        //当前的页数
        if( pageNum == null ){
            pageNum = 0;
        }
        
        //如同一个页面的大小
        if( pageSize == null ){
            pageSize = 10;
        }
        
        Pageable pageable = new PageRequest(pageNum, pageSize, Sort.by(Sort.Direction.DESC, "modifyTime"));
        
        Page<EntryItem> items = this.entryItemRepository.findAll(new Specification<EntryItem>() {
            @Override
            public Predicate toPredicate(Root<EntryItem> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
               Predicate idLike = cb.like(root.get(EntryItem.KEY_TITLE_FIELD_STRING), pattenText );
               Predicate introLike = cb.like(root.get(EntryItem.KEY_INTRODUCTION_FIELD_STRING), pattenText);
               return cb.or(idLike,introLike);
            }
        }, pageable);
        
        //this.entrySearchHisService.writeSearchHis(text);
        return items.getContent();
    }
    
    public EntryItem newEntryItem( EntryItem entryItem ){
        Date date = new Date();
        entryItem.createTime = date.getTime();
        entryItem.modifyTime = date.getTime();
        entryItem.id = date.getTime();
        //返回新值
        EntryItem newEntryItem = this.entryItemRepository.save(entryItem);
        return newEntryItem;
    }
}
