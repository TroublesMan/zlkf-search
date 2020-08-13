/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.service.baidunetdisk;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author king
 */
public class BaiduNetDiskService {
    
    @Autowired
    private RestTemplate restTemplate;
    
    public String accessToken = null;
    
    
    
}
