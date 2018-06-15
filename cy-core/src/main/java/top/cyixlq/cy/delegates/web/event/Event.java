package top.cyixlq.cy.delegates.web.event;

import android.content.Context;

import top.cyixlq.cy.delegates.CyDelegate;

public abstract class Event implements IEvent {

    private Context mContext;
    private String mAction;
    private CyDelegate mDelegate;
    private String mUrl;

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        this.mContext = context;
    }

    public String getAction() {
        return mAction;
    }

    public void setAction(String action) {
        this.mAction = action;
    }

    public CyDelegate getDelegate() {
        return mDelegate;
    }

    public void setDelegate(CyDelegate delegate) {
        this.mDelegate = delegate;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        this.mUrl = url;
    }
}
