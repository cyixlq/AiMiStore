package top.cyixlq.cy.delegates.web.client;

import android.webkit.WebView;
import android.webkit.WebViewClient;

import top.cyixlq.cy.delegates.web.WebDelegate;
import top.cyixlq.cy.delegates.web.router.Router;
import top.cyixlq.cy.util.log.CyLog;

public class WebViewClientImpl extends WebViewClient {

    private final WebDelegate DELEGATE;

    public WebViewClientImpl(WebDelegate delegate){
        this.DELEGATE = delegate;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        CyLog.d("shouldOverrideUrlLoading", url);
        return Router.getInstance().handleWebUrl(DELEGATE,url);
    }
}
