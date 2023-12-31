package screens.epub.fontAndBackgroundSettingsEpub;

import aquality.appium.mobile.screens.Screen;
import enums.localization.reader.ReaderSettingKeys;
import org.openqa.selenium.By;

public abstract class FontAndBackgroundSettingsEpubScreen extends Screen {
    protected FontAndBackgroundSettingsEpubScreen(By locator) {
        super(locator, "Font choices");
    }

    public abstract void setSetting(ReaderSettingKeys readerSettingKey);
}
