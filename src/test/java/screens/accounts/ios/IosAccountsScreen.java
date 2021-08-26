package screens.accounts.ios;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import framework.utilities.swipe.SwipeElementUtils;
import org.openqa.selenium.By;
import screens.accounts.AccountsScreen;

@ScreenType(platform = PlatformName.IOS)
public class IosAccountsScreen extends AccountsScreen {
    private static final String MAIN_ELEMENT = "//XCUIElementTypeNavigationBar[@name=\"Accounts\"]";
    private static final String BUTTON_LIBRARY_LOCATOR = "//XCUIElementTypeStaticText[@name=\"%s\"]";

    private final IButton btnAdd = getElementFactory().getButton(
            By.xpath("//XCUIElementTypeButton[@name=\"Add Library\"]"), "Add library");
    private final IButton btnDelete =
            getElementFactory().getButton(By.name("Delete"), "Delete library");

    public IosAccountsScreen() {
        super(By.xpath(MAIN_ELEMENT));
    }

    @Override
    public boolean isLibraryPresent(String libraryName) {
        return getLibraryButton(libraryName).state().isDisplayed();
    }

    private IButton getLibraryButton(String libraryName) {
        return getElementFactory().getButton(By.xpath(String.format(BUTTON_LIBRARY_LOCATOR, libraryName)), libraryName);
    }

    @Override
    public void openLibraryAccount(String libraryName) {
        getLibraryButton(libraryName).click();
    }

    @Override
    public void addAccount() {
        btnAdd.click();
    }

    @Override
    public void deleteLibrary(String libraryName) {
        IButton libraryToSwipeLeft = getLibraryButton(libraryName);
        SwipeElementUtils.swipeElementLeft(libraryToSwipeLeft);
        btnDelete.click();
    }
}
