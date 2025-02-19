package com.masa.aryan.buy;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003JP\u0010/\u001a\u0002002\"\u00101\u001a\u001e\u0012\u0004\u0012\u000203\u0012\u0004\u0012\u00020#02j\u000e\u0012\u0004\u0012\u000203\u0012\u0004\u0012\u00020#`42\"\u00105\u001a\u001e\u0012\u0004\u0012\u000203\u0012\u0004\u0012\u00020#02j\u000e\u0012\u0004\u0012\u000203\u0012\u0004\u0012\u00020#`4H\u0002J\u0010\u00106\u001a\u0002002\u0006\u00107\u001a\u00020#H\u0002J\u001a\u00108\u001a\u00020\u00022\u0006\u00109\u001a\u00020:2\b\u0010;\u001a\u0004\u0018\u00010<H\u0016J\u001a\u0010=\u001a\u0002002\u0006\u0010>\u001a\u00020?2\b\u0010@\u001a\u0004\u0018\u00010AH\u0017J,\u0010B\u001a\u0002002\"\u0010C\u001a\u001e\u0012\u0004\u0012\u000203\u0012\u0004\u0012\u00020#02j\u000e\u0012\u0004\u0012\u000203\u0012\u0004\u0012\u00020#`4H\u0002J\b\u0010D\u001a\u000200H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\b\u001a\u00020\t8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001e\u0010\u000e\u001a\u00020\u000f8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001b\u0010\u0014\u001a\u00020\u00158BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u0016\u0010\u0017R\u001e\u0010\u001a\u001a\u00020\u001b8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u000e\u0010 \u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010%\u001a\u00020&8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b)\u0010\u0019\u001a\u0004\b\'\u0010(R\u001b\u0010*\u001a\u00020+8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b.\u0010\u0019\u001a\u0004\b,\u0010-\u00a8\u0006E"}, d2 = {"Lcom/masa/aryan/buy/BuyFragment;", "Lcom/masa/aryan/base/BaseFragment;", "Lcom/masa/aryan/databinding/FragmentBuyBinding;", "()V", "adviceRepeatCount", "", "adviceTransaction", "Lcom/fanap/corepos/database/service/model/Transaction;", "appContext", "Landroid/content/Context;", "getAppContext", "()Landroid/content/Context;", "setAppContext", "(Landroid/content/Context;)V", "appInfoAmount", "Landroid/content/SharedPreferences;", "getAppInfoAmount", "()Landroid/content/SharedPreferences;", "setAppInfoAmount", "(Landroid/content/SharedPreferences;)V", "hsm", "Lcom/fanap/corepos/device/hsm/HSMInterface;", "getHsm", "()Lcom/fanap/corepos/device/hsm/HSMInterface;", "hsm$delegate", "Lkotlin/Lazy;", "loading", "Lcom/masa/aryan/main/view/LoadingFragment;", "getLoading", "()Lcom/masa/aryan/main/view/LoadingFragment;", "setLoading", "(Lcom/masa/aryan/main/view/LoadingFragment;)V", "reverseCount", "reverseTransaction", "track2", "", "transaction", "transactionManager", "Lcom/fanap/corepos/iso/IIso;", "getTransactionManager", "()Lcom/fanap/corepos/iso/IIso;", "transactionManager$delegate", "viewModell", "Lcom/masa/aryan/buy/viewmodel/BuyViewModel;", "getViewModell", "()Lcom/masa/aryan/buy/viewmodel/BuyViewModel;", "viewModell$delegate", "advice", "", "adviceMap", "Ljava/util/HashMap;", "Lcom/fanap/corepos/iso/utils/IsoFields;", "Lkotlin/collections/HashMap;", "response", "doTransaction", "pinBlock", "getViewBinding", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "reverse", "reverseMap", "showPinPad", "aryan_debug"})
@dagger.hilt.android.AndroidEntryPoint()
public final class BuyFragment extends com.masa.aryan.base.BaseFragment<com.masa.aryan.databinding.FragmentBuyBinding> {
    @javax.inject.Inject()
    public android.content.Context appContext;
    private final kotlin.Lazy viewModell$delegate = null;
    @javax.inject.Inject()
    public android.content.SharedPreferences appInfoAmount;
    @javax.inject.Inject()
    public com.masa.aryan.main.view.LoadingFragment loading;
    private final kotlin.Lazy transactionManager$delegate = null;
    private final kotlin.Lazy hsm$delegate = null;
    private java.lang.String track2;
    private com.fanap.corepos.database.service.model.Transaction adviceTransaction;
    private com.fanap.corepos.database.service.model.Transaction reverseTransaction;
    private com.fanap.corepos.database.service.model.Transaction transaction;
    private int adviceRepeatCount = 0;
    private int reverseCount = 0;
    
    public BuyFragment() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context getAppContext() {
        return null;
    }
    
    public final void setAppContext(@org.jetbrains.annotations.NotNull()
    android.content.Context p0) {
    }
    
    private final com.masa.aryan.buy.viewmodel.BuyViewModel getViewModell() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.SharedPreferences getAppInfoAmount() {
        return null;
    }
    
    public final void setAppInfoAmount(@org.jetbrains.annotations.NotNull()
    android.content.SharedPreferences p0) {
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
    public com.masa.aryan.databinding.FragmentBuyBinding getViewBinding(@org.jetbrains.annotations.NotNull()
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
    
    private final void showPinPad() {
    }
    
    private final void doTransaction(java.lang.String pinBlock) {
    }
    
    private final void advice(java.util.HashMap<com.fanap.corepos.iso.utils.IsoFields, java.lang.String> adviceMap, java.util.HashMap<com.fanap.corepos.iso.utils.IsoFields, java.lang.String> response) {
    }
    
    private final void reverse(java.util.HashMap<com.fanap.corepos.iso.utils.IsoFields, java.lang.String> reverseMap) {
    }
}