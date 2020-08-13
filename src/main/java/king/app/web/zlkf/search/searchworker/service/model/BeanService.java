/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.service.model;

import java.util.Random;
import king.app.web.zlkf.search.searchworker.model.bean.anno.TableIdentifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author king
 */
@Service
public class BeanService {
    
    @Autowired
    private BeanService self;
    
    private final int TABLE_ID = 1;
    
    /**
     * id的组成部分为 13 个时间节点 ，  外加 3位数 数据库名称 , 外加2位数分表名 ， 外加3位随机数
     * 
     * {数据表标号}{数据分表标号}{时间节点}{随机数}
     * 总长度为21
     * 
     * 数据库标号暂时用注解来实现
     * 分表标号暂时都默认为 01
     * 时间节点 ，由外传
     * 随机数随机生成
     * 
     * 根据bean 的class 类 来生成对应的参数
     * @param beanClass
     * @param timeMillis
     * @return 
     */
    public Long id( Class<?> beanClass , Long timeMillis ){
        //首先获取TableIdentifier 标注内的信息
        TableIdentifier tableIdentifier = beanClass.getDeclaredAnnotation( TableIdentifier.class );
        
        if( tableIdentifier == null ){
            return 0L;
        }
        StringBuilder builder = new StringBuilder();
        Integer identifier = tableIdentifier.identifier();
        
        //获取随机数
        Random random = new Random();
        Integer randNum = random.nextInt(10);
        
        //下面就将这三位数字按照规范输入
        this.appendInt(builder, identifier , 3);
        this.appendInt(builder, TABLE_ID, 1);
        builder.append( timeMillis );
        this.appendInt( builder, randNum, 2);
        String idStr =  builder.toString();
        return Long.parseLong( idStr );
    }
    
    public Long id( Object bean ,Long timeMillis ){
        Class<?> beanClass = bean.getClass();
        return self.id(beanClass, timeMillis);
    }
    
    private void appendInt( StringBuilder builder , Integer num , Integer len ){
        
        String numStr = num.toString();
        Integer numLen = numStr.length();
        for( Integer index = numLen ; index < len ; index++){
            builder.append( "0"); //若果这个数离暂位符还缺少长度，那么就会自动添加
        }
        builder.append( numStr );
        
    }
}
