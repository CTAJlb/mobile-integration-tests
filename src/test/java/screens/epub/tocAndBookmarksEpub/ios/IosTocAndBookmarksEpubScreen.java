package screens.epub.tocAndBookmarksEpub.ios;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import constants.epub.EnumTabsTocAndBookmarksEpub;
import org.openqa.selenium.By;
import screens.epub.tocAndBookmarksEpub.TocAndBookmarksEpubScreen;
import screens.epub.tocEpub.TocEpubScreen;

@ScreenType(platform = PlatformName.IOS)
public class IosTocAndBookmarksEpubScreen extends TocAndBookmarksEpubScreen {
    public IosTocAndBookmarksEpubScreen() {
        super(By.xpath("//XCUIElementTypeStaticText[@name=\"Table of Contents\"]"));
        tocEpubScreen = AqualityServices.getScreenFactory().getScreen(TocEpubScreen.class);
    }

    @Override
    public void returnToPreviousScreen() {
        getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@name=\"Back\"]"), "btnBack").click();
    }

    @Override
    public void openTab(EnumTabsTocAndBookmarksEpub tab) {
        getElementFactory().getLabel(By.xpath(String.format("//XCUIElementTypeButton[@name=\"%s\"]", tab.getValue())), "tab").click();
    }

    @Override
    public TocEpubScreen getTocEpubScreen() {
        return tocEpubScreen;
    }
}
