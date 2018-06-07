package top.cyixlq.cy.app;

import android.content.Context;
import android.os.Handler;

import java.util.HashMap;

/**
 * Created by 24215 on 2018/6/2.
 */

public final class Cy {

    public static Configurator init(Context context) {
        Configurator.getInstance()
                .getCyConfigs()
                .put(ConfigKeys.APPLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration((Enum<ConfigKeys>) key);
    }

    public static Context getApplicationContext() {
        return getConfiguration(ConfigKeys.APPLICATION_CONTEXT);
    }

    public static Handler getHandler() {
        return getConfiguration(ConfigKeys.HANDLER);
    }

    public static void test(){
    }
}
