package top.cyixlq.cy.ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import top.cyixlq.cy.delegates.CyDelegate;
import top.cyixlq.cy.ec.R;
import top.cyixlq.cy.ec.R2;
import top.cyixlq.cy.net.RestClient;
import top.cyixlq.cy.net.callback.ISuccess;
import top.cyixlq.cy.wechat.CyWeChat;
import top.cyixlq.cy.wechat.callbacks.IWeChatSignInCallback;

public class SignInDelegate extends CyDelegate {

    @BindView(R2.id.edit_name_phone_email)
    TextInputEditText mName;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPassword;

    private ISignListener mISignListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof ISignListener){
            mISignListener = (ISignListener) activity;
        }
    }

    @OnClick(R2.id.icon_sign_in_wechat)
    void onClickSignInWechat() {
        CyWeChat.getInstance().onSignInSuccess(new IWeChatSignInCallback() {
            @Override
            public void onSignInSuccess(String userInfo) {

            }
        }).signIn();
    }

    @OnClick(R2.id.btn_sign_in)
    void onClickSignIn() {
        if(checkForm()){
            Map<String,Object> params = new HashMap<>();
            params.put("username",mName.getText().toString().trim());
            params.put("password",mPassword.getText().toString().trim());
            RestClient.builder().url("/buyer/login")
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                            SignHandler.onSignIn(response, mISignListener);
                        }
                    })
                    .params(params)
                    .build()
                    .post();
        }
    }

    @OnClick(R2.id.tv_link_sign_in)
    void onClickLinkSignIn() {
        start(new SignUpDelegate());
    }

    private boolean checkForm() {
        final String name = mName.getText().toString().trim();
        final String password = mPassword.getText().toString().trim();
        boolean isPass = true;

        if (name.isEmpty()) {
            mName.setError("用户名/手机号码/邮箱不能为空！");
            isPass = false;
        }
        if (password.length() < 6 || password.length() > 16) {
            mPassword.setError("密码长度应该在6~16之间！");
            isPass = false;
        }

        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindeView(@Nullable Bundle savedInstanceState, View rootView) {

    }

}
