package screens.notifications.android;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import constants.localization.application.catalog.BookActionButtonKeys;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import screens.notifications.NotificationModal;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidNotificationModal extends NotificationModal {
    public AndroidNotificationModal() {
        super(By.xpath(StringUtils.EMPTY));
    }

    @Override
    public void performActionOnAlert(BookActionButtonKeys bookActionButtonKeys) {
        // only ios
    }
}
