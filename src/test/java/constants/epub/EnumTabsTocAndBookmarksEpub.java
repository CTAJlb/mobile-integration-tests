package constants.epub;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;

public enum EnumTabsTocAndBookmarksEpub {
    BOOKMARKS("Bookmarks", "BOOKMARKS"),
    TOC("Contents", "TABLE OF CONTENTS");

    private String iosValue;
    private String androidValue;

    EnumTabsTocAndBookmarksEpub(String iosValue, String androidValue) {
        this.iosValue = iosValue;
        this.androidValue = androidValue;
    }

    public String getValue() {
        if (AqualityServices.getApplication().getPlatformName() == PlatformName.ANDROID) {
            return androidValue;
        } else {
            return iosValue;
        }
    }
}
