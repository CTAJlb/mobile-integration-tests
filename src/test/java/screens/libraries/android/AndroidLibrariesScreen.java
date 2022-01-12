package screens.libraries.android;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;
import screens.libraries.LibrariesScreen;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidLibrariesScreen extends LibrariesScreen {
    public static final String LIBRARY_BUTTON_BY_LIBRARY_NAME_LOC =
            "//android.widget.TextView[@text=\"%s\"]";
    public static final String POPUP_MENU_BUTTON_BY_LIBRARY_NAME_LOC =
            "//android.widget.TextView[@text=\"%s\"]/parent::android.widget.LinearLayout/following-sibling::android.widget.ImageButton";

    private final IButton btnAddLibrary =
            getElementFactory().getButton(By.id("accountsMenuActionAccountAdd"), "btnAddLibrary");
    private final IButton btnDelete =
            getElementFactory().getButton(By.xpath("//android.widget.TextView[@text=\"Delete\"]"), "btnDelete");
    private final IButton btnAcceptDeletion =
            getElementFactory().getButton(By.id("android:id/button1"), "btnAcceptDeletion");

    public AndroidLibrariesScreen() {
        super(By.xpath("//android.widget.TextView[@text=\"Libraries\"]"));
    }

    @Override
    public boolean isLibraryPresent(String libraryName) {
        return getLibraryButton(libraryName).state().waitForDisplayed();
    }

    private IButton getLibraryButton(String libraryName) {
        return getElementFactory().getButton(By.xpath(String.format(LIBRARY_BUTTON_BY_LIBRARY_NAME_LOC, libraryName)), libraryName);
    }

    @Override
    public void openLibrary(String libraryName) {
        getLibraryButton(libraryName).click();
    }

    @Override
    public void addLibrary() {
        btnAddLibrary.click();
    }

    @Override
    public void deleteLibrary(String libraryName) {
        getElementFactory().getButton(By.xpath(String.format(POPUP_MENU_BUTTON_BY_LIBRARY_NAME_LOC, libraryName)), "btnPopupMenu").click();
        btnDelete.click();
        btnAcceptDeletion.click();
    }
}
