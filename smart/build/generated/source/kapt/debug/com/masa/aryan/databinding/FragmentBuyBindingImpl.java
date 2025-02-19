package com.masa.aryan.databinding;
import com.masa.aryan.R;
import com.masa.aryan.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentBuyBindingImpl extends FragmentBuyBinding implements com.masa.aryan.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.back, 3);
        sViewsWithIds.put(R.id.title, 4);
        sViewsWithIds.put(R.id.subtitle, 5);
        sViewsWithIds.put(R.id.frm, 6);
        sViewsWithIds.put(R.id.getAmount, 7);
        sViewsWithIds.put(R.id.cancel, 8);
    }
    // views
    // variables
    @Nullable
    private final android.view.View.OnClickListener mCallback15;
    // values
    // listeners
    // Inverse Binding Event Handlers
    private androidx.databinding.InverseBindingListener edtAmountandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of viewModel.amount.get()
            //         is viewModel.amount.set((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(edtAmount);
            // localize variables for thread safety
            // viewModel.amount != null
            boolean viewModelAmountJavaLangObjectNull = false;
            // viewModel.amount
            androidx.databinding.ObservableField<java.lang.String> viewModelAmount = null;
            // viewModel.amount.get()
            java.lang.String viewModelAmountGet = null;
            // viewModel
            com.masa.aryan.buy.viewmodel.BuyViewModel viewModel = mViewModel;
            // viewModel != null
            boolean viewModelJavaLangObjectNull = false;



            viewModelJavaLangObjectNull = (viewModel) != (null);
            if (viewModelJavaLangObjectNull) {


                viewModelAmount = viewModel.getAmount();

                viewModelAmountJavaLangObjectNull = (viewModelAmount) != (null);
                if (viewModelAmountJavaLangObjectNull) {




                    viewModelAmount.set(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };

    public FragmentBuyBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds));
    }
    private FragmentBuyBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (android.widget.ImageButton) bindings[3]
            , (android.widget.Button) bindings[8]
            , (android.widget.Button) bindings[2]
            , (android.widget.EditText) bindings[1]
            , (android.widget.FrameLayout) bindings[6]
            , (android.widget.TextView) bindings[7]
            , (android.widget.FrameLayout) bindings[0]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[4]
            );
        this.confirm.setTag(null);
        this.edtAmount.setTag(null);
        this.main.setTag(null);
        setRootTag(root);
        // listeners
        mCallback15 = new com.masa.aryan.generated.callback.OnClickListener(this, 1);
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x4L;
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
            setViewModel((com.masa.aryan.buy.viewmodel.BuyViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setViewModel(@Nullable com.masa.aryan.buy.viewmodel.BuyViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeViewModelAmount((androidx.databinding.ObservableField<java.lang.String>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeViewModelAmount(androidx.databinding.ObservableField<java.lang.String> ViewModelAmount, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
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
        androidx.databinding.ObservableField<java.lang.String> viewModelAmount = null;
        java.lang.String viewModelAmountGet = null;
        com.masa.aryan.buy.viewmodel.BuyViewModel viewModel = mViewModel;

        if ((dirtyFlags & 0x7L) != 0) {



                if (viewModel != null) {
                    // read viewModel.amount
                    viewModelAmount = viewModel.getAmount();
                }
                updateRegistration(0, viewModelAmount);


                if (viewModelAmount != null) {
                    // read viewModel.amount.get()
                    viewModelAmountGet = viewModelAmount.get();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x4L) != 0) {
            // api target 1

            this.confirm.setOnClickListener(mCallback15);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.edtAmount, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, edtAmountandroidTextAttrChanged);
        }
        if ((dirtyFlags & 0x7L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.edtAmount, viewModelAmountGet);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        // localize variables for thread safety
        // viewModel
        com.masa.aryan.buy.viewmodel.BuyViewModel viewModel = mViewModel;
        // viewModel != null
        boolean viewModelJavaLangObjectNull = false;



        viewModelJavaLangObjectNull = (viewModel) != (null);
        if (viewModelJavaLangObjectNull) {


            viewModel.setOnConfirmClicked();
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): viewModel.amount
        flag 1 (0x2L): viewModel
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}