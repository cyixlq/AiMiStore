package top.cyixlq.store;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import top.cyixlq.cy.delegates.CyDelegate;
import top.cyixlq.cy.net.RestClient;
import top.cyixlq.cy.net.callback.IError;
import top.cyixlq.cy.net.callback.IFailure;
import top.cyixlq.cy.net.callback.ISuccess;

public class TestDelegate extends CyDelegate {
    @Override
    public Object setLayout() {
        return R.layout.test_delegate;
    }

    @Override
    public void onBindeView(@Nullable Bundle savedInstanceState, View rootView) {
        testRestClient();
    }

    private void testRestClient(){
        RestClient.builder()
                .url("http://127.0.0.1/index")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                       Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        
                    }
                })
                .build()
                .get();
    }
}
