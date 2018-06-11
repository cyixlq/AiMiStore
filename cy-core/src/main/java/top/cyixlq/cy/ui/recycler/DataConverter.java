package top.cyixlq.cy.ui.recycler;

import java.util.ArrayList;

public abstract class DataConverter {
    protected final ArrayList<MultipleItemEntity> ENTYTIES = new ArrayList<>();
    private String mJsonData;

    public abstract ArrayList<MultipleItemEntity> convert();

    public DataConverter setJsonData(String json){
        this.mJsonData = json;
        return this;
    }

    protected String getJsonData(){
        if(mJsonData == null || mJsonData.isEmpty()){
            throw new NullPointerException("json数据为空！");
        }
        return mJsonData;
    }
}
