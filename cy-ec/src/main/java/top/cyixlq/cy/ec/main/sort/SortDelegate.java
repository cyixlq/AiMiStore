package top.cyixlq.cy.ec.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import top.cyixlq.cy.delegates.bottom.BottomItemDelegate;
import top.cyixlq.cy.ec.R;
import top.cyixlq.cy.ec.main.sort.content.ContentDelegate;
import top.cyixlq.cy.ec.main.sort.list.VerticalListDelegate;

public class SortDelegate extends BottomItemDelegate{
    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindeView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final VerticalListDelegate listDelegate = new VerticalListDelegate();
        getSupportDelegate().loadRootFragment(R.id.vertical_list_container, listDelegate);
        //设置右侧第一个分类显示，默认显示分类一
        getSupportDelegate().loadRootFragment(R.id.sort_content_container, ContentDelegate.newInstance(1));
    }
}
