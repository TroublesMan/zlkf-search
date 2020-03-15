/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.model.jpa;

import king.app.web.zlkf.search.searchworker.model.bean.EntryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 *
 * @author king
 */
public interface EntryItemRepository extends JpaRepository<EntryItem,Long> ,JpaSpecificationExecutor<EntryItem> {
    
}
