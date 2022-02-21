package screens.audiobook.sleepTimerAudiobook.android;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import constants.localization.application.catalog.TimerKeys;
import org.openqa.selenium.By;
import screens.audiobook.sleepTimerAudiobook.SleepTimerAudiobookScreen;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidSleepTimerAudiobookScreen extends SleepTimerAudiobookScreen {
    private final IButton btnSleep = getElementFactory().getButton(By.id("player_menu_sleep_image"), "Sleep");
    private static final String TIMER_XPATH_PATTERN_LOCATOR = "//*[contains(@resource-id, \"player_sleep_item_view_name\") and @text=\"%s\"]";

    public AndroidSleepTimerAudiobookScreen() {
        super(By.xpath(""));
    }
    @Override
    public void setTimer(TimerKeys timerSetting) {
        btnSleep.click();
        String timerSettingName = timerSetting.i18n().replace("Chapter", "chapter");
        getElementFactory().getButton(By.xpath(String.format(TIMER_XPATH_PATTERN_LOCATOR, timerSettingName)), timerSettingName).click();
    }

}
