package top.cyixlq.cy.app;

import top.cyixlq.cy.util.storage.CyPreference;

public class AccountManager {

    private enum SignTag {
        SIGN_TAG
    }

    public static void setSignState(boolean state) {
        CyPreference.setAppFlag(SignTag.SIGN_TAG.name(), state);
    }

    private static boolean isSignIn() {
        return CyPreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    public static void checkAccount(IUserChecker checker) {
        if (isSignIn()) {
            checker.onSignIn();
        } else {
            checker.onNotSignIn();
        }
    }
}
