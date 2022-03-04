package screens.epub.fontAndBackgroundSettingsEpub.android;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import constants.localization.application.reader.ReaderSettingKeys;
import org.openqa.selenium.By;
import screens.epub.fontAndBackgroundSettingsEpub.FontAndBackgroundSettingsEpubScreen;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidFontAndBackgroundSettingsEpubScreen extends FontAndBackgroundSettingsEpubScreen {

    public AndroidFontAndBackgroundSettingsEpubScreen() {
        super(By.xpath("//android.widget.FrameLayout[contains(@resource-id,\"custom\")]"));
    }

    @Override
    public void setSetting(ReaderSettingKeys readerSettingKey) {
        String setting = readerSettingKey.i18n();
        if(ReaderSettingKeys.FONT_DYSLEXIC == readerSettingKey || ReaderSettingKeys.FONT_SERIF == readerSettingKey || ReaderSettingKeys.FONT_SANS == readerSettingKey){
            getElementFactory().getButton(By.xpath(String.format("//android.widget.FrameLayout[contains(@resource-id,\"%s\")]", setting)), setting).click();
        }else {
            getElementFactory().getButton(By.xpath(String.format("//android.widget.LinearLayout/android.view.ViewGroup[contains(@resource-id,\"%s\")]", setting)), setting).click();
        }
    }
}
