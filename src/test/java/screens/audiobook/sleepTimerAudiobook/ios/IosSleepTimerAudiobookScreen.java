package screens.audiobook.sleepTimerAudiobook.ios;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import constants.localization.application.catalog.TimerKeys;
import org.openqa.selenium.By;
import screens.audiobook.sleepTimerAudiobook.SleepTimerAudiobookScreen;

@ScreenType(platform = PlatformName.IOS)
public class IosSleepTimerAudiobookScreen extends SleepTimerAudiobookScreen {
    public IosSleepTimerAudiobookScreen() {
        super(By.xpath("//XCUIElementTypeStaticText[@name=\"Sleep Timer\"]"));
    }

    @Override
    public void setTimer(TimerKeys timerSetting) {
        String buttonName = timerSetting.i18n();
        getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@name=\"" + buttonName + "\"]"), buttonName).click();
    }
}
