package com.masa.aryan.databinding;
import com.masa.aryan.R;
import com.masa.aryan.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentBalanceSucessBindingImpl extends FragmentBalanceSucessBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.back, 1);
        sViewsWithIds.put(R.id.title, 2);
        sViewsWithIds.put(R.id.subtitle, 3);
        sViewsWithIds.put(R.id.txt_title, 4);
        sViewsWithIds.put(R.id.lnr_details, 5);
        sViewsWithIds.put(R.id.txt_balance, 6);
        sViewsWithIds.put(R.id.txt_bank_name, 7);
        sViewsWithIds.put(R.id.txt_card, 8);
        sViewsWithIds.put(R.id.txt_datetime, 9);
        sViewsWithIds.put(R.id.txt_timer, 10);
        sViewsWithIds.put(R.id.bottom, 11);
        sViewsWithIds.put(R.id.btn_print, 12);
        sViewsWithIds.put(R.id.avl, 13);
        sViewsWithIds.put(R.id.btn_cancel, 14);
    }
    // views
    @NonNull
    private final android.widget.FrameLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentBalanceSucessBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 15, sIncludes, sViewsWithIds));
    }
    private FragmentBalanceSucessBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (com.wang.avi.AVLoadingIndicatorView) bindings[13]
            , (android.widget.ImageButton) bindings[1]
            , (android.widget.LinearLayout) bindings[11]
            , (android.widget.Button) bindings[14]
            , (android.widget.Button) bindings[12]
            , (android.widget.LinearLayout) bindings[5]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[8]
            , (android.widget.TextView) bindings[9]
            , (android.widget.TextView) bindings[10]
            , (android.widget.TextView) bindings[4]
            );
        this.mboundView0 = (android.widget.FrameLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x1L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
            return variableSet;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        // batch finished
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}