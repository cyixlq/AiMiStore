package top.cyixlq.cy.wechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;

import top.cyixlq.cy.net.RestClient;
import top.cyixlq.cy.net.callback.IError;
import top.cyixlq.cy.net.callback.IFailure;
import top.cyixlq.cy.net.callback.ISuccess;
import top.cyixlq.cy.util.log.CyLog;

public abstract class BaseWXEntryActivity extends BaseWXActivity {

    //用户登陆成功回调
    protected abstract void onSignInSuccess(String userInfo);

    //微信发送请求到第三方应用后的回调
    @Override
    public void onReq(BaseReq baseReq) {

    }

    //第三方发送请求到微信后的回调
    @Override
    public void onResp(BaseResp baseResp) {
        final String code = ((SendAuth.Resp)baseResp).code;
        final StringBuilder authUrl = new StringBuilder();
        authUrl.append("https://api.weixin.qq.com/sns/oauth2/access_token?appid=")
                .append(CyWeChat.APP_ID)
                .append("&secret=")
                .append(CyWeChat.APP_SECRET)
                .append("&code=")
                .append(code)
                .append("&grant_type=authorization_code");

        CyLog.d("authUrl", authUrl.toString());
        getAuth(authUrl.toString());
    }

    private void getAuth(String authUrl) {
        RestClient
                .builder()
                .url(authUrl)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {

                        final JSONObject authObj = JSON.parseObject(response);
                        final String accessToken = authObj.getString("access_token");
                        final String openId = authObj.getString("openid");

                        final StringBuilder userInfoUrl = new StringBuilder();
                        userInfoUrl
                                .append("https://api.weixin.qq.com/sns/userinfo?access_token=")
                                .append(accessToken)
                                .append("&openid=")
                                .append(openId)
                                .append("&lang=")
                                .append("zh_CN");

                        CyLog.d("userInfoUrl", userInfoUrl.toString());
                        getUserInfo(userInfoUrl.toString());

                    }
                })
                .build()
                .get();
    }

    private void getUserInfo(String userInfoUrl) {
        RestClient.builder()
                .url(userInfoUrl)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        onSignInSuccess(response);
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build()
                .get();
    }
}
