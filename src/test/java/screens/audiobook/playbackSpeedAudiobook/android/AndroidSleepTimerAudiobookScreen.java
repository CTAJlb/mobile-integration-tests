package screens.audiobook.playbackSpeedAudiobook.android;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;
import screens.audiobook.playbackSpeedAudiobook.PlaybackSpeedAudiobookScreen;
import screens.audiobook.sleepTimerAudiobook.SleepTimerAudiobookScreen;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidSleepTimerAudiobookScreen extends PlaybackSpeedAudiobookScreen {
    private final IButton btnPlaybackSpeed = getElementFactory().getButton(By.id("player_menu_playback_rate_image"), "Player speed");

    public AndroidSleepTimerAudiobookScreen() {
        super(By.xpath(""));
    }

    @Override
    public void selectPlaybackSpeed(String playbackSpeed) {
        btnPlaybackSpeed.click();
        getElementFactory().getButton(By.xpath("//*[@text=\"" + playbackSpeed + "x\"]"), "Playback speed").click();
    }
}
