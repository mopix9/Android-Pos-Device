package com.masa.aryan.databinding;
import com.masa.aryan.R;
import com.masa.aryan.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentTerminalBindingImpl extends FragmentTerminalBinding implements com.masa.aryan.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.top_rtl, 4);
        sViewsWithIds.put(R.id.back, 5);
        sViewsWithIds.put(R.id.terminal, 6);
        sViewsWithIds.put(R.id.merchant, 7);
        sViewsWithIds.put(R.id.cancel, 8);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    // variables
    @Nullable
    private final android.view.View.OnClickListener mCallback1;
    // values
    // listeners
    // Inverse Binding Event Handlers
    private androidx.databinding.InverseBindingListener edtMerchantandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of viewModel.observableMerchant.get()
            //         is viewModel.observableMerchant.set((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(edtMerchant);
            // localize variables for thread safety
            // viewModel.observableMerchant.get()
            java.lang.String viewModelObservableMerchantGet = null;
            // viewModel.observableMerchant
            androidx.databinding.ObservableField<java.lang.String> viewModelObservableMerchant = null;
            // viewModel
            com.masa.aryan.settings.management.viewmodel.TerminalViewModel viewModel = mViewModel;
            // viewModel != null
            boolean viewModelJavaLangObjectNull = false;
            // viewModel.observableMerchant != null
            boolean viewModelObservableMerchantJavaLangObjectNull = false;



            viewModelJavaLangObjectNull = (viewModel) != (null);
            if (viewModelJavaLangObjectNull) {


                viewModelObservableMerchant = viewModel.getObservableMerchant();

                viewModelObservableMerchantJavaLangObjectNull = (viewModelObservableMerchant) != (null);
                if (viewModelObservableMerchantJavaLangObjectNull) {




                    viewModelObservableMerchant.set(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };
    private androidx.databinding.InverseBindingListener edtTerminalandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of viewModel.observableTerminal.get()
            //         is viewModel.observableTerminal.set((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(edtTerminal);
            // localize variables for thread safety
            // viewModel.observableTerminal != null
            boolean viewModelObservableTerminalJavaLangObjectNull = false;
            // viewModel
            com.masa.aryan.settings.management.viewmodel.TerminalViewModel viewModel = mViewModel;
            // viewModel.observableTerminal.get()
            java.lang.String viewModelObservableTerminalGet = null;
            // viewModel != null
            boolean viewModelJavaLangObjectNull = false;
            // viewModel.observableTerminal
            androidx.databinding.ObservableField<java.lang.String> viewModelObservableTerminal = null;



            viewModelJavaLangObjectNull = (viewModel) != (null);
            if (viewModelJavaLangObjectNull) {


                viewModelObservableTerminal = viewModel.getObservableTerminal();

                viewModelObservableTerminalJavaLangObjectNull = (viewModelObservableTerminal) != (null);
                if (viewModelObservableTerminalJavaLangObjectNull) {




                    viewModelObservableTerminal.set(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };

    public FragmentTerminalBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds));
    }
    private FragmentTerminalBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2
            , (android.widget.ImageButton) bindings[5]
            , (android.widget.Button) bindings[8]
            , (android.widget.Button) bindings[3]
            , (android.widget.EditText) bindings[2]
            , (android.widget.EditText) bindings[1]
            , (android.widget.FrameLayout) bindings[7]
            , (android.widget.FrameLayout) bindings[6]
            , (android.widget.RelativeLayout) bindings[4]
            );
        this.confirm.setTag(null);
        this.edtMerchant.setTag(null);
        this.edtTerminal.setTag(null);
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        mCallback1 = new com.masa.aryan.generated.callback.OnClickListener(this, 1);
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
            setViewModel((com.masa.aryan.settings.management.viewmodel.TerminalViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setViewModel(@Nullable com.masa.aryan.settings.management.viewmodel.TerminalViewModel ViewModel) {
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
                return onChangeViewModelObservableMerchant((androidx.databinding.ObservableField<java.lang.String>) object, fieldId);
            case 1 :
                return onChangeViewModelObservableTerminal((androidx.databinding.ObservableField<java.lang.String>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeViewModelObservableMerchant(androidx.databinding.ObservableField<java.lang.String> ViewModelObservableMerchant, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelObservableTerminal(androidx.databinding.ObservableField<java.lang.String> ViewModelObservableTerminal, int fieldId) {
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
        java.lang.String viewModelObservableMerchantGet = null;
        androidx.databinding.ObservableField<java.lang.String> viewModelObservableMerchant = null;
        com.masa.aryan.settings.management.viewmodel.TerminalViewModel viewModel = mViewModel;
        java.lang.String viewModelObservableTerminalGet = null;
        androidx.databinding.ObservableField<java.lang.String> viewModelObservableTerminal = null;

        if ((dirtyFlags & 0xfL) != 0) {


            if ((dirtyFlags & 0xdL) != 0) {

                    if (viewModel != null) {
                        // read viewModel.observableMerchant
                        viewModelObservableMerchant = viewModel.getObservableMerchant();
                    }
                    updateRegistration(0, viewModelObservableMerchant);


                    if (viewModelObservableMerchant != null) {
                        // read viewModel.observableMerchant.get()
                        viewModelObservableMerchantGet = viewModelObservableMerchant.get();
                    }
            }
            if ((dirtyFlags & 0xeL) != 0) {

                    if (viewModel != null) {
                        // read viewModel.observableTerminal
                        viewModelObservableTerminal = viewModel.getObservableTerminal();
                    }
                    updateRegistration(1, viewModelObservableTerminal);


                    if (viewModelObservableTerminal != null) {
                        // read viewModel.observableTerminal.get()
                        viewModelObservableTerminalGet = viewModelObservableTerminal.get();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0x8L) != 0) {
            // api target 1

            this.confirm.setOnClickListener(mCallback1);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.edtMerchant, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, edtMerchantandroidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.edtTerminal, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, edtTerminalandroidTextAttrChanged);
        }
        if ((dirtyFlags & 0xdL) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.edtMerchant, viewModelObservableMerchantGet);
        }
        if ((dirtyFlags & 0xeL) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.edtTerminal, viewModelObservableTerminalGet);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        // localize variables for thread safety
        // viewModel
        com.masa.aryan.settings.management.viewmodel.TerminalViewModel viewModel = mViewModel;
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
        flag 0 (0x1L): viewModel.observableMerchant
        flag 1 (0x2L): viewModel.observableTerminal
        flag 2 (0x3L): viewModel
        flag 3 (0x4L): null
    flag mapping end*/
    //end
}