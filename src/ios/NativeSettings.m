#import "NativeSettings.h"

@implementation NativeSettings

- (BOOL)do_open:(NSString *)pref {
    NSURL *url = [NSURL URLWithString:pref];
    if ([[UIApplication sharedApplication] canOpenURL:url]) {
        [[UIApplication sharedApplication] openURL:url options:@{} completionHandler:^(BOOL success) {
            if (!success) {
                NSLog(@"Failed to open URL: %@", pref);
            }
        }];
        return YES;
    } else {
        return NO;
    }
}

- (void)open:(CDVInvokedUrlCommand*)command
{
    CDVPluginResult* pluginResult = nil;
    NSString* key = [command.arguments objectAtIndex:0];
    NSString* prefix = @"App-Prefs:";
    BOOL result = NO;

    if(SYSTEM_VERSION_LESS_THAN(@"11.3")){
        prefix = @"app-settings:";
    }

    if ([key isEqualToString:@"application_details"]) {
        result = [self do_open:UIApplicationOpenSettingsURLString];
    }
    else if ([key isEqualToString:@"settings"]) {
        result = [self do_open:prefix];
    }
    else if ([key isEqualToString:@"about"]) {
        result = [self do_open:[prefix stringByAppendingString:@"General&path=About"]];
    }
    else if ([key isEqualToString:@"accessibility"]) {
        result = [self do_open:[prefix stringByAppendingString:@"General&path=ACCESSIBILITY"]];
    }
    else if ([key isEqualToString:@"account"]) {
        result = [self do_open:[prefix stringByAppendingString:@"ACCOUNT_SETTINGS"]];
    }
    else if ([key isEqualToString:@"autolock"]) {
        result = [self do_open:[prefix stringByAppendingString:@"DISPLAY&path=AUTOLOCK"]];
    }
    else if ([key isEqualToString:@"display"]) {
        result = [self do_open:[prefix stringByAppendingString:@"Brightness"]];
    }
    else if ([key isEqualToString:@"bluetooth"]) {
        result = [self do_open:[prefix stringByAppendingString:@"Bluetooth"]];
    }
    else if ([key isEqualToString:@"castle"]) {
        result = [self do_open:[prefix stringByAppendingString:@"CASTLE"]];
    }
    else if ([key isEqualToString:@"cellular_usage"]) {
        result = [self do_open:[prefix stringByAppendingString:@"General&path=USAGE/CELLULAR_USAGE"]];
    }
    else if ([key isEqualToString:@"configuration_list"]) {
        result = [self do_open:[prefix stringByAppendingString:@"General&path=ManagedConfigurationList"]];
    }
    else if ([key isEqualToString:@"date"]) {
        result = [self do_open:[prefix stringByAppendingString:@"General&path=DATE_AND_TIME"]];
    }
    else if ([key isEqualToString:@"facetime"]) {
        result = [self do_open:[prefix stringByAppendingString:@"FACETIME"]];
    }
    else if ([key isEqualToString:@"settings"]) {
        result = [self do_open:[prefix stringByAppendingString:@"General"]];
    }
    else if ([key isEqualToString:@"tethering"]) {
        result = [self do_open:[prefix stringByAppendingString:@"INTERNET_TETHERING"]];
    }
    else if ([key isEqualToString:@"music"]) {
        result = [self do_open:[prefix stringByAppendingString:@"MUSIC"]];
    }
    else if ([key isEqualToString:@"music_equalizer"]) {
        result = [self do_open:[prefix stringByAppendingString:@"MUSIC&path=EQ"]];
    }
    else if ([key isEqualToString:@"music_volume"]) {
        result = [self do_open:[prefix stringByAppendingString:@"MUSIC&path=VolumeLimit"]];
    }
    else if ([key isEqualToString:@"keyboard"]) {
        result = [self do_open:[prefix stringByAppendingString:@"General&path=Keyboard"]];
    }
    else if ([key isEqualToString:@"locale"]) {
        result = [self do_open:[prefix stringByAppendingString:@"General&path=INTERNATIONAL"]];
    }
    else if ([key isEqualToString:@"location"]) {
        result = [self do_open:[prefix stringByAppendingString:@"LOCATION_SERVICES"]];
    }
    else if ([key isEqualToString:@"locations"]) {
        result = [self do_open:[prefix stringByAppendingString:@"Privacy&path=LOCATION"]];
    }
    else if ([key isEqualToString:@"tracking"]) {
        result = [self do_open:[prefix stringByAppendingString:@"Privacy&path=USER_TRACKING"]];
    }
    else if ([key isEqualToString:@"network"]) {
        result = [self do_open:[prefix stringByAppendingString:@"General&path=Network"]];
    }
    else if ([key isEqualToString:@"nike_ipod"]) {
        result = [self do_open:[prefix stringByAppendingString:@"NIKE_PLUS_IPOD"]];
    }
    else if ([key isEqualToString:@"notes"]) {
        result = [self do_open:[prefix stringByAppendingString:@"NOTES"]];
    }
    else if ([key isEqualToString:@"notification_id"]) {
        result = [self do_open:[prefix stringByAppendingString:@"NOTIFICATIONS_ID"]];
    }
    else if ([key isEqualToString:@"passbook"]) {
        result = [self do_open:[prefix stringByAppendingString:@"PASSBOOK"]];
    }
    else if ([key isEqualToString:@"phone"]) {
        result = [self do_open:[prefix stringByAppendingString:@"Phone"]];
    }
    else if ([key isEqualToString:@"photos"]) {
        result = [self do_open:[prefix stringByAppendingString:@"Photos"]];
    }
    else if ([key isEqualToString:@"reset"]) {
        result = [self do_open:[prefix stringByAppendingString:@"General&path=Reset"]];
    }
    else if ([key isEqualToString:@"ringtone"]) {
        result = [self do_open:[prefix stringByAppendingString:@"Sounds&path=Ringtone"]];
    }
    else if ([key isEqualToString:@"browser"]) {
        result = [self do_open:[prefix stringByAppendingString:@"Safari"]];
    }
    else if ([key isEqualToString:@"search"]) {
        result = [self do_open:[prefix stringByAppendingString:@"SIRI"]];
    }
    else if ([key isEqualToString:@"sound"]) {
        result = [self do_open:[prefix stringByAppendingString:@"Sounds"]];
    }
    else if ([key isEqualToString:@"software_update"]) {
        result = [self do_open:[prefix stringByAppendingString:@"General&path=SOFTWARE_UPDATE_LINK"]];
    }
    else if ([key isEqualToString:@"storage"]) {
        result = [self do_open:[prefix stringByAppendingString:@"CASTLE&path=STORAGE_AND_BACKUP"]];
    }
    else if ([key isEqualToString:@"store"]) {
        result = [self do_open:[prefix stringByAppendingString:@"STORE"]];
    }
    else if ([key isEqualToString:@"usage"]) {
        result = [self do_open:[prefix stringByAppendingString:@"General&path=USAGE"]];
    }
    else if ([key isEqualToString:@"video"]) {
        result = [self do_open:[prefix stringByAppendingString:@"VIDEO"]];
    }
    else if ([key isEqualToString:@"vpn"]) {
        result = [self do_open:[prefix stringByAppendingString:@"General&path=Network/VPN"]];
    }
    else if ([key isEqualToString:@"wallpaper"]) {
        result = [self do_open:[prefix stringByAppendingString:@"Wallpaper"]];
    }
    else if ([key isEqualToString:@"wifi"]) {
        result = [self do_open:[prefix stringByAppendingString:@"WIFI"]];
    }
    else if ([key isEqualToString:@"touch"]) {
        result = [self do_open:[prefix stringByAppendingString:@"TOUCHID_PASSCODE"]];
    }
    else if ([key isEqualToString:@"battery"]) {
        result = [self do_open:[prefix stringByAppendingString:@"BATTERY_USAGE"]];
    }
    else if ([key isEqualToString:@"privacy"]) {
        result = [self do_open:[prefix stringByAppendingString:@"Privacy"]];
    }
    else if ([key isEqualToString:@"do_not_disturb"]) {
        result = [self do_open:[prefix stringByAppendingString:@"General&path=DO_NOT_DISTURB"]];
    }
    else if ([key isEqualToString:@"keyboards"]) {
        result = [self do_open:[prefix stringByAppendingString:@"General&path=Keyboard/KEYBOARDS"]];
    }
    else if ([key isEqualToString:@"mobile_data"]) {
        result = [self do_open:[prefix stringByAppendingString:@"MOBILE_DATA_SETTINGS_ID"]];
    }
    else {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"Invalid Action"];
    }
        
    if (result) {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@"Opened"];
    } else {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"Cannot open"];
    }
    
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

@end
