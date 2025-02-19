package com.masa.aryan.charge;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003JP\u00100\u001a\u0002012\"\u00102\u001a\u001e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u00050\u001ej\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u0005` 2\"\u00103\u001a\u001e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u00050\u001ej\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u0005` H\u0002J\u0018\u00104\u001a\u0002012\u0006\u00105\u001a\u00020\u00052\u0006\u00106\u001a\u00020\u0005H\u0002J\u001a\u00107\u001a\u00020\u00022\u0006\u00108\u001a\u0002092\b\u0010:\u001a\u0004\u0018\u00010;H\u0016J\u001a\u0010<\u001a\u0002012\u0006\u0010=\u001a\u00020>2\b\u0010?\u001a\u0004\u0018\u00010@H\u0017JA\u0010A\u001a\u00020B2&\u0010C\u001a\"\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u001ej\u0010\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u0005\u0018\u0001` 2\u0006\u00106\u001a\u00020\u0005H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010DJ,\u0010E\u001a\u0002012\"\u0010F\u001a\u001e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u00050\u001ej\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u0005` H\u0002J\b\u0010G\u001a\u000201H\u0002J\b\u0010H\u001a\u000201H\u0002J\u0010\u0010I\u001a\u0002012\u0006\u0010J\u001a\u00020BH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u00020\u000b8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0011\u001a\u00020\u00128BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0013\u0010\u0014R\u001e\u0010\u0017\u001a\u00020\u00188\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR*\u0010\u001d\u001a\u001e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u00050\u001ej\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u0005` X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010&\u001a\u00020\'8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b*\u0010\u0016\u001a\u0004\b(\u0010)R\u001b\u0010+\u001a\u00020,8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b/\u0010\u0016\u001a\u0004\b-\u0010.\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006K"}, d2 = {"Lcom/masa/aryan/charge/ChargeFragment;", "Lcom/masa/aryan/base/BaseFragment;", "Lcom/masa/aryan/databinding/FragmentChargeBinding;", "()V", "AMOUNT_REQUEST", "", "adviceRepeatCount", "", "adviceTransaction", "Lcom/fanap/corepos/database/service/model/Transaction;", "appContext", "Landroid/content/Context;", "getAppContext", "()Landroid/content/Context;", "setAppContext", "(Landroid/content/Context;)V", "chargeCodeAmount", "hsm", "Lcom/fanap/corepos/device/hsm/HSMInterface;", "getHsm", "()Lcom/fanap/corepos/device/hsm/HSMInterface;", "hsm$delegate", "Lkotlin/Lazy;", "loading", "Lcom/masa/aryan/main/view/LoadingFragment;", "getLoading", "()Lcom/masa/aryan/main/view/LoadingFragment;", "setLoading", "(Lcom/masa/aryan/main/view/LoadingFragment;)V", "map", "Ljava/util/HashMap;", "Lcom/fanap/corepos/iso/utils/IsoFields;", "Lkotlin/collections/HashMap;", "reverseCount", "reverseTransaction", "topupAmount", "track2", "transaction", "transactionManager", "Lcom/fanap/corepos/iso/IIso;", "getTransactionManager", "()Lcom/fanap/corepos/iso/IIso;", "transactionManager$delegate", "viewModel", "Lcom/masa/aryan/charge/ChargeViewModel;", "getViewModel", "()Lcom/masa/aryan/charge/ChargeViewModel;", "viewModel$delegate", "advice", "", "adviceMap", "response", "doTransaction", "pinBlock", "amount", "getViewBinding", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "prepareReceiptPrint", "", "result", "(Ljava/util/HashMap;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "reverse", "reverseMap", "sendChargeCodeRequest", "sendTopupRequest", "showAmountBottomSheet", "isChargeCode", "aryan_debug"})
@dagger.hilt.android.AndroidEntryPoint()
public final class ChargeFragment extends com.masa.aryan.base.BaseFragment<com.masa.aryan.databinding.FragmentChargeBinding> {
    private final kotlin.Lazy viewModel$delegate = null;
    private final java.lang.String AMOUNT_REQUEST = "200";
    private java.lang.String topupAmount;
    private java.lang.String chargeCodeAmount;
    @javax.inject.Inject()
    public android.content.Context appContext;
    @javax.inject.Inject()
    public com.masa.aryan.main.view.LoadingFragment loading;
    private final kotlin.Lazy transactionManager$delegate = null;
    private final kotlin.Lazy hsm$delegate = null;
    private java.lang.String track2;
    private java.util.HashMap<com.fanap.corepos.iso.utils.IsoFields, java.lang.String> map;
    private com.fanap.corepos.database.service.model.Transaction adviceTransaction;
    private com.fanap.corepos.database.service.model.Transaction reverseTransaction;
    private com.fanap.corepos.database.service.model.Transaction transaction;
    private int adviceRepeatCount = 0;
    private int reverseCount = 0;
    
    public ChargeFragment() {
        super();
    }
    
    private final com.masa.aryan.charge.ChargeViewModel getViewModel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context getAppContext() {
        return null;
    }
    
    public final void setAppContext(@org.jetbrains.annotations.NotNull()
    android.content.Context p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.masa.aryan.main.view.LoadingFragment getLoading() {
        return null;
    }
    
    public final void setLoading(@org.jetbrains.annotations.NotNull()
    com.masa.aryan.main.view.LoadingFragment p0) {
    }
    
    private final com.fanap.corepos.iso.IIso getTransactionManager() {
        return null;
    }
    
    private final com.fanap.corepos.device.hsm.HSMInterface getHsm() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.masa.aryan.databinding.FragmentChargeBinding getViewBinding(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container) {
        return null;
    }
    
    @android.annotation.SuppressLint(value = {"ClickableViewAccessibility"})
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void sendChargeCodeRequest() {
    }
    
    private final void sendTopupRequest() {
    }
    
    private final void showAmountBottomSheet(boolean isChargeCode) {
    }
    
    private final void doTransaction(java.lang.String pinBlock, java.lang.String amount) {
    }
    
    private final void advice(java.util.HashMap<com.fanap.corepos.iso.utils.IsoFields, java.lang.String> adviceMap, java.util.HashMap<com.fanap.corepos.iso.utils.IsoFields, java.lang.String> response) {
    }
    
    private final void reverse(java.util.HashMap<com.fanap.corepos.iso.utils.IsoFields, java.lang.String> reverseMap) {
    }
    
    private final java.lang.Object prepareReceiptPrint(java.util.HashMap<com.fanap.corepos.iso.utils.IsoFields, java.lang.String> result, java.lang.String amount, kotlin.coroutines.Continuation<? super java.lang.Boolean> continuation) {
        return null;
    }
}