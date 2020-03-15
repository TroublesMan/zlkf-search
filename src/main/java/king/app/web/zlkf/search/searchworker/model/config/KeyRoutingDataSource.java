/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.model.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 *
 * @author king
 */
public class KeyRoutingDataSource extends AbstractRoutingDataSource{
    
    public final String key ;
    
    public KeyRoutingDataSource( String key ){
        this.key = key;
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return this.key;
    }
    
}
