package com.masa.aryan.settings.shop.report.viewmodel;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J,\u0010\u0005\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b`\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002J,\u0010\f\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b`\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002J4\u0010\r\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b`\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J0\u0010\u0010\u001a\"\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0018\u00010\u0006j\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0018\u0001`\t2\b\u0010\u0011\u001a\u0004\u0018\u00010\u000b\u00a8\u0006\u0012"}, d2 = {"Lcom/masa/aryan/settings/shop/report/viewmodel/ShowReceiptViewModel;", "Lcom/masa/aryan/base/BaseViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "makeBillReceipt", "Ljava/util/HashMap;", "Lcom/fanap/corepos/iso/utils/IsoFields;", "", "Lkotlin/collections/HashMap;", "transaction", "Lcom/fanap/corepos/database/service/model/Transaction;", "makeBuyReceipt", "makeChargeReceipt", "isTopUp", "", "makeReceipt", "input", "aryan_debug"})
public final class ShowReceiptViewModel extends com.masa.aryan.base.BaseViewModel {
    
    public ShowReceiptViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.HashMap<com.fanap.corepos.iso.utils.IsoFields, java.lang.Object> makeReceipt(@org.jetbrains.annotations.Nullable()
    com.fanap.corepos.database.service.model.Transaction input) {
        return null;
    }
    
    private final java.util.HashMap<com.fanap.corepos.iso.utils.IsoFields, java.lang.Object> makeBuyReceipt(com.fanap.corepos.database.service.model.Transaction transaction) {
        return null;
    }
    
    private final java.util.HashMap<com.fanap.corepos.iso.utils.IsoFields, java.lang.Object> makeBillReceipt(com.fanap.corepos.database.service.model.Transaction transaction) {
        return null;
    }
    
    private final java.util.HashMap<com.fanap.corepos.iso.utils.IsoFields, java.lang.Object> makeChargeReceipt(com.fanap.corepos.database.service.model.Transaction transaction, boolean isTopUp) {
        return null;
    }
}