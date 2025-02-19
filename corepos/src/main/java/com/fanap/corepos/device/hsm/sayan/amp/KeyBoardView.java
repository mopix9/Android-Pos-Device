package com.fanap.corepos.device.hsm.sayan.amp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import com.fanap.corepos.R;
import com.secure.api.PinKeyInfo;

public class KeyBoardView extends View {
    private static final String TAG = "KeyBoardView";
    private static final int KEY_CANCEL = 223;
    private static final int KEY_CLEAR = 355;
    private static final int KEY_ENTER = 28;
    //键盘布局4列
    private static final int COLUMN = 4;
    //键盘布局4行
    private static final int ROW = 4;
    //功能键在右侧
    private static final int FUNC_KEYS_RIGHT = 0;
    //功能键在左侧
    private static final int FUNC_KEYS_LEFT = 1;

    //keymap是否显示完成
    private boolean keyMapDisplayed = false;
    private Paint mPaint;
    private int keyWidth;
    private int keyHeight;

    //取消键
    private Rect cancelKey = new Rect();
    //退格键
    private Rect clearKey = new Rect();
    //确定键
    private Rect enterKey = new Rect();
    //按键信息定义
    private PinKeyInfo key0 = new PinKeyInfo();
    private PinKeyInfo key1 = new PinKeyInfo();
    private PinKeyInfo key2 = new PinKeyInfo();
    private PinKeyInfo key3 = new PinKeyInfo();
    private PinKeyInfo key4 = new PinKeyInfo();
    private PinKeyInfo key5 = new PinKeyInfo();
    private PinKeyInfo key6 = new PinKeyInfo();
    private PinKeyInfo key7 = new PinKeyInfo();
    private PinKeyInfo key8 = new PinKeyInfo();
    private PinKeyInfo key9 = new PinKeyInfo();
    private PinKeyInfo keyCancel = new PinKeyInfo();
    private PinKeyInfo keyClear = new PinKeyInfo();
    private PinKeyInfo keyEnter = new PinKeyInfo();

    private Typeface boldTypeface;
    private Typeface normalTypeface;


    private PinKeyInfo[] keyArray = new PinKeyInfo[]
            {
                    key0, key1, key2, key3, key4, key5, key6, key7, key8, key9, keyCancel, keyClear, keyEnter
            };

    private final Object mutex = new Object();
    private int scaleLocation = 0;
    private int marginLeft = 0;
    private int funcKeySide = FUNC_KEYS_RIGHT;
    private boolean cancelKeyIsTop = true;

    public KeyBoardView(Context context) {
        super(context);
    }

    public KeyBoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        boldTypeface = Typeface.createFromAsset(context.getAssets(),"fonts/iransansbold.ttf");
        normalTypeface = Typeface.createFromAsset(context.getAssets(),"fonts/iransans.ttf");

        mPaint.setTypeface(normalTypeface);
    }

    /**
     * 获得相对屏幕的垂直位置
     *
     * @return 返回垂直位置
     */
    private int getAbsoluteY() {
        int absoluteY = getTop();
        return absoluteY;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //Log.d(TAG, "***************************onDraw*************************");
        int width = getWidth();
        int height = getHeight();
        keyWidth = (width - scaleLocation - marginLeft) / COLUMN;
        keyHeight = (height - scaleLocation - marginLeft) / ROW;

        mPaint.setColor(getResources().getColor(R.color.divide_line));
        mPaint.setStrokeWidth(1.0f);

        //水平分割线坐标X,Y的起点和终点
        int horizontalLineStartX = marginLeft;
        int horizontalLineStartY = scaleLocation;
        int horizontalLineStopX = marginLeft + (COLUMN - 1) * keyWidth;
        int horizontalLineStopY = scaleLocation;

        if (funcKeySide == FUNC_KEYS_LEFT) {
            horizontalLineStartX = width / 4 + marginLeft;
            horizontalLineStopX = horizontalLineStartX + 3 * keyWidth;
        }
        for (int row = 0; row <= ROW; row++) { //绘制水平方向分割线
            canvas.drawLine(horizontalLineStartX, horizontalLineStartY, horizontalLineStopX, horizontalLineStopY, mPaint);
            horizontalLineStartY += keyHeight;
            horizontalLineStopY = horizontalLineStartY;
        }

        //垂直分割线坐标X,Y的起点和终点
        int verticalLineStartX = marginLeft;
        int verticalLineStartY = scaleLocation;
        int verticalLineStopX = marginLeft;
        int verticalLineStopY = scaleLocation + 4 * keyHeight;
        if (funcKeySide == FUNC_KEYS_LEFT) {
            verticalLineStartX = width / 4 + marginLeft;
            verticalLineStopX = verticalLineStartX;
        }
        for (int column = 0; column < COLUMN; column++) { //绘制垂直方向分割线
            canvas.drawLine(verticalLineStartX, verticalLineStartY, verticalLineStopX, verticalLineStopY, mPaint);
            verticalLineStartX += keyWidth;
            verticalLineStopX = verticalLineStartX;
        }

        int x = 0;
        int funKeyX = 3 * getWidth() / 4;
        int funKeyW = getWidth() / 4;
        int funKeyH = getHeight() / 4;
        //绘制功能键
        switch (funcKeySide) {
            case FUNC_KEYS_RIGHT:
                cancelKey.set(funKeyX, 0, width, funKeyH);
                clearKey.set(funKeyX, funKeyH, width, 2 * funKeyH);
                enterKey.set(funKeyX, 2 * funKeyH, width, 4 * funKeyH);
                x = width - cancelKey.width() / 2;
                break;
            case FUNC_KEYS_LEFT:
                cancelKey.set(0, 0, funKeyW, funKeyH);
                clearKey.set(0, funKeyH, funKeyW, 2 * funKeyH);
                enterKey.set(0, 2 * funKeyH, funKeyW, 4 * funKeyH);
                x = cancelKey.width() / 2;
                break;
            default:
                x = width - cancelKey.width() / 2;
                break;
        }

        mPaint.setColor(getResources().getColor(R.color.cansel));
        canvas.drawRect(cancelKey, mPaint);
        mPaint.setColor(getResources().getColor(R.color.white));
        mPaint.setTextSize(getResources().getDimension(R.dimen.pub_text_size_20));
        mPaint.setTextAlign(Paint.Align.CENTER);


        Paint.FontMetricsInt fontMetrics = mPaint.getFontMetricsInt();
        float top = fontMetrics.top;//为基线到字体上边框的距离
        float bottom = fontMetrics.bottom;//为基线到字体下边框的距离
        int baseLineY = (int) (cancelKey.centerY() - top / 2 - bottom / 2);//基线中间点的y轴计算公式
        canvas.drawText("بستن", x, baseLineY, mPaint);


        mPaint.setColor(getResources().getColor(R.color.yellow));
        canvas.drawRect(clearKey, mPaint);
        mPaint.setColor(getResources().getColor(R.color.white));
        mPaint.setTextSize(getResources().getDimension(R.dimen.pub_text_size_20));
        mPaint.setTextAlign(Paint.Align.CENTER);
        baseLineY = (int) (clearKey.centerY() - top / 2 - bottom / 2);
        canvas.drawText("پاک کردن", x, baseLineY, mPaint);

        mPaint.setColor(getResources().getColor(R.color.turquoise));
        canvas.drawRect(enterKey, mPaint);
        mPaint.setColor(getResources().getColor(R.color.white));
        mPaint.setTextSize(getResources().getDimension(R.dimen.pub_text_size_20));
        mPaint.setTextAlign(Paint.Align.CENTER);
        baseLineY = (int) (enterKey.centerY() - top / 2 - bottom / 2);
        canvas.drawText("تایید", x, baseLineY, mPaint);

        if (!keyMapDisplayed) {
            generateKeyMap(cancelKeyIsTop, funcKeySide);
        } else {
            updateKeyMap(canvas);
        }
    }

    /**
     * 生成按键的布局
     */
    private void generateKeyMap(boolean cancelKeyIsTop, int funcKeySide) {
        int k = 0;
        int w = getWidth() / 4;
        for (int i = 0; i < ROW - 1; i++) {//行
            int y = getAbsoluteY() + scaleLocation + i * keyHeight;
            for (int j = 0; j < COLUMN - 1; j++) {//列
                int x = j * keyWidth;
                if (funcKeySide == FUNC_KEYS_RIGHT) {
                    x = marginLeft + j * keyWidth;
                } else if (funcKeySide == FUNC_KEYS_LEFT) {
                    x = marginLeft + j * keyWidth + w;
                }
                keyArray[k].setX(x);
                keyArray[k].setY(y);
                keyArray[k].setWidth(keyWidth);
                keyArray[k].setHeight(keyHeight);
                k++;
            }
        }
        //功能键X坐标
        int funcKeyX = 3 * getWidth() / 4;
        if (funcKeySide == FUNC_KEYS_RIGHT) {
            key9.setX(marginLeft + keyWidth);
            funcKeyX = 3 * getWidth() / 4;
        } else if (funcKeySide == FUNC_KEYS_LEFT) {
            key9.setX(marginLeft + keyWidth + w);
            funcKeyX = 0;
        }
        key9.setY(getAbsoluteY() + scaleLocation + 3 * keyHeight);
        key9.setWidth(keyWidth);
        key9.setHeight(keyHeight);

        int funcKeyWidth = getWidth() / 4;
        int funKeyHeight = getHeight() / 4;
        keyCancel.setX(funcKeyX);
        keyCancel.setY(getAbsoluteY());
        keyCancel.setWidth(funcKeyWidth);
        keyCancel.setHeight(funKeyHeight);
        keyCancel.setKeyValue(cancelKeyIsTop ? KEY_CANCEL : KEY_CLEAR);

        keyClear.setX(funcKeyX);
        keyClear.setY(getAbsoluteY() + funKeyHeight);
        keyClear.setWidth(funcKeyWidth);
        keyClear.setHeight(funKeyHeight);
        keyClear.setKeyValue(cancelKeyIsTop ? KEY_CLEAR : KEY_CANCEL);

        keyEnter.setX(funcKeyX);
        keyEnter.setY(getAbsoluteY() + 2 * funKeyHeight);
        keyEnter.setWidth(funcKeyWidth);
        keyEnter.setHeight(2 * funKeyHeight);
        keyEnter.setKeyValue(KEY_ENTER);

        keyMapDisplayed = true;

        synchronized (mutex) {
            mutex.notifyAll();
        }
    }

    /**
     * 获取按键的坐标布局,在子线程中调用
     */
    protected PinKeyInfo[] getKeyMap() {
        synchronized (mutex) {
            try {
                mutex.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return keyArray;
    }

    protected void setKeyMap(PinKeyInfo[] keys) {
        keyArray = keys;
        this.invalidate();
    }

    protected void updateKeyMap(Canvas canvas) {
        mPaint.setColor(getResources().getColor(R.color.black));
        mPaint.setTextSize(getResources().getDimension(R.dimen.pub_text_size_30));
        mPaint.setTextAlign(Paint.Align.CENTER);

        Paint.FontMetricsInt fontMetrics = mPaint.getFontMetricsInt();
        float top = fontMetrics.top;//为基线到字体上边框的距离
        float bottom = fontMetrics.bottom;//为基线到字体下边框的距离

        for (PinKeyInfo info : keyArray) {
            int x = info.getX() + info.getWidth() / 2;
            int baseLineY = (int) (info.getY() - getAbsoluteY() + info.getHeight() / 2 - top / 2 - bottom / 2);
            //Log.d(TAG, "x:" + x + "; baseLineY:" + baseLineY);
            int keyValue = info.getKeyValue();
            if (keyValue != KEY_CANCEL && keyValue != KEY_CLEAR && keyValue != KEY_ENTER) {
                canvas.drawText(String.valueOf(info.getKeyValue()), x, baseLineY, mPaint);
            }
            //Log.d(TAG, "x:" + info.getX() + "; y:" + info.getY() + "; w:" + info.getWidth() + ";h:" + info.getHeight() + ";val:" + info.getKeyValue());
        }
    }
}
