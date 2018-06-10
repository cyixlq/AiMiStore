package top.cyixlq.cy.ec.sign;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;
import top.cyixlq.cy.delegates.CyDelegate;
import top.cyixlq.cy.ec.R;
import top.cyixlq.cy.ec.R2;
import top.cyixlq.cy.net.RestClient;
import top.cyixlq.cy.net.callback.ISuccess;
import top.cyixlq.cy.ui.loader.CyLoader;
import top.cyixlq.cy.util.log.CyLog;

public class SignUpDelegate extends CyDelegate {

    @BindView(R2.id.edit_name)
    TextInputEditText mName;
    @BindView(R2.id.edit_phone)
    TextInputEditText mPhone;
    @BindView(R2.id.edit_password)
    TextInputEditText mPassword;
    @BindView(R2.id.edit_re_password)
    TextInputEditText mRePassword;

    private ISignListener mISignListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof  ISignListener){
            mISignListener = (ISignListener) activity;
        }
    }

    @OnClick(R2.id.btn_sign_up)
    void onClickSignUp() {
        if (checkForm()) {
            Map<String, Object> params = new HashMap<>();
            params.put("username", mName.getText().toString().trim());
            params.put("phone", mPhone.getText().toString().trim());
            params.put("password", mPassword.getText().toString().trim());
            RestClient.builder()
                    .url("/buyer/u")
                    .params(params)
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                        }
                    })
                    .build()
                    .post();
        }
    }

    @OnClick(R2.id.tv_link_sign_up)
    void onClickLinkSignIn() {
        start(new SignInDelegate());
    }

    private boolean checkForm() {
        final String name = mName.getText().toString().trim();
        final String phone = mPhone.getText().toString().trim();
        final String password = mPassword.getText().toString().trim();
        final String rePassword = mRePassword.getText().toString().trim();
        boolean isPass = true;

        if (name.isEmpty()) {
            mName.setError("用户名不能为空！");
            isPass = false;
        }
        if (phone.isEmpty()) {
            mPhone.setError("手机号码不能为空！");
            isPass = false;
        } else if (!validatePhone(phone)) {
            mPhone.setError("手机号码格式错误！");
            isPass = false;
        }
        if (password.length() < 6 || password.length() > 16) {
            mPassword.setError("密码长度应该在6~16之间！");
            isPass = false;
        }
        if (!rePassword.equals(password)) {
            mRePassword.setError("两次输入的密码不一致！");
            isPass = false;
        }
        return isPass;
    }

    private boolean validatePhone(String phone) {
        String regExp = "^[1](([3][0-9])|([4][5,7,9])|([5][^4,6,9])|([6][6])|([7][3,5,6,7,8])|([8][0-9])|([9][8,9]))[0-9]{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(phone);
        return m.find();

    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBindeView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
