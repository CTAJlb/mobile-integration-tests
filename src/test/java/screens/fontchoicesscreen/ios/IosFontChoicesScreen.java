package screens.fontchoicesscreen.ios;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import constants.localization.application.reader.ReaderSettingKeys;
import framework.utilities.CoordinatesClickUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import screens.fontchoicesscreen.FontChoicesScreen;

@ScreenType(platform = PlatformName.IOS)
public class IosFontChoicesScreen extends FontChoicesScreen {
    private static final String MAIN_ELEMENT = "//XCUIElementTypeOther[./XCUIElementTypeButton[@name=\"Sans font\"]]";
    private static final String SETTING_LOC = "//XCUIElementTypeButton[@name=\"%1$s\"]";

    private final ILabel fontChoicesScreen = getElementFactory().getLabel(By.xpath(MAIN_ELEMENT), "Font choices screen");

    public IosFontChoicesScreen() {
        super(By.xpath(MAIN_ELEMENT));
    }

    @Override
    public void setSetting(ReaderSettingKeys readerSettingKey) {
        String setting = readerSettingKey.i18n();
        getElementFactory().getButton(By.xpath(String.format(SETTING_LOC, setting)), setting).click();
    }

    @Override
    public void closeFontChoices() {
        CoordinatesClickUtils.clickOutOfElement(fontChoicesScreen);
        Assert.assertTrue("Font choices screen is not closed", super.state().waitForNotDisplayed());
        CoordinatesClickUtils.clickAtCenterOfScreen();
    }
}
