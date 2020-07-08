/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.service.async;

import java.util.function.Function;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * 目前存在的问题为下面：
 * 
 * 1.我们最好不要用一个类来填充信息，我们可以将其分析成为两个类 ， 
 * 一个装载如何异步操作的信息，简单的说，我们可以把逻辑放入这里面，
 * 另一个类则装载条件信息。 
 * 
 * 既然如此，那么我们为什么还不进行这样的改造呢？
 * 原因就是未知性，我们尚且不知道是否能加强，因此，我们暂时就只用逻辑来处理
 * 
 * @author king
 */
public abstract class AsyncTask implements Function< WebApplicationContext , Boolean >{
    
    /**
     * 进行处理对应的操作
     * 
     * @return 
     */
    
}
