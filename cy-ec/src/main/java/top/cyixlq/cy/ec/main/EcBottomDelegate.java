package top.cyixlq.cy.ec.main;

import android.graphics.Color;

import java.util.LinkedHashMap;

import top.cyixlq.cy.delegates.bottom.BaseBottomDelegate;
import top.cyixlq.cy.delegates.bottom.BottomItemDelegate;
import top.cyixlq.cy.delegates.bottom.BottomTabBean;
import top.cyixlq.cy.delegates.bottom.ItemBuilder;
import top.cyixlq.cy.ec.main.index.IndexDelegate;
import top.cyixlq.cy.ec.main.sort.SortDelegate;

public class EcBottomDelegate extends BaseBottomDelegate {
    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}","首页"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}","分类"), new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}","发现"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}","购物车"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-user}","我的"), new IndexDelegate());
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }
}
