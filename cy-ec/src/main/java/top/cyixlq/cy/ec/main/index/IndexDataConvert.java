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
        final JSONObject dataObject = JSON.parseObject(getJsonData()).getJSONObject("data");
        //先获取轮播图的数组并且组成轮播图
        JSONArray banners = dataObject.getJSONArray("banners");
        final int bannerSize = banners.size();
        final ArrayList<String> bannerImages = new ArrayList<>();
        for(int i=0;i<bannerSize;i++){
            final String imageUrl = banners.getJSONObject(i).getString("imageUrl");
            bannerImages.add(imageUrl);
        }
        final MultipleItemEntity bannerEntity = MultipleItemEntity.builder()
                .setField(MultipleFields.ITEM_TYPE, ItemType.BANNER)
                .setField(MultipleFields.SPAN_SIZE, 4)
                .setField(MultipleFields.BANNERS, bannerImages)
                .build();
        ENTYTIES.add(bannerEntity);
        //之后获取商品的数据并且组成商品列表
        JSONArray products = dataObject.getJSONArray("otherProducts");
        final int productSize = products.size();
        for (int i = 0; i < productSize; i++) {
            final JSONObject data = products.getJSONObject(i);
            final String imgUrl = data.getString("imageUrl");
            final String text = data.getString("name");
            final int sort = data.getInteger("sort");
            final String id = data.getString("id");
            int type = 0;
            int spanSize = 0;
            if (sort == 1) {
                type = ItemType.TEXT_IMAGE;
                spanSize = 4;

            } else if (sort == 2) {
                type = ItemType.TEXT_IMAGE;
                spanSize = 2;
            }
            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, type)
                    .setField(MultipleFields.SPAN_SIZE, spanSize)
                    .setField(MultipleFields.ID, id)
                    .setField(MultipleFields.TEXT, text)
                    .setField(MultipleFields.IMAGE_URL, imgUrl)
                    .build();
            ENTYTIES.add(entity);
        }
        return ENTYTIES;
    }
}
