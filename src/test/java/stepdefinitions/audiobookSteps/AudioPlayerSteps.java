package stepdefinitions.audiobookSteps;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import com.google.inject.Inject;
import constants.RegEx;
import framework.utilities.RegExUtil;
import framework.utilities.ScenarioContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import screens.audiobook.audioPlayer.AudioPlayerScreen;

import java.time.Duration;

import static constants.localization.application.catalog.TimerKeys.END_OF_CHAPTER;

public class AudioPlayerSteps {
    private final AudioPlayerScreen audioPlayerScreen;
    private static final int PING_COUNT_OF_SECONDS = 6;
    private final ScenarioContext context;
    private long diffBetweenTimePointsWhenForward;
    private long diffBetweenTimePointsWhenBehind;
    private long diffForMiddleOfChapter;

    @Inject
    public AudioPlayerSteps(ScenarioContext context) {
        audioPlayerScreen = AqualityServices.getScreenFactory().getScreen(AudioPlayerScreen.class);
        this.context = context;
    }
    @And("Open toc audiobook screen")
    public void openTocAudiobookScreen() {
        audioPlayerScreen.openToc();
    }

    @Then("Chapter name on audio player screen is equal to {string} saved chapter name")
    public void checkThatChapterNameOnAudioPlayerScreenIsEqualToSavedChapterName(String keyChapter) {
        String expectedChapterName = context.get(keyChapter);
        Assert.assertEquals(String.format("Chapter name on audio player screen is not equal to saved chapter name. " +
                "Expected chapter name - %s; actual chapter name - %s", expectedChapterName, getChapterName()), expectedChapterName.toLowerCase(), getChapterName().toLowerCase());
    }

    private String getChapterName() {
        return RegExUtil.getStringFromFirstGroup(audioPlayerScreen.getChapterName(), RegEx.AUDIO_BOOK_CURRENT_CHAPTER_TEXT_REGEX);
    }

    @And("Tap play button on audio player screen")
    public void tapPlayButtonOnAudioPlayerScreen() {
        audioPlayerScreen.tapPlayBtn();
    }

    @When("Tap pause button on audio player screen")
    public void tapPauseButtonOnAudioPlayerScreen() {
        audioPlayerScreen.tapPauseBtn();
    }

    @Then("Pause button is present on audio player screen")
    public void checkThatPauseButtonIsPresentOnAudioPlayerScreen() {
        Assert.assertTrue("Pause button is not present on audio player screen", audioPlayerScreen.isPauseButtonPresent());
    }

    @Then("Play button is present on audio player screen")
    public void checkThatPlayButtonIsPresentOnAudioPlayerScreen() {
        Assert.assertTrue("Play button is not present on audio player screen", audioPlayerScreen.isPlayButtonPresent());
    }

    @Then("Book is playing on audio player screen")
    public void checkThatBookIsPlayingOnAudioPlayerScreen() {
        Duration firstTiming = audioPlayerScreen.getLeftTime();
        Assert.assertTrue("Book is not playing on audio player screen",
                AqualityServices.getConditionalWait().waitFor(() -> !firstTiming.equals(audioPlayerScreen.getLeftTime())));
    }

    @And("Book is not playing on audio player screen")
    public void checkThatBookIsNotPlayingOnAudioPlayerScreen() {
        Duration firstTiming = audioPlayerScreen.getLeftTime();
        //todo tread sleep
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals("Book is playing on audio player screen", firstTiming, audioPlayerScreen.getLeftTime());
    }

    @And("Save book play time as {string} on audio player screen")
    public void saveBookPlayTimeOnAudioPlayerScreen(String dateKey) {
        context.add(dateKey, audioPlayerScreen.getLeftTime());
    }

    @And("Skip ahead 15 seconds on audio player screen")
    public void skipAheadOnAudioPlayerScreen() {
        audioPlayerScreen.skipAhead();
    }

    @And("Skip behind 15 seconds on audio player screen")
    public void skipBehindOnAudioPlayerScreen() {
        audioPlayerScreen.skipBehind();
    }

    @And("Return to previous screen from audio player screen")
    public void returnToPreviousScreenFromAudioPlayerScreen() {
        audioPlayerScreen.returnToPreviousScreen();
    }

    @Then("Playback has been moved forward by {int} seconds from {string} seconds on audio player screen")
    public void checkThatPlaybackHasBeenMovedForwardOnAudioPlayerScreen(Integer secondsForward, String timeKey) {
        Duration savedDate = context.get(timeKey);
        long secondsBefore = savedDate.getSeconds();
        boolean isResultTrue = false;
        isResultTrue = AqualityServices.getConditionalWait().waitFor(() -> {
            long diffInSeconds = audioPlayerScreen.getLeftTime().getSeconds() - secondsBefore;
            boolean isConditionTrue = diffInSeconds >= secondsForward && diffInSeconds <= secondsForward + PING_COUNT_OF_SECONDS;
            setDiffBetweenTimePointsWhenForward(diffInSeconds);

            return isConditionTrue;
        }, Duration.ofSeconds(PING_COUNT_OF_SECONDS));

        AqualityServices.getLogger().info("diff between times for move forward - " + diffBetweenTimePointsWhenForward);
        Assert.assertTrue("Date is not moved forward by " + secondsForward + " seconds, Date is moved forward by " + diffBetweenTimePointsWhenForward, isResultTrue);
    }

    private void setDiffBetweenTimePointsWhenForward(long diff) {
        diffBetweenTimePointsWhenForward = diff;
    }

    private void setDiffBetweenTimePointsWhenBehind(long diff) {
        diffBetweenTimePointsWhenBehind = diff;
    }

    @Then("Playback has been moved behind by {int} seconds from {string} seconds on audio player screen")
    public void checkThatPlaybackHasBeenMovedBehindOnAudioPlayerScreen(int secondsBehind, String timeKey) {
        Duration savedDate = context.get(timeKey);
        long secondsBefore = savedDate.getSeconds();
        boolean isResultTrue = AqualityServices.getConditionalWait().waitFor(() -> {
            long diffInSeconds = secondsBefore - audioPlayerScreen.getLeftTime().getSeconds();
            boolean isConditionTrue = diffInSeconds <= secondsBehind && diffInSeconds >= secondsBehind - PING_COUNT_OF_SECONDS;
            setDiffBetweenTimePointsWhenBehind(diffInSeconds);

            return isConditionTrue;
        }, Duration.ofSeconds(PING_COUNT_OF_SECONDS));

        AqualityServices.getLogger().info("diff between times for move behind - " + diffBetweenTimePointsWhenBehind);
        Assert.assertTrue("Date is not moved behind by " + secondsBehind + " seconds, Date is moved behind by " + diffBetweenTimePointsWhenBehind, isResultTrue);
    }

    @And("Current playback speed value is {double}X on audio player screen")
    public void checkCurrentPlaybackSpeedValueIsCorrectOnAudioPlayerScreen(Double playbackSpeedDouble) {
        String playbackSpeed = String.valueOf(playbackSpeedDouble);
        Assert.assertTrue("Current playback speed value is not correct on audio player screen", audioPlayerScreen.isPlaybackSpeedPresent(playbackSpeed));
    }

    private void setDiffForMiddleOfChapter(long diff) {
        diffForMiddleOfChapter = diff;
    }

    @Then("Sleep timer is set to endOfChapter on audio player screen")
    public void checkThatSleepTimerIsSetToEndOfChapterOnAudioPLayerScreen() {
        if (AqualityServices.getApplication().getPlatformName() == PlatformName.ANDROID) {
            Assert.assertTrue("Timer value is not correct", audioPlayerScreen.isTimerSetTo(END_OF_CHAPTER));
        } else if (AqualityServices.getApplication().getPlatformName() == PlatformName.IOS) {
            Assert.assertTrue("Timer value is not correct", audioPlayerScreen.isTimerEqualTo(audioPlayerScreen.getRightTime()));
        }
    }
}
