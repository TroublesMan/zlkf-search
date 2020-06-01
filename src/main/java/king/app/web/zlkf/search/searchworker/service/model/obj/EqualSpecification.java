/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.service.model.obj;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author king
 */
public class EqualSpecification<T> implements Specification<T>{
    
    private String key = null;
    
    private Object val = null;
    
    public EqualSpecification( String result , Object val ){
        this.key = result;
        this.val = val;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        return cb.equal( root.get(this.key),this.val);
    }
    

    
    
}
