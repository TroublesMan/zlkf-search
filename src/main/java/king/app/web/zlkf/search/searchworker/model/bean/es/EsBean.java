/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.model.bean.es;

/**
 *
 * @author king
 */
public interface EsBean <T> {
    //根据对应的 bean 的属性 来生成值的内容
    public void clone( T bean );
}
