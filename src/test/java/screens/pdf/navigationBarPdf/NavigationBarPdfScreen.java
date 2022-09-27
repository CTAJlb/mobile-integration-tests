package screens.pdf.navigationBarPdf;

import aquality.appium.mobile.screens.Screen;
import org.openqa.selenium.By;

public abstract class NavigationBarPdfScreen extends Screen {
    protected NavigationBarPdfScreen(By locator) {
        super(locator, "NavigationBarPdf");
    }

    public abstract void tapTocBookmarksBarButton();

    public abstract void tapBackButton();

    public abstract void tapSettingsButton();

    public abstract void tapSearchButton();

    public abstract void tapBookmarkButton();
}
