package top.cyixlq.cy.ec.main.cart;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

import top.cyixlq.cy.ui.recycler.DataConverter;
import top.cyixlq.cy.ui.recycler.ItemType;
import top.cyixlq.cy.ui.recycler.MultipleFields;
import top.cyixlq.cy.ui.recycler.MultipleItemEntity;

public class ShopCartDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {

        final ArrayList<MultipleItemEntity> dataList = new ArrayList<>();
        JSONArray dataArray = JSON.parseObject(getJsonData()).getJSONArray("data");
        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final String imgUrl = data.getString("imageUrl");
            final String desc = data.getString("detail");
            final String name = data.getString("name");
            final int id = data.getInteger("id");
            final int number = data.getInteger("number");
            final double price = data.getDouble("price");
            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, ShopCartItemType.SHOP_CART_ITEM)
                    .setField(MultipleFields.ID, id)
                    .setField(ShopCartItemFields.TITLE, name)
                    .setField(MultipleFields.IMAGE_URL, imgUrl)
                    .setField(ShopCartItemFields.DESC, desc)
                    .setField(ShopCartItemFields.NUMBER, number)
                    .setField(ShopCartItemFields.PRICE, price)
                    .setField(ShopCartItemFields.IS_SELECTED, false)
                    .build();
            dataList.add(entity);
        }
        return dataList;
    }
}
