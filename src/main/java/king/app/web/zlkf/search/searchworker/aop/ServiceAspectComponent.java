/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.aop;

import king.app.web.zlkf.search.searchworker.service.model.EntrySearchHisService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.QAbstractAuditable;
import org.springframework.stereotype.Component;

/**
 *
 * @author king
 */
@Aspect
@Component
public class ServiceAspectComponent {

    /**
     * 用来应用对应的 entryService 查询的时候， 来直接写入程序信息的操作
     */
    @Pointcut("execution( * king.app.web.zlkf.search.searchworker.service.model.EntryItemService.searchByText(..) )")
    public void entry_search_text() { }


    @Autowired
    private EntrySearchHisService enrtEntrySearchHisService;
    
    /**
     * 在运行了搜索操作之后，倘若输出不为null ，（ 无论报不报错都可以其实这个搞不好都不需要 ，） 然后写入搜索历史
     * @param joinPoint
     * @return 
     * @throws Throwable 
     */
    //@Around("entry_search_text()")
    public Object searchTextAtferHis(ProceedingJoinPoint joinPoint) throws Throwable{
        
        Object result = joinPoint.proceed();
        
        if( result != null ){
            //只有当返回结果不为空的时候才会进行应用
            Object args[] = joinPoint.getArgs();
            
            Object textObj = args[0];
            String text = null;
            if( textObj == null  ){
                text = "";
            }
            text = textObj.toString();
            //最终直接写入
           this.enrtEntrySearchHisService.writeSearchHis(text);
        }
        
        return result;
    }
}
