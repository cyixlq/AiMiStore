package top.cyixlq.store;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import qiu.niorgai.StatusBarCompat;
import top.cyixlq.cy.activities.ProxyActivity;
import top.cyixlq.cy.app.Cy;
import top.cyixlq.cy.delegates.CyDelegate;
import top.cyixlq.cy.ec.launcher.LauncherDelegate;
import top.cyixlq.cy.ec.launcher.LauncherScrollDelegate;
import top.cyixlq.cy.ec.main.EcBottomDelegate;
import top.cyixlq.cy.ec.sign.ISignListener;
import top.cyixlq.cy.ec.sign.SignInDelegate;
import top.cyixlq.cy.ec.sign.SignUpDelegate;
import top.cyixlq.cy.ui.launcher.ILauncherListener;
import top.cyixlq.cy.ui.launcher.OnLauncherFinishTag;

public class MainActivity extends ProxyActivity implements ISignListener, ILauncherListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        Cy.getConfigurator().withActivity(this);
        StatusBarCompat.translucentStatusBar(this, true);
    }

    @Override
    public CyDelegate setRootdelegate() {
        return new LauncherDelegate();
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "登陆成功！", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSignUpSuccess() {

    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag) {
            case SIGNED:
                Toast.makeText(this, "启动结束，用户登陆了！", Toast.LENGTH_LONG).show();
                startWithPop(new EcBottomDelegate());
                break;
            case NOT_SIGNED:
                Toast.makeText(this, "启动结束，用户没登陆！", Toast.LENGTH_LONG).show();
                startWithPop(new SignInDelegate());
                break;
            default:
                break;
        }
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        //Toast.makeText(Cy.getApplicationContext(), "传入Context啦！", Toast.LENGTH_SHORT).show();
//    }
}
