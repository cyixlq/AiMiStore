package top.cyixlq.cy.ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import top.cyixlq.cy.app.AccountManager;
import top.cyixlq.cy.ec.database.DatabaseManager;
import top.cyixlq.cy.ec.database.UserProfile;

public class SignHandler {

    public static void onSignIn(String respons, ISignListener signListener){
        final JSONObject profileJson = JSON.parseObject(respons).getJSONObject("data");
        final String token = profileJson.getString("token");
        final String username = profileJson.getString("username");

        //登陆成功之后把相应信息写入数据库
        final UserProfile profile = new UserProfile();
        profile.setToken(token);
        profile.setUsername(username);
        DatabaseManager.getInstance()
                .getUserProfileDao().insert(profile);
        //设置登陆状态为已经登陆并且回调登陆成功函数
        AccountManager.setSignState(true);
        signListener.onSignInSuccess();
    }
}
