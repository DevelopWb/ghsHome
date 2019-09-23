package com.ghs.ghshome.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Author:wang_sir
 * Time:2019/5/21 17:27
 * Description:This is EditServiceMultiItem
 */
public class MultiItemBean implements MultiItemEntity {
    public static final int MINE_FRAGMENT_DIVIDER = 1;//我的模块分割线
    public static final int MINE_FRAGMENT_CONTENT = 2;//我的模块内容
    public static final int SELECT_CITY_DIVIDER = 1;//选择城市
    public static final int SELECT_CITY_CONTENT = 2;//选择城市内容
    private int itemType;
    private Object object;

    public MultiItemBean(int itemType, Object object) {
        this.itemType = itemType;
        this.object = object;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
