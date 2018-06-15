package top.cyixlq.cy.ec.main.cart;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.List;

import top.cyixlq.cy.app.Cy;
import top.cyixlq.cy.ec.R;
import top.cyixlq.cy.ui.recycler.MultipleFields;
import top.cyixlq.cy.ui.recycler.MultipleItemEntity;
import top.cyixlq.cy.ui.recycler.MultipleRecyclerAdapter;
import top.cyixlq.cy.ui.recycler.MultipleViewHolder;

public class ShopCartAdapter extends MultipleRecyclerAdapter {

    private boolean mIsSelectedAll = false;
    private double mTotalPrice = 0.00;

    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .dontAnimate();

    protected ShopCartAdapter(List<MultipleItemEntity> data) {
        super(data);
        //初始化总价
        for (MultipleItemEntity entity : data) {
            final double price = entity.getField(ShopCartItemFields.PRICE);
            final int count = entity.getField(ShopCartItemFields.NUMBER);
            final double total = price * count;
            mTotalPrice = mTotalPrice + total;
        }
        //添加购物车item布局
        addItemType(ShopCartItemType.SHOP_CART_ITEM, R.layout.item_shop_cart);
    }

    public void setIsSelectedAll(boolean isSelectedAll) {
        this.mIsSelectedAll = isSelectedAll;
    }

    public double getTotalPrice() {
        return mTotalPrice;
    }

    @Override
    protected void convert(MultipleViewHolder holder, final MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()){
            case ShopCartItemType.SHOP_CART_ITEM:
                //获取值
                final int id = entity.getField(MultipleFields.ID);
                final String imgUrl = entity.getField(MultipleFields.IMAGE_URL);
                final String desc = entity.getField(ShopCartItemFields.DESC);
                final String name = entity.getField(ShopCartItemFields.TITLE);
                final int number = entity.getField(ShopCartItemFields.NUMBER);
                final double price = entity.getField(ShopCartItemFields.PRICE);

                //获取控件
                final AppCompatImageView img = holder.getView(R.id.image_item_shop_cart);
                final AppCompatTextView tvName = holder.getView(R.id.tv_item_shop_cart_title);
                final AppCompatTextView tvDesc = holder.getView(R.id.tv_item_shop_cart_desc);
                final AppCompatTextView tvPrice = holder.getView(R.id.tv_item_shop_cart_price);
                final IconTextView iconMinus = holder.getView(R.id.icon_item_minus);
                final IconTextView iconPlus = holder.getView(R.id.icon_item_plus);
                final AppCompatTextView tvNumber = holder.getView(R.id.tv_item_shop_cart_count);
                final IconTextView iconIsSelected = holder.getView(R.id.icon_item_shop_cart);

                //赋值
                tvName.setText(name);
                tvDesc.setText(desc);
                tvPrice.setText(String.valueOf(price));
                tvNumber.setText(String.valueOf(number));
                Glide.with(mContext)
                        .load(imgUrl)
                        .apply(OPTIONS)
                        .into(img);

                //在左侧勾勾渲染之前改变全选与否状态
                entity.setField(ShopCartItemFields.IS_SELECTED, mIsSelectedAll);
                final boolean isSelected = entity.getField(ShopCartItemFields.IS_SELECTED);

                //根据数据状态显示左侧勾选状态
                if (isSelected) {
                    iconIsSelected.setTextColor
                            (ContextCompat.getColor(Cy.getApplicationContext(), R.color.app_main));
                } else {
                    iconIsSelected.setTextColor(Color.GRAY);
                }

                //给勾选按钮添加点击事件
                iconIsSelected.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final boolean currentSelected = entity.getField(ShopCartItemFields.IS_SELECTED);
                        if (currentSelected) {
                            iconIsSelected.setTextColor(Color.GRAY);
                            entity.setField(ShopCartItemFields.IS_SELECTED, false);
                        } else {
                            iconIsSelected.setTextColor
                                    (ContextCompat.getColor(Cy.getApplicationContext(), R.color.app_main));
                            entity.setField(ShopCartItemFields.IS_SELECTED, true);
                        }
                    }
                });
                break;
            default:
                break;
        }
    }
}
