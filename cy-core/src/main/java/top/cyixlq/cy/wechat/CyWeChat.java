package top.cyixlq.cy.wechat;

import android.app.Activity;

import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import top.cyixlq.cy.app.ConfigKeys;
import top.cyixlq.cy.app.Cy;
import top.cyixlq.cy.wechat.callbacks.IWeChatSignInCallback;

public class CyWeChat {

    public static final String APP_ID = Cy.getConfiguration(ConfigKeys.WE_CHAT_APP_ID);
    public static final String APP_SECRET = Cy.getConfiguration(ConfigKeys.WE_CHAT_APP_SECRET);
    private IWeChatSignInCallback mSignInCallback;
    private final IWXAPI wxapi;

    private static final class Holder{
        private static final CyWeChat INSTANCE = new CyWeChat();
    }

    public static CyWeChat getInstance(){
        return Holder.INSTANCE;
    }

    private CyWeChat(){
        final Activity activity = Cy.getConfiguration(ConfigKeys.ACTIVITY);
        wxapi = WXAPIFactory.createWXAPI(activity, APP_ID, true);
        wxapi.registerApp(APP_ID);
    }

    public final IWXAPI getWxapi(){
        return wxapi;
    }

    public CyWeChat onSignInSuccess(IWeChatSignInCallback callback){
        this.mSignInCallback = callback;
        return this;
    }

    public IWeChatSignInCallback getSignInCallback() {
        return mSignInCallback;
    }

    public final void signIn(){
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "random_state";
        wxapi.sendReq(req);
    }
}
