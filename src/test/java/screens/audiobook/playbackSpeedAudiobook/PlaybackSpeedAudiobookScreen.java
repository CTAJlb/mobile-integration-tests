package screens.audiobook.playbackSpeedAudiobook;

import aquality.appium.mobile.screens.Screen;
import org.openqa.selenium.By;

public abstract class PlaybackSpeedAudiobookScreen extends Screen {
    protected PlaybackSpeedAudiobookScreen(By locator) {
        super(locator, "PlaybackSpeedAudiobookScreen");
    }
    public abstract void selectPlaybackSpeed(String playbackSpeed);
}
