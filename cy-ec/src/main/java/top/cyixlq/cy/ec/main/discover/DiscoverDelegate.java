package top.cyixlq.cy.ec.main.discover;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import top.cyixlq.cy.delegates.bottom.BottomItemDelegate;
import top.cyixlq.cy.delegates.web.WebDelegateImpl;
import top.cyixlq.cy.ec.R;

public class DiscoverDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_discover;
    }

    @Override
    public void onBindeView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final WebDelegateImpl delegate = WebDelegateImpl.create("second.html");
        delegate.setTopDelegate(this.getParentDelegate());
        loadRootFragment(R.id.web_discover_container, delegate);
    }
}
