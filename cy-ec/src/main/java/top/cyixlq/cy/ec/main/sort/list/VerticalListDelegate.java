package top.cyixlq.cy.ec.main.sort.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import top.cyixlq.cy.delegates.CyDelegate;
import top.cyixlq.cy.ec.R;
import top.cyixlq.cy.ec.R2;
import top.cyixlq.cy.ec.main.sort.SortDelegate;
import top.cyixlq.cy.net.RestClient;
import top.cyixlq.cy.net.callback.ISuccess;
import top.cyixlq.cy.ui.recycler.MultipleItemEntity;

public class VerticalListDelegate extends CyDelegate {

    @BindView(R2.id.rv_vertical_menu_list)
    RecyclerView mRecyclerView;

    @Override
    public Object setLayout() {
        return R.layout.delegate_vertical_list;
    }

    private void initRecyclerView(){
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        //屏蔽动画效果
        mRecyclerView.setItemAnimator(null);

    }

    @Override
    public void onBindeView(@Nullable Bundle savedInstanceState, View rootView) {
        initRecyclerView();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .url("/buyer/category/0")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final List<MultipleItemEntity> data =
                                new VerticalListDataConverter().setJsonData(response).convert();
                        final SortDelegate delegate = getParentDelegate();
                        final SortRecyclerAdapter adapter = new SortRecyclerAdapter(data, delegate);
                        mRecyclerView.setAdapter(adapter);
                    }
                })
                .build()
                .get();

    }
}
