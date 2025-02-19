package com.masa.aryan.charge;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\r\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010:\u001a\u0002032\u0006\u0010\u0005\u001a\u000203H\u0002J\b\u0010;\u001a\u000203H\u0002JV\u0010<\u001a\u001e\u0012\u0004\u0012\u00020>\u0012\u0004\u0012\u0002030=j\u000e\u0012\u0004\u0012\u00020>\u0012\u0004\u0012\u000203`?2\u0006\u0010@\u001a\u0002032\"\u0010A\u001a\u001e\u0012\u0004\u0012\u00020>\u0012\u0004\u0012\u0002030=j\u000e\u0012\u0004\u0012\u00020>\u0012\u0004\u0012\u000203`?2\u0006\u0010\u0005\u001a\u000203J*\u0010B\u001a\u000e\u0012\u0004\u0012\u00020>\u0012\u0004\u0012\u0002030=2\u0006\u0010@\u001a\u0002032\u0006\u0010C\u001a\u0002032\u0006\u0010\u0005\u001a\u000203J\u000e\u0010D\u001a\u00020\u001e2\u0006\u0010E\u001a\u00020FJ\u000e\u0010G\u001a\u00020\u001e2\u0006\u0010H\u001a\u00020\u0011J\u000e\u0010I\u001a\u00020\u001e2\u0006\u0010J\u001a\u00020KR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f8F\u00a2\u0006\u0006\u001a\u0004\b\r\u0010\u000eR \u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0017X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u0017X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0018\"\u0004\b\u001c\u0010\u001aR\u0011\u0010\u001d\u001a\u00020\u001e8F\u00a2\u0006\u0006\u001a\u0004\b\u001d\u0010\u001fR\u001a\u0010 \u001a\u00020\u0017X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0018\"\u0004\b!\u0010\u001aR\u001a\u0010\"\u001a\u00020\u0017X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0018\"\u0004\b#\u0010\u001aR\u0011\u0010$\u001a\u00020\u001e8F\u00a2\u0006\u0006\u001a\u0004\b$\u0010\u001fR\u001a\u0010%\u001a\u00020\u0017X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0018\"\u0004\b&\u0010\u001aR\u001a\u0010\'\u001a\u00020\u0017X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\'\u0010\u0018\"\u0004\b(\u0010\u001aR\u0011\u0010)\u001a\u00020\u001e8F\u00a2\u0006\u0006\u001a\u0004\b)\u0010\u001fR\u001a\u0010*\u001a\u00020\u0017X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\u0018\"\u0004\b+\u0010\u001aR\u001a\u0010,\u001a\u00020\u0017X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\u0018\"\u0004\b-\u0010\u001aR\u0011\u0010.\u001a\u00020\u001e8F\u00a2\u0006\u0006\u001a\u0004\b.\u0010\u001fR\u001a\u0010/\u001a\u00020\u0017X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\u0018\"\u0004\b0\u0010\u001aR \u00101\u001a\b\u0012\u0004\u0012\u00020302X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b4\u00105\"\u0004\b6\u00107R\u0013\u00108\u001a\u0004\u0018\u00010\f8F\u00a2\u0006\u0006\u001a\u0004\b9\u0010\u000e\u00a8\u0006L"}, d2 = {"Lcom/masa/aryan/charge/ChargeViewModel;", "Lcom/masa/aryan/base/BaseViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "amount", "Landroidx/databinding/ObservableInt;", "getAmount", "()Landroidx/databinding/ObservableInt;", "setAmount", "(Landroidx/databinding/ObservableInt;)V", "chargeCodeOperator", "Lcom/fanap/corepos/utils/Operator;", "getChargeCodeOperator", "()Lcom/fanap/corepos/utils/Operator;", "codeTabClicked", "Landroidx/lifecycle/MutableLiveData;", "", "getCodeTabClicked", "()Landroidx/lifecycle/MutableLiveData;", "setCodeTabClicked", "(Landroidx/lifecycle/MutableLiveData;)V", "isMci", "Landroidx/databinding/ObservableBoolean;", "()Landroidx/databinding/ObservableBoolean;", "setMci", "(Landroidx/databinding/ObservableBoolean;)V", "isMciChargeCode", "setMciChargeCode", "isMciTopUp", "", "()Lkotlin/Unit;", "isMtn", "setMtn", "isMtnChargeCode", "setMtnChargeCode", "isMtnTopUp", "isRightel", "setRightel", "isRightelChargeCode", "setRightelChargeCode", "isRightelTopUp", "isTalia", "setTalia", "isTaliaChargeCode", "setTaliaChargeCode", "isTaliaTopUp", "isTopUp", "setTopUp", "phone", "Landroidx/databinding/ObservableField;", "", "getPhone", "()Landroidx/databinding/ObservableField;", "setPhone", "(Landroidx/databinding/ObservableField;)V", "topupOperator", "getTopupOperator", "calcChargeAmountCode", "getChargeOrganization", "makeReceipt", "Ljava/util/HashMap;", "Lcom/fanap/corepos/iso/utils/IsoFields;", "Lkotlin/collections/HashMap;", "track2", "data", "makeTransaction", "pinBlock", "onEdittextChanged", "phoneNumber", "", "onTabClicked", "isTopUpClicked", "toggleChargeCode", "type", "", "aryan_debug"})
public final class ChargeViewModel extends com.masa.aryan.base.BaseViewModel {
    @org.jetbrains.annotations.NotNull()
    private androidx.databinding.ObservableBoolean isTopUp;
    @org.jetbrains.annotations.NotNull()
    private androidx.lifecycle.MutableLiveData<java.lang.Boolean> codeTabClicked;
    @org.jetbrains.annotations.NotNull()
    private androidx.databinding.ObservableInt amount;
    @org.jetbrains.annotations.NotNull()
    private androidx.databinding.ObservableBoolean isMci;
    @org.jetbrains.annotations.NotNull()
    private androidx.databinding.ObservableBoolean isMtn;
    @org.jetbrains.annotations.NotNull()
    private androidx.databinding.ObservableBoolean isRightel;
    @org.jetbrains.annotations.NotNull()
    private androidx.databinding.ObservableBoolean isTalia;
    @org.jetbrains.annotations.NotNull()
    private androidx.databinding.ObservableField<java.lang.String> phone;
    @org.jetbrains.annotations.NotNull()
    private androidx.databinding.ObservableBoolean isMciChargeCode;
    @org.jetbrains.annotations.NotNull()
    private androidx.databinding.ObservableBoolean isMtnChargeCode;
    @org.jetbrains.annotations.NotNull()
    private androidx.databinding.ObservableBoolean isRightelChargeCode;
    @org.jetbrains.annotations.NotNull()
    private androidx.databinding.ObservableBoolean isTaliaChargeCode;
    
    public ChargeViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.databinding.ObservableBoolean isTopUp() {
        return null;
    }
    
    public final void setTopUp(@org.jetbrains.annotations.NotNull()
    androidx.databinding.ObservableBoolean p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.Boolean> getCodeTabClicked() {
        return null;
    }
    
    public final void setCodeTabClicked(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<java.lang.Boolean> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.databinding.ObservableInt getAmount() {
        return null;
    }
    
    public final void setAmount(@org.jetbrains.annotations.NotNull()
    androidx.databinding.ObservableInt p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.databinding.ObservableBoolean isMci() {
        return null;
    }
    
    public final void setMci(@org.jetbrains.annotations.NotNull()
    androidx.databinding.ObservableBoolean p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.databinding.ObservableBoolean isMtn() {
        return null;
    }
    
    public final void setMtn(@org.jetbrains.annotations.NotNull()
    androidx.databinding.ObservableBoolean p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.databinding.ObservableBoolean isRightel() {
        return null;
    }
    
    public final void setRightel(@org.jetbrains.annotations.NotNull()
    androidx.databinding.ObservableBoolean p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.databinding.ObservableBoolean isTalia() {
        return null;
    }
    
    public final void setTalia(@org.jetbrains.annotations.NotNull()
    androidx.databinding.ObservableBoolean p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.databinding.ObservableField<java.lang.String> getPhone() {
        return null;
    }
    
    public final void setPhone(@org.jetbrains.annotations.NotNull()
    androidx.databinding.ObservableField<java.lang.String> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.databinding.ObservableBoolean isMciChargeCode() {
        return null;
    }
    
    public final void setMciChargeCode(@org.jetbrains.annotations.NotNull()
    androidx.databinding.ObservableBoolean p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.databinding.ObservableBoolean isMtnChargeCode() {
        return null;
    }
    
    public final void setMtnChargeCode(@org.jetbrains.annotations.NotNull()
    androidx.databinding.ObservableBoolean p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.databinding.ObservableBoolean isRightelChargeCode() {
        return null;
    }
    
    public final void setRightelChargeCode(@org.jetbrains.annotations.NotNull()
    androidx.databinding.ObservableBoolean p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.databinding.ObservableBoolean isTaliaChargeCode() {
        return null;
    }
    
    public final void setTaliaChargeCode(@org.jetbrains.annotations.NotNull()
    androidx.databinding.ObservableBoolean p0) {
    }
    
    public final void onTabClicked(boolean isTopUpClicked) {
    }
    
    public final void toggleChargeCode(int type) {
    }
    
    public final void onEdittextChanged(@org.jetbrains.annotations.NotNull()
    java.lang.CharSequence phoneNumber) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlin.Unit isMciTopUp() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlin.Unit isMtnTopUp() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlin.Unit isRightelTopUp() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlin.Unit isTaliaTopUp() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.fanap.corepos.utils.Operator getTopupOperator() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.fanap.corepos.utils.Operator getChargeCodeOperator() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.HashMap<com.fanap.corepos.iso.utils.IsoFields, java.lang.String> makeTransaction(@org.jetbrains.annotations.NotNull()
    java.lang.String track2, @org.jetbrains.annotations.NotNull()
    java.lang.String pinBlock, @org.jetbrains.annotations.NotNull()
    java.lang.String amount) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.HashMap<com.fanap.corepos.iso.utils.IsoFields, java.lang.String> makeReceipt(@org.jetbrains.annotations.NotNull()
    java.lang.String track2, @org.jetbrains.annotations.NotNull()
    java.util.HashMap<com.fanap.corepos.iso.utils.IsoFields, java.lang.String> data, @org.jetbrains.annotations.NotNull()
    java.lang.String amount) {
        return null;
    }
    
    private final java.lang.String getChargeOrganization() {
        return null;
    }
    
    private final java.lang.String calcChargeAmountCode(java.lang.String amount) {
        return null;
    }
}