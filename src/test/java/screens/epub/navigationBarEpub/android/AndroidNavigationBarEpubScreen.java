package screens.epub.navigationBarEpub.android;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;
import screens.epub.navigationBarEpub.NavigationBarEpubScreen;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidNavigationBarEpubScreen extends NavigationBarEpubScreen {
    private final IButton btnFontSettings =
            getElementFactory().getButton(By.xpath("//android.widget.TextView[contains(@resource-id,\"readerMenuSettings\")]"), "btnFontSettings");
    private final IButton btnTOC =
            getElementFactory().getButton(By.xpath("//android.widget.TextView[contains(@resource-id,\"readerMenuTOC\")]"), "btnChapters");
    private final IButton btnAddBookmark =
            getElementFactory().getButton(By.xpath("//android.widget.TextView[contains(@resource-id,\"readerMenuAddBookmark\")]"), "btnAddBookmark");
    private final IButton btnDeleteBookmark =
            getElementFactory().getButton(By.xpath("//android.widget.TextView[contains(@resource-id,\"readerMenuAddBookmark\")]"), "btnDeleteBookmark");
    private final IButton btnBack =
            getElementFactory().getButton(By.xpath("//android.view.ViewGroup[contains(@resource-id,\"readerToolbar\")]/android.widget.ImageButton"), "btnBack");

    public AndroidNavigationBarEpubScreen() {
        super(By.xpath("//android.view.ViewGroup[contains(@resource-id,\"readerToolbar\")]"));
    }

    @Override
    public void returnToPreviousScreen() {
        btnBack.click();
    }

    @Override
    public void tapFontSettingsButton() {
        btnFontSettings.click();
    }

    @Override
    public void tapAddBookmarkButton() {
        btnAddBookmark.click();
    }

    @Override
    public void tapDeleteBookmarkButton() {
        btnDeleteBookmark.click();
    }

    @Override
    public void tapTOCBookmarksButton() {
        btnTOC.click();
    }

    @Override
    public boolean isBookmarkDisplayed() {
        return btnDeleteBookmark.state().isDisplayed();
    }
}
