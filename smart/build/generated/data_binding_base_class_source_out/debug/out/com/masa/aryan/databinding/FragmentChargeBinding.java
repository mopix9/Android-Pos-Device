// Generated by data binding compiler. Do not edit!
package com.masa.aryan.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.masa.aryan.R;
import com.masa.aryan.charge.ChargeViewModel;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class FragmentChargeBinding extends ViewDataBinding {
  @NonNull
  public final FrameLayout amountCode;

  @NonNull
  public final FrameLayout amountTopup;

  @NonNull
  public final ImageButton back;

  @NonNull
  public final Button cancel;

  @NonNull
  public final Button confirm;

  @NonNull
  public final EditText edtPhone;

  @NonNull
  public final FrameLayout main;

  @NonNull
  public final TextView subtitle;

  @NonNull
  public final TextView title;

  @NonNull
  public final LinearLayout top;

  @NonNull
  public final TextView txtCodeAmount;

  @NonNull
  public final TextView txtTopupAmount;

  @Bindable
  protected ChargeViewModel mViewModel;

  protected FragmentChargeBinding(Object _bindingComponent, View _root, int _localFieldCount,
      FrameLayout amountCode, FrameLayout amountTopup, ImageButton back, Button cancel,
      Button confirm, EditText edtPhone, FrameLayout main, TextView subtitle, TextView title,
      LinearLayout top, TextView txtCodeAmount, TextView txtTopupAmount) {
    super(_bindingComponent, _root, _localFieldCount);
    this.amountCode = amountCode;
    this.amountTopup = amountTopup;
    this.back = back;
    this.cancel = cancel;
    this.confirm = confirm;
    this.edtPhone = edtPhone;
    this.main = main;
    this.subtitle = subtitle;
    this.title = title;
    this.top = top;
    this.txtCodeAmount = txtCodeAmount;
    this.txtTopupAmount = txtTopupAmount;
  }

  public abstract void setViewModel(@Nullable ChargeViewModel viewModel);

  @Nullable
  public ChargeViewModel getViewModel() {
    return mViewModel;
  }

  @NonNull
  public static FragmentChargeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_charge, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static FragmentChargeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<FragmentChargeBinding>inflateInternal(inflater, R.layout.fragment_charge, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentChargeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_charge, null, false, component)
   */
  @NonNull
  @Deprecated
  public static FragmentChargeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<FragmentChargeBinding>inflateInternal(inflater, R.layout.fragment_charge, null, false, component);
  }

  public static FragmentChargeBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.bind(view, component)
   */
  @Deprecated
  public static FragmentChargeBinding bind(@NonNull View view, @Nullable Object component) {
    return (FragmentChargeBinding)bind(component, view, R.layout.fragment_charge);
  }
}
