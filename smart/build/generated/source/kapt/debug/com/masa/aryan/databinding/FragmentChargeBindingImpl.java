package com.masa.aryan.databinding;
import com.masa.aryan.R;
import com.masa.aryan.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentChargeBindingImpl extends FragmentChargeBinding implements com.masa.aryan.generated.callback.OnClickListener.Listener, com.masa.aryan.generated.callback.OnTextChanged.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.back, 19);
        sViewsWithIds.put(R.id.title, 20);
        sViewsWithIds.put(R.id.top, 21);
        sViewsWithIds.put(R.id.amount_topup, 22);
        sViewsWithIds.put(R.id.txt_topup_amount, 23);
        sViewsWithIds.put(R.id.amount_code, 24);
        sViewsWithIds.put(R.id.txt_code_amount, 25);
        sViewsWithIds.put(R.id.cancel, 26);
        sViewsWithIds.put(R.id.confirm, 27);
    }
    // views
    @NonNull
    private final android.widget.ImageView mboundView10;
    @NonNull
    private final android.widget.ImageView mboundView11;
    @NonNull
    private final android.widget.ImageView mboundView12;
    @NonNull
    private final android.widget.ImageView mboundView13;
    @NonNull
    private final android.widget.LinearLayout mboundView14;
    @NonNull
    private final android.widget.ImageView mboundView15;
    @NonNull
    private final android.widget.ImageView mboundView16;
    @NonNull
    private final android.widget.ImageView mboundView17;
    @NonNull
    private final android.widget.ImageView mboundView18;
    @NonNull
    private final android.widget.RelativeLayout mboundView2;
    @NonNull
    private final android.widget.TextView mboundView3;
    @NonNull
    private final android.widget.TextView mboundView4;
    @NonNull
    private final android.widget.RelativeLayout mboundView5;
    @NonNull
    private final android.widget.TextView mboundView6;
    @NonNull
    private final android.widget.TextView mboundView7;
    @NonNull
    private final android.widget.LinearLayout mboundView8;
    // variables
    @Nullable
    private final android.view.View.OnClickListener mCallback11;
    @Nullable
    private final android.view.View.OnClickListener mCallback6;
    @Nullable
    private final android.view.View.OnClickListener mCallback2;
    @Nullable
    private final android.view.View.OnClickListener mCallback12;
    @Nullable
    private final android.view.View.OnClickListener mCallback9;
    @Nullable
    private final android.view.View.OnClickListener mCallback5;
    @Nullable
    private final android.view.View.OnClickListener mCallback8;
    @Nullable
    private final androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged mCallback4;
    @Nullable
    private final android.view.View.OnClickListener mCallback10;
    @Nullable
    private final android.view.View.OnClickListener mCallback7;
    @Nullable
    private final android.view.View.OnClickListener mCallback3;
    // values
    // listeners
    // Inverse Binding Event Handlers
    private androidx.databinding.InverseBindingListener edtPhoneandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of viewModel.phone.get()
            //         is viewModel.phone.set((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(edtPhone);
            // localize variables for thread safety
            // viewModel.phone != null
            boolean viewModelPhoneJavaLangObjectNull = false;
            // viewModel
            com.masa.aryan.charge.ChargeViewModel viewModel = mViewModel;
            // viewModel != null
            boolean viewModelJavaLangObjectNull = false;
            // viewModel.phone
            androidx.databinding.ObservableField<java.lang.String> viewModelPhone = null;
            // viewModel.phone.get()
            java.lang.String viewModelPhoneGet = null;



            viewModelJavaLangObjectNull = (viewModel) != (null);
            if (viewModelJavaLangObjectNull) {


                viewModelPhone = viewModel.getPhone();

                viewModelPhoneJavaLangObjectNull = (viewModelPhone) != (null);
                if (viewModelPhoneJavaLangObjectNull) {




                    viewModelPhone.set(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };

    public FragmentChargeBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 28, sIncludes, sViewsWithIds));
    }
    private FragmentChargeBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 10
            , (android.widget.FrameLayout) bindings[24]
            , (android.widget.FrameLayout) bindings[22]
            , (android.widget.ImageButton) bindings[19]
            , (android.widget.Button) bindings[26]
            , (android.widget.Button) bindings[27]
            , (android.widget.EditText) bindings[9]
            , (android.widget.FrameLayout) bindings[0]
            , (android.widget.TextView) bindings[1]
            , (android.widget.TextView) bindings[20]
            , (android.widget.LinearLayout) bindings[21]
            , (android.widget.TextView) bindings[25]
            , (android.widget.TextView) bindings[23]
            );
        this.edtPhone.setTag(null);
        this.main.setTag(null);
        this.mboundView10 = (android.widget.ImageView) bindings[10];
        this.mboundView10.setTag(null);
        this.mboundView11 = (android.widget.ImageView) bindings[11];
        this.mboundView11.setTag(null);
        this.mboundView12 = (android.widget.ImageView) bindings[12];
        this.mboundView12.setTag(null);
        this.mboundView13 = (android.widget.ImageView) bindings[13];
        this.mboundView13.setTag(null);
        this.mboundView14 = (android.widget.LinearLayout) bindings[14];
        this.mboundView14.setTag(null);
        this.mboundView15 = (android.widget.ImageView) bindings[15];
        this.mboundView15.setTag(null);
        this.mboundView16 = (android.widget.ImageView) bindings[16];
        this.mboundView16.setTag(null);
        this.mboundView17 = (android.widget.ImageView) bindings[17];
        this.mboundView17.setTag(null);
        this.mboundView18 = (android.widget.ImageView) bindings[18];
        this.mboundView18.setTag(null);
        this.mboundView2 = (android.widget.RelativeLayout) bindings[2];
        this.mboundView2.setTag(null);
        this.mboundView3 = (android.widget.TextView) bindings[3];
        this.mboundView3.setTag(null);
        this.mboundView4 = (android.widget.TextView) bindings[4];
        this.mboundView4.setTag(null);
        this.mboundView5 = (android.widget.RelativeLayout) bindings[5];
        this.mboundView5.setTag(null);
        this.mboundView6 = (android.widget.TextView) bindings[6];
        this.mboundView6.setTag(null);
        this.mboundView7 = (android.widget.TextView) bindings[7];
        this.mboundView7.setTag(null);
        this.mboundView8 = (android.widget.LinearLayout) bindings[8];
        this.mboundView8.setTag(null);
        this.subtitle.setTag(null);
        setRootTag(root);
        // listeners
        mCallback11 = new com.masa.aryan.generated.callback.OnClickListener(this, 10);
        mCallback6 = new com.masa.aryan.generated.callback.OnClickListener(this, 5);
        mCallback2 = new com.masa.aryan.generated.callback.OnClickListener(this, 1);
        mCallback12 = new com.masa.aryan.generated.callback.OnClickListener(this, 11);
        mCallback9 = new com.masa.aryan.generated.callback.OnClickListener(this, 8);
        mCallback5 = new com.masa.aryan.generated.callback.OnClickListener(this, 4);
        mCallback8 = new com.masa.aryan.generated.callback.OnClickListener(this, 7);
        mCallback4 = new com.masa.aryan.generated.callback.OnTextChanged(this, 3);
        mCallback10 = new com.masa.aryan.generated.callback.OnClickListener(this, 9);
        mCallback7 = new com.masa.aryan.generated.callback.OnClickListener(this, 6);
        mCallback3 = new com.masa.aryan.generated.callback.OnClickListener(this, 2);
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x800L;
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
            setViewModel((com.masa.aryan.charge.ChargeViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setViewModel(@Nullable com.masa.aryan.charge.ChargeViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x400L;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeViewModelIsMtn((androidx.databinding.ObservableBoolean) object, fieldId);
            case 1 :
                return onChangeViewModelPhone((androidx.databinding.ObservableField<java.lang.String>) object, fieldId);
            case 2 :
                return onChangeViewModelIsMci((androidx.databinding.ObservableBoolean) object, fieldId);
            case 3 :
                return onChangeViewModelIsTalia((androidx.databinding.ObservableBoolean) object, fieldId);
            case 4 :
                return onChangeViewModelIsRightel((androidx.databinding.ObservableBoolean) object, fieldId);
            case 5 :
                return onChangeViewModelIsMtnChargeCode((androidx.databinding.ObservableBoolean) object, fieldId);
            case 6 :
                return onChangeViewModelIsRightelChargeCode((androidx.databinding.ObservableBoolean) object, fieldId);
            case 7 :
                return onChangeViewModelIsTaliaChargeCode((androidx.databinding.ObservableBoolean) object, fieldId);
            case 8 :
                return onChangeViewModelIsMciChargeCode((androidx.databinding.ObservableBoolean) object, fieldId);
            case 9 :
                return onChangeViewModelIsTopUp((androidx.databinding.ObservableBoolean) object, fieldId);
        }
        return false;
    }
    private boolean onChangeViewModelIsMtn(androidx.databinding.ObservableBoolean ViewModelIsMtn, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelPhone(androidx.databinding.ObservableField<java.lang.String> ViewModelPhone, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelIsMci(androidx.databinding.ObservableBoolean ViewModelIsMci, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelIsTalia(androidx.databinding.ObservableBoolean ViewModelIsTalia, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x8L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelIsRightel(androidx.databinding.ObservableBoolean ViewModelIsRightel, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x10L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelIsMtnChargeCode(androidx.databinding.ObservableBoolean ViewModelIsMtnChargeCode, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x20L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelIsRightelChargeCode(androidx.databinding.ObservableBoolean ViewModelIsRightelChargeCode, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x40L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelIsTaliaChargeCode(androidx.databinding.ObservableBoolean ViewModelIsTaliaChargeCode, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x80L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelIsMciChargeCode(androidx.databinding.ObservableBoolean ViewModelIsMciChargeCode, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x100L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelIsTopUp(androidx.databinding.ObservableBoolean ViewModelIsTopUp, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x200L;
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
        android.graphics.drawable.Drawable viewModelIsMciMboundView10AndroidDrawableHamrahAvalMboundView10AndroidDrawableHamrahAval2 = null;
        android.graphics.drawable.Drawable viewModelIsTaliaChargeCodeMboundView18AndroidDrawableTaliaMboundView18AndroidDrawableTalia2 = null;
        java.lang.String viewModelPhoneGet = null;
        int viewModelIsTopUpViewGONEViewVISIBLE = 0;
        android.graphics.drawable.Drawable viewModelIsRightelChargeCodeMboundView17AndroidDrawableRightelMboundView17AndroidDrawableRightel2 = null;
        androidx.databinding.ObservableBoolean viewModelIsMtn = null;
        android.graphics.drawable.Drawable viewModelIsRightelMboundView12AndroidDrawableRightelMboundView12AndroidDrawableRightel2 = null;
        int viewModelIsTopUpMboundView6AndroidColorDarkMboundView6AndroidColorSlate = 0;
        boolean viewModelIsTaliaGet = false;
        androidx.databinding.ObservableField<java.lang.String> viewModelPhone = null;
        boolean viewModelIsRightelChargeCodeGet = false;
        android.graphics.drawable.Drawable viewModelIsMciChargeCodeMboundView15AndroidDrawableHamrahAvalMboundView15AndroidDrawableHamrahAval2 = null;
        android.graphics.drawable.Drawable viewModelIsTaliaMboundView13AndroidDrawableTaliaMboundView13AndroidDrawableTalia2 = null;
        androidx.databinding.ObservableBoolean viewModelIsMci = null;
        androidx.databinding.ObservableBoolean viewModelIsTalia = null;
        int viewModelIsTopUpMboundView3AndroidColorSlateMboundView3AndroidColorDark = 0;
        boolean viewModelIsTopUpGet = false;
        boolean viewModelIsTaliaChargeCodeGet = false;
        androidx.databinding.ObservableBoolean viewModelIsRightel = null;
        androidx.databinding.ObservableBoolean viewModelIsMtnChargeCode = null;
        androidx.databinding.ObservableBoolean viewModelIsRightelChargeCode = null;
        android.graphics.drawable.Drawable viewModelIsMtnMboundView11AndroidDrawableIrancelMboundView11AndroidDrawableIrancell2 = null;
        boolean viewModelIsMciChargeCodeGet = false;
        int viewModelIsTopUpViewVISIBLEViewGONE = 0;
        androidx.databinding.ObservableBoolean viewModelIsTaliaChargeCode = null;
        boolean viewModelIsRightelGet = false;
        boolean viewModelIsMciGet = false;
        android.graphics.drawable.Drawable viewModelIsMtnChargeCodeMboundView16AndroidDrawableIrancelMboundView16AndroidDrawableIrancell2 = null;
        androidx.databinding.ObservableBoolean viewModelIsMciChargeCode = null;
        androidx.databinding.ObservableBoolean viewModelIsTopUp = null;
        boolean viewModelIsMtnGet = false;
        boolean viewModelIsMtnChargeCodeGet = false;
        java.lang.String viewModelIsTopUpSubtitleAndroidStringEnterPhoneSubtitleAndroidStringEnterAmountAndOperator = null;
        com.masa.aryan.charge.ChargeViewModel viewModel = mViewModel;

        if ((dirtyFlags & 0xfffL) != 0) {


            if ((dirtyFlags & 0xc01L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.isMtn
                        viewModelIsMtn = viewModel.isMtn();
                    }
                    updateRegistration(0, viewModelIsMtn);


                    if (viewModelIsMtn != null) {
                        // read viewModel.isMtn.get()
                        viewModelIsMtnGet = viewModelIsMtn.get();
                    }
                if((dirtyFlags & 0xc01L) != 0) {
                    if(viewModelIsMtnGet) {
                            dirtyFlags |= 0x80000000L;
                    }
                    else {
                            dirtyFlags |= 0x40000000L;
                    }
                }


                    // read viewModel.isMtn.get() ? @android:drawable/irancel : @android:drawable/irancell_2
                    viewModelIsMtnMboundView11AndroidDrawableIrancelMboundView11AndroidDrawableIrancell2 = ((viewModelIsMtnGet) ? (androidx.appcompat.content.res.AppCompatResources.getDrawable(mboundView11.getContext(), R.drawable.irancel)) : (androidx.appcompat.content.res.AppCompatResources.getDrawable(mboundView11.getContext(), R.drawable.irancell_2)));
            }
            if ((dirtyFlags & 0xc02L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.phone
                        viewModelPhone = viewModel.getPhone();
                    }
                    updateRegistration(1, viewModelPhone);


                    if (viewModelPhone != null) {
                        // read viewModel.phone.get()
                        viewModelPhoneGet = viewModelPhone.get();
                    }
            }
            if ((dirtyFlags & 0xc04L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.isMci
                        viewModelIsMci = viewModel.isMci();
                    }
                    updateRegistration(2, viewModelIsMci);


                    if (viewModelIsMci != null) {
                        // read viewModel.isMci.get()
                        viewModelIsMciGet = viewModelIsMci.get();
                    }
                if((dirtyFlags & 0xc04L) != 0) {
                    if(viewModelIsMciGet) {
                            dirtyFlags |= 0x2000L;
                    }
                    else {
                            dirtyFlags |= 0x1000L;
                    }
                }


                    // read viewModel.isMci.get() ? @android:drawable/hamrah_aval : @android:drawable/hamrah_aval_2
                    viewModelIsMciMboundView10AndroidDrawableHamrahAvalMboundView10AndroidDrawableHamrahAval2 = ((viewModelIsMciGet) ? (androidx.appcompat.content.res.AppCompatResources.getDrawable(mboundView10.getContext(), R.drawable.hamrah_aval)) : (androidx.appcompat.content.res.AppCompatResources.getDrawable(mboundView10.getContext(), R.drawable.hamrah_aval_2)));
            }
            if ((dirtyFlags & 0xc08L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.isTalia
                        viewModelIsTalia = viewModel.isTalia();
                    }
                    updateRegistration(3, viewModelIsTalia);


                    if (viewModelIsTalia != null) {
                        // read viewModel.isTalia.get()
                        viewModelIsTaliaGet = viewModelIsTalia.get();
                    }
                if((dirtyFlags & 0xc08L) != 0) {
                    if(viewModelIsTaliaGet) {
                            dirtyFlags |= 0x8000000L;
                    }
                    else {
                            dirtyFlags |= 0x4000000L;
                    }
                }


                    // read viewModel.isTalia.get() ? @android:drawable/talia : @android:drawable/talia_2
                    viewModelIsTaliaMboundView13AndroidDrawableTaliaMboundView13AndroidDrawableTalia2 = ((viewModelIsTaliaGet) ? (androidx.appcompat.content.res.AppCompatResources.getDrawable(mboundView13.getContext(), R.drawable.talia)) : (androidx.appcompat.content.res.AppCompatResources.getDrawable(mboundView13.getContext(), R.drawable.talia_2)));
            }
            if ((dirtyFlags & 0xc10L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.isRightel
                        viewModelIsRightel = viewModel.isRightel();
                    }
                    updateRegistration(4, viewModelIsRightel);


                    if (viewModelIsRightel != null) {
                        // read viewModel.isRightel.get()
                        viewModelIsRightelGet = viewModelIsRightel.get();
                    }
                if((dirtyFlags & 0xc10L) != 0) {
                    if(viewModelIsRightelGet) {
                            dirtyFlags |= 0x200000L;
                    }
                    else {
                            dirtyFlags |= 0x100000L;
                    }
                }


                    // read viewModel.isRightel.get() ? @android:drawable/rightel : @android:drawable/rightel_2
                    viewModelIsRightelMboundView12AndroidDrawableRightelMboundView12AndroidDrawableRightel2 = ((viewModelIsRightelGet) ? (androidx.appcompat.content.res.AppCompatResources.getDrawable(mboundView12.getContext(), R.drawable.rightel)) : (androidx.appcompat.content.res.AppCompatResources.getDrawable(mboundView12.getContext(), R.drawable.rightel_2)));
            }
            if ((dirtyFlags & 0xc20L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.isMtnChargeCode
                        viewModelIsMtnChargeCode = viewModel.isMtnChargeCode();
                    }
                    updateRegistration(5, viewModelIsMtnChargeCode);


                    if (viewModelIsMtnChargeCode != null) {
                        // read viewModel.isMtnChargeCode.get()
                        viewModelIsMtnChargeCodeGet = viewModelIsMtnChargeCode.get();
                    }
                if((dirtyFlags & 0xc20L) != 0) {
                    if(viewModelIsMtnChargeCodeGet) {
                            dirtyFlags |= 0x800000000L;
                    }
                    else {
                            dirtyFlags |= 0x400000000L;
                    }
                }


                    // read viewModel.isMtnChargeCode.get() ? @android:drawable/irancel : @android:drawable/irancell_2
                    viewModelIsMtnChargeCodeMboundView16AndroidDrawableIrancelMboundView16AndroidDrawableIrancell2 = ((viewModelIsMtnChargeCodeGet) ? (androidx.appcompat.content.res.AppCompatResources.getDrawable(mboundView16.getContext(), R.drawable.irancel)) : (androidx.appcompat.content.res.AppCompatResources.getDrawable(mboundView16.getContext(), R.drawable.irancell_2)));
            }
            if ((dirtyFlags & 0xc40L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.isRightelChargeCode
                        viewModelIsRightelChargeCode = viewModel.isRightelChargeCode();
                    }
                    updateRegistration(6, viewModelIsRightelChargeCode);


                    if (viewModelIsRightelChargeCode != null) {
                        // read viewModel.isRightelChargeCode.get()
                        viewModelIsRightelChargeCodeGet = viewModelIsRightelChargeCode.get();
                    }
                if((dirtyFlags & 0xc40L) != 0) {
                    if(viewModelIsRightelChargeCodeGet) {
                            dirtyFlags |= 0x80000L;
                    }
                    else {
                            dirtyFlags |= 0x40000L;
                    }
                }


                    // read viewModel.isRightelChargeCode.get() ? @android:drawable/rightel : @android:drawable/rightel_2
                    viewModelIsRightelChargeCodeMboundView17AndroidDrawableRightelMboundView17AndroidDrawableRightel2 = ((viewModelIsRightelChargeCodeGet) ? (androidx.appcompat.content.res.AppCompatResources.getDrawable(mboundView17.getContext(), R.drawable.rightel)) : (androidx.appcompat.content.res.AppCompatResources.getDrawable(mboundView17.getContext(), R.drawable.rightel_2)));
            }
            if ((dirtyFlags & 0xc80L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.isTaliaChargeCode
                        viewModelIsTaliaChargeCode = viewModel.isTaliaChargeCode();
                    }
                    updateRegistration(7, viewModelIsTaliaChargeCode);


                    if (viewModelIsTaliaChargeCode != null) {
                        // read viewModel.isTaliaChargeCode.get()
                        viewModelIsTaliaChargeCodeGet = viewModelIsTaliaChargeCode.get();
                    }
                if((dirtyFlags & 0xc80L) != 0) {
                    if(viewModelIsTaliaChargeCodeGet) {
                            dirtyFlags |= 0x8000L;
                    }
                    else {
                            dirtyFlags |= 0x4000L;
                    }
                }


                    // read viewModel.isTaliaChargeCode.get() ? @android:drawable/talia : @android:drawable/talia_2
                    viewModelIsTaliaChargeCodeMboundView18AndroidDrawableTaliaMboundView18AndroidDrawableTalia2 = ((viewModelIsTaliaChargeCodeGet) ? (androidx.appcompat.content.res.AppCompatResources.getDrawable(mboundView18.getContext(), R.drawable.talia)) : (androidx.appcompat.content.res.AppCompatResources.getDrawable(mboundView18.getContext(), R.drawable.talia_2)));
            }
            if ((dirtyFlags & 0xd00L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.isMciChargeCode
                        viewModelIsMciChargeCode = viewModel.isMciChargeCode();
                    }
                    updateRegistration(8, viewModelIsMciChargeCode);


                    if (viewModelIsMciChargeCode != null) {
                        // read viewModel.isMciChargeCode.get()
                        viewModelIsMciChargeCodeGet = viewModelIsMciChargeCode.get();
                    }
                if((dirtyFlags & 0xd00L) != 0) {
                    if(viewModelIsMciChargeCodeGet) {
                            dirtyFlags |= 0x2000000L;
                    }
                    else {
                            dirtyFlags |= 0x1000000L;
                    }
                }


                    // read viewModel.isMciChargeCode.get() ? @android:drawable/hamrah_aval : @android:drawable/hamrah_aval_2
                    viewModelIsMciChargeCodeMboundView15AndroidDrawableHamrahAvalMboundView15AndroidDrawableHamrahAval2 = ((viewModelIsMciChargeCodeGet) ? (androidx.appcompat.content.res.AppCompatResources.getDrawable(mboundView15.getContext(), R.drawable.hamrah_aval)) : (androidx.appcompat.content.res.AppCompatResources.getDrawable(mboundView15.getContext(), R.drawable.hamrah_aval_2)));
            }
            if ((dirtyFlags & 0xe00L) != 0) {

                    if (viewModel != null) {
                        // read viewModel.isTopUp
                        viewModelIsTopUp = viewModel.isTopUp();
                    }
                    updateRegistration(9, viewModelIsTopUp);


                    if (viewModelIsTopUp != null) {
                        // read viewModel.isTopUp.get()
                        viewModelIsTopUpGet = viewModelIsTopUp.get();
                    }
                if((dirtyFlags & 0xe00L) != 0) {
                    if(viewModelIsTopUpGet) {
                            dirtyFlags |= 0x20000L;
                            dirtyFlags |= 0x800000L;
                            dirtyFlags |= 0x20000000L;
                            dirtyFlags |= 0x200000000L;
                            dirtyFlags |= 0x2000000000L;
                    }
                    else {
                            dirtyFlags |= 0x10000L;
                            dirtyFlags |= 0x400000L;
                            dirtyFlags |= 0x10000000L;
                            dirtyFlags |= 0x100000000L;
                            dirtyFlags |= 0x1000000000L;
                    }
                }


                    // read viewModel.isTopUp.get() ? View.GONE : View.VISIBLE
                    viewModelIsTopUpViewGONEViewVISIBLE = ((viewModelIsTopUpGet) ? (android.view.View.GONE) : (android.view.View.VISIBLE));
                    // read viewModel.isTopUp.get() ? @android:color/dark : @android:color/slate
                    viewModelIsTopUpMboundView6AndroidColorDarkMboundView6AndroidColorSlate = ((viewModelIsTopUpGet) ? (getColorFromResource(mboundView6, R.color.dark)) : (getColorFromResource(mboundView6, R.color.slate)));
                    // read viewModel.isTopUp.get() ? @android:color/slate : @android:color/dark
                    viewModelIsTopUpMboundView3AndroidColorSlateMboundView3AndroidColorDark = ((viewModelIsTopUpGet) ? (getColorFromResource(mboundView3, R.color.slate)) : (getColorFromResource(mboundView3, R.color.dark)));
                    // read viewModel.isTopUp.get() ? View.VISIBLE : View.GONE
                    viewModelIsTopUpViewVISIBLEViewGONE = ((viewModelIsTopUpGet) ? (android.view.View.VISIBLE) : (android.view.View.GONE));
                    // read viewModel.isTopUp.get() ? @android:string/enter_phone : @android:string/enter_amount_and_operator
                    viewModelIsTopUpSubtitleAndroidStringEnterPhoneSubtitleAndroidStringEnterAmountAndOperator = ((viewModelIsTopUpGet) ? (subtitle.getResources().getString(R.string.enter_phone)) : (subtitle.getResources().getString(R.string.enter_amount_and_operator)));
            }
        }
        // batch finished
        if ((dirtyFlags & 0xc02L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.edtPhone, viewModelPhoneGet);
        }
        if ((dirtyFlags & 0x800L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.edtPhone, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, mCallback4, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, edtPhoneandroidTextAttrChanged);
            this.mboundView10.setOnClickListener(mCallback5);
            this.mboundView11.setOnClickListener(mCallback6);
            this.mboundView12.setOnClickListener(mCallback7);
            this.mboundView13.setOnClickListener(mCallback8);
            this.mboundView15.setOnClickListener(mCallback9);
            this.mboundView16.setOnClickListener(mCallback10);
            this.mboundView17.setOnClickListener(mCallback11);
            this.mboundView18.setOnClickListener(mCallback12);
            this.mboundView2.setOnClickListener(mCallback2);
            this.mboundView5.setOnClickListener(mCallback3);
        }
        if ((dirtyFlags & 0xc04L) != 0) {
            // api target 1

            androidx.databinding.adapters.ImageViewBindingAdapter.setImageDrawable(this.mboundView10, viewModelIsMciMboundView10AndroidDrawableHamrahAvalMboundView10AndroidDrawableHamrahAval2);
        }
        if ((dirtyFlags & 0xc01L) != 0) {
            // api target 1

            androidx.databinding.adapters.ImageViewBindingAdapter.setImageDrawable(this.mboundView11, viewModelIsMtnMboundView11AndroidDrawableIrancelMboundView11AndroidDrawableIrancell2);
        }
        if ((dirtyFlags & 0xc10L) != 0) {
            // api target 1

            androidx.databinding.adapters.ImageViewBindingAdapter.setImageDrawable(this.mboundView12, viewModelIsRightelMboundView12AndroidDrawableRightelMboundView12AndroidDrawableRightel2);
        }
        if ((dirtyFlags & 0xc08L) != 0) {
            // api target 1

            androidx.databinding.adapters.ImageViewBindingAdapter.setImageDrawable(this.mboundView13, viewModelIsTaliaMboundView13AndroidDrawableTaliaMboundView13AndroidDrawableTalia2);
        }
        if ((dirtyFlags & 0xe00L) != 0) {
            // api target 1

            this.mboundView14.setVisibility(viewModelIsTopUpViewGONEViewVISIBLE);
            this.mboundView3.setTextColor(viewModelIsTopUpMboundView3AndroidColorSlateMboundView3AndroidColorDark);
            this.mboundView4.setVisibility(viewModelIsTopUpViewGONEViewVISIBLE);
            this.mboundView6.setTextColor(viewModelIsTopUpMboundView6AndroidColorDarkMboundView6AndroidColorSlate);
            this.mboundView7.setVisibility(viewModelIsTopUpViewVISIBLEViewGONE);
            this.mboundView8.setVisibility(viewModelIsTopUpViewVISIBLEViewGONE);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.subtitle, viewModelIsTopUpSubtitleAndroidStringEnterPhoneSubtitleAndroidStringEnterAmountAndOperator);
        }
        if ((dirtyFlags & 0xd00L) != 0) {
            // api target 1

            androidx.databinding.adapters.ImageViewBindingAdapter.setImageDrawable(this.mboundView15, viewModelIsMciChargeCodeMboundView15AndroidDrawableHamrahAvalMboundView15AndroidDrawableHamrahAval2);
        }
        if ((dirtyFlags & 0xc20L) != 0) {
            // api target 1

            androidx.databinding.adapters.ImageViewBindingAdapter.setImageDrawable(this.mboundView16, viewModelIsMtnChargeCodeMboundView16AndroidDrawableIrancelMboundView16AndroidDrawableIrancell2);
        }
        if ((dirtyFlags & 0xc40L) != 0) {
            // api target 1

            androidx.databinding.adapters.ImageViewBindingAdapter.setImageDrawable(this.mboundView17, viewModelIsRightelChargeCodeMboundView17AndroidDrawableRightelMboundView17AndroidDrawableRightel2);
        }
        if ((dirtyFlags & 0xc80L) != 0) {
            // api target 1

            androidx.databinding.adapters.ImageViewBindingAdapter.setImageDrawable(this.mboundView18, viewModelIsTaliaChargeCodeMboundView18AndroidDrawableTaliaMboundView18AndroidDrawableTalia2);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        switch(sourceId) {
            case 10: {
                // localize variables for thread safety
                // viewModel
                com.masa.aryan.charge.ChargeViewModel viewModel = mViewModel;
                // viewModel != null
                boolean viewModelJavaLangObjectNull = false;



                viewModelJavaLangObjectNull = (viewModel) != (null);
                if (viewModelJavaLangObjectNull) {



                    viewModel.toggleChargeCode(3);
                }
                break;
            }
            case 5: {
                // localize variables for thread safety
                // viewModel.isMtnTopUp()
                kotlin.Unit viewModelIsMtnTopUp = null;
                // viewModel
                com.masa.aryan.charge.ChargeViewModel viewModel = mViewModel;
                // viewModel != null
                boolean viewModelJavaLangObjectNull = false;



                viewModelJavaLangObjectNull = (viewModel) != (null);
                if (viewModelJavaLangObjectNull) {


                    viewModelIsMtnTopUp = viewModel.isMtnTopUp();
                }
                break;
            }
            case 1: {
                // localize variables for thread safety
                // viewModel
                com.masa.aryan.charge.ChargeViewModel viewModel = mViewModel;
                // viewModel != null
                boolean viewModelJavaLangObjectNull = false;



                viewModelJavaLangObjectNull = (viewModel) != (null);
                if (viewModelJavaLangObjectNull) {



                    viewModel.onTabClicked(false);
                }
                break;
            }
            case 11: {
                // localize variables for thread safety
                // viewModel
                com.masa.aryan.charge.ChargeViewModel viewModel = mViewModel;
                // viewModel != null
                boolean viewModelJavaLangObjectNull = false;



                viewModelJavaLangObjectNull = (viewModel) != (null);
                if (viewModelJavaLangObjectNull) {



                    viewModel.toggleChargeCode(4);
                }
                break;
            }
            case 8: {
                // localize variables for thread safety
                // viewModel
                com.masa.aryan.charge.ChargeViewModel viewModel = mViewModel;
                // viewModel != null
                boolean viewModelJavaLangObjectNull = false;



                viewModelJavaLangObjectNull = (viewModel) != (null);
                if (viewModelJavaLangObjectNull) {



                    viewModel.toggleChargeCode(1);
                }
                break;
            }
            case 4: {
                // localize variables for thread safety
                // viewModel
                com.masa.aryan.charge.ChargeViewModel viewModel = mViewModel;
                // viewModel != null
                boolean viewModelJavaLangObjectNull = false;
                // viewModel.isMciTopUp()
                kotlin.Unit viewModelIsMciTopUp = null;



                viewModelJavaLangObjectNull = (viewModel) != (null);
                if (viewModelJavaLangObjectNull) {


                    viewModelIsMciTopUp = viewModel.isMciTopUp();
                }
                break;
            }
            case 7: {
                // localize variables for thread safety
                // viewModel.isTaliaTopUp()
                kotlin.Unit viewModelIsTaliaTopUp = null;
                // viewModel
                com.masa.aryan.charge.ChargeViewModel viewModel = mViewModel;
                // viewModel != null
                boolean viewModelJavaLangObjectNull = false;



                viewModelJavaLangObjectNull = (viewModel) != (null);
                if (viewModelJavaLangObjectNull) {


                    viewModelIsTaliaTopUp = viewModel.isTaliaTopUp();
                }
                break;
            }
            case 9: {
                // localize variables for thread safety
                // viewModel
                com.masa.aryan.charge.ChargeViewModel viewModel = mViewModel;
                // viewModel != null
                boolean viewModelJavaLangObjectNull = false;



                viewModelJavaLangObjectNull = (viewModel) != (null);
                if (viewModelJavaLangObjectNull) {



                    viewModel.toggleChargeCode(2);
                }
                break;
            }
            case 6: {
                // localize variables for thread safety
                // viewModel
                com.masa.aryan.charge.ChargeViewModel viewModel = mViewModel;
                // viewModel != null
                boolean viewModelJavaLangObjectNull = false;
                // viewModel.isRightelTopUp()
                kotlin.Unit viewModelIsRightelTopUp = null;



                viewModelJavaLangObjectNull = (viewModel) != (null);
                if (viewModelJavaLangObjectNull) {


                    viewModelIsRightelTopUp = viewModel.isRightelTopUp();
                }
                break;
            }
            case 2: {
                // localize variables for thread safety
                // viewModel
                com.masa.aryan.charge.ChargeViewModel viewModel = mViewModel;
                // viewModel != null
                boolean viewModelJavaLangObjectNull = false;



                viewModelJavaLangObjectNull = (viewModel) != (null);
                if (viewModelJavaLangObjectNull) {



                    viewModel.onTabClicked(true);
                }
                break;
            }
        }
    }
    public final void _internalCallbackOnTextChanged(int sourceId , java.lang.CharSequence callbackArg_0, int callbackArg_1, int callbackArg_2, int callbackArg_3) {
        // localize variables for thread safety
        // viewModel
        com.masa.aryan.charge.ChargeViewModel viewModel = mViewModel;
        // viewModel != null
        boolean viewModelJavaLangObjectNull = false;



        viewModelJavaLangObjectNull = (viewModel) != (null);
        if (viewModelJavaLangObjectNull) {



            viewModel.onEdittextChanged(callbackArg_0);
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): viewModel.isMtn
        flag 1 (0x2L): viewModel.phone
        flag 2 (0x3L): viewModel.isMci
        flag 3 (0x4L): viewModel.isTalia
        flag 4 (0x5L): viewModel.isRightel
        flag 5 (0x6L): viewModel.isMtnChargeCode
        flag 6 (0x7L): viewModel.isRightelChargeCode
        flag 7 (0x8L): viewModel.isTaliaChargeCode
        flag 8 (0x9L): viewModel.isMciChargeCode
        flag 9 (0xaL): viewModel.isTopUp
        flag 10 (0xbL): viewModel
        flag 11 (0xcL): null
        flag 12 (0xdL): viewModel.isMci.get() ? @android:drawable/hamrah_aval : @android:drawable/hamrah_aval_2
        flag 13 (0xeL): viewModel.isMci.get() ? @android:drawable/hamrah_aval : @android:drawable/hamrah_aval_2
        flag 14 (0xfL): viewModel.isTaliaChargeCode.get() ? @android:drawable/talia : @android:drawable/talia_2
        flag 15 (0x10L): viewModel.isTaliaChargeCode.get() ? @android:drawable/talia : @android:drawable/talia_2
        flag 16 (0x11L): viewModel.isTopUp.get() ? View.GONE : View.VISIBLE
        flag 17 (0x12L): viewModel.isTopUp.get() ? View.GONE : View.VISIBLE
        flag 18 (0x13L): viewModel.isRightelChargeCode.get() ? @android:drawable/rightel : @android:drawable/rightel_2
        flag 19 (0x14L): viewModel.isRightelChargeCode.get() ? @android:drawable/rightel : @android:drawable/rightel_2
        flag 20 (0x15L): viewModel.isRightel.get() ? @android:drawable/rightel : @android:drawable/rightel_2
        flag 21 (0x16L): viewModel.isRightel.get() ? @android:drawable/rightel : @android:drawable/rightel_2
        flag 22 (0x17L): viewModel.isTopUp.get() ? @android:color/dark : @android:color/slate
        flag 23 (0x18L): viewModel.isTopUp.get() ? @android:color/dark : @android:color/slate
        flag 24 (0x19L): viewModel.isMciChargeCode.get() ? @android:drawable/hamrah_aval : @android:drawable/hamrah_aval_2
        flag 25 (0x1aL): viewModel.isMciChargeCode.get() ? @android:drawable/hamrah_aval : @android:drawable/hamrah_aval_2
        flag 26 (0x1bL): viewModel.isTalia.get() ? @android:drawable/talia : @android:drawable/talia_2
        flag 27 (0x1cL): viewModel.isTalia.get() ? @android:drawable/talia : @android:drawable/talia_2
        flag 28 (0x1dL): viewModel.isTopUp.get() ? @android:color/slate : @android:color/dark
        flag 29 (0x1eL): viewModel.isTopUp.get() ? @android:color/slate : @android:color/dark
        flag 30 (0x1fL): viewModel.isMtn.get() ? @android:drawable/irancel : @android:drawable/irancell_2
        flag 31 (0x20L): viewModel.isMtn.get() ? @android:drawable/irancel : @android:drawable/irancell_2
        flag 32 (0x21L): viewModel.isTopUp.get() ? View.VISIBLE : View.GONE
        flag 33 (0x22L): viewModel.isTopUp.get() ? View.VISIBLE : View.GONE
        flag 34 (0x23L): viewModel.isMtnChargeCode.get() ? @android:drawable/irancel : @android:drawable/irancell_2
        flag 35 (0x24L): viewModel.isMtnChargeCode.get() ? @android:drawable/irancel : @android:drawable/irancell_2
        flag 36 (0x25L): viewModel.isTopUp.get() ? @android:string/enter_phone : @android:string/enter_amount_and_operator
        flag 37 (0x26L): viewModel.isTopUp.get() ? @android:string/enter_phone : @android:string/enter_amount_and_operator
    flag mapping end*/
    //end
}