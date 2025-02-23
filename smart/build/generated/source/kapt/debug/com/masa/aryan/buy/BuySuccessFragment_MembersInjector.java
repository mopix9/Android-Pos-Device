// Generated by Dagger (https://dagger.dev).
package com.masa.aryan.buy;

import android.content.Context;
import android.content.SharedPreferences;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.inject.Provider;

@QualifierMetadata
@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class BuySuccessFragment_MembersInjector implements MembersInjector<BuySuccessFragment> {
  private final Provider<Context> appContextProvider;

  private final Provider<SharedPreferences> appInfoAmountProvider;

  public BuySuccessFragment_MembersInjector(Provider<Context> appContextProvider,
      Provider<SharedPreferences> appInfoAmountProvider) {
    this.appContextProvider = appContextProvider;
    this.appInfoAmountProvider = appInfoAmountProvider;
  }

  public static MembersInjector<BuySuccessFragment> create(Provider<Context> appContextProvider,
      Provider<SharedPreferences> appInfoAmountProvider) {
    return new BuySuccessFragment_MembersInjector(appContextProvider, appInfoAmountProvider);
  }

  @Override
  public void injectMembers(BuySuccessFragment instance) {
    injectAppContext(instance, appContextProvider.get());
    injectAppInfoAmount(instance, appInfoAmountProvider.get());
  }

  @InjectedFieldSignature("com.masa.aryan.buy.BuySuccessFragment.appContext")
  public static void injectAppContext(BuySuccessFragment instance, Context appContext) {
    instance.appContext = appContext;
  }

  @InjectedFieldSignature("com.masa.aryan.buy.BuySuccessFragment.appInfoAmount")
  public static void injectAppInfoAmount(BuySuccessFragment instance,
      SharedPreferences appInfoAmount) {
    instance.appInfoAmount = appInfoAmount;
  }
}
