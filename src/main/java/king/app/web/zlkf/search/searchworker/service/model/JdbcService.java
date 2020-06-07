/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.service.model;

import java.util.List;
import javax.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 *
 * @author king
 */
@Service
public class JdbcService {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    
    private final String SEARCH_BY_IDS_SQL = " SELECT * ";
}
