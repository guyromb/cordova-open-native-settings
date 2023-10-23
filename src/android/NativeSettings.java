/*
 * PhoneGap is available under *either* the terms of the modified BSD license *or* the
 * MIT License (2008). See http://opensource.org/licenses/alphabetical for full text.
 *
 * Copyright (c) 2005-2010, Nitobi Software Inc.
 * Copyright (c) 2011, IBM Corporation
 */

package com.phonegap.plugins.nativesettings;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Intent;
import android.content.Context;
import android.net.Uri;

import android.provider.Settings;

import android.os.Build;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;

public class NativeSettings extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
	Context context = this.cordova.getActivity().getApplicationContext();
        PluginResult.Status status = PluginResult.Status.OK;
        Uri packageUri = Uri.parse("package:" + this.cordova.getActivity().getPackageName());
        String result = "";

        //Information on settings can be found here:
        //http://developer.android.com/reference/android/provider/Settings.html
		
	action = args.getString(0);
	Intent intent = null;

        if (action.equals("accessibility")) {
            intent = new Intent(android.provider.Settings.ACTION_ACCESSIBILITY_SETTINGS);
        } else if (action.equals("account")) {
            intent = new Intent(android.provider.Settings.ACTION_ADD_ACCOUNT);
        } else if (action.equals("airplane_mode")) {
            intent = new Intent(android.provider.Settings.ACTION_AIRPLANE_MODE_SETTINGS);
        } else if (action.equals("apn")) {
            intent = new Intent(android.provider.Settings.ACTION_APN_SETTINGS);
        } else if (action.equals("application_details")) {
            intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageUri);
        } else if (action.equals("application_development")) {
            intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS);
        } else if (action.equals("application")) {
            intent = new Intent(android.provider.Settings.ACTION_APPLICATION_SETTINGS);
        }
        //else if (action.equals("battery_saver")) {
        //    intent = new Intent(android.provider.Settings.ACTION_BATTERY_SAVER_SETTINGS);
        //}
        else if (action.equals("battery_optimization")) {
            intent = new Intent(android.provider.Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS);
        } else if (action.equals("biometric")) {
	    if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
                intent = new Intent(android.provider.Settings.ACTION_BIOMETRIC_ENROLL);
            } else if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                intent = new Intent(android.provider.Settings.ACTION_FINGERPRINT_ENROLL);
            } else {
                // Atleast open the settings landing page
                intent = new Intent(android.provider.Settings.ACTION_SETTINGS);
            }
        } else if (action.equals("bluetooth")) {
            intent = new Intent(android.provider.Settings.ACTION_BLUETOOTH_SETTINGS);
        } else if (action.equals("captioning")) {
            intent = new Intent(android.provider.Settings.ACTION_CAPTIONING_SETTINGS);
        } else if (action.equals("cast")) {
            intent = new Intent(android.provider.Settings.ACTION_CAST_SETTINGS);
        } else if (action.equals("data_roaming")) {
            intent = new Intent(android.provider.Settings.ACTION_DATA_ROAMING_SETTINGS);
        } else if (action.equals("date")) {
            intent = new Intent(android.provider.Settings.ACTION_DATE_SETTINGS);
        } else if (action.equals("about")) {
            intent = new Intent(android.provider.Settings.ACTION_DEVICE_INFO_SETTINGS);
        } else if (action.equals("display")) {
            intent = new Intent(android.provider.Settings.ACTION_DISPLAY_SETTINGS);
        } else if (action.equals("dream")) {
            intent = new Intent(android.provider.Settings.ACTION_DREAM_SETTINGS);
        } else if (action.equals("home")) {
            intent = new Intent(android.provider.Settings.ACTION_HOME_SETTINGS);
        } else if (action.equals("keyboard")) {
            intent = new Intent(android.provider.Settings.ACTION_INPUT_METHOD_SETTINGS);
        } else if (action.equals("keyboard_subtype")) {
            intent = new Intent(android.provider.Settings.ACTION_INPUT_METHOD_SUBTYPE_SETTINGS);
        } else if (action.equals("storage")) {
            intent = new Intent(android.provider.Settings.ACTION_INTERNAL_STORAGE_SETTINGS);
        } else if (action.equals("locale")) {
            intent = new Intent(android.provider.Settings.ACTION_LOCALE_SETTINGS);
        } else if (action.equals("location")) {
            intent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        } else if (action.equals("manage_all_applications")) {
            intent = new Intent(android.provider.Settings.ACTION_MANAGE_ALL_APPLICATIONS_SETTINGS);
        } else if (action.equals("manage_applications")) {
            intent = new Intent(android.provider.Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS);
        } else if (action.equals("memory_card")) {
            intent = new Intent(android.provider.Settings.ACTION_MEMORY_CARD_SETTINGS);
        } else if (action.equals("network")) {
            intent = new Intent(android.provider.Settings.ACTION_NETWORK_OPERATOR_SETTINGS);
        } else if (action.equals("nfcsharing")) {
            intent = new Intent(android.provider.Settings.ACTION_NFCSHARING_SETTINGS);
        } else if (action.equals("nfc_payment")) {
            intent = new Intent(android.provider.Settings.ACTION_NFC_PAYMENT_SETTINGS);
        } else if (action.equals("nfc_settings")) {
            intent = new Intent(android.provider.Settings.ACTION_NFC_SETTINGS);
        } else if (action.equals("notification_id")) {
			// from: https://stackoverflow.com/questions/32366649/any-way-to-link-to-the-android-notification-settings-for-my-app
			intent = new Intent();
			if(android.os.Build.VERSION.SDK_INT > Build.VERSION_CODES.N_MR1){
				intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
				intent.putExtra("android.provider.extra.APP_PACKAGE", context.getPackageName());
			}else if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
				intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
				intent.putExtra("app_package", context.getPackageName());
				intent.putExtra("app_uid", context.getApplicationInfo().uid);
			}else {
				intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
				intent.addCategory(Intent.CATEGORY_DEFAULT);
				intent.setData(Uri.parse("package:" + context.getPackageName()));
			}
		}
        //else if (action.equals("notification_listner")) {
        //    intent = new Intent(android.provider.Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS);
        //}
        else if (action.equals("print")) {
            intent = new Intent(android.provider.Settings.ACTION_PRINT_SETTINGS);
        } else if (action.equals("privacy")) {
            intent = new Intent(android.provider.Settings.ACTION_PRIVACY_SETTINGS);
        } else if (action.equals("quick_launch")) {
            intent = new Intent(android.provider.Settings.ACTION_QUICK_LAUNCH_SETTINGS);
        } else if (action.equals("search")) {
            intent = new Intent(android.provider.Settings.ACTION_SEARCH_SETTINGS);
        } else if (action.equals("security")) {
            intent = new Intent(android.provider.Settings.ACTION_SECURITY_SETTINGS);
        } else if (action.equals("settings")) {
            intent = new Intent(android.provider.Settings.ACTION_SETTINGS);
        } else if (action.equals("show_regulatory_info")) {
            intent = new Intent(android.provider.Settings.ACTION_SHOW_REGULATORY_INFO);
        } else if (action.equals("sound")) {
            intent = new Intent(android.provider.Settings.ACTION_SOUND_SETTINGS);
        } else if (action.equals("store")) {
            intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=" + this.cordova.getActivity().getPackageName()));
        } else if (action.equals("sync")) {
            intent = new Intent(android.provider.Settings.ACTION_SYNC_SETTINGS);
        } else if (action.equals("usage")) {
            intent = new Intent(android.provider.Settings.ACTION_USAGE_ACCESS_SETTINGS);
        } else if (action.equals("user_dictionary")) {
            intent = new Intent(android.provider.Settings.ACTION_USER_DICTIONARY_SETTINGS);
        } else if (action.equals("voice_input")) {
            intent = new Intent(android.provider.Settings.ACTION_VOICE_INPUT_SETTINGS);
        } else if (action.equals("wifi_ip")) {
            intent = new Intent(android.provider.Settings.ACTION_WIFI_IP_SETTINGS);
        } else if (action.equals("wifi")) {
        	intent = new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS);
        } else if (action.equals("wireless")) {
            intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
        } else {
             status = PluginResult.Status.INVALID_ACTION;
             callbackContext.sendPluginResult(new PluginResult(status, result));
        	return false;
        }
        
        if(args.length() > 1 && args.getBoolean(1)) {
        	intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        this.cordova.getActivity().startActivity(intent);
        
        callbackContext.sendPluginResult(new PluginResult(status, result));
        return true;
    }
}

