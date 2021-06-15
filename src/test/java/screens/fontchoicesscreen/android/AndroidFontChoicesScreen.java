package screens.fontchoicesscreen.android;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import constants.localization.application.reader.ReaderSettingKeys;
import org.openqa.selenium.By;
import screens.fontchoicesscreen.FontChoicesScreen;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidFontChoicesScreen extends FontChoicesScreen {

    public AndroidFontChoicesScreen() {
        super(By.xpath("//android.view.ViewGroup[contains(@resource-id,\"setFontSans\")]"));
    }

    @Override
    public void setSetting(ReaderSettingKeys increaseFontSettings) {
        String setting = increaseFontSettings.i18n();
        getElementFactory().getButton(By.xpath(String.format("//android.view.ViewGroup[contains(@resource-id,\"%s\")]", setting)), setting).click();
    }

    @Override
    public void closeFontChoices() {
        AqualityServices.getApplication().getDriver().navigate().back();
    }
}
