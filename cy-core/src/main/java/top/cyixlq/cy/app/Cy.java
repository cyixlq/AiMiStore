package top.cyixlq.cy.app;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by 24215 on 2018/6/2.
 */

public final class Cy {

    public static Configurator init (Context context) {
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(),
                context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static HashMap<String, Object> getConfigurations(){
        return Configurator.getInstance().getCyConfigs();
    }

    public static Context getApplicationContext(){
        return (Context) getConfigurations().get(ConfigType.APPLICATION_CONTEXT.name());
    }
}
