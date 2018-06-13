package top.cyixlq.cy.delegates.web;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

import top.cyixlq.cy.delegates.CyDelegate;
import top.cyixlq.cy.delegates.web.router.RouterKeys;

public abstract class WebDelegate extends CyDelegate {

    private WebView mWebView;
    private final ReferenceQueue<WebView> WEB_VIEW_QUEUE = new ReferenceQueue<>();
    private String mUrl;
    private boolean mIsWebViewAbilable = false;

    public WebDelegate() {
    }

    public abstract IWebViewIntializer setIntializer();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        mUrl = args.getString(RouterKeys.URL.name());
        initWebView();
    }

    @SuppressLint("JavascriptInterface")
    private void initWebView() {
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
        } else {
            final IWebViewIntializer intializer = setIntializer();
            if (intializer != null) {
                final WeakReference<WebView> webViewWeakReference =
                        new WeakReference<>(new WebView(getContext()), WEB_VIEW_QUEUE);
                mWebView = webViewWeakReference.get();
                mWebView = intializer.initWebView(mWebView);
                mWebView.setWebViewClient(intializer.initWebViewClient());
                mWebView.setWebChromeClient(intializer.initWebChromeClient());
                mWebView.addJavascriptInterface(CyWebInterface.create(this), "cy");
                mIsWebViewAbilable = true;
            } else {
                throw new NullPointerException("IWebViewIntializer对象为空！无法创建webview！");
            }
        }
    }

    public WebView getWebView() {
        if (mWebView == null) {
            throw new NullPointerException("WebView对象为空！");
        }
        return mIsWebViewAbilable ? mWebView : null;
    }

    public String getUrl(){
        if (mWebView == null) {
            throw new NullPointerException("Url值为空！");
        }
        return mUrl;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mWebView != null) {
            mWebView.onPause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mWebView != null) {
            mWebView.onResume();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mIsWebViewAbilable = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
            mWebView = null;
        }
    }
}
