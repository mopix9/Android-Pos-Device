package com.masa.aryan.bill;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003JP\u0010,\u001a\u00020-2\"\u0010.\u001a\u001e\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\t0/j\u000e\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\t`12\"\u00102\u001a\u001e\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\t0/j\u000e\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\t`1H\u0002J\u0010\u00103\u001a\u00020-2\u0006\u00104\u001a\u00020\tH\u0002J\u001a\u00105\u001a\u00020\u00022\u0006\u00106\u001a\u0002072\b\u00108\u001a\u0004\u0018\u000109H\u0016J\u001a\u0010:\u001a\u00020-2\u0006\u0010;\u001a\u00020<2\b\u0010=\u001a\u0004\u0018\u00010>H\u0016J,\u0010?\u001a\u00020-2\"\u0010@\u001a\u001e\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\t0/j\u000e\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\t`1H\u0002J\b\u0010A\u001a\u00020-H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u00020\u000b8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0011\u001a\u00020\u00128BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0013\u0010\u0014R\u001e\u0010\u0017\u001a\u00020\u00188\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u000e\u0010\u001d\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\"\u001a\u00020#8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b&\u0010\u0016\u001a\u0004\b$\u0010%R\u001b\u0010\'\u001a\u00020(8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b+\u0010\u0016\u001a\u0004\b)\u0010*\u00a8\u0006B"}, d2 = {"Lcom/masa/aryan/bill/BillDetailFragment;", "Lcom/masa/aryan/base/BaseFragment;", "Lcom/masa/aryan/databinding/FragmentBillDetailBinding;", "()V", "adviceRepeatCount", "", "adviceTransaction", "Lcom/fanap/corepos/database/service/model/Transaction;", "amount", "", "appContext", "Landroid/content/Context;", "getAppContext", "()Landroid/content/Context;", "setAppContext", "(Landroid/content/Context;)V", "billId", "hsm", "Lcom/fanap/corepos/device/hsm/HSMInterface;", "getHsm", "()Lcom/fanap/corepos/device/hsm/HSMInterface;", "hsm$delegate", "Lkotlin/Lazy;", "loading", "Lcom/masa/aryan/main/view/LoadingFragment;", "getLoading", "()Lcom/masa/aryan/main/view/LoadingFragment;", "setLoading", "(Lcom/masa/aryan/main/view/LoadingFragment;)V", "payId", "reverseCount", "reverseTransaction", "track2", "transaction", "transactionManager", "Lcom/fanap/corepos/iso/IIso;", "getTransactionManager", "()Lcom/fanap/corepos/iso/IIso;", "transactionManager$delegate", "viewModel", "Lcom/masa/aryan/bill/viewmodel/BillDetailViewModel;", "getViewModel", "()Lcom/masa/aryan/bill/viewmodel/BillDetailViewModel;", "viewModel$delegate", "advice", "", "adviceMap", "Ljava/util/HashMap;", "Lcom/fanap/corepos/iso/utils/IsoFields;", "Lkotlin/collections/HashMap;", "response", "doTransaction", "pinBlock", "getViewBinding", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "reverse", "reverseMap", "showPinPad", "aryan_debug"})
@dagger.hilt.android.AndroidEntryPoint()
public final class BillDetailFragment extends com.masa.aryan.base.BaseFragment<com.masa.aryan.databinding.FragmentBillDetailBinding> {
    @javax.inject.Inject()
    public android.content.Context appContext;
    private final kotlin.Lazy viewModel$delegate = null;
    @javax.inject.Inject()
    public com.masa.aryan.main.view.LoadingFragment loading;
    private final kotlin.Lazy transactionManager$delegate = null;
    private final kotlin.Lazy hsm$delegate = null;
    private java.lang.String track2;
    private java.lang.String billId;
    private java.lang.String payId;
    private java.lang.String amount;
    private com.fanap.corepos.database.service.model.Transaction adviceTransaction;
    private com.fanap.corepos.database.service.model.Transaction reverseTransaction;
    private com.fanap.corepos.database.service.model.Transaction transaction;
    private int adviceRepeatCount = 0;
    private int reverseCount = 0;
    
    public BillDetailFragment() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context getAppContext() {
        return null;
    }
    
    public final void setAppContext(@org.jetbrains.annotations.NotNull()
    android.content.Context p0) {
    }
    
    private final com.masa.aryan.bill.viewmodel.BillDetailViewModel getViewModel() {
        return null;
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
    public com.masa.aryan.databinding.FragmentBillDetailBinding getViewBinding(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void showPinPad() {
    }
    
    private final void doTransaction(java.lang.String pinBlock) {
    }
    
    private final void advice(java.util.HashMap<com.fanap.corepos.iso.utils.IsoFields, java.lang.String> adviceMap, java.util.HashMap<com.fanap.corepos.iso.utils.IsoFields, java.lang.String> response) {
    }
    
    private final void reverse(java.util.HashMap<com.fanap.corepos.iso.utils.IsoFields, java.lang.String> reverseMap) {
    }
}