/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.model.bean;

/**
 *
 * @author king
 */
public class AnalyWdStruct {
    
    //对应关系的结构体
    public AnalyWordRecord record;
    
    public AnalyWordRelationship relationship;
    
    public AnalyWdStruct(){
    }
    public AnalyWdStruct( AnalyWordRecord record , AnalyWordRelationship relationship){
        this.buildObj(record, relationship);
    }
    
    public void buildObj( AnalyWordRecord record , AnalyWordRelationship relationship){
        this.record = record;
        this.relationship = relationship;
    }
    
}
