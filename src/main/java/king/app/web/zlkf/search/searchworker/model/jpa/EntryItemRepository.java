/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.model.jpa;

import java.util.List;
import king.app.web.zlkf.search.searchworker.model.bean.EntryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author king
 */
public interface EntryItemRepository extends JpaRepository<EntryItem,Long> ,JpaSpecificationExecutor<EntryItem> {
    
    @Query(value="SELECT * FROM entry_item ei WHERE ei.id = :ids",nativeQuery = true)
    public List<EntryItem> searchEntryByIds( List<Long> ids );
  
}
