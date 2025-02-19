package com.masa.aryan.databinding;
import com.masa.aryan.R;
import com.masa.aryan.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentServerBindingImpl extends FragmentServerBinding implements com.masa.aryan.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.top_rtl, 5);
        sViewsWithIds.put(R.id.back, 6);
        sViewsWithIds.put(R.id.title_1, 7);
        sViewsWithIds.put(R.id.ip, 8);
        sViewsWithIds.put(R.id.title_2, 9);
        sViewsWithIds.put(R.id.port, 10);
        sViewsWithIds.put(R.id.title_3, 11);
        sViewsWithIds.put(R.id.nii, 12);
        sViewsWithIds.put(R.id.cancel, 13);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    @NonNull
    private final android.widget.EditText mboundView1;
    @NonNull
    private final android.widget.EditText mboundView2;
    @NonNull
    private final android.widget.EditText mboundView3;
    // variables
    @Nullable
    private final android.view.View.OnClickListener mCallback16;
    // values
    // listeners
    // Inverse Binding Event Handlers
    private androidx.databinding.InverseBindingListener mboundView1androidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of viewModel.observableIp.get()
            //         is viewModel.observableIp.set((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(mboundView1);
            // localize variables for thread safety
            // viewModel.observableIp != null
            boolean viewModelObservableIpJavaLangObjectNull = false;
            // viewModel.observableIp.get()
            java.lang.String viewModelObservableIpGet = null;
            // viewModel
            com.masa.aryan.settings.management.viewmodel.ServerViewModel viewModel = mViewModel;
            // viewModel.observableIp
            androidx.databinding.ObservableField<java.lang.String> viewModelObservableIp = null;
            // viewModel != null
            boolean viewModelJavaLangObjectNull = false;



            viewModelJavaLangObjectNull = (viewModel) != (null);
            if (viewModelJavaLangObjectNull) {


                viewModelObservableIp = viewModel.getObservableIp();

                viewModelObservableIpJavaLangObjectNull = (viewModelObservableIp) != (null);
                if (viewModelObservableIpJavaLangObjectNull) {




                    viewModelObservableIp.set(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };
    private androidx.databinding.InverseBindingListener mboundView2androidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of viewModel.observablePort.get()
            //         is viewModel.observablePort.set((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(mboundView2);
            // localize variables for thread safety
            // viewModel.observablePort != null
            boolean viewModelObservablePortJavaLangObjectNull = false;
            // viewModel.observablePort.get()
            java.lang.String viewModelObservablePortGet = null;
            // viewModel
            com.masa.aryan.settings.management.viewmodel.ServerViewModel viewModel = mViewModel;
            // viewModel.observablePort
            androidx.databinding.ObservableField<java.lang.String> viewModelObservablePort = null;
            // viewModel != null
            boolean viewModelJavaLangObjectNull = false;



            viewModelJavaLangObjectNull = (viewModel) != (null);
            if (viewModelJavaLangObjectNull) {


                viewModelObservablePort = viewModel.getObservablePort();

                viewModelObservablePortJavaLangObjectNull = (viewModelObservablePort) != (null);
                if (viewModelObservablePortJavaLangObjectNull) {




                    viewModelObservablePort.set(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };
    private androidx.databinding.InverseBindingListener mboundView3androidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of viewModel.observableNii.get()
            //         is viewModel.observableNii.set((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(mboundView3);
            // localize variables for thread safety
            // viewModel.observableNii.get()
            java.lang.String viewModelObservableNiiGet = null;
            // viewModel.observableNii
            androidx.databinding.ObservableField<java.lang.String> viewModelObservableNii = null;
            // viewModel
            com.masa.aryan.settings.management.viewmodel.ServerViewModel viewModel = mViewModel;
            // viewModel != null
            boolean viewModelJavaLangObjectNull = false;
            // viewModel.observableNii != null
            boolean viewModelObservableNiiJavaLangObjectNull = false;



            viewModelJavaLangObjectNull = (viewModel) != (null);
            if (viewModelJavaLangObjectNull) {


                viewModelObservableNii = viewModel.getObservableNii();

                viewModelObservableNiiJavaLangObjectNull = (viewModelObservableNii) != (null);
                if (viewModelObservableNiiJavaLangObjectNull) {




                    viewModelObservableNii.set(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };

    public FragmentServerBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 14, sIncludes, sViewsWithIds));
    }
    private FragmentServerBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 3
            , (android.widget.ImageButton) bindings[6]
            , (android.widget.Button) bindings[13]
            , (android.widget.Button) bindings[4]
            , (android.widget.FrameLayout) bindings[8]
            , (android.widget.FrameLayout) bindings[12]
            , (android.widget.FrameLayout) bindings[10]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[9]
            , (android.widget.TextView) bindings[11]
            , (android.widget.RelativeLayout) bindings[5]
            );
        this.confirm.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (android.widget.EditText) bindings[1];
        this.mboundView1.setTag(null);
        this.mboundView2 = (android.widget.EditText) bindings[2];
        this.mboundView2.setTag(null);
        this.mboundView3 = (android.widget.EditText) bindings[3];
        this.mboundView3.setTag(null);
        setRootTag(root);
        // listeners
        mCallback16 = new com.masa.aryan.generated.callback.OnClickListener(this, 1);
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x10L;
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
            setViewModel((com.masa.aryan.settings.management.viewmodel.ServerViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setViewModel(@Nullable com.masa.aryan.settings.management.viewmodel.ServerViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x8L;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeViewModelObservablePort((androidx.databinding.ObservableField<java.lang.String>) object, fieldId);
            case 1 :
                return onChangeViewModelObservableNii((androidx.databinding.ObservableField<java.lang.String>) object, fieldId);
            case 2 :
                return onChangeViewModelObservableIp((androidx.databinding.ObservableField<java.lang.String>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeViewModelObservablePort(androidx.databinding.ObservableField<java.lang.String> ViewModelObservablePort, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelObservableNii(androidx.databinding.ObservableField<java.lang.String> ViewModelObservableNii, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelObservableIp(androidx.databinding.ObservableField<java.lang.String> ViewModelObservableIp, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
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
        java.lang.String viewModelObservableIpGet = null;
        java.lang.String viewModelObservablePortGet = null;
        java.lang.String viewModelObservableNiiGet = null;
        androidx.databinding.ObservableField<java.lang.String> viewModelObservablePort = null;
        androidx.databinding.ObservableField<java.lang.String> viewModelObservableNii = null;
        com.masa.aryan.settings.management.viewmodel.ServerViewModel viewModel = mViewModel;
        androidx.databinding.ObservableField<java.lang.String> viewModelObservableIp = null;

        if ((dirtyFlags & 0x1fL) != 0) {


            if ((dirtyFlags & 0x19L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.observablePort
                        viewModelObservablePort = viewModel.getObservablePort();
                    }
                    updateRegistration(0, viewModelObservablePort);


                    if (viewModelObservablePort != null) {
                        // read viewModel.observablePort.get()
                        viewModelObservablePortGet = viewModelObservablePort.get();
                    }
            }
            if ((dirtyFlags & 0x1aL) != 0) {

                    if (viewModel != null) {
                        // read viewModel.observableNii
                        viewModelObservableNii = viewModel.getObservableNii();
                    }
                    updateRegistration(1, viewModelObservableNii);


                    if (viewModelObservableNii != null) {
                        // read viewModel.observableNii.get()
                        viewModelObservableNiiGet = viewModelObservableNii.get();
                    }
            }
            if ((dirtyFlags & 0x1cL) != 0) {

                    if (viewModel != null) {
                        // read viewModel.observableIp
                        viewModelObservableIp = viewModel.getObservableIp();
                    }
                    updateRegistration(2, viewModelObservableIp);


                    if (viewModelObservableIp != null) {
                        // read viewModel.observableIp.get()
                        viewModelObservableIpGet = viewModelObservableIp.get();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0x10L) != 0) {
            // api target 1

            this.confirm.setOnClickListener(mCallback16);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.mboundView1, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, mboundView1androidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.mboundView2, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, mboundView2androidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.mboundView3, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, mboundView3androidTextAttrChanged);
        }
        if ((dirtyFlags & 0x1cL) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView1, viewModelObservableIpGet);
        }
        if ((dirtyFlags & 0x19L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView2, viewModelObservablePortGet);
        }
        if ((dirtyFlags & 0x1aL) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView3, viewModelObservableNiiGet);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        // localize variables for thread safety
        // viewModel
        com.masa.aryan.settings.management.viewmodel.ServerViewModel viewModel = mViewModel;
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
        flag 0 (0x1L): viewModel.observablePort
        flag 1 (0x2L): viewModel.observableNii
        flag 2 (0x3L): viewModel.observableIp
        flag 3 (0x4L): viewModel
        flag 4 (0x5L): null
    flag mapping end*/
    //end
}