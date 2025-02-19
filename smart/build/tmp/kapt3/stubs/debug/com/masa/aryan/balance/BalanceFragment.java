package com.masa.aryan.balance;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0010\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u0017H\u0002J\u001a\u0010%\u001a\u00020\u00022\u0006\u0010&\u001a\u00020\'2\b\u0010(\u001a\u0004\u0018\u00010)H\u0016J\u001a\u0010*\u001a\u00020#2\u0006\u0010+\u001a\u00020,2\b\u0010-\u001a\u0004\u0018\u00010.H\u0016J\b\u0010/\u001a\u00020#H\u0002R\u001e\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR\u001e\u0010\u0010\u001a\u00020\u00118\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0018\u001a\u00020\u00198BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001c\u0010\u000f\u001a\u0004\b\u001a\u0010\u001bR\u001b\u0010\u001d\u001a\u00020\u001e8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b!\u0010\u000f\u001a\u0004\b\u001f\u0010 \u00a8\u00060"}, d2 = {"Lcom/masa/aryan/balance/BalanceFragment;", "Lcom/masa/aryan/base/BaseFragment;", "Lcom/masa/aryan/databinding/FragmentBalanceBinding;", "()V", "appContext", "Landroid/content/Context;", "getAppContext", "()Landroid/content/Context;", "setAppContext", "(Landroid/content/Context;)V", "hsm", "Lcom/fanap/corepos/device/hsm/HSMInterface;", "getHsm", "()Lcom/fanap/corepos/device/hsm/HSMInterface;", "hsm$delegate", "Lkotlin/Lazy;", "loading", "Lcom/masa/aryan/main/view/LoadingFragment;", "getLoading", "()Lcom/masa/aryan/main/view/LoadingFragment;", "setLoading", "(Lcom/masa/aryan/main/view/LoadingFragment;)V", "track2", "", "transactionManager", "Lcom/fanap/corepos/iso/IIso;", "getTransactionManager", "()Lcom/fanap/corepos/iso/IIso;", "transactionManager$delegate", "viewModel", "Lcom/masa/aryan/balance/viewmodel/BalanceViewModel;", "getViewModel", "()Lcom/masa/aryan/balance/viewmodel/BalanceViewModel;", "viewModel$delegate", "doTransaction", "", "pinBlock", "getViewBinding", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "showPinPad", "aryan_debug"})
@dagger.hilt.android.AndroidEntryPoint()
public final class BalanceFragment extends com.masa.aryan.base.BaseFragment<com.masa.aryan.databinding.FragmentBalanceBinding> {
    @javax.inject.Inject()
    public android.content.Context appContext;
    private final kotlin.Lazy viewModel$delegate = null;
    @javax.inject.Inject()
    public com.masa.aryan.main.view.LoadingFragment loading;
    private final kotlin.Lazy transactionManager$delegate = null;
    private final kotlin.Lazy hsm$delegate = null;
    private java.lang.String track2;
    
    public BalanceFragment() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context getAppContext() {
        return null;
    }
    
    public final void setAppContext(@org.jetbrains.annotations.NotNull()
    android.content.Context p0) {
    }
    
    private final com.masa.aryan.balance.viewmodel.BalanceViewModel getViewModel() {
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
    public com.masa.aryan.databinding.FragmentBalanceBinding getViewBinding(@org.jetbrains.annotations.NotNull()
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
}