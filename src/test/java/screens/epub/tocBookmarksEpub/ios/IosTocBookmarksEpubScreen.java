package screens.epub.tocBookmarksEpub.ios;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import enums.epub.EnumTabsTocAndBookmarksEpub;
import org.openqa.selenium.By;
import screens.epub.bookmarksEpub.BookmarksEpubScreen;
import screens.epub.tocBookmarksEpub.TocBookmarksEpubScreen;
import screens.epub.tocEpub.TocEpubScreen;

@ScreenType(platform = PlatformName.IOS)
public class IosTocBookmarksEpubScreen extends TocBookmarksEpubScreen {
    public IosTocBookmarksEpubScreen() {
        super(By.xpath("//XCUIElementTypeStaticText[@name=\"Table of Contents\"]"));
        tocEpubScreen = AqualityServices.getScreenFactory().getScreen(TocEpubScreen.class);
        bookmarksEpubScreen = AqualityServices.getScreenFactory().getScreen(BookmarksEpubScreen.class);
    }

    @Override
    public void returnToPreviousScreen() {
        getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@name=\"Back\"]"), "btnBack").click();
    }

    @Override
    public void openTab(EnumTabsTocAndBookmarksEpub tab) {
        ILabel lblTab = getElementFactory().getLabel(By.xpath(String.format("//XCUIElementTypeButton[@name=\"%s\"]", tab.getValue())), "tab");
        lblTab.state().waitForDisplayed();
        lblTab.click();
    }

    @Override
    public TocEpubScreen getTocEpubScreen() {
        return tocEpubScreen;
    }

    @Override
    public BookmarksEpubScreen getBookmarksEpubScreen() {
        return bookmarksEpubScreen;
    }
}
