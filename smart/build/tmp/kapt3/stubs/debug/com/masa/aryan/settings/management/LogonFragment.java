package com.masa.aryan.settings.management;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u001a\u0010)\u001a\u00020\u00022\u0006\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010-H\u0016J\b\u0010.\u001a\u00020/H\u0002J\u001a\u00100\u001a\u00020/2\u0006\u00101\u001a\u0002022\b\u00103\u001a\u0004\u0018\u000104H\u0016R\u001e\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR\u001b\u0010\u0010\u001a\u00020\u00118BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0014\u0010\u000f\u001a\u0004\b\u0012\u0010\u0013R\u001b\u0010\u0015\u001a\u00020\u00168BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0019\u0010\u000f\u001a\u0004\b\u0017\u0010\u0018R\u001b\u0010\u001a\u001a\u00020\u001b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001e\u0010\u000f\u001a\u0004\b\u001c\u0010\u001dR\u001b\u0010\u001f\u001a\u00020 8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b#\u0010\u000f\u001a\u0004\b!\u0010\"R\u001b\u0010$\u001a\u00020%8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b(\u0010\u000f\u001a\u0004\b&\u0010\'\u00a8\u00065"}, d2 = {"Lcom/masa/aryan/settings/management/LogonFragment;", "Lcom/masa/aryan/base/BaseFragment;", "Lcom/masa/aryan/databinding/FragmentLogonBinding;", "()V", "appContext", "Landroid/content/Context;", "getAppContext", "()Landroid/content/Context;", "setAppContext", "(Landroid/content/Context;)V", "dateTimeInterface", "Lcom/fanap/corepos/device/date/DateTimeInterface;", "getDateTimeInterface", "()Lcom/fanap/corepos/device/date/DateTimeInterface;", "dateTimeInterface$delegate", "Lkotlin/Lazy;", "hsm", "Lcom/fanap/corepos/device/hsm/HSMInterface;", "getHsm", "()Lcom/fanap/corepos/device/hsm/HSMInterface;", "hsm$delegate", "serialInterface", "Lcom/fanap/corepos/device/serial/SerialInterface;", "getSerialInterface", "()Lcom/fanap/corepos/device/serial/SerialInterface;", "serialInterface$delegate", "settingsRepository", "Lcom/fanap/corepos/database/base/ISettingsRepository;", "getSettingsRepository", "()Lcom/fanap/corepos/database/base/ISettingsRepository;", "settingsRepository$delegate", "transactionManager", "Lcom/fanap/corepos/iso/IIso;", "getTransactionManager", "()Lcom/fanap/corepos/iso/IIso;", "transactionManager$delegate", "transactionRepository", "Lcom/fanap/corepos/database/base/ITransactionRepository;", "getTransactionRepository", "()Lcom/fanap/corepos/database/base/ITransactionRepository;", "transactionRepository$delegate", "getViewBinding", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "logon", "", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "aryan_debug"})
@dagger.hilt.android.AndroidEntryPoint()
public final class LogonFragment extends com.masa.aryan.base.BaseFragment<com.masa.aryan.databinding.FragmentLogonBinding> {
    @javax.inject.Inject()
    public android.content.Context appContext;
    private final kotlin.Lazy transactionRepository$delegate = null;
    private final kotlin.Lazy transactionManager$delegate = null;
    private final kotlin.Lazy settingsRepository$delegate = null;
    private final kotlin.Lazy hsm$delegate = null;
    private final kotlin.Lazy dateTimeInterface$delegate = null;
    private final kotlin.Lazy serialInterface$delegate = null;
    
    public LogonFragment() {
        super();
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
    
    private final com.fanap.corepos.iso.IIso getTransactionManager() {
        return null;
    }
    
    private final com.fanap.corepos.database.base.ISettingsRepository getSettingsRepository() {
        return null;
    }
    
    private final com.fanap.corepos.device.hsm.HSMInterface getHsm() {
        return null;
    }
    
    private final com.fanap.corepos.device.date.DateTimeInterface getDateTimeInterface() {
        return null;
    }
    
    private final com.fanap.corepos.device.serial.SerialInterface getSerialInterface() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.masa.aryan.databinding.FragmentLogonBinding getViewBinding(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void logon() {
    }
}