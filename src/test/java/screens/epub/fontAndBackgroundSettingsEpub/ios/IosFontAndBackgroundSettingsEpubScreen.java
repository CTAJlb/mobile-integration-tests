package screens.epub.fontAndBackgroundSettingsEpub.ios;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import enums.localization.reader.ReaderSettingKeys;
import org.openqa.selenium.By;
import screens.epub.fontAndBackgroundSettingsEpub.FontAndBackgroundSettingsEpubScreen;

@ScreenType(platform = PlatformName.IOS)
public class IosFontAndBackgroundSettingsEpubScreen extends FontAndBackgroundSettingsEpubScreen {
    private static final String MAIN_ELEMENT = "//XCUIElementTypeButton[@name=\"Sans font\"]";
    private static final String SETTING_LOC = "//XCUIElementTypeButton[@name=\"%1$s\"]";

    public IosFontAndBackgroundSettingsEpubScreen() {
        super(By.xpath(MAIN_ELEMENT));
    }

    @Override
    public void setSetting(ReaderSettingKeys readerSettingKey) {
        String setting = readerSettingKey.i18n();
        getElementFactory().getButton(By.xpath(String.format(SETTING_LOC, setting)), setting).click();
    }
}
