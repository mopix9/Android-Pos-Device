package com.masa.aryan;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.masa.aryan.databinding.ActivityMainBindingImpl;
import com.masa.aryan.databinding.FragmentBalanceBindingImpl;
import com.masa.aryan.databinding.FragmentBalanceSucessBindingImpl;
import com.masa.aryan.databinding.FragmentBillBindingImpl;
import com.masa.aryan.databinding.FragmentBillDetailBindingImpl;
import com.masa.aryan.databinding.FragmentBillSuccessBindingImpl;
import com.masa.aryan.databinding.FragmentBuyBindingImpl;
import com.masa.aryan.databinding.FragmentBuyOptionsBindingImpl;
import com.masa.aryan.databinding.FragmentBuySuccessBindingImpl;
import com.masa.aryan.databinding.FragmentBuyerBindingImpl;
import com.masa.aryan.databinding.FragmentChargeAmountBindingImpl;
import com.masa.aryan.databinding.FragmentChargeBindingImpl;
import com.masa.aryan.databinding.FragmentChargeSuccessBindingImpl;
import com.masa.aryan.databinding.FragmentConfigurationBindingImpl;
import com.masa.aryan.databinding.FragmentConnectivityBindingImpl;
import com.masa.aryan.databinding.FragmentFailBindingImpl;
import com.masa.aryan.databinding.FragmentFirstReciptAndSecondReciptBindingImpl;
import com.masa.aryan.databinding.FragmentGetInfoBindingImpl;
import com.masa.aryan.databinding.FragmentLogonBindingImpl;
import com.masa.aryan.databinding.FragmentManagementBindingImpl;
import com.masa.aryan.databinding.FragmentMerchantPasswordBindingImpl;
import com.masa.aryan.databinding.FragmentPasswordBindingImpl;
import com.masa.aryan.databinding.FragmentPrintReceiptBindingImpl;
import com.masa.aryan.databinding.FragmentReciptPrintSettingBindingImpl;
import com.masa.aryan.databinding.FragmentReportsBindingImpl;
import com.masa.aryan.databinding.FragmentSearchReceiptBindingImpl;
import com.masa.aryan.databinding.FragmentSearchTransactionBindingImpl;
import com.masa.aryan.databinding.FragmentSelectReciptTypeToPrintBindingImpl;
import com.masa.aryan.databinding.FragmentSellerRollRequestBindingImpl;
import com.masa.aryan.databinding.FragmentServerBindingImpl;
import com.masa.aryan.databinding.FragmentSettingsBindingImpl;
import com.masa.aryan.databinding.FragmentShopBindingImpl;
import com.masa.aryan.databinding.FragmentShowReceiptBindingImpl;
import com.masa.aryan.databinding.FragmentSwipeBindingImpl;
import com.masa.aryan.databinding.FragmentTerminalBindingImpl;
import com.masa.aryan.databinding.FragmentTotalReportBindingImpl;
import com.masa.aryan.databinding.FragmentTransactionDetailsBindingImpl;
import com.masa.aryan.databinding.FragmentTransactionListBindingImpl;
import com.masa.aryan.databinding.TransactionItemBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ACTIVITYMAIN = 1;

  private static final int LAYOUT_FRAGMENTBALANCE = 2;

  private static final int LAYOUT_FRAGMENTBALANCESUCESS = 3;

  private static final int LAYOUT_FRAGMENTBILL = 4;

  private static final int LAYOUT_FRAGMENTBILLDETAIL = 5;

  private static final int LAYOUT_FRAGMENTBILLSUCCESS = 6;

  private static final int LAYOUT_FRAGMENTBUY = 7;

  private static final int LAYOUT_FRAGMENTBUYOPTIONS = 8;

  private static final int LAYOUT_FRAGMENTBUYSUCCESS = 9;

  private static final int LAYOUT_FRAGMENTBUYER = 10;

  private static final int LAYOUT_FRAGMENTCHARGE = 11;

  private static final int LAYOUT_FRAGMENTCHARGEAMOUNT = 12;

  private static final int LAYOUT_FRAGMENTCHARGESUCCESS = 13;

  private static final int LAYOUT_FRAGMENTCONFIGURATION = 14;

  private static final int LAYOUT_FRAGMENTCONNECTIVITY = 15;

  private static final int LAYOUT_FRAGMENTFAIL = 16;

  private static final int LAYOUT_FRAGMENTFIRSTRECIPTANDSECONDRECIPT = 17;

  private static final int LAYOUT_FRAGMENTGETINFO = 18;

  private static final int LAYOUT_FRAGMENTLOGON = 19;

  private static final int LAYOUT_FRAGMENTMANAGEMENT = 20;

  private static final int LAYOUT_FRAGMENTMERCHANTPASSWORD = 21;

  private static final int LAYOUT_FRAGMENTPASSWORD = 22;

  private static final int LAYOUT_FRAGMENTPRINTRECEIPT = 23;

  private static final int LAYOUT_FRAGMENTRECIPTPRINTSETTING = 24;

  private static final int LAYOUT_FRAGMENTREPORTS = 25;

  private static final int LAYOUT_FRAGMENTSEARCHRECEIPT = 26;

  private static final int LAYOUT_FRAGMENTSEARCHTRANSACTION = 27;

  private static final int LAYOUT_FRAGMENTSELECTRECIPTTYPETOPRINT = 28;

  private static final int LAYOUT_FRAGMENTSELLERROLLREQUEST = 29;

  private static final int LAYOUT_FRAGMENTSERVER = 30;

  private static final int LAYOUT_FRAGMENTSETTINGS = 31;

  private static final int LAYOUT_FRAGMENTSHOP = 32;

  private static final int LAYOUT_FRAGMENTSHOWRECEIPT = 33;

  private static final int LAYOUT_FRAGMENTSWIPE = 34;

  private static final int LAYOUT_FRAGMENTTERMINAL = 35;

  private static final int LAYOUT_FRAGMENTTOTALREPORT = 36;

  private static final int LAYOUT_FRAGMENTTRANSACTIONDETAILS = 37;

  private static final int LAYOUT_FRAGMENTTRANSACTIONLIST = 38;

  private static final int LAYOUT_TRANSACTIONITEM = 39;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(39);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.masa.aryan.R.layout.activity_main, LAYOUT_ACTIVITYMAIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.masa.aryan.R.layout.fragment_balance, LAYOUT_FRAGMENTBALANCE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.masa.aryan.R.layout.fragment_balance_sucess, LAYOUT_FRAGMENTBALANCESUCESS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.masa.aryan.R.layout.fragment_bill, LAYOUT_FRAGMENTBILL);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.masa.aryan.R.layout.fragment_bill_detail, LAYOUT_FRAGMENTBILLDETAIL);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.masa.aryan.R.layout.fragment_bill_success, LAYOUT_FRAGMENTBILLSUCCESS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.masa.aryan.R.layout.fragment_buy, LAYOUT_FRAGMENTBUY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.masa.aryan.R.layout.fragment_buy_options, LAYOUT_FRAGMENTBUYOPTIONS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.masa.aryan.R.layout.fragment_buy_success, LAYOUT_FRAGMENTBUYSUCCESS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.masa.aryan.R.layout.fragment_buyer, LAYOUT_FRAGMENTBUYER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.masa.aryan.R.layout.fragment_charge, LAYOUT_FRAGMENTCHARGE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.masa.aryan.R.layout.fragment_charge_amount, LAYOUT_FRAGMENTCHARGEAMOUNT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.masa.aryan.R.layout.fragment_charge_success, LAYOUT_FRAGMENTCHARGESUCCESS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.masa.aryan.R.layout.fragment_configuration, LAYOUT_FRAGMENTCONFIGURATION);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.masa.aryan.R.layout.fragment_connectivity, LAYOUT_FRAGMENTCONNECTIVITY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.masa.aryan.R.layout.fragment_fail, LAYOUT_FRAGMENTFAIL);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.masa.aryan.R.layout.fragment_first_recipt_and_second_recipt, LAYOUT_FRAGMENTFIRSTRECIPTANDSECONDRECIPT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.masa.aryan.R.layout.fragment_get_info, LAYOUT_FRAGMENTGETINFO);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.masa.aryan.R.layout.fragment_logon, LAYOUT_FRAGMENTLOGON);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.masa.aryan.R.layout.fragment_management, LAYOUT_FRAGMENTMANAGEMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.masa.aryan.R.layout.fragment_merchant_password, LAYOUT_FRAGMENTMERCHANTPASSWORD);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.masa.aryan.R.layout.fragment_password, LAYOUT_FRAGMENTPASSWORD);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.masa.aryan.R.layout.fragment_print_receipt, LAYOUT_FRAGMENTPRINTRECEIPT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.masa.aryan.R.layout.fragment_recipt_print_setting, LAYOUT_FRAGMENTRECIPTPRINTSETTING);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.masa.aryan.R.layout.fragment_reports, LAYOUT_FRAGMENTREPORTS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.masa.aryan.R.layout.fragment_search_receipt, LAYOUT_FRAGMENTSEARCHRECEIPT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.masa.aryan.R.layout.fragment_search_transaction, LAYOUT_FRAGMENTSEARCHTRANSACTION);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.masa.aryan.R.layout.fragment_select_recipt_type_to_print, LAYOUT_FRAGMENTSELECTRECIPTTYPETOPRINT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.masa.aryan.R.layout.fragment_seller_roll_request, LAYOUT_FRAGMENTSELLERROLLREQUEST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.masa.aryan.R.layout.fragment_server, LAYOUT_FRAGMENTSERVER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.masa.aryan.R.layout.fragment_settings, LAYOUT_FRAGMENTSETTINGS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.masa.aryan.R.layout.fragment_shop, LAYOUT_FRAGMENTSHOP);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.masa.aryan.R.layout.fragment_show_receipt, LAYOUT_FRAGMENTSHOWRECEIPT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.masa.aryan.R.layout.fragment_swipe, LAYOUT_FRAGMENTSWIPE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.masa.aryan.R.layout.fragment_terminal, LAYOUT_FRAGMENTTERMINAL);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.masa.aryan.R.layout.fragment_total_report, LAYOUT_FRAGMENTTOTALREPORT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.masa.aryan.R.layout.fragment_transaction_details, LAYOUT_FRAGMENTTRANSACTIONDETAILS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.masa.aryan.R.layout.fragment_transaction_list, LAYOUT_FRAGMENTTRANSACTIONLIST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.masa.aryan.R.layout.transaction_item, LAYOUT_TRANSACTIONITEM);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_ACTIVITYMAIN: {
          if ("layout/activity_main_0".equals(tag)) {
            return new ActivityMainBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_main is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTBALANCE: {
          if ("layout/fragment_balance_0".equals(tag)) {
            return new FragmentBalanceBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_balance is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTBALANCESUCESS: {
          if ("layout/fragment_balance_sucess_0".equals(tag)) {
            return new FragmentBalanceSucessBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_balance_sucess is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTBILL: {
          if ("layout/fragment_bill_0".equals(tag)) {
            return new FragmentBillBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_bill is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTBILLDETAIL: {
          if ("layout/fragment_bill_detail_0".equals(tag)) {
            return new FragmentBillDetailBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_bill_detail is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTBILLSUCCESS: {
          if ("layout/fragment_bill_success_0".equals(tag)) {
            return new FragmentBillSuccessBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_bill_success is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTBUY: {
          if ("layout/fragment_buy_0".equals(tag)) {
            return new FragmentBuyBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_buy is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTBUYOPTIONS: {
          if ("layout/fragment_buy_options_0".equals(tag)) {
            return new FragmentBuyOptionsBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_buy_options is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTBUYSUCCESS: {
          if ("layout/fragment_buy_success_0".equals(tag)) {
            return new FragmentBuySuccessBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_buy_success is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTBUYER: {
          if ("layout/fragment_buyer_0".equals(tag)) {
            return new FragmentBuyerBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_buyer is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTCHARGE: {
          if ("layout/fragment_charge_0".equals(tag)) {
            return new FragmentChargeBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_charge is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTCHARGEAMOUNT: {
          if ("layout/fragment_charge_amount_0".equals(tag)) {
            return new FragmentChargeAmountBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_charge_amount is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTCHARGESUCCESS: {
          if ("layout/fragment_charge_success_0".equals(tag)) {
            return new FragmentChargeSuccessBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_charge_success is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTCONFIGURATION: {
          if ("layout/fragment_configuration_0".equals(tag)) {
            return new FragmentConfigurationBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_configuration is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTCONNECTIVITY: {
          if ("layout/fragment_connectivity_0".equals(tag)) {
            return new FragmentConnectivityBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_connectivity is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTFAIL: {
          if ("layout/fragment_fail_0".equals(tag)) {
            return new FragmentFailBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_fail is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTFIRSTRECIPTANDSECONDRECIPT: {
          if ("layout/fragment_first_recipt_and_second_recipt_0".equals(tag)) {
            return new FragmentFirstReciptAndSecondReciptBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_first_recipt_and_second_recipt is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTGETINFO: {
          if ("layout/fragment_get_info_0".equals(tag)) {
            return new FragmentGetInfoBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_get_info is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTLOGON: {
          if ("layout/fragment_logon_0".equals(tag)) {
            return new FragmentLogonBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_logon is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTMANAGEMENT: {
          if ("layout/fragment_management_0".equals(tag)) {
            return new FragmentManagementBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_management is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTMERCHANTPASSWORD: {
          if ("layout/fragment_merchant_password_0".equals(tag)) {
            return new FragmentMerchantPasswordBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_merchant_password is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTPASSWORD: {
          if ("layout/fragment_password_0".equals(tag)) {
            return new FragmentPasswordBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_password is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTPRINTRECEIPT: {
          if ("layout/fragment_print_receipt_0".equals(tag)) {
            return new FragmentPrintReceiptBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_print_receipt is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTRECIPTPRINTSETTING: {
          if ("layout/fragment_recipt_print_setting_0".equals(tag)) {
            return new FragmentReciptPrintSettingBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_recipt_print_setting is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTREPORTS: {
          if ("layout/fragment_reports_0".equals(tag)) {
            return new FragmentReportsBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_reports is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTSEARCHRECEIPT: {
          if ("layout/fragment_search_receipt_0".equals(tag)) {
            return new FragmentSearchReceiptBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_search_receipt is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTSEARCHTRANSACTION: {
          if ("layout/fragment_search_transaction_0".equals(tag)) {
            return new FragmentSearchTransactionBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_search_transaction is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTSELECTRECIPTTYPETOPRINT: {
          if ("layout/fragment_select_recipt_type_to_print_0".equals(tag)) {
            return new FragmentSelectReciptTypeToPrintBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_select_recipt_type_to_print is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTSELLERROLLREQUEST: {
          if ("layout/fragment_seller_roll_request_0".equals(tag)) {
            return new FragmentSellerRollRequestBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_seller_roll_request is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTSERVER: {
          if ("layout/fragment_server_0".equals(tag)) {
            return new FragmentServerBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_server is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTSETTINGS: {
          if ("layout/fragment_settings_0".equals(tag)) {
            return new FragmentSettingsBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_settings is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTSHOP: {
          if ("layout/fragment_shop_0".equals(tag)) {
            return new FragmentShopBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_shop is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTSHOWRECEIPT: {
          if ("layout/fragment_show_receipt_0".equals(tag)) {
            return new FragmentShowReceiptBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_show_receipt is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTSWIPE: {
          if ("layout/fragment_swipe_0".equals(tag)) {
            return new FragmentSwipeBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_swipe is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTTERMINAL: {
          if ("layout/fragment_terminal_0".equals(tag)) {
            return new FragmentTerminalBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_terminal is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTTOTALREPORT: {
          if ("layout/fragment_total_report_0".equals(tag)) {
            return new FragmentTotalReportBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_total_report is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTTRANSACTIONDETAILS: {
          if ("layout/fragment_transaction_details_0".equals(tag)) {
            return new FragmentTransactionDetailsBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_transaction_details is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTTRANSACTIONLIST: {
          if ("layout/fragment_transaction_list_0".equals(tag)) {
            return new FragmentTransactionListBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_transaction_list is invalid. Received: " + tag);
        }
        case  LAYOUT_TRANSACTIONITEM: {
          if ("layout/transaction_item_0".equals(tag)) {
            return new TransactionItemBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for transaction_item is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(2);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    result.add(new com.fanap.corepos.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(2);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "viewModel");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(39);

    static {
      sKeys.put("layout/activity_main_0", com.masa.aryan.R.layout.activity_main);
      sKeys.put("layout/fragment_balance_0", com.masa.aryan.R.layout.fragment_balance);
      sKeys.put("layout/fragment_balance_sucess_0", com.masa.aryan.R.layout.fragment_balance_sucess);
      sKeys.put("layout/fragment_bill_0", com.masa.aryan.R.layout.fragment_bill);
      sKeys.put("layout/fragment_bill_detail_0", com.masa.aryan.R.layout.fragment_bill_detail);
      sKeys.put("layout/fragment_bill_success_0", com.masa.aryan.R.layout.fragment_bill_success);
      sKeys.put("layout/fragment_buy_0", com.masa.aryan.R.layout.fragment_buy);
      sKeys.put("layout/fragment_buy_options_0", com.masa.aryan.R.layout.fragment_buy_options);
      sKeys.put("layout/fragment_buy_success_0", com.masa.aryan.R.layout.fragment_buy_success);
      sKeys.put("layout/fragment_buyer_0", com.masa.aryan.R.layout.fragment_buyer);
      sKeys.put("layout/fragment_charge_0", com.masa.aryan.R.layout.fragment_charge);
      sKeys.put("layout/fragment_charge_amount_0", com.masa.aryan.R.layout.fragment_charge_amount);
      sKeys.put("layout/fragment_charge_success_0", com.masa.aryan.R.layout.fragment_charge_success);
      sKeys.put("layout/fragment_configuration_0", com.masa.aryan.R.layout.fragment_configuration);
      sKeys.put("layout/fragment_connectivity_0", com.masa.aryan.R.layout.fragment_connectivity);
      sKeys.put("layout/fragment_fail_0", com.masa.aryan.R.layout.fragment_fail);
      sKeys.put("layout/fragment_first_recipt_and_second_recipt_0", com.masa.aryan.R.layout.fragment_first_recipt_and_second_recipt);
      sKeys.put("layout/fragment_get_info_0", com.masa.aryan.R.layout.fragment_get_info);
      sKeys.put("layout/fragment_logon_0", com.masa.aryan.R.layout.fragment_logon);
      sKeys.put("layout/fragment_management_0", com.masa.aryan.R.layout.fragment_management);
      sKeys.put("layout/fragment_merchant_password_0", com.masa.aryan.R.layout.fragment_merchant_password);
      sKeys.put("layout/fragment_password_0", com.masa.aryan.R.layout.fragment_password);
      sKeys.put("layout/fragment_print_receipt_0", com.masa.aryan.R.layout.fragment_print_receipt);
      sKeys.put("layout/fragment_recipt_print_setting_0", com.masa.aryan.R.layout.fragment_recipt_print_setting);
      sKeys.put("layout/fragment_reports_0", com.masa.aryan.R.layout.fragment_reports);
      sKeys.put("layout/fragment_search_receipt_0", com.masa.aryan.R.layout.fragment_search_receipt);
      sKeys.put("layout/fragment_search_transaction_0", com.masa.aryan.R.layout.fragment_search_transaction);
      sKeys.put("layout/fragment_select_recipt_type_to_print_0", com.masa.aryan.R.layout.fragment_select_recipt_type_to_print);
      sKeys.put("layout/fragment_seller_roll_request_0", com.masa.aryan.R.layout.fragment_seller_roll_request);
      sKeys.put("layout/fragment_server_0", com.masa.aryan.R.layout.fragment_server);
      sKeys.put("layout/fragment_settings_0", com.masa.aryan.R.layout.fragment_settings);
      sKeys.put("layout/fragment_shop_0", com.masa.aryan.R.layout.fragment_shop);
      sKeys.put("layout/fragment_show_receipt_0", com.masa.aryan.R.layout.fragment_show_receipt);
      sKeys.put("layout/fragment_swipe_0", com.masa.aryan.R.layout.fragment_swipe);
      sKeys.put("layout/fragment_terminal_0", com.masa.aryan.R.layout.fragment_terminal);
      sKeys.put("layout/fragment_total_report_0", com.masa.aryan.R.layout.fragment_total_report);
      sKeys.put("layout/fragment_transaction_details_0", com.masa.aryan.R.layout.fragment_transaction_details);
      sKeys.put("layout/fragment_transaction_list_0", com.masa.aryan.R.layout.fragment_transaction_list);
      sKeys.put("layout/transaction_item_0", com.masa.aryan.R.layout.transaction_item);
    }
  }
}
