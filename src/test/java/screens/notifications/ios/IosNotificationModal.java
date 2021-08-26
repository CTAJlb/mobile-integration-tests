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

    public IosNotificationModal() {
        super(By.xpath(MAIN_ELEMENT));
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
}
