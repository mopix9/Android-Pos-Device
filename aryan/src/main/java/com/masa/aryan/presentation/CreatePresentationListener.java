package com.masa.aryan.presentation;

/**
 * Created by @author chencz on 2018/7/17.
 */

public interface CreatePresentationListener {
    /**
     * 该方法只是用于返回该页面所需要的Layout的资源ID。
     * @return 如：R.layout.activity_main
     */
    int initLayout();


    /**
     * 初始化window属性
     * 如：
     * getWindow().setFormat(PixelFormat.TRANSLUCENT);
     * getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
     */
    void initWindowAttribute();

    /**
     * 该方法用于初始化界面上的控件
     */
    void initView();

    /**
     * 该方法用于初始化控件属性以及界面上的一些数据的处理
     */
    void initData();
}
