// Generated by view binder compiler. Do not edit!
package com.masa.aryan.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.masa.aryan.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public final class FragmentScannerBinding implements ViewBinding {
  @NonNull
  private final FrameLayout rootView;

  @NonNull
  public final ImageView close;

  @NonNull
  public final ZXingScannerView scanner;

  private FragmentScannerBinding(@NonNull FrameLayout rootView, @NonNull ImageView close,
      @NonNull ZXingScannerView scanner) {
    this.rootView = rootView;
    this.close = close;
    this.scanner = scanner;
  }

  @Override
  @NonNull
  public FrameLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentScannerBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentScannerBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_scanner, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentScannerBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.close;
      ImageView close = ViewBindings.findChildViewById(rootView, id);
      if (close == null) {
        break missingId;
      }

      id = R.id.scanner;
      ZXingScannerView scanner = ViewBindings.findChildViewById(rootView, id);
      if (scanner == null) {
        break missingId;
      }

      return new FragmentScannerBinding((FrameLayout) rootView, close, scanner);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
