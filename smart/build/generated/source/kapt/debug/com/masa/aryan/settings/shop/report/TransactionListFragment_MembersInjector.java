// Generated by Dagger (https://dagger.dev).
package com.masa.aryan.settings.shop.report;

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
public final class TransactionListFragment_MembersInjector implements MembersInjector<TransactionListFragment> {
  private final Provider<LoadingFragment> loadingProvider;

  private final Provider<Context> appContextProvider;

  public TransactionListFragment_MembersInjector(Provider<LoadingFragment> loadingProvider,
      Provider<Context> appContextProvider) {
    this.loadingProvider = loadingProvider;
    this.appContextProvider = appContextProvider;
  }

  public static MembersInjector<TransactionListFragment> create(
      Provider<LoadingFragment> loadingProvider, Provider<Context> appContextProvider) {
    return new TransactionListFragment_MembersInjector(loadingProvider, appContextProvider);
  }

  @Override
  public void injectMembers(TransactionListFragment instance) {
    injectLoading(instance, loadingProvider.get());
    injectAppContext(instance, appContextProvider.get());
  }

  @InjectedFieldSignature("com.masa.aryan.settings.shop.report.TransactionListFragment.loading")
  public static void injectLoading(TransactionListFragment instance, LoadingFragment loading) {
    instance.loading = loading;
  }

  @InjectedFieldSignature("com.masa.aryan.settings.shop.report.TransactionListFragment.appContext")
  public static void injectAppContext(TransactionListFragment instance, Context appContext) {
    instance.appContext = appContext;
  }
}
