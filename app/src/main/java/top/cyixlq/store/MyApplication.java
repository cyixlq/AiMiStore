package top.cyixlq.store;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;

import top.cyixlq.cy.app.Cy;
import top.cyixlq.cy.ec.icon.FontEcModule;

/**
 * Created by 24215 on 2018/6/2.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Cy.init(this)
                .withApiHost("http://localhost:8080/")
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .configure();
    }
}
