package screens.audiobook.playbackSpeedAudiobook.android;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;
import screens.audiobook.playbackSpeedAudiobook.PlaybackSpeedAudiobookScreen;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidSleepTimerAudiobookScreen extends PlaybackSpeedAudiobookScreen {
    public AndroidSleepTimerAudiobookScreen() {
        super(By.xpath("//androidx.recyclerview.widget.RecyclerView[contains(@resource-id,\"list\")]"));
    }

    @Override
    public void selectPlaybackSpeed(String playbackSpeed) {
        getElementFactory().getButton(By.xpath("//*[@text=\"" + playbackSpeed + "x\"]"), "Playback speed").click();
    }
}
