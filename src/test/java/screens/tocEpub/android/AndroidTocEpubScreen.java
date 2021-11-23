package screens.tocEpub.android;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import constants.epub.EpubConstants;
import org.openqa.selenium.By;
import screens.epubtableofcontents.EpubTableOfContentsScreen;
import screens.tocEpub.TocEpubScreen;

import java.util.List;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidTocEpubScreen extends TocEpubScreen {
    private final EpubTableOfContentsScreen epubTableOfContentsScreen;

    public AndroidTocEpubScreen() {
        super(By.xpath("//android.widget.HorizontalScrollView[contains(@resource-id,\"tocTabs\")]"));
        epubTableOfContentsScreen = AqualityServices.getScreenFactory().getScreen(EpubTableOfContentsScreen.class);
    }

    @Override
    public void openChapter(String chapterName) {
        openTab(EpubConstants.CONTENTS_ANDROID);
        epubTableOfContentsScreen.openChapter(chapterName);
    }

    @Override
    public List<String> openListOfChaptersAndGetListOfBookChapters() {
        openTab(EpubConstants.CONTENTS_ANDROID);
        return epubTableOfContentsScreen.getListOfBookChapters();
    }

    @Override
    public void returnToPreviousScreen() {
        AqualityServices.getApplication().getDriver().navigate().back();
    }

    private void openTab(String tabName) {
        getElementFactory().getLabel(By.xpath(String.format("//android.widget.TextView[@text=\"%s\"]", tabName)), "tab").click();
    }
}
