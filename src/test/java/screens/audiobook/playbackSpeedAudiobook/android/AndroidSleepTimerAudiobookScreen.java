package screens.audiobook.playbackSpeedAudiobook.android;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;
import screens.audiobook.playbackSpeedAudiobook.PlaybackSpeedAudiobookScreen;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidSleepTimerAudiobookScreen extends PlaybackSpeedAudiobookScreen {
    private static final String PLAYBACK_SPEED = "//*[@text=\"%sx\"]";

    public AndroidSleepTimerAudiobookScreen() {
        super(By.xpath("//androidx.recyclerview.widget.RecyclerView[contains(@resource-id,\"list\")]"));
    }

    @Override
    public void selectPlaybackSpeed(String playbackSpeed) {
        getElementFactory().getButton(By.xpath(String.format(PLAYBACK_SPEED, playbackSpeed)), "Playback speed").click();
    }

    @Override
    public void closePlaybackScreen() {
        //only for iOS
    }
}
