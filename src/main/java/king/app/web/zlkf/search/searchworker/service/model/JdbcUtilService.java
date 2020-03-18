/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.service.model;

import org.springframework.stereotype.Service;

/**
 *
 * @author king
 */
@Service
public class JdbcUtilService {
    
    private final static String LIKE_STRING_SPACE = "%";
    
    /**
     *  整合成对应的 
     * @param text
     * @return 
     */
    public String buildLikeStr( String text ){
        StringBuilder builder = new StringBuilder();
        
        int len = text.length();
        
        for( int index = 0 ; index < len ; index ++ ){
            if( index == 0 ){
                builder.append( LIKE_STRING_SPACE );
            }
            
            //添加对应的 字符串信息
            char charAt = text.charAt(index);
            builder.append( charAt );
            builder.append( LIKE_STRING_SPACE  );
        }
        
        String likeStr = builder.toString();
        
        System.out.println( likeStr );
        
        return likeStr ;
    }
    
}
