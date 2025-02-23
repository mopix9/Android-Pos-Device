package com.fanap.corepos.utils.sina

object SinaResponse {

    fun getResponse(input: String) = when (input) {
        "00" -> "موفق"
        "01" -> " صادرکننده ی کارت از انجام تراکنش صرف نظر کرد."
        "03" -> "پذیرندهی فروشگاهی نامعتبر میباشد."
        "04" -> "کارت توسط دستگاه ضبط شود."
        "05" -> "از انجام تراکنش صرف نظر شد."
        "07" -> "به دلیل شرایط خاص کارت توسط دستگاه ضبط شود."
        "08" -> "باتشخیص هویت دارنده ی کارت، تراکنش موفق میباشد."
        "12" -> " تراکنش نامعتبر است"
        "14" -> "شماره کارت ارسالی نامعتبر است. (وجود ندارد)"
        "15" -> "صادرکننده ی کارت نامعتبراست. (وجود ندارد)"
        "16" -> "تراکنش مورد تایید است و اطلاعات شیار سوم کارت به روز رسانی شود"
        "19" -> " تراکنش مجدداً ارسال شود."
        "23" -> "کارمزد ارسالی پذیرنده غیر قابل قبول است."
        "30" -> "قالب پیام دارای اشکال است."
        "31" -> "پذیرنده توسط سوئیچ پشتیبانی نمی شود."
        "33" -> "تاریخ انقضای کارت سپری شده است. کارت توسط دستگاه ضبط شود."
        "34" -> "کارت مظنون به تقلب است. کارت توسط دستگاه ضبط شود."
        "36" -> "کارت محدود شده است. کارت توسط دستگاه ضبط شود."
        "38" -> "تعداد دفعات ورود رمزغلط بیشاز حدمجاز است. کارت توسط دستگاه ضبط شود"
        "39" -> "کارت حساب اعتباری ندارد."
        "40" -> "عملیات درخواستی پشتیبانی نمی گردد."
        "41" -> "کارت مفقودی می باشد. کارت توسط دستگاه ضبط شود."
        "42" -> "کارت حساب عمومی ندارد."
        "43" -> "کارت مسروقه می باشد. کارت توسط دستگاه ضبط شود."
        "44" -> "کارت حساب سرمایه گذاری ندارد."
        "51" -> "موجودی کافی نمی باشد."
        "52" -> "کارت حساب جاری ندارد."
        "53" -> "کارت حساب قرضالحسنه ندارد."
        "54" -> "تاریخ انقضای کارت سپری شده است"
        "55" -> " رمز کارت نا معتبر است."
        "56" -> "کارت نا معتبر است."
        "57" -> "انجام تراکنش مربوطه توسط دارنده ی کارت مجاز نمی باشد."
        "58" -> " انجام تراکنش مربوطه توسط پایانه ی انجام دهنده مجاز نمی باشد."
        "59" -> "کارت مظنون به تقلب است."
        "61" -> "مبلغ تراکنش بیشاز حد مجاز می باشد."
        "62" -> "کارت محدود شده است."
        "63" -> "تمهیدات امنیتی نقض گردیده است."
        "65" -> "تعداد درخواست تراکنش بیشاز حد مجاز می باشد."
        "67" -> "کارت توسط دستگاه ضبط شود."
        "75" -> "تعداد دفعات ورود رمزغلط بیشاز حدمجاز است."
        "90" -> "سامانه مقصد تراکنش درحال انجام عملیات پایان روز می باشد"

        else -> "نامشخص"
    }


}