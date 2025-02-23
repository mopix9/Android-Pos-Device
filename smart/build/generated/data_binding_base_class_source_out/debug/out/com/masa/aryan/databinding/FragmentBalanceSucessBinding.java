// Generated by data binding compiler. Do not edit!
package com.masa.aryan.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.masa.aryan.R;
import com.wang.avi.AVLoadingIndicatorView;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class FragmentBalanceSucessBinding extends ViewDataBinding {
  @NonNull
  public final AVLoadingIndicatorView avl;

  @NonNull
  public final ImageButton back;

  @NonNull
  public final LinearLayout bottom;

  @NonNull
  public final Button btnCancel;

  @NonNull
  public final Button btnPrint;

  @NonNull
  public final LinearLayout lnrDetails;

  @NonNull
  public final TextView subtitle;

  @NonNull
  public final TextView title;

  @NonNull
  public final TextView txtBalance;

  @NonNull
  public final TextView txtBankName;

  @NonNull
  public final TextView txtCard;

  @NonNull
  public final TextView txtDatetime;

  @NonNull
  public final TextView txtTimer;

  @NonNull
  public final TextView txtTitle;

  protected FragmentBalanceSucessBinding(Object _bindingComponent, View _root, int _localFieldCount,
      AVLoadingIndicatorView avl, ImageButton back, LinearLayout bottom, Button btnCancel,
      Button btnPrint, LinearLayout lnrDetails, TextView subtitle, TextView title,
      TextView txtBalance, TextView txtBankName, TextView txtCard, TextView txtDatetime,
      TextView txtTimer, TextView txtTitle) {
    super(_bindingComponent, _root, _localFieldCount);
    this.avl = avl;
    this.back = back;
    this.bottom = bottom;
    this.btnCancel = btnCancel;
    this.btnPrint = btnPrint;
    this.lnrDetails = lnrDetails;
    this.subtitle = subtitle;
    this.title = title;
    this.txtBalance = txtBalance;
    this.txtBankName = txtBankName;
    this.txtCard = txtCard;
    this.txtDatetime = txtDatetime;
    this.txtTimer = txtTimer;
    this.txtTitle = txtTitle;
  }

  @NonNull
  public static FragmentBalanceSucessBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_balance_sucess, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static FragmentBalanceSucessBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<FragmentBalanceSucessBinding>inflateInternal(inflater, R.layout.fragment_balance_sucess, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentBalanceSucessBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_balance_sucess, null, false, component)
   */
  @NonNull
  @Deprecated
  public static FragmentBalanceSucessBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<FragmentBalanceSucessBinding>inflateInternal(inflater, R.layout.fragment_balance_sucess, null, false, component);
  }

  public static FragmentBalanceSucessBinding bind(@NonNull View view) {
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
  public static FragmentBalanceSucessBinding bind(@NonNull View view, @Nullable Object component) {
    return (FragmentBalanceSucessBinding)bind(component, view, R.layout.fragment_balance_sucess);
  }
}
