package screens.tocEpub.ios;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import constants.epub.EpubConstants;
import org.openqa.selenium.By;
import screens.epubtableofcontents.EpubTableOfContentsScreen;
import screens.tocEpub.TocEpubScreen;

import java.util.List;

@ScreenType(platform = PlatformName.IOS)
public class IosTocEpubScreen extends TocEpubScreen {
    private final EpubTableOfContentsScreen epubTableOfContentsScreen;

    public IosTocEpubScreen() {
        super(By.xpath("//XCUIElementTypeStaticText[@name=\"Table of Contents\"]"));
        epubTableOfContentsScreen = AqualityServices.getScreenFactory().getScreen(EpubTableOfContentsScreen.class);
    }

    @Override
    public void openChapter(String chapterName) {
        openTab(EpubConstants.CONTENTS_IOS);
        epubTableOfContentsScreen.openChapter(chapterName);
    }

    @Override
    public List<String> openListOfChaptersAndGetListOfBookChapters() {
        openTab(EpubConstants.CONTENTS_IOS);
        return epubTableOfContentsScreen.getListOfBookChapters();
    }

    @Override
    public void returnToPreviousScreen() {
        getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@name=\"Back\"]"), "btnBack").click();
    }

    private void openTab(String tabName) {
        getElementFactory().getLabel(By.xpath(String.format("//XCUIElementTypeButton[@name=\"%s\"]", tabName)), "tab").click();
    }
}
