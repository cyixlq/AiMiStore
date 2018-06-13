package top.cyixlq.cy.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;

import top.cyixlq.cy.app.Cy;
import top.cyixlq.cy.net.RestClient;
import top.cyixlq.cy.net.callback.ISuccess;
import top.cyixlq.cy.ui.recycler.DataConverter;
import top.cyixlq.cy.ui.recycler.MultipleRecyclerAdapter;

public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener{

    private final SwipeRefreshLayout REFRESH_LAYOUT;
    private final PagingBean BEAN;
    private final RecyclerView RECYCLERVIEW;
    private MultipleRecyclerAdapter mAdapter = null;
    private final DataConverter CONVERTER;

    private RefreshHandler(SwipeRefreshLayout refreshLayout,
                          RecyclerView recyclerView,
                          DataConverter converter,
                          PagingBean bean) {
        this.REFRESH_LAYOUT = refreshLayout;
        this.RECYCLERVIEW = recyclerView;
        this.CONVERTER = converter;
        this.BEAN = bean;
        REFRESH_LAYOUT.setOnRefreshListener(this);
    }

    public static RefreshHandler create(SwipeRefreshLayout refreshLayout,
                                        RecyclerView recyclerView,
                                        DataConverter converter){
        return new RefreshHandler(refreshLayout,recyclerView,converter,new PagingBean());
    }

    private void refresh(){
        REFRESH_LAYOUT.setRefreshing(true);
        Cy.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //进行网络请求
                REFRESH_LAYOUT.setRefreshing(false);
            }
        }, 2000);
    }

    public void firstPage(String url){
        BEAN.setDeplayed(1000);
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final JSONObject object = JSON.parseObject(response).getJSONObject("data").getJSONObject("page");
                        BEAN.setTotal(object.getInteger("totalPages"))
                                .setPageSize(object.getInteger("size"));
                        //设置Adapter
                        mAdapter = MultipleRecyclerAdapter.create(CONVERTER.setJsonData(response));
                        mAdapter.setOnLoadMoreListener(RefreshHandler.this, RECYCLERVIEW);
                        RECYCLERVIEW.setAdapter(mAdapter);
                        BEAN.addIndex();
                    }
                }).build().get();
    }

    @Override
    public void onRefresh() {
        refresh();
    }

    @Override
    public void onLoadMoreRequested() {

    }
}
