/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.service;

import king.app.web.zlkf.search.searchworker.service.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * 注意, cache service 的实际作用并不是进行实际的缓存操作、
 * 因为缓存的种类或许会存在着，差异。而且进行缓存必须制定对应的方式，而不能进行适配。
 * 我们无法使用一个 cache service 来完成所有的操作。
 * 
 * 但是，我们还是设置这样的一个类型，主要的目的，
 * 就是希望让这里成为显示中心与控制中心
 * @author king
 */
@Service
public class CacheService {
    
    @Autowired
    private RedisService redisService;
    
}
