package com.masa.aryan.settings.shop.report.viewmodel;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J)\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00122\u0006\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\fH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0016J1\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00122\u0006\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u0019H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001aJ\u0006\u0010\u001b\u001a\u00020\fJ@\u0010\u001c\u001a\u001e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001f0\u001dj\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001f` 2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\"\u001a\u00020\f2\u0006\u0010#\u001a\u00020\fJ0\u0010$\u001a\u001e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001f0\u001dj\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001f` 2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012J@\u0010%\u001a\u001e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001f0\u001dj\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001f` 2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010&\u001a\u00020\f2\u0006\u0010\'\u001a\u00020\fJ@\u0010(\u001a\u001e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001f0\u001dj\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001f` 2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\"\u001a\u00020\f2\u0006\u0010#\u001a\u00020\fR\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\u000b\u001a\u00020\fX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006)"}, d2 = {"Lcom/masa/aryan/settings/shop/report/viewmodel/TransactionListViewModel;", "Lcom/masa/aryan/base/BaseViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "transactionRepository", "Lcom/fanap/corepos/database/base/ITransactionRepository;", "getTransactionRepository", "()Lcom/fanap/corepos/database/base/ITransactionRepository;", "transactionRepository$delegate", "Lkotlin/Lazy;", "type", "", "getType", "()Ljava/lang/String;", "setType", "(Ljava/lang/String;)V", "getSuccessTransactions", "", "Lcom/fanap/corepos/database/service/model/Transaction;", "start", "end", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSuccessTransactionsLazy", "l", "", "(Ljava/lang/String;Ljava/lang/String;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTitle", "makeAllReceipt", "Ljava/util/HashMap;", "Lcom/fanap/corepos/iso/utils/IsoFields;", "", "Lkotlin/collections/HashMap;", "transactions", "startDate", "endDate", "makeBodyReceipt", "makeFooterReceipt", "amount", "count", "makeHeaderReceipt", "aryan_debug"})
public final class TransactionListViewModel extends com.masa.aryan.base.BaseViewModel {
    public java.lang.String type;
    private final kotlin.Lazy transactionRepository$delegate = null;
    
    public TransactionListViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getType() {
        return null;
    }
    
    public final void setType(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    private final com.fanap.corepos.database.base.ITransactionRepository getTransactionRepository() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.HashMap<com.fanap.corepos.iso.utils.IsoFields, java.lang.Object> makeAllReceipt(@org.jetbrains.annotations.NotNull()
    java.util.List<com.fanap.corepos.database.service.model.Transaction> transactions, @org.jetbrains.annotations.NotNull()
    java.lang.String startDate, @org.jetbrains.annotations.NotNull()
    java.lang.String endDate) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.HashMap<com.fanap.corepos.iso.utils.IsoFields, java.lang.Object> makeHeaderReceipt(@org.jetbrains.annotations.NotNull()
    java.util.List<com.fanap.corepos.database.service.model.Transaction> transactions, @org.jetbrains.annotations.NotNull()
    java.lang.String startDate, @org.jetbrains.annotations.NotNull()
    java.lang.String endDate) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.HashMap<com.fanap.corepos.iso.utils.IsoFields, java.lang.Object> makeBodyReceipt(@org.jetbrains.annotations.NotNull()
    java.util.List<com.fanap.corepos.database.service.model.Transaction> transactions) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.HashMap<com.fanap.corepos.iso.utils.IsoFields, java.lang.Object> makeFooterReceipt(@org.jetbrains.annotations.NotNull()
    java.util.List<com.fanap.corepos.database.service.model.Transaction> transactions, @org.jetbrains.annotations.NotNull()
    java.lang.String amount, @org.jetbrains.annotations.NotNull()
    java.lang.String count) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getSuccessTransactionsLazy(@org.jetbrains.annotations.NotNull()
    java.lang.String start, @org.jetbrains.annotations.NotNull()
    java.lang.String end, long l, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.fanap.corepos.database.service.model.Transaction>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getSuccessTransactions(@org.jetbrains.annotations.NotNull()
    java.lang.String start, @org.jetbrains.annotations.NotNull()
    java.lang.String end, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.fanap.corepos.database.service.model.Transaction>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTitle() {
        return null;
    }
}