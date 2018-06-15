package top.cyixlq.cy.delegates.web;

import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSON;

import top.cyixlq.cy.delegates.web.event.Event;
import top.cyixlq.cy.delegates.web.event.EventManager;
import top.cyixlq.cy.util.log.CyLog;

final class CyWebInterface {
    private final  WebDelegate DELEGATE;

    private CyWebInterface(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    static CyWebInterface create(WebDelegate delegate){
        return new CyWebInterface(delegate);
    }

    @SuppressWarnings("unused")
    @JavascriptInterface
    public String event(String params){
        final String action = JSON.parseObject(params).getString("action");
        final Event event = EventManager.getInstance().createEvent(action);
        if(event!=null){
            event.setAction((action));
            event.setDelegate(DELEGATE);
            event.setContext(DELEGATE.getContext());
            event.setUrl(DELEGATE.getUrl());
            return event.execute(params);
        }
        return null;
    }
}
