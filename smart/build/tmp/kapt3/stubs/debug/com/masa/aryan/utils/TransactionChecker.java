package com.masa.aryan.utils;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0001-B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"J\b\u0010#\u001a\u00020 H\u0002J,\u0010$\u001a\u00020 2\"\u0010%\u001a\u001e\u0012\u0004\u0012\u00020\'\u0012\u0004\u0012\u00020\u00040&j\u000e\u0012\u0004\u0012\u00020\'\u0012\u0004\u0012\u00020\u0004`(H\u0002J\u001c\u0010)\u001a\u000e\u0012\u0004\u0012\u00020\'\u0012\u0004\u0012\u00020\u00040&2\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u001c\u0010*\u001a\u000e\u0012\u0004\u0012\u00020\'\u0012\u0004\u0012\u00020\u00040&2\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0010\u0010+\u001a\u00020 2\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0010\u0010,\u001a\u00020 2\u0006\u0010\u0015\u001a\u00020\u0016H\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u000e8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0017\u001a\u00020\u00188BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u0019\u0010\u001aR\u000e\u0010\u001d\u001a\u00020\u001eX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006."}, d2 = {"Lcom/masa/aryan/utils/TransactionChecker;", "Lkotlinx/coroutines/CoroutineScope;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "callBack", "Landroidx/lifecycle/MutableLiveData;", "Lcom/masa/aryan/utils/TransactionChecker$TransactionCheckerStatus;", "getCallBack", "()Landroidx/lifecycle/MutableLiveData;", "setCallBack", "(Landroidx/lifecycle/MutableLiveData;)V", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "merchant", "settingsRepository", "Lcom/fanap/corepos/database/base/ISettingsRepository;", "terminal", "transaction", "Lcom/fanap/corepos/database/service/model/Transaction;", "transactionManager", "Lcom/fanap/corepos/iso/IIso;", "getTransactionManager", "()Lcom/fanap/corepos/iso/IIso;", "transactionManager$delegate", "Lkotlin/Lazy;", "transactionRepository", "Lcom/fanap/corepos/database/base/ITransactionRepository;", "check", "", "context", "Landroid/content/Context;", "checkLastTransaction", "doTransaction", "adviceMap", "Ljava/util/HashMap;", "Lcom/fanap/corepos/iso/utils/IsoFields;", "Lkotlin/collections/HashMap;", "makeAdvice", "makeReverse", "startAdvice", "startReverse", "TransactionCheckerStatus", "aryan_debug"})
public final class TransactionChecker implements kotlinx.coroutines.CoroutineScope {
    @org.jetbrains.annotations.NotNull()
    public static final com.masa.aryan.utils.TransactionChecker INSTANCE = null;
    private static final java.lang.String TAG = null;
    @org.jetbrains.annotations.NotNull()
    private static androidx.lifecycle.MutableLiveData<com.masa.aryan.utils.TransactionChecker.TransactionCheckerStatus> callBack;
    private static com.fanap.corepos.database.base.ISettingsRepository settingsRepository;
    private static com.fanap.corepos.database.base.ITransactionRepository transactionRepository;
    private static final kotlin.Lazy transactionManager$delegate = null;
    private static java.lang.String terminal;
    private static java.lang.String merchant;
    private static com.fanap.corepos.database.service.model.Transaction transaction;
    
    private TransactionChecker() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public kotlin.coroutines.CoroutineContext getCoroutineContext() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<com.masa.aryan.utils.TransactionChecker.TransactionCheckerStatus> getCallBack() {
        return null;
    }
    
    public final void setCallBack(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<com.masa.aryan.utils.TransactionChecker.TransactionCheckerStatus> p0) {
    }
    
    private final com.fanap.corepos.iso.IIso getTransactionManager() {
        return null;
    }
    
    public final void check(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    private final void checkLastTransaction() {
    }
    
    private final void startReverse(com.fanap.corepos.database.service.model.Transaction transaction) {
    }
    
    private final void startAdvice(com.fanap.corepos.database.service.model.Transaction transaction) {
    }
    
    private final void doTransaction(java.util.HashMap<com.fanap.corepos.iso.utils.IsoFields, java.lang.String> adviceMap) {
    }
    
    private final java.util.HashMap<com.fanap.corepos.iso.utils.IsoFields, java.lang.String> makeAdvice(com.fanap.corepos.database.service.model.Transaction transaction) {
        return null;
    }
    
    private final java.util.HashMap<com.fanap.corepos.iso.utils.IsoFields, java.lang.String> makeReverse(com.fanap.corepos.database.service.model.Transaction transaction) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0003\u0004\u0005\u0006B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0004\u0007\b\t\n\u00a8\u0006\u000b"}, d2 = {"Lcom/masa/aryan/utils/TransactionChecker$TransactionCheckerStatus;", "", "()V", "Finished", "HideIcon", "ShowAdviceIcon", "ShowReverseIcon", "Lcom/masa/aryan/utils/TransactionChecker$TransactionCheckerStatus$Finished;", "Lcom/masa/aryan/utils/TransactionChecker$TransactionCheckerStatus$HideIcon;", "Lcom/masa/aryan/utils/TransactionChecker$TransactionCheckerStatus$ShowAdviceIcon;", "Lcom/masa/aryan/utils/TransactionChecker$TransactionCheckerStatus$ShowReverseIcon;", "aryan_debug"})
    public static abstract class TransactionCheckerStatus {
        
        private TransactionCheckerStatus() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/masa/aryan/utils/TransactionChecker$TransactionCheckerStatus$ShowReverseIcon;", "Lcom/masa/aryan/utils/TransactionChecker$TransactionCheckerStatus;", "()V", "aryan_debug"})
        public static final class ShowReverseIcon extends com.masa.aryan.utils.TransactionChecker.TransactionCheckerStatus {
            @org.jetbrains.annotations.NotNull()
            public static final com.masa.aryan.utils.TransactionChecker.TransactionCheckerStatus.ShowReverseIcon INSTANCE = null;
            
            private ShowReverseIcon() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/masa/aryan/utils/TransactionChecker$TransactionCheckerStatus$ShowAdviceIcon;", "Lcom/masa/aryan/utils/TransactionChecker$TransactionCheckerStatus;", "()V", "aryan_debug"})
        public static final class ShowAdviceIcon extends com.masa.aryan.utils.TransactionChecker.TransactionCheckerStatus {
            @org.jetbrains.annotations.NotNull()
            public static final com.masa.aryan.utils.TransactionChecker.TransactionCheckerStatus.ShowAdviceIcon INSTANCE = null;
            
            private ShowAdviceIcon() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/masa/aryan/utils/TransactionChecker$TransactionCheckerStatus$HideIcon;", "Lcom/masa/aryan/utils/TransactionChecker$TransactionCheckerStatus;", "()V", "aryan_debug"})
        public static final class HideIcon extends com.masa.aryan.utils.TransactionChecker.TransactionCheckerStatus {
            @org.jetbrains.annotations.NotNull()
            public static final com.masa.aryan.utils.TransactionChecker.TransactionCheckerStatus.HideIcon INSTANCE = null;
            
            private HideIcon() {
                super();
            }
        }
        
        @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/masa/aryan/utils/TransactionChecker$TransactionCheckerStatus$Finished;", "Lcom/masa/aryan/utils/TransactionChecker$TransactionCheckerStatus;", "()V", "aryan_debug"})
        public static final class Finished extends com.masa.aryan.utils.TransactionChecker.TransactionCheckerStatus {
            @org.jetbrains.annotations.NotNull()
            public static final com.masa.aryan.utils.TransactionChecker.TransactionCheckerStatus.Finished INSTANCE = null;
            
            private Finished() {
                super();
            }
        }
    }
}