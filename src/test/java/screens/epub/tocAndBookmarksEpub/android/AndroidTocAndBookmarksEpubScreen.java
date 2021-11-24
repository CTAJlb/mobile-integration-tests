package screens.epub.tocAndBookmarksEpub.android;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import constants.epub.EnumTabsTocAndBookmarksEpub;
import org.openqa.selenium.By;
import screens.epub.tocAndBookmarksEpub.TocAndBookmarksEpubScreen;
import screens.epub.tocEpub.TocEpubScreen;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidTocAndBookmarksEpubScreen extends TocAndBookmarksEpubScreen {
    public AndroidTocAndBookmarksEpubScreen() {
        super(By.xpath("//android.widget.HorizontalScrollView[contains(@resource-id,\"tocTabs\")]"));
        tocEpubScreen = AqualityServices.getScreenFactory().getScreen(TocEpubScreen.class);
    }

    @Override
    public void returnToPreviousScreen() {
        AqualityServices.getApplication().getDriver().navigate().back();
    }

    @Override
    public void openTab(EnumTabsTocAndBookmarksEpub tab) {
        getElementFactory().getLabel(By.xpath(String.format("//android.widget.TextView[@text=\"%s\"]", tab.getValue())), "tab").click();
    }

    @Override
    public TocEpubScreen getTocEpubScreen() {
        return tocEpubScreen;
    }
}
