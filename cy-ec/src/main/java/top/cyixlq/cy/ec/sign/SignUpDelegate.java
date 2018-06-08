package top.cyixlq.cy.ec.sign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;
import top.cyixlq.cy.delegates.CyDelegate;
import top.cyixlq.cy.ec.R;
import top.cyixlq.cy.ec.R2;
import top.cyixlq.cy.net.RestClient;
import top.cyixlq.cy.net.callback.ISuccess;

public class SignUpDelegate extends CyDelegate {

    @BindView(R2.id.edit_name)
    TextInputEditText mName;
    @BindView(R2.id.edit_phone)
    TextInputEditText mPhone;
    @BindView(R2.id.edit_password)
    TextInputEditText mPassword;
    @BindView(R2.id.edit_re_password)
    TextInputEditText mRePassword;

    @OnClick(R2.id.btn_sign_up)
    void onClickSignUp() {
        if (checkForm()) {
            /*RestClient.builder()
                    .url("sign_up")
                    .params("","")
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {

                        }
                    })
                    .build()
                    .post();*/
            Toast.makeText(getContext(), "验证通过", Toast.LENGTH_SHORT).show();
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

    private boolean validatePhone(String phobe) {
        String regExp = "^[1]([3][0-9]{1}|59|58|88|89)[0-9]{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(phobe);
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
