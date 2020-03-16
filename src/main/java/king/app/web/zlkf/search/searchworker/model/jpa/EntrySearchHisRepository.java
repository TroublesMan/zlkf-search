/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.model.jpa;

import king.app.web.zlkf.search.searchworker.model.bean.EntrySearchHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author king
 */
public interface EntrySearchHisRepository extends JpaRepository<EntrySearchHistory,Long> ,JpaSpecificationExecutor<EntrySearchHistory>{
    
    @Query( value = "SELECT *  FROM entry_search_history e WHERE e.content = :content",nativeQuery=true)
    public EntrySearchHistory getEntryHisByContent( @Param("content") String content );
    
    /**
     * 根据对应的 count 来进行更新
     * @param id
     * @param currentCount
     * @param count
     * @return 
     */
    
    //由于版本问题  ，  因此 我们暂时先利用对应的 JdbcTemplate 完成对应的工作
    /*
    @Modifying
    @Query( value = "UPDATE entry_search_history esh SET esh.search_count = :count WHERE esh.id= :id AND esh.search_count = :currentCount",nativeQuery=true)
    public void updateHisCountById( @Param("id") Long id ,@Param("currentCount") Long currentCount ,  @Param("count") Long count );
    */
}
