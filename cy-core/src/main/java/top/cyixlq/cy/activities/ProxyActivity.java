package top.cyixlq.cy.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import me.yokeyword.fragmentation.SupportActivity;
import top.cyixlq.cy.R;
import top.cyixlq.cy.delegates.CyDelegate;

/**
 * Created by 24215 on 2018/6/2.
 */

public abstract class ProxyActivity extends SupportActivity {

    public abstract CyDelegate setRootdelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    private void initContainer(@Nullable Bundle savedInstanceState) {
        final ContentFrameLayout container = new ContentFrameLayout(this);
        container.setId(R.id.delegate_container);
        setContentView(container);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.delegate_container, setRootdelegate());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}
