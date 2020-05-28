/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.service.model;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * 专门设计的分词器，我们决定分词操作归纳到我们可以手动操作设置的范围里面去
 * 
 * 
 * @author king
 */
@Service
public class AnalyWdService {
    
    @Autowired
    private WordService word;
    
    public Set<String> analyWord( String str ){
        return this.word.analyStrToWd(str);
    }
    
}
