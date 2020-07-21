/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.service.model.aspect;

import java.util.List;
import king.app.web.zlkf.search.searchworker.model.bean.EntryItem;
import king.app.web.zlkf.search.searchworker.service.async.AsyncService;
import king.app.web.zlkf.search.searchworker.service.async.AsyncTask;
import king.app.web.zlkf.search.searchworker.service.redis.RedisImpService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * 主要关于EntryItemService 之中的插片方程
 *
 * @author king
 */
@Aspect
//@Component
public class EntryItemAspect {

    private final String search_text_format_str = "%s_%d_%d";

    @Autowired
    private RedisImpService redisCacheService;

    @Autowired
    private AsyncService asyncService;

    /**
     * 切割searchByText()方法
     */
    @Pointcut("execution( * king.app.web.zlkf.search.searchworker.service.model.EntryItemService.searchByText(..))")
    public void cut_search_text() {

    }

    @Around("cut_search_text()")
    public Object around_search_text(ProceedingJoinPoint joinPoint) throws Throwable {
        Object args[] = joinPoint.getArgs();

        Object textObj = args[0];
        Object pageNumberObj = args[1];
        Object pageSizeObj = args[2];

        String text = (String) textObj;
        Integer pageNumber = (Integer) pageNumberObj;
        Integer pageSize = (Integer) pageSizeObj;

        String key_str = String.format(this.search_text_format_str, text, pageNumber, pageSize);

        //获取对应的结果 , 若缓存不存在则进行下一步的操作
        List<EntryItem> entryItem = this.redisCacheService.getEntryItemsByText(key_str);

        if (entryItem != null) {
            System.out.println("使用了缓存");
            return entryItem;
        }else{
            System.out.println("并没有使用上缓存");
        }
        try {
            Object result = joinPoint.proceed();
            entryItem = (List<EntryItem>) result;
            //若不存在缓存则直接进行插入操作
            this.redisCacheService.saveEntryItemByText(text, entryItem);
            final List<EntryItem> finalEntryItems = entryItem;
            this.asyncService.async(new AsyncTask() {
                @Override
                public Boolean apply(WebApplicationContext t) {
                    return redisCacheService.saveEntryItemByText(search_text_format_str, finalEntryItems);
                }
            });
            return entryItem;
        } catch (Exception ex) {
            return null;
        }
    }

}
