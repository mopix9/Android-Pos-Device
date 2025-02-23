// Generated by Dagger (https://dagger.dev).
package com.masa.aryan.charge;

import android.content.Context;
import com.masa.aryan.main.view.LoadingFragment;
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
public final class ChargeFragment_MembersInjector implements MembersInjector<ChargeFragment> {
  private final Provider<Context> appContextProvider;

  private final Provider<LoadingFragment> loadingProvider;

  public ChargeFragment_MembersInjector(Provider<Context> appContextProvider,
      Provider<LoadingFragment> loadingProvider) {
    this.appContextProvider = appContextProvider;
    this.loadingProvider = loadingProvider;
  }

  public static MembersInjector<ChargeFragment> create(Provider<Context> appContextProvider,
      Provider<LoadingFragment> loadingProvider) {
    return new ChargeFragment_MembersInjector(appContextProvider, loadingProvider);
  }

  @Override
  public void injectMembers(ChargeFragment instance) {
    injectAppContext(instance, appContextProvider.get());
    injectLoading(instance, loadingProvider.get());
  }

  @InjectedFieldSignature("com.masa.aryan.charge.ChargeFragment.appContext")
  public static void injectAppContext(ChargeFragment instance, Context appContext) {
    instance.appContext = appContext;
  }

  @InjectedFieldSignature("com.masa.aryan.charge.ChargeFragment.loading")
  public static void injectLoading(ChargeFragment instance, LoadingFragment loading) {
    instance.loading = loading;
  }
}
