/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.service.obj;

import java.util.Set;
import king.app.web.zlkf.search.searchworker.model.bean.AnalyWdStruct;
import king.app.web.zlkf.search.searchworker.model.bean.EntryItem;

/**
 *
 * @author king
 */
public class CreateEntryItemObj {
    
    public Set<AnalyWdStruct> analyStructSet;
    
    public EntryItem entryItem;
    
    public CreateEntryItemObj( EntryItem newEntryItem , Set<AnalyWdStruct> analyStructSet ){
        this.entryItem = newEntryItem;
        this.analyStructSet = analyStructSet;
    }
    
}
