package top.cyixlq.store;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

import top.cyixlq.cy.app.Cy;
import top.cyixlq.cy.ec.database.DatabaseManager;
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
                .withApiHost("http://192.168.0.113:8081")
                .withInterceptor(new DebugInterceptor("index", R.raw.test))
                .withInterceptor(new DebugInterceptor("menu", R.raw.menu))
                .withInterceptor(new DebugInterceptor("content", R.raw.content))
                .configure();
        DatabaseManager.getInstance().init(this);
        initStetho();
    }

    private void initStetho() {
        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build());
    }
}
