package top.cyixlq.cy.ec.sign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import butterknife.BindView;
import butterknife.OnClick;
import top.cyixlq.cy.delegates.CyDelegate;
import top.cyixlq.cy.ec.R;
import top.cyixlq.cy.ec.R2;

public class SignInDelegate extends CyDelegate {

    @BindView(R2.id.edit_name_phone_email)
    TextInputEditText mName;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPassword;

    @OnClick(R2.id.icon_sign_in_wechat)
    void onClickSignInWechat() {

    }

    @OnClick(R2.id.btn_sign_in)
    void onClickSignIn() {

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
