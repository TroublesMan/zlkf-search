/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.service.model;

import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import king.app.web.zlkf.search.searchworker.model.bean.AnalyWdStruct;
import king.app.web.zlkf.search.searchworker.model.bean.EntryItem;
import king.app.web.zlkf.search.searchworker.model.jpa.EntryItemRepository;
import king.app.web.zlkf.search.searchworker.service.obj.CreateEntryItemObj;
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
    
    @Autowired
    private AnalyWdService analyWdService;
    
    @Autowired
    private EntryItemService self;
    
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
        
        return items.getContent();
    }
    
    /**
     * 根据对应 entryItem 的id 号，来生成对应的信息
     * 
     * @param idList
     * @return 
     * 注意，这个方法，并没有带入对应的 缓存效果，因此将来还需要进一步的优化。
     */
    public List<EntryItem> searchEntryByIds( List<Long> idList ){
        return this.entryItemRepository.searchEntryByIds(idList);
    }
    
    /**
     * 
     * 创建对应的EntryItem ， 无需要对应的下一步的共享
     * 
     * @param entryItem
     * @return 
     */
    public CreateEntryItemObj createEntryItem( EntryItem entryItem ){
        
        this.self.newEntryItem(entryItem);
        //将对应的操作带入里面 ， 我们暂时先不管其他的东西，直接将操作写入这里面
        EntryItem newEntryItem = this.entryItemRepository.save(entryItem);
        Set<AnalyWdStruct> analyStructSet = this.analyWdService.analyEntryItem(entryItem);
        CreateEntryItemObj createObj = new CreateEntryItemObj(newEntryItem, analyStructSet);
        return createObj;
        
    }
    
    public EntryItem newEntryItem( EntryItem entryItem ){
        Date date = new Date();
        entryItem.createTime = date.getTime();
        entryItem.modifyTime = date.getTime();
        entryItem.id = date.getTime();
        //返回新值
        return entryItem;
    }
}
