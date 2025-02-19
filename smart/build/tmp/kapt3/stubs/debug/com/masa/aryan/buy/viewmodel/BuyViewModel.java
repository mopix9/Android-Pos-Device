package com.masa.aryan.buy.viewmodel;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J2\u0010\u0017\u001a\u001e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u001a0\u0018j\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u001a`\u001b2\u0006\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u001eJ2\u0010\u001f\u001a\u001e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u001a0\u0018j\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u001a`\u001b2\u0006\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u001eJ2\u0010 \u001a\u001e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u001a0\u0018j\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u001a`\u001b2\u0006\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u001eJ\"\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00070\u00182\u0006\u0010\u001c\u001a\u00020\u00072\u0006\u0010\"\u001a\u00020\u0007J\u0006\u0010\u0012\u001a\u00020#R(\u0010\u0005\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u00070\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR \u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R \u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00070\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0011\"\u0004\b\u0016\u0010\u0013\u00a8\u0006$"}, d2 = {"Lcom/masa/aryan/buy/viewmodel/BuyViewModel;", "Lcom/masa/aryan/base/BaseViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "amount", "Landroidx/databinding/ObservableField;", "", "kotlin.jvm.PlatformType", "getAmount", "()Landroidx/databinding/ObservableField;", "setAmount", "(Landroidx/databinding/ObservableField;)V", "onConfirmClicked", "Landroidx/lifecycle/MutableLiveData;", "", "getOnConfirmClicked", "()Landroidx/lifecycle/MutableLiveData;", "setOnConfirmClicked", "(Landroidx/lifecycle/MutableLiveData;)V", "onError", "getOnError", "setOnError", "makeFailReceipt", "Ljava/util/HashMap;", "Lcom/fanap/corepos/iso/utils/IsoFields;", "", "Lkotlin/collections/HashMap;", "track2", "transaction", "Lcom/fanap/corepos/database/service/model/Transaction;", "makeNullResponseReceipt", "makeReceipt", "makeTransaction", "pinBlock", "", "aryan_debug"})
public final class BuyViewModel extends com.masa.aryan.base.BaseViewModel {
    @org.jetbrains.annotations.NotNull()
    private androidx.databinding.ObservableField<java.lang.String> amount;
    @org.jetbrains.annotations.NotNull()
    private androidx.lifecycle.MutableLiveData<java.lang.Boolean> onConfirmClicked;
    @org.jetbrains.annotations.NotNull()
    private androidx.lifecycle.MutableLiveData<java.lang.String> onError;
    
    public BuyViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.databinding.ObservableField<java.lang.String> getAmount() {
        return null;
    }
    
    public final void setAmount(@org.jetbrains.annotations.NotNull()
    androidx.databinding.ObservableField<java.lang.String> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.Boolean> getOnConfirmClicked() {
        return null;
    }
    
    public final void setOnConfirmClicked(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<java.lang.Boolean> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.String> getOnError() {
        return null;
    }
    
    public final void setOnError(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<java.lang.String> p0) {
    }
    
    public final void setOnConfirmClicked() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.HashMap<com.fanap.corepos.iso.utils.IsoFields, java.lang.String> makeTransaction(@org.jetbrains.annotations.NotNull()
    java.lang.String track2, @org.jetbrains.annotations.NotNull()
    java.lang.String pinBlock) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.HashMap<com.fanap.corepos.iso.utils.IsoFields, java.lang.Object> makeReceipt(@org.jetbrains.annotations.NotNull()
    java.lang.String track2, @org.jetbrains.annotations.NotNull()
    com.fanap.corepos.database.service.model.Transaction transaction) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.HashMap<com.fanap.corepos.iso.utils.IsoFields, java.lang.Object> makeFailReceipt(@org.jetbrains.annotations.NotNull()
    java.lang.String track2, @org.jetbrains.annotations.NotNull()
    com.fanap.corepos.database.service.model.Transaction transaction) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.HashMap<com.fanap.corepos.iso.utils.IsoFields, java.lang.Object> makeNullResponseReceipt(@org.jetbrains.annotations.NotNull()
    java.lang.String track2, @org.jetbrains.annotations.NotNull()
    com.fanap.corepos.database.service.model.Transaction transaction) {
        return null;
    }
}