package com.fanap.corepos.device.hsm.sayan.amp.other;

public class AmpConstant {
    /**
     * 更新视图显示
     */
    public static final int UPDATE_KEYBOARD_VIEW = 0x00;
    /**
     * 窗口添加
     */
    public static final int ADD_WINDOW = 0x01;
    /**
     * 窗口移除
     */
    public static final int REMOVE_WINDOW = 0x02;
    /**
     * PIN按键显示
     */
    public static final int KEYMAP_DISPLAYED = 0x03;
    /**
     * 按PIN时候符号变化
     */
    public static final int SYMBOL_DISPLAYED = 0x04;
    /**
     *
     */
    public static final int RUN_KEYLISTENER = 0x05;
    /**
     * PED 空闲
     */
    public static final int PIN_STATUS_IDLE = 0;
    /**
     * PIN输入状态
     */
    public static final int PIN_STATUS_INPUT_KEY = 1;
    /**
     * PIN输入完成确定
     */
    public static final int PIN_STATUS_READY = 2;
    /**
     * 无效的确定
     */
    public static final int PIN_STATUS_INVALID_ENTER = 3;
    /**
     * 取消键
     */
    public static final int PIN_STATUS_CANCEL = 4;
    /**
     * 无效的退格
     */
    public static final int PIN_STATUS_INVALID_BACKSPACE = 5;
    /**
     * PIN输入满
     */
    public static final int PIN_STATUS_FULL = 6;

}
