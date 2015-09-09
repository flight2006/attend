package com.hdu.weixinmenu;

/**
 * 复杂按钮（父按钮）
 * Created by Flight on 2015/8/1.
 */

public class ComplexButton extends Button {
    private Button[] sub_button;

    public Button[] getSub_button() {
        return sub_button;
    }

    public void setSub_button(Button[] sub_button) {
        this.sub_button = sub_button;
    }
}