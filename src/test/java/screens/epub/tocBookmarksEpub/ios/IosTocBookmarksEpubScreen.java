package screens.epub.tocBookmarksEpub.ios;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import enums.epub.EnumTabsTocAndBookmarksEpub;
import org.openqa.selenium.By;
import screens.epub.bookmarksEpub.BookmarksEpubScreen;
import screens.epub.tocBookmarksEpub.TocBookmarksEpubScreen;
import screens.epub.tocEpub.TocEpubScreen;

@ScreenType(platform = PlatformName.IOS)
public class IosTocBookmarksEpubScreen extends TocBookmarksEpubScreen {
    private final IButton btnBack = getElementFactory().getButton(By.xpath("//XCUIElementTypeNavigationBar/XCUIElementTypeButton"), "Back button");
    private final ILabel lblTOCHeader = getElementFactory().getLabel(By.xpath("//XCUIElementTypeNavigationBar/XCUIElementTypeStaticText"), "TOC header");
    private final IButton btnContents = getElementFactory().getButton(By.xpath("//XCUIElementTypeSegmentedControl/XCUIElementTypeButton[1]"), "Contents button");
    private final IButton btnBookmarks = getElementFactory().getButton(By.xpath("//XCUIElementTypeSegmentedControl/XCUIElementTypeButton[2]"), "Bookmarks button");

    public IosTocBookmarksEpubScreen() {
        super(By.xpath("//XCUIElementTypeStaticText[@name=\"Table of Contents\"]"));
        tocEpubScreen = AqualityServices.getScreenFactory().getScreen(TocEpubScreen.class);
        bookmarksEpubScreen = AqualityServices.getScreenFactory().getScreen(BookmarksEpubScreen.class);
    }

    @Override
    public void returnToPreviousScreen() {
        btnBack.click();
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

    @Override
    public String getTextFromBackBtn() {
        return btnBack.getText();
    }

    @Override
    public String getTextFromTOCLabel() {
        return lblTOCHeader.getText();
    }

    @Override
    public String getTextFromContentsBtn() {
        return btnContents.getText();
    }

    @Override
    public String getTextFromBookmarksBtn() {
        return btnBookmarks.getText();
    }
}
