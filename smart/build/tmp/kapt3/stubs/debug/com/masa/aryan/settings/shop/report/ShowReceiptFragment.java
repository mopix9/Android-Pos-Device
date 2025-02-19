package com.masa.aryan.settings.shop.report;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0010\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u0017H\u0002J\u0010\u0010%\u001a\u00020#2\u0006\u0010$\u001a\u00020\u0017H\u0002J\u0010\u0010&\u001a\u00020#2\u0006\u0010$\u001a\u00020\u0017H\u0002J\u0010\u0010\'\u001a\u00020#2\u0006\u0010$\u001a\u00020\u0017H\u0002J\u001a\u0010(\u001a\u00020\u00022\u0006\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010,H\u0016J\u001a\u0010-\u001a\u00020#2\u0006\u0010.\u001a\u00020/2\b\u00100\u001a\u0004\u0018\u000101H\u0016J\u0010\u00102\u001a\u00020#2\u0006\u0010$\u001a\u00020\u0017H\u0002R\u001e\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001e\u0010\n\u001a\u00020\u000b8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001d\u0010\u0010\u001a\u0004\u0018\u00010\u00118BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0012\u0010\u0013R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0018\u001a\u00020\u00198BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001c\u0010\u0015\u001a\u0004\b\u001a\u0010\u001bR\u001b\u0010\u001d\u001a\u00020\u001e8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b!\u0010\u0015\u001a\u0004\b\u001f\u0010 \u00a8\u00063"}, d2 = {"Lcom/masa/aryan/settings/shop/report/ShowReceiptFragment;", "Lcom/masa/aryan/base/BaseFragment;", "Lcom/masa/aryan/databinding/FragmentShowReceiptBinding;", "()V", "appContext", "Landroid/content/Context;", "getAppContext", "()Landroid/content/Context;", "setAppContext", "(Landroid/content/Context;)V", "loading", "Lcom/masa/aryan/main/view/LoadingFragment;", "getLoading", "()Lcom/masa/aryan/main/view/LoadingFragment;", "setLoading", "(Lcom/masa/aryan/main/view/LoadingFragment;)V", "printer", "Lcom/fanap/corepos/device/print/PrinterInterface;", "getPrinter", "()Lcom/fanap/corepos/device/print/PrinterInterface;", "printer$delegate", "Lkotlin/Lazy;", "transaction", "Lcom/fanap/corepos/database/service/model/Transaction;", "transactionRepository", "Lcom/fanap/corepos/database/base/ITransactionRepository;", "getTransactionRepository", "()Lcom/fanap/corepos/database/base/ITransactionRepository;", "transactionRepository$delegate", "viewModel", "Lcom/masa/aryan/settings/shop/report/viewmodel/ShowReceiptViewModel;", "getViewModel", "()Lcom/masa/aryan/settings/shop/report/viewmodel/ShowReceiptViewModel;", "viewModel$delegate", "drawBill", "", "item", "drawBuy", "drawCharge", "drawTopup", "getViewBinding", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "showTransaction", "aryan_debug"})
@dagger.hilt.android.AndroidEntryPoint()
public final class ShowReceiptFragment extends com.masa.aryan.base.BaseFragment<com.masa.aryan.databinding.FragmentShowReceiptBinding> {
    @javax.inject.Inject()
    public com.masa.aryan.main.view.LoadingFragment loading;
    private final kotlin.Lazy viewModel$delegate = null;
    @javax.inject.Inject()
    public android.content.Context appContext;
    private final kotlin.Lazy transactionRepository$delegate = null;
    private com.fanap.corepos.database.service.model.Transaction transaction;
    private final kotlin.Lazy printer$delegate = null;
    
    public ShowReceiptFragment() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.masa.aryan.main.view.LoadingFragment getLoading() {
        return null;
    }
    
    public final void setLoading(@org.jetbrains.annotations.NotNull()
    com.masa.aryan.main.view.LoadingFragment p0) {
    }
    
    private final com.masa.aryan.settings.shop.report.viewmodel.ShowReceiptViewModel getViewModel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context getAppContext() {
        return null;
    }
    
    public final void setAppContext(@org.jetbrains.annotations.NotNull()
    android.content.Context p0) {
    }
    
    private final com.fanap.corepos.database.base.ITransactionRepository getTransactionRepository() {
        return null;
    }
    
    private final com.fanap.corepos.device.print.PrinterInterface getPrinter() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.masa.aryan.databinding.FragmentShowReceiptBinding getViewBinding(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void showTransaction(com.fanap.corepos.database.service.model.Transaction item) {
    }
    
    private final void drawBuy(com.fanap.corepos.database.service.model.Transaction item) {
    }
    
    private final void drawTopup(com.fanap.corepos.database.service.model.Transaction item) {
    }
    
    private final void drawCharge(com.fanap.corepos.database.service.model.Transaction item) {
    }
    
    private final void drawBill(com.fanap.corepos.database.service.model.Transaction item) {
    }
}