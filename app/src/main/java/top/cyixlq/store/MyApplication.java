package top.cyixlq.store;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;

import top.cyixlq.cy.app.Cy;
import top.cyixlq.cy.ec.icon.FontEcModule;
import top.cyixlq.cy.net.interceptors.DebugInterceptor;

/**
 * Created by 24215 on 2018/6/2.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Cy.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withLoaderDelayed(1000)
                .withApiHost("http://127.0.0.1/")
                .withInterceptor(new DebugInterceptor("index",R.raw.test))
                .configure();
    }
}
