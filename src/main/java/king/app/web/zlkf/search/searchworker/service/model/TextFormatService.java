/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.service.model;

import com.alibaba.fastjson.JSONObject;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author king
 */
@Service
public class TextFormatService {
    
    @Autowired
    private TextFormatService self;
    
    /**
     * 将对应的json obj 转化成 对应的 string
     * @param obj
     * @return 
     */
    public String objToJson( Object obj ){
        return JSONObject.toJSONString(obj);
    }
    
    public <T> T jsonToObj( String json , Class<T> targetClass ){
        return JSONObject.parseObject(json, targetClass);
    }
    
    public <T> List<T> jsonToArr( String json , Class<T> targetClass ){
        return JSONObject.parseArray(json, targetClass);
    }
    
    
}
