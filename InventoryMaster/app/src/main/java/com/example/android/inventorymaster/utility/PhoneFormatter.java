package com.example.android.inventorymaster.utility;

import android.os.Build;
import android.telephony.PhoneNumberUtils;

import java.util.Locale;

public class PhoneFormatter {

    //used to format the supplier phone number
    public static String formatPhone(String phone) {
        String formattedNumber = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            formattedNumber = PhoneNumberUtils.formatNumber(phone, Locale.getDefault().getCountry());
            return formattedNumber;
        } else {
            formattedNumber = PhoneNumberUtils.formatNumber(phone);
            return formattedNumber;
        }
    }
}
