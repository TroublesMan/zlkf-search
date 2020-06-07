/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.app.web.zlkf.search.searchworker.service.elasticsearch;

import org.apache.http.HttpHost;

/**
 *
 * 利用对应的 elasticsearch 来设置对应的结构体
 *
 * @author king
 */
public class ESearchLinkStruct {

    public String elasticSearchIp = null;

    public Integer elasticSearchPort = null;

    //根据对应的 属性来直接生成相对应的信息
    public HttpHost httpHost = null;

    //确定是否合法的属性 
    public boolean isLegal = false;
    
    private final static String DEFAULT_ELASTICSEARCH_IP = "127.0.0.1";
    
    private final static Integer DEFAULT_ELASTICSEARCH_PORT = 9200;
    
    //若无参数 ，则直接生成 localhost 与 9200
    public ESearchLinkStruct(){
        this( DEFAULT_ELASTICSEARCH_IP, DEFAULT_ELASTICSEARCH_PORT );
    }

    public ESearchLinkStruct(String elasticSearchIp, Integer elasticSearchPort) {
        this.elasticSearchIp = elasticSearchIp;
        this.elasticSearchPort = elasticSearchPort;
        //若属性合法 ， 则生成对应的属性
        if (this.legal()) {
            HttpHost httpHost = new HttpHost(this.elasticSearchIp, this.elasticSearchPort, "http");
            this.httpHost = httpHost;
        }
    }

    private boolean legal() {
        boolean isLegal = this.elasticSearchIp != null  && this.elasticSearchPort != null;
        this.isLegal = isLegal;
        return this.isLegal;
    }

}
