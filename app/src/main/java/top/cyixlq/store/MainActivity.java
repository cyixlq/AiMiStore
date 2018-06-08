package top.cyixlq.store;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import top.cyixlq.cy.activities.ProxyActivity;
import top.cyixlq.cy.delegates.CyDelegate;
import top.cyixlq.cy.ec.launcher.LauncherDelegate;
import top.cyixlq.cy.ec.launcher.LauncherScrollDelegate;
import top.cyixlq.cy.ec.sign.SignInDelegate;
import top.cyixlq.cy.ec.sign.SignUpDelegate;

public class MainActivity extends ProxyActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
    }

    @Override
    public CyDelegate setRootdelegate() {
        return new SignInDelegate();
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        //Toast.makeText(Cy.getApplicationContext(), "传入Context啦！", Toast.LENGTH_SHORT).show();
//    }
}
