/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.model.service;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import king.app.web.zlkf.search.searchworker.model.bean.EntryItem;
import king.app.web.zlkf.search.searchworker.model.jpa.EntryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
     * @return 
     */
    public List<EntryItem> searchByText( String text  ){
        String pattenText = this.jdbcUtils.buildLikeStr( text );
        Page<EntryItem> items = this.entryItemRepository.findAll(new Specification<EntryItem>() {
            @Override
            public Predicate toPredicate(Root<EntryItem> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
               Predicate idLike = cb.like(root.get(EntryItem.KEY_TITLE_FIELD_STRING), pattenText );
               Predicate introLike = cb.like(root.get(EntryItem.KEY_INTRODUCTION_FIELD_STRING), pattenText);
               return cb.or(idLike,introLike);
            }
        }, Pageable.unpaged());
        
        this.entrySearchHisService.writeSearchHis(text);
        return items.getContent();
    }
    
}
