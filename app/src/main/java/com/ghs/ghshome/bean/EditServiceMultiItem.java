package com.ghs.ghshome.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Author:wang_sir
 * Time:2019/5/21 17:27
 * Description:This is EditServiceMultiItem
 */
public class EditServiceMultiItem implements MultiItemEntity {
    public static final int EDIT_SERVER_TITLE = 1;//编辑服务 标题
    public static final int EDIT_SERVER_CONTENT = 2;//内容
    private int itemType;
    private Object object;

    public EditServiceMultiItem(int itemType, Object object) {
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
