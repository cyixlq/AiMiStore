package top.cyixlq.cy.ec.main.index;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

import top.cyixlq.cy.ui.recycler.DataConverter;
import top.cyixlq.cy.ui.recycler.ItemType;
import top.cyixlq.cy.ui.recycler.MultipleFields;
import top.cyixlq.cy.ui.recycler.MultipleItemEntity;

public class IndexDataConvert extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final JSONArray dataArray = JSON.parseObject(getJsonData()).getJSONArray("data");
        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final String imgUrl = data.getString("imageUrl");
            final String text = data.getString("text");
            final int spanSize = data.getInteger("spanSize");
            final int id = data.getInteger("goodsId");
            final JSONArray banners = data.getJSONArray("banners");

            final ArrayList<String> bannerImages = new ArrayList<>();
            int type = 0;
            if (imgUrl == null && text != null) {
                type = ItemType.TEXT;
            } else if (imgUrl != null && text == null) {
                type = ItemType.IMAGE;
            } else if (imgUrl != null) {
                type = ItemType.TEXT_IMAGE;
            } else if (banners != null) {
                type = ItemType.BANNER;
                final int bannerSize = banners.size();
                for(int j=0;j<bannerSize;j++){
                    final String banner = banners.getString(j);
                    bannerImages.add(banner);
                }
            }
            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, type)
                    .setField(MultipleFields.SPAN_SIZE, spanSize)
                    .setField(MultipleFields.ID, id)
                    .setField(MultipleFields.TEXT, text)
                    .setField(MultipleFields.IMAGE_URL, imgUrl)
                    .setField(MultipleFields.BANNERS, bannerImages)
                    .build();
            ENTYTIES.add(entity);
        }
        return ENTYTIES;
    }
}
