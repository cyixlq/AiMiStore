package top.cyixlq.cy.delegates.web;

import com.alibaba.fastjson.JSON;

public class CyWebInterface {
    private final  WebDelegate DELEGATE;

    private CyWebInterface(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    static CyWebInterface create(WebDelegate delegate){
        return new CyWebInterface(delegate);
    }

    public String event(String params){
        final String action = JSON.parseObject(params).getString("action");
        return null;
    }
}
