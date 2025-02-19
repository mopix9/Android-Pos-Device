package com.masa.aryan.databinding;
import com.masa.aryan.R;
import com.masa.aryan.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentBuySuccessBindingImpl extends FragmentBuySuccessBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.top_rtl, 1);
        sViewsWithIds.put(R.id.back, 2);
        sViewsWithIds.put(R.id.txtDateTime, 3);
        sViewsWithIds.put(R.id.txtAmount, 4);
        sViewsWithIds.put(R.id.txtBankName, 5);
        sViewsWithIds.put(R.id.txtCardNumber, 6);
        sViewsWithIds.put(R.id.txtTrackingNumber, 7);
        sViewsWithIds.put(R.id.txtReferenceNumberc, 8);
        sViewsWithIds.put(R.id.txtReferenceNumber, 9);
        sViewsWithIds.put(R.id.txt_timer, 10);
        sViewsWithIds.put(R.id.recivedAmount, 11);
        sViewsWithIds.put(R.id.bottom, 12);
        sViewsWithIds.put(R.id.cancel, 13);
        sViewsWithIds.put(R.id.seller_receipt, 14);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentBuySuccessBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 15, sIncludes, sViewsWithIds));
    }
    private FragmentBuySuccessBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.ImageButton) bindings[2]
            , (android.widget.LinearLayout) bindings[12]
            , (android.widget.Button) bindings[13]
            , (android.widget.TextView) bindings[11]
            , (android.widget.Button) bindings[14]
            , (android.widget.RelativeLayout) bindings[1]
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[9]
            , (android.widget.TextView) bindings[8]
            , (android.widget.TextView) bindings[10]
            , (android.widget.TextView) bindings[7]
            );
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
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
        if (BR.viewModel == variableId) {
            setViewModel((com.masa.aryan.buy.viewmodel.BuySuccessViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setViewModel(@Nullable com.masa.aryan.buy.viewmodel.BuySuccessViewModel ViewModel) {
        this.mViewModel = ViewModel;
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
        flag 0 (0x1L): viewModel
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}