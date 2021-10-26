package screens.fontchoicesscreen;

import aquality.appium.mobile.screens.Screen;
import constants.localization.application.reader.ReaderSettingKeys;
import org.openqa.selenium.By;

public abstract class EpubFontChoicesScreen extends Screen {
    protected EpubFontChoicesScreen(By locator) {
        super(locator, "Font choices");
    }

    public abstract void setSetting(ReaderSettingKeys readerSettingKey);

    public abstract void closeFontChoices();
}
