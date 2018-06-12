package top.cyixlq.cy.delegates;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

public abstract class CyDelegate extends PermissionCheckerDelegate {
    @SuppressWarnings("unchecked")
    public <T extends CyDelegate> T getParentDelegate() {
        return (T) getParentFragment();
    }
}
