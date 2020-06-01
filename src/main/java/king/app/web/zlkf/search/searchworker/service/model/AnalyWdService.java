/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.service.model;

import java.util.HashSet;
import king.app.web.zlkf.search.searchworker.service.WordService;
import java.util.Set;
import king.app.web.zlkf.search.searchworker.model.bean.AnalyWdStruct;
import king.app.web.zlkf.search.searchworker.model.bean.AnalyWordRecord;
import king.app.web.zlkf.search.searchworker.model.bean.AnalyWordRelationship;
import king.app.web.zlkf.search.searchworker.model.bean.EntryItem;
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
    
    @Autowired
    private AnalyWdRdService analyWdRdService;
    
    @Autowired
    private AnalyWdRpService analyWdRpService;
    
    /**
     *  根据 一段字符串 来得到对应的词汇
     * @param str
     * @return 
     */
    public Set<String> analyWord( String str ){
        return this.word.analyStrToWd(str);
    }
    
    /**
     * 来根据对应的 entryItem 来进行分析得到对应的结果
     * @param entryItem
     * @return 
     */
    public Set<AnalyWdStruct> analyEntryItem( EntryItem entryItem ){
        
        String title = entryItem.title;
        String introduction = entryItem.introduction;
        //下面开始分析词汇
        Set<String> analyTitle = this.analyWord( title );
        Set<String> analyIntroduction = this.analyWord( introduction );
        
        //将二者进行整合
        analyTitle.addAll( analyIntroduction );
        Set<String> analyTotal = analyTitle;
        
        Set<AnalyWdStruct> analyTotalSet = new HashSet<AnalyWdStruct>();
     
        
        //最后写入关系
        for( String analyStr : analyTotal ){
            AnalyWordRecord record =  this.analyWdRdService.findOrInsert(analyStr);
            AnalyWordRelationship relationship = this.analyWdRpService.newObj(record, entryItem);
            if( record != null && relationship != null ){
                AnalyWdStruct analyTotalStruct = new AnalyWdStruct( record , relationship );
                analyTotalSet.add(analyTotalStruct);
            }
        }
        
        return analyTotalSet;
    }
    
}
