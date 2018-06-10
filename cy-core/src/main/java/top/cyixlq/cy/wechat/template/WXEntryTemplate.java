package top.cyixlq.cy.wechat.template;

import top.cyixlq.cy.activities.ProxyActivity;
import top.cyixlq.cy.delegates.CyDelegate;
import top.cyixlq.cy.wechat.BaseWXEntryActivity;
import top.cyixlq.cy.wechat.CyWeChat;

public class WXEntryTemplate extends BaseWXEntryActivity {

    @Override
    protected void onResume() {
        super.onResume();
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onSignInSuccess(String userInfo) {
        CyWeChat.getInstance().getSignInCallback().onSignInSuccess(userInfo);
    }
}
