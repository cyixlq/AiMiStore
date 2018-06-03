package top.cyixlq.cy.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import top.cyixlq.cy.app.Cy;

public class DimenUtil {

    public static int getScreenWidth() {
        final Resources resources = Cy.getApplicationContext().getResources();
        final DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    public static int getScreenHeight() {
        final Resources resources = Cy.getApplicationContext().getResources();
        final DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.heightPixels;
    }

}
