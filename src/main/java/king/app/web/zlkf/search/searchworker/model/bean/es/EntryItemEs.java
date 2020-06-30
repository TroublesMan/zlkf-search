/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.model.bean.es;

import king.app.web.zlkf.search.searchworker.model.bean.EntryItem;

/**
 * 对应的EntryItem 对应的 entryItem
 * @author king
 */
public class EntryItemEs extends EntryItem implements EsBean<EntryItem> {

    @Override
    public void clone(EntryItem bean) {
        this.id = bean.id;
        this.introduction = bean.introduction;
        this.title = bean.title;
        this.url = bean.url;
        this.createTime = bean.createTime;
        this.modifyTime = bean.modifyTime;
    }
    
    
}
