package com.masa.aryan.presentation;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.Display;

import java.lang.reflect.Constructor;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;


/**
 * Created by @author chencz on 2020/9/2.
 */
public class PresentationManager {
    public static PresentationManager INSTANCE;
    private Handler handler = new Handler(Looper.getMainLooper());

    private Context context = getInstance().context;
    private LinkedList<BasePresentation> presentationStack = new LinkedList<>();
    private Display display;
    private List<String> customerAdList;

     PresentationManager() {

    }

    public static PresentationManager getInstance() {
        if (INSTANCE == null) {
            synchronized (PresentationManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new PresentationManager();
                }
            }
        }
        return INSTANCE;
    }

     boolean initDisplays() {
        if (PresentationHelper.hasOtherDisplay(context)) {
            Display[] displays = PresentationHelper.getDisplayList(context);
            display = displays[displays.length - 1];
            return true;
        }
        return false;
    }

     BasePresentation createPresentation(Class<? extends BasePresentation> presentationClazz){
        BasePresentation presentation = null;
        try {
            Constructor constructor = presentationClazz.getDeclaredConstructor(new Class[]{Context.class, Display.class});
            presentation = (BasePresentation)constructor.newInstance(new Object[]{context, display});
            constructor.setAccessible(true);
            //presentation.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return presentation;
    }


    /**
     * 往Presentation栈里新增一个新的presentation
     * 如果栈顶的Presentation和待新增的是一样类型的，则不新增
     */
    public void add(Class<? extends BasePresentation> presentationClazz){
        if (!canUsePresentation()) {
            return;
        }
        boolean initSuccess = initDisplays();
        if (initSuccess) {
            boolean needCreate = true;
            if(!isStackEmpty()){
                BasePresentation topPresentation = presentationStack.peek();
                if(topPresentation.getClass() == presentationClazz){
                    needCreate = false;
                }
            }
            BasePresentation presentation;
            if(needCreate){
                presentation = createPresentation(presentationClazz);
                presentationStack.push(presentation);
            } else {
                presentation = presentationStack.peek();
            }
            if(presentation != null){
                presentation.show();
            }
        }
    }

    /**
     * 往Presentation栈里新增一个新的presentation
     * 不管栈顶的Presentation和待新增的是否是一样类型的，都新增
     */
    public void addIngnoreSame(Class<? extends BasePresentation> presentationClazz){
        if (!canUsePresentation()) {
            return;
        }
        boolean initSuccess = initDisplays();
        if (initSuccess) {
            BasePresentation presentation = createPresentation(presentationClazz);
            if(presentation != null){
                presentationStack.push(presentation);
                presentation.show();
            }
        }
    }

    public void replaceIgnoreSame(Class<? extends BasePresentation> presentationClazz){
        if (!canUsePresentation()) {
            return;
        }
        boolean initSuccess = initDisplays();
        if (initSuccess) {
            popAll();
            BasePresentation presentation = createPresentation(presentationClazz);
            if(presentation != null){
                presentationStack.push(presentation);
                presentation.show();
            }
        }
    }

    /**
     * 把栈顶的Presentation弹出并且dismiss掉
     */
    public void pop() {
        if (!canUsePresentation()) {
            return;
        }
        if(!isStackEmpty()){
            BasePresentation presentation = presentationStack.pop();
            presentation.dismiss();
        }
    }

    /**
     * 把栈顶的指定Presentation弹出并且dismiss掉
     */
    public void pop(Class<? extends BasePresentation> presentationClazz){
        if (!canUsePresentation()) {
            return;
        }
        if(!isStackEmpty()){
            BasePresentation topPresentation = presentationStack.peek();
            if(topPresentation.getClass() == presentationClazz){
                presentationStack.pop();
                topPresentation.dismiss();
            }
        }
    }

    /**
     * 把栈里面所有Presentation都弹出并且dismiss掉
     */
    public void popAll(){
        if (!canUsePresentation()) {
            return;
        }
        while(!isStackEmpty()) {//栈内元素都清掉
            BasePresentation presentation = presentationStack.pop();
            presentation.dismiss();
        }
    }

    public BasePresentation getCurrent(){
        if (!canUsePresentation()) {
            return null;
        }
        if(!isStackEmpty()){
            return presentationStack.peek();
        }
        return null;
    }

    private boolean isStackEmpty(){
        if(presentationStack.size() > 0){
            return false;
        }
        return true;
    }

    private boolean canUsePresentation() {
        if (!PresentationHelper.isSupportPresentation() || !PresentationHelper.isPermitForPresentation(context)) {
            return false;
        }
        return true;
    }

//    public void setCustomerAdList(FaceInfo faceInfo) {
//        customerAdList = new ArrayList<>();
//        List<AdModel> adList = faceInfo.getAdList();
//        for (AdModel ad : adList) {
//            customerAdList.add(ad.getImgName());
//        }
//    }

//    public List<String> getCustomerAdList() {
//        return customerAdList;
//    }
}
