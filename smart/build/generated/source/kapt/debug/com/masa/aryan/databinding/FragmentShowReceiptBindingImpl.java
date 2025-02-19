package com.masa.aryan.databinding;
import com.masa.aryan.R;
import com.masa.aryan.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentShowReceiptBindingImpl extends FragmentShowReceiptBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.top_rtl, 1);
        sViewsWithIds.put(R.id.back, 2);
        sViewsWithIds.put(R.id.card, 3);
        sViewsWithIds.put(R.id.txt_title, 4);
        sViewsWithIds.put(R.id.txt_bank, 5);
        sViewsWithIds.put(R.id.txt_card, 6);
        sViewsWithIds.put(R.id.txt_date, 7);
        sViewsWithIds.put(R.id.txt_time, 8);
        sViewsWithIds.put(R.id.txt_rrn, 9);
        sViewsWithIds.put(R.id.txt_rrn_value, 10);
        sViewsWithIds.put(R.id.txt_response, 11);
        sViewsWithIds.put(R.id.row1, 12);
        sViewsWithIds.put(R.id.txt_row1, 13);
        sViewsWithIds.put(R.id.txt_row1_value, 14);
        sViewsWithIds.put(R.id.row2, 15);
        sViewsWithIds.put(R.id.txt_row2, 16);
        sViewsWithIds.put(R.id.txt_row2_value, 17);
        sViewsWithIds.put(R.id.row3, 18);
        sViewsWithIds.put(R.id.txt_row3, 19);
        sViewsWithIds.put(R.id.txt_row3_value, 20);
        sViewsWithIds.put(R.id.row4, 21);
        sViewsWithIds.put(R.id.txt_row4, 22);
        sViewsWithIds.put(R.id.txt_row4_value, 23);
        sViewsWithIds.put(R.id.btn_print_receipt, 24);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentShowReceiptBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 25, sIncludes, sViewsWithIds));
    }
    private FragmentShowReceiptBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.ImageButton) bindings[2]
            , (com.google.android.material.floatingactionbutton.FloatingActionButton) bindings[24]
            , (android.widget.LinearLayout) bindings[3]
            , (android.widget.LinearLayout) bindings[12]
            , (android.widget.LinearLayout) bindings[15]
            , (android.widget.LinearLayout) bindings[18]
            , (android.widget.LinearLayout) bindings[21]
            , (android.widget.RelativeLayout) bindings[1]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[11]
            , (android.widget.TextView) bindings[13]
            , (android.widget.TextView) bindings[14]
            , (android.widget.TextView) bindings[16]
            , (android.widget.TextView) bindings[17]
            , (android.widget.TextView) bindings[19]
            , (android.widget.TextView) bindings[20]
            , (android.widget.TextView) bindings[22]
            , (android.widget.TextView) bindings[23]
            , (android.widget.TextView) bindings[9]
            , (android.widget.TextView) bindings[10]
            , (android.widget.TextView) bindings[8]
            , (android.widget.TextView) bindings[4]
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