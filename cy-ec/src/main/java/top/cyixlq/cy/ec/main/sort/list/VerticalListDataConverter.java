package top.cyixlq.cy.ec.main.sort.list;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

import top.cyixlq.cy.ui.recycler.DataConverter;
import top.cyixlq.cy.ui.recycler.ItemType;
import top.cyixlq.cy.ui.recycler.MultipleFields;
import top.cyixlq.cy.ui.recycler.MultipleItemEntity;

public final class VerticalListDataConverter extends DataConverter {

    @Override
    public ArrayList<MultipleItemEntity> convert() {

        final ArrayList<MultipleItemEntity> dataList = new ArrayList<>();
        final JSONArray dataArray = JSON.parseObject(getJsonData()).getJSONArray("data");
        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final String name = data.getString("name");
            final int id = data.getInteger("id");

            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, ItemType.VERTICAL_MENU_LIST)
                    .setField(MultipleFields.ID, id)
                    .setField(MultipleFields.TEXT, name)
                    .setField(MultipleFields.TAG, false)
                    .build();
            dataList.add(entity);
        }
        //设置第一个被选中
        dataList.get(0).setField(MultipleFields.TAG, true);

        return dataList;
    }
}
