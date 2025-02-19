package com.masa.aryan.databinding;
import com.masa.aryan.R;
import com.masa.aryan.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentTotalReportBindingImpl extends FragmentTotalReportBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.top_rtl, 1);
        sViewsWithIds.put(R.id.back, 2);
        sViewsWithIds.put(R.id.rtl_date_picker, 3);
        sViewsWithIds.put(R.id.btn_start_date, 4);
        sViewsWithIds.put(R.id.txt_start_date, 5);
        sViewsWithIds.put(R.id.btn_end_date, 6);
        sViewsWithIds.put(R.id.txt_end_date, 7);
        sViewsWithIds.put(R.id.lnr_data, 8);
        sViewsWithIds.put(R.id.success_buy, 9);
        sViewsWithIds.put(R.id.sum_buy, 10);
        sViewsWithIds.put(R.id.success_bill, 11);
        sViewsWithIds.put(R.id.sum_bill, 12);
        sViewsWithIds.put(R.id.success_charge, 13);
        sViewsWithIds.put(R.id.sum_charge, 14);
        sViewsWithIds.put(R.id.success_topup, 15);
        sViewsWithIds.put(R.id.sum_topup, 16);
        sViewsWithIds.put(R.id.txt_empty, 17);
        sViewsWithIds.put(R.id.btn_print_receipt, 18);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentTotalReportBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 19, sIncludes, sViewsWithIds));
    }
    private FragmentTotalReportBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.ImageButton) bindings[2]
            , (android.widget.RelativeLayout) bindings[6]
            , (com.google.android.material.floatingactionbutton.FloatingActionButton) bindings[18]
            , (android.widget.RelativeLayout) bindings[4]
            , (android.widget.LinearLayout) bindings[8]
            , (android.widget.RelativeLayout) bindings[3]
            , (android.widget.TextView) bindings[11]
            , (android.widget.TextView) bindings[9]
            , (android.widget.TextView) bindings[13]
            , (android.widget.TextView) bindings[15]
            , (android.widget.TextView) bindings[12]
            , (android.widget.TextView) bindings[10]
            , (android.widget.TextView) bindings[14]
            , (android.widget.TextView) bindings[16]
            , (android.widget.RelativeLayout) bindings[1]
            , (android.widget.TextView) bindings[17]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[5]
            );
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
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