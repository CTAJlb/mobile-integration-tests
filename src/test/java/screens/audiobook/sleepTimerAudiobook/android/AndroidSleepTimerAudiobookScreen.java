package screens.audiobook.sleepTimerAudiobook.android;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import enums.localization.catalog.TimerKeys;
import org.openqa.selenium.By;
import screens.audiobook.sleepTimerAudiobook.SleepTimerAudiobookScreen;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidSleepTimerAudiobookScreen extends SleepTimerAudiobookScreen {
    private static final String SLEEP_TIMER_LOC = "//*[contains(@resource-id, \"player_sleep_item_view_name\") and @text=\"%s\"]";

    private final IButton btnCancel = getElementFactory().getButton(By.xpath("//android.widget.TextView[@text=\"Cancel\"]"), "Cancel button");

    public AndroidSleepTimerAudiobookScreen() {
        super(By.xpath("//android.widget.TextView[contains(@resource-id,\"player_sleep_item_view_name\")]"));
    }

    @Override
    public void setTimer(TimerKeys timerSetting) {
        String timerSettingName = timerSetting.i18n().replace("Chapter", "chapter");
        getElementFactory().getButton(By.xpath(String.format(SLEEP_TIMER_LOC, timerSettingName)), timerSettingName).click();
    }

    @Override
    public void closeSleepTimer() {
        btnCancel.click();
    }
}
