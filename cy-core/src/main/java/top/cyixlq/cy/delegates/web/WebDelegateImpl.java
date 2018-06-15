package top.cyixlq.cy.delegates.web;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import top.cyixlq.cy.delegates.web.client.WebViewClientImpl;
import top.cyixlq.cy.delegates.web.router.Router;
import top.cyixlq.cy.delegates.web.router.RouterKeys;

public class WebDelegateImpl extends WebDelegate implements IWebViewInitializer {

    public static WebDelegateImpl create(String url){
        final Bundle args = new Bundle();
        args.putString(RouterKeys.URL.name(), url);
        final WebDelegateImpl delegate = new WebDelegateImpl();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public Object setLayout() {
        return getWebView();
    }

    @Override
    public void onBindeView(@Nullable Bundle savedInstanceState, View rootView) {
        if(getUrl()!=null){
            //用原生的方式模拟web跳转并进行页面加载
            Router.getInstance().loadPage(this, getUrl());
        }
    }

    @Override
    public WebView initWebView(WebView webView) {
        return new WebViewInitializer().createWebView(webView);
    }

    @Override
    public WebViewClient initWebViewClient() {
        final WebViewClientImpl client = new WebViewClientImpl(this);
        return client;
    }

    @Override
    public WebChromeClient initWebChromeClient() {
        return null;
    }

    @Override
    public IWebViewInitializer setInitializer() {
        return this;
    }
}
