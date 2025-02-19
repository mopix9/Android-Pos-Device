package com.masa.aryan.databinding;
import com.masa.aryan.R;
import com.masa.aryan.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentBillBindingImpl extends FragmentBillBinding implements com.masa.aryan.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.back, 4);
        sViewsWithIds.put(R.id.title, 5);
        sViewsWithIds.put(R.id.subtitle, 6);
        sViewsWithIds.put(R.id.scanner, 7);
        sViewsWithIds.put(R.id.lnr_bottom, 8);
        sViewsWithIds.put(R.id.cancel, 9);
    }
    // views
    // variables
    @Nullable
    private final android.view.View.OnClickListener mCallback13;
    // values
    // listeners
    // Inverse Binding Event Handlers
    private androidx.databinding.InverseBindingListener edtBillIdandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of viewModel.billId.get()
            //         is viewModel.billId.set((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(edtBillId);
            // localize variables for thread safety
            // viewModel.billId.get()
            java.lang.String viewModelBillIdGet = null;
            // viewModel
            com.masa.aryan.bill.viewmodel.BillViewModel viewModel = mViewModel;
            // viewModel.billId != null
            boolean viewModelBillIdJavaLangObjectNull = false;
            // viewModel != null
            boolean viewModelJavaLangObjectNull = false;
            // viewModel.billId
            androidx.databinding.ObservableField<java.lang.String> viewModelBillId = null;



            viewModelJavaLangObjectNull = (viewModel) != (null);
            if (viewModelJavaLangObjectNull) {


                viewModelBillId = viewModel.getBillId();

                viewModelBillIdJavaLangObjectNull = (viewModelBillId) != (null);
                if (viewModelBillIdJavaLangObjectNull) {




                    viewModelBillId.set(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };
    private androidx.databinding.InverseBindingListener edtPayIdandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of viewModel.payId.get()
            //         is viewModel.payId.set((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(edtPayId);
            // localize variables for thread safety
            // viewModel.payId
            androidx.databinding.ObservableField<java.lang.String> viewModelPayId = null;
            // viewModel.payId != null
            boolean viewModelPayIdJavaLangObjectNull = false;
            // viewModel.payId.get()
            java.lang.String viewModelPayIdGet = null;
            // viewModel
            com.masa.aryan.bill.viewmodel.BillViewModel viewModel = mViewModel;
            // viewModel != null
            boolean viewModelJavaLangObjectNull = false;



            viewModelJavaLangObjectNull = (viewModel) != (null);
            if (viewModelJavaLangObjectNull) {


                viewModelPayId = viewModel.getPayId();

                viewModelPayIdJavaLangObjectNull = (viewModelPayId) != (null);
                if (viewModelPayIdJavaLangObjectNull) {




                    viewModelPayId.set(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };

    public FragmentBillBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds));
    }
    private FragmentBillBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2
            , (android.widget.ImageButton) bindings[4]
            , (android.widget.Button) bindings[9]
            , (android.widget.Button) bindings[3]
            , (android.widget.EditText) bindings[1]
            , (android.widget.EditText) bindings[2]
            , (android.widget.LinearLayout) bindings[8]
            , (android.widget.FrameLayout) bindings[0]
            , (android.widget.Button) bindings[7]
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[5]
            );
        this.confirm.setTag(null);
        this.edtBillId.setTag(null);
        this.edtPayId.setTag(null);
        this.main.setTag(null);
        setRootTag(root);
        // listeners
        mCallback13 = new com.masa.aryan.generated.callback.OnClickListener(this, 1);
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x8L;
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
            setViewModel((com.masa.aryan.bill.viewmodel.BillViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setViewModel(@Nullable com.masa.aryan.bill.viewmodel.BillViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x4L;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeViewModelPayId((androidx.databinding.ObservableField<java.lang.String>) object, fieldId);
            case 1 :
                return onChangeViewModelBillId((androidx.databinding.ObservableField<java.lang.String>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeViewModelPayId(androidx.databinding.ObservableField<java.lang.String> ViewModelPayId, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelBillId(androidx.databinding.ObservableField<java.lang.String> ViewModelBillId, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
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
        androidx.databinding.ObservableField<java.lang.String> viewModelPayId = null;
        java.lang.String viewModelPayIdGet = null;
        java.lang.String viewModelBillIdGet = null;
        com.masa.aryan.bill.viewmodel.BillViewModel viewModel = mViewModel;
        androidx.databinding.ObservableField<java.lang.String> viewModelBillId = null;

        if ((dirtyFlags & 0xfL) != 0) {


            if ((dirtyFlags & 0xdL) != 0) {

                    if (viewModel != null) {
                        // read viewModel.payId
                        viewModelPayId = viewModel.getPayId();
                    }
                    updateRegistration(0, viewModelPayId);


                    if (viewModelPayId != null) {
                        // read viewModel.payId.get()
                        viewModelPayIdGet = viewModelPayId.get();
                    }
            }
            if ((dirtyFlags & 0xeL) != 0) {

                    if (viewModel != null) {
                        // read viewModel.billId
                        viewModelBillId = viewModel.getBillId();
                    }
                    updateRegistration(1, viewModelBillId);


                    if (viewModelBillId != null) {
                        // read viewModel.billId.get()
                        viewModelBillIdGet = viewModelBillId.get();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0x8L) != 0) {
            // api target 1

            this.confirm.setOnClickListener(mCallback13);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.edtBillId, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, edtBillIdandroidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.edtPayId, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, edtPayIdandroidTextAttrChanged);
        }
        if ((dirtyFlags & 0xeL) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.edtBillId, viewModelBillIdGet);
        }
        if ((dirtyFlags & 0xdL) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.edtPayId, viewModelPayIdGet);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        // localize variables for thread safety
        // viewModel
        com.masa.aryan.bill.viewmodel.BillViewModel viewModel = mViewModel;
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
        flag 0 (0x1L): viewModel.payId
        flag 1 (0x2L): viewModel.billId
        flag 2 (0x3L): viewModel
        flag 3 (0x4L): null
    flag mapping end*/
    //end
}