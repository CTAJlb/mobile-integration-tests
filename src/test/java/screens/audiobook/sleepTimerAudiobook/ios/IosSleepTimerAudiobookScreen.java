package screens.audiobook.sleepTimerAudiobook.ios;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import constants.localization.application.catalog.TimerKeys;
import org.openqa.selenium.By;
import screens.audiobook.sleepTimerAudiobook.SleepTimerAudiobookScreen;

@ScreenType(platform = PlatformName.IOS)
public class IosSleepTimerAudiobookScreen extends SleepTimerAudiobookScreen {
    private final IButton btnTimer =
            getElementFactory().getButton(By.xpath("//XCUIElementTypeToolbar//XCUIElementTypeButton[3]"), "Timer");
    public IosSleepTimerAudiobookScreen() {
        super(By.xpath(""));
    }

    @Override
    public void setTimer(TimerKeys timerSetting) {
        btnTimer.click();
        String buttonName = timerSetting.i18n();
        getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@name=\"" + buttonName + "\"]"), buttonName).click();
    }
}
