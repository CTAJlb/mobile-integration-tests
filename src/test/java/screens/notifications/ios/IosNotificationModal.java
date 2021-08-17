package screens.notifications.ios;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import constants.localization.application.catalog.BookActionButtonKeys;
import org.openqa.selenium.By;
import screens.notifications.NotificationModal;

@ScreenType(platform = PlatformName.IOS)
public class IosNotificationModal extends NotificationModal {
    private static final String MAIN_ELEMENT = "//XCUIElementTypeButton";
    private static final String ACTION_BTN_FOR_POPUP_PATTERN = "//XCUIElementTypeAlert//XCUIElementTypeButton[@name = \"%s\"]";

    private final IButton btnDontAllowNotifications = getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@name=\"Don’t Allow\"]"),
            "Dont allow notifications");

    private final IButton btnOkCannotAddBook = getElementFactory().getButton(
            By.xpath("//XCUIElementTypeScrollView[.//XCUIElementTypeStaticText[@name=\"Borrowing failed\"]]"
                    + "/following-sibling::XCUIElementTypeScrollView//XCUIElementTypeButton[@name=\"OK\"]"),
            "Button ok");

    public IosNotificationModal() {
        super(By.xpath(MAIN_ELEMENT));
    }

    @Override
    public void closeSyncNotificationIfDisplayed() {
        if (btnDontAllowNotifications.state().waitForDisplayed()) {
            btnDontAllowNotifications.click();
        }
    }

    @Override
    public void closeCannotAddBookModalIfDisplayed() {
        if (btnOkCannotAddBook.state().isDisplayed()) {
            btnOkCannotAddBook.click();
        }
    }

    @Override
    public void performActionForNotificationPopup(BookActionButtonKeys buttonName) {
        IButton btnApproveAction = getElementFactory().getButton(
                By.xpath(String.format(ACTION_BTN_FOR_POPUP_PATTERN, buttonName.i18n())), buttonName.i18n());
        btnApproveAction.state().waitForDisplayed();
        if (btnApproveAction.state().isDisplayed()) {
            btnApproveAction.click();
        }
    }

    @Override
    public boolean isModalPresent() {
        return btnOkCannotAddBook.state().isDisplayed();
    }
}
