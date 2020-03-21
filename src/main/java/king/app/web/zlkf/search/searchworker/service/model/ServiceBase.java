/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.service.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author king
 * @param <T>
 * @param <M>
 */
public class ServiceBase<T extends JpaRepository<M,?>,M> {
    @Autowired
    protected T repository;
    
}
