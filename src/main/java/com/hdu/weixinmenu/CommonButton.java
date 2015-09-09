package com.hdu.weixinmenu;

/**
 * 普通按钮（子按钮）
 * Created by Flight on 2015/8/1.
 */
public class CommonButton extends Button {
    private String type;
    private String key;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}