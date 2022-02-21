package stepdefinitions;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import com.google.inject.Inject;
import constants.RegEx;
import constants.localization.application.catalog.TimerKeys;
import framework.utilities.RegExUtil;
import framework.utilities.ScenarioContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Assert;
import screens.audiobook.AudioPlayerScreen2;

import java.time.Duration;

import static constants.localization.application.catalog.TimerKeys.END_OF_CHAPTER;

public class AudioPlayerSteps {
    private static final int PING_COUNT_OF_SECONDS = 6;
    private final AudioPlayerScreen2 audioPlayerScreen2;
    private final ScenarioContext context;
    private long diffBetweenTimePointsWhenForward;
    private long diffBetweenTimePointsWhenBehind;
    private long diffForMiddleOfChapter;

    @Inject
    public AudioPlayerSteps(ScenarioContext context) {
        audioPlayerScreen2 = AqualityServices.getScreenFactory().getScreen(AudioPlayerScreen2.class);
        this.context = context;
    }

    @And("Open toc audiobook screen")
    public void openTocAudiobookScreen() {
        audioPlayerScreen2.openToc();
    }

    @When("Open random chapter on toc audiobook screen and save chapter name as {string}")
    public void openRandomChapterOnTocAudiobookScreenAndSaveChapterName(String keyChapterName) {
        String chapterName = audioPlayerScreen2.selectChapterAndGetText(RandomUtils.nextInt(1, audioPlayerScreen2.getCountOfChapters()));
        context.add(keyChapterName, chapterName);
    }

    @When("Open the {int} chapter on toc audiobook screen and save the chapter name as {string}")
    public void openSpecificChapterOnTocAudiobookScreenAndSaveChapterName(int chapterNumber, String keyChapterName) {
        String chapter = audioPlayerScreen2.selectChapterAndGetText(chapterNumber - 1);
        context.add(keyChapterName, chapter);
    }

    @Then("Chapter name on audio player screen is equal to {string} saved chapter name")
    public void checkThatChapterNameOnAudioPlayerScreenIsEqualToSavedChapterName(String keyChapter) {
        String expectedChapterName = context.get(keyChapter);
        Assert.assertTrue(String.format("Chapter name on audio player screen is not equal to saved chapter name. " +
                "Expected chapter name - %s; actual chapter name - %s", expectedChapterName, getChapterName()), expectedChapterName.toLowerCase().equals(getChapterName().toLowerCase()));
    }

    @And("Tap play button on audio player screen")
    public void tapPlayButtonOnAudioPlayerScreen() {
        audioPlayerScreen2.playBook();
    }

    @When("Tap pause button on audio player screen")
    public void tapPauseButtonOnAudioPlayerScreen() {
        audioPlayerScreen2.pauseBook();
    }

    @Then("Pause button is present on audio player screen")
    public void checkThatPauseButtonIsPresentOnAudioPlayerScreen() {
        Assert.assertTrue("Pause button is not present on audio player screen", audioPlayerScreen2.isPauseButtonPresent());
    }

    @Then("Play button is present on audio player screen")
    public void checkThatPlayButtonIsPresentOnAudioPlayerScreen() {
        Assert.assertTrue("Play button is not present on audio player screen", audioPlayerScreen2.isPlayButtonPresent());
    }

    @Then("Book is playing on audio player screen")
    public void checkThatBookIsPlayingOnAudioPlayerScreen() {
        Duration firstTiming = audioPlayerScreen2.getCurrentPlayTime();
        Assert.assertTrue("Book is not playing on audio player screen",
                AqualityServices.getConditionalWait().waitFor(() -> !firstTiming.equals(audioPlayerScreen2.getCurrentPlayTime())));
    }

    @And("Book is not playing on audio player screen")
    public void checkThatBookIsNotPlayingOnAudioPlayerScreen() {
        Duration firstTiming = audioPlayerScreen2.getCurrentPlayTime();
        //todo tread sleep
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals("Book is playing on audio player screen", firstTiming, audioPlayerScreen2.getCurrentPlayTime());
    }

    @And("Save book play time as {string} on audio player screen")
    public void saveBookPlayTimeOnAudioPlayerScreen(String dateKey) {
        context.add(dateKey, audioPlayerScreen2.getCurrentPlayTime());
    }

    @And("Skip ahead 15 seconds on audio player screen")
    public void skipAheadOnAudioPlayerScreen() {
        audioPlayerScreen2.skipAhead();
    }

    @And("Skip behind 15 seconds on audio player screen")
    public void skipBehindOnAudioPlayerScreen() {
        audioPlayerScreen2.skipBehind();
    }

    @And("Return to previous screen from audio player screen")
    public void returnToPreviousScreenFromAudioPlayerScreen() {
        audioPlayerScreen2.goBack();
    }

    @Then("Playback has been moved forward by {int} seconds from {string} seconds on audio player screen")
    public void checkThatPlaybackHasBeenMovedForwardOnAudioPlayerScreen(Integer secondsForward, String timeKey) {
        Duration savedDate = context.get(timeKey);
        long secondsBefore = savedDate.getSeconds();
        boolean isResultTrue = false;
        isResultTrue = AqualityServices.getConditionalWait().waitFor(() -> {
            long diffInSeconds = audioPlayerScreen2.getCurrentPlayTime().getSeconds() - secondsBefore;
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
            long diffInSeconds = secondsBefore - audioPlayerScreen2.getCurrentPlayTime().getSeconds();
            boolean isConditionTrue = diffInSeconds <= secondsBehind && diffInSeconds >= secondsBehind - PING_COUNT_OF_SECONDS;
            setDiffBetweenTimePointsWhenBehind(diffInSeconds);

            return isConditionTrue;
        }, Duration.ofSeconds(PING_COUNT_OF_SECONDS));

        AqualityServices.getLogger().info("diff between times for move behind - " + diffBetweenTimePointsWhenBehind);
        Assert.assertTrue("Date is not moved behind by " + secondsBehind + " seconds, Date is moved behind by " + diffBetweenTimePointsWhenBehind, isResultTrue);
    }

    @And("Move the slider to the middle of the chapter on audio player screen")
    public void moveSliderToMiddleOfChapterOnAudioPlayerScreen() {
        audioPlayerScreen2.moveChapterToMiddle();
    }

    private void setDiffForMiddleOfChapter(long diff) {
        diffForMiddleOfChapter = diff;
    }

    @And("I wait for {int} seconds")
    public void waitForSeconds(Integer secondsCount) {
        if (secondsCount > 10) {
            AqualityServices.getConditionalWait().waitFor(() -> false, Duration.ofSeconds(secondsCount / 3));
            AqualityServices.getApplication().getDriver().getContext();
            AqualityServices.getConditionalWait().waitFor(() -> false, Duration.ofSeconds(secondsCount / 3));
            AqualityServices.getApplication().getDriver().getContext();
            AqualityServices.getConditionalWait().waitFor(() -> false, Duration.ofSeconds(secondsCount / 3));
        } else {
            AqualityServices.getConditionalWait().waitFor(() -> false, Duration.ofSeconds(secondsCount));
        }
    }

    @And("Select {double}X playback speed on playback speed audiobook screen")
    public void selectPlaybackSpeedOnPlaybackSpeedAudiobookScreen(Double playbackSpeedDouble) {
        String playbackSpeed = String.valueOf(playbackSpeedDouble);
        audioPlayerScreen2.selectPlaybackSpeed(playbackSpeed);
    }

    @And("Current playback speed value is {double}X on audio player screen")
    public void checkCurrentPlaybackSpeedValueIsCorrectOnAudioPlayerScreen(Double playbackSpeedDouble) {
        String playbackSpeed = String.valueOf(playbackSpeedDouble);
        Assert.assertTrue("Current playback speed value is not correct on audio player screen", audioPlayerScreen2.isSpeedOptionSelected(playbackSpeed));
    }

    @When("Set {} sleep timer on sleep timer audiobook screen")
    public void setSleepTimerOnSleepTimerAudiobookScreen(TimerKeys timerSetting) {
        audioPlayerScreen2.setTimer(timerSetting);
    }

    @And("Save chapter length as {string} on audio player screen")
    public void saveChapterLengthOnAudioPlayerScreen(String chapterLength) {
        context.add(chapterLength, audioPlayerScreen2.getChapterLength());
    }

    @Then("Current play time is middle of {string} saved chapter play time")
    public void checkThatCurrentPlayTimeIsMiddleOfSavedChapterPlayTime(String chapterLengthKey) {
        Duration fullChapterLength = context.get(chapterLengthKey);
        long middleOfChapterInSeconds = fullChapterLength.getSeconds() / 2;
        boolean isResultTrue = false;
        isResultTrue = AqualityServices.getConditionalWait().waitFor(() -> {
            long currentTimeDifference = audioPlayerScreen2.getCurrentPlayTime().getSeconds();
            boolean isConditionTrue = currentTimeDifference >= middleOfChapterInSeconds && currentTimeDifference <= middleOfChapterInSeconds + PING_COUNT_OF_SECONDS;
            setDiffForMiddleOfChapter(currentTimeDifference);
            return isConditionTrue;
        }, Duration.ofSeconds(PING_COUNT_OF_SECONDS));

        AqualityServices.getLogger().info("diff between times for middle of chapter - " + diffForMiddleOfChapter);
        Assert.assertTrue("Middle of chapter wasn't opened, middleOfChapterInSeconds - " + middleOfChapterInSeconds + " currentTimeDifference - " + diffForMiddleOfChapter, isResultTrue);

    }

    @Then("Sleep timer is set to endOfChapter on audio player screen")
    public void checkThatSleepTimerIsSetToEndOfChapterOnAudioPLayerScreen() {
        if (AqualityServices.getApplication().getPlatformName() == PlatformName.ANDROID) {
            Assert.assertTrue("Timer value is not correct", audioPlayerScreen2.isTimerSetTo(END_OF_CHAPTER));
        } else if (AqualityServices.getApplication().getPlatformName() == PlatformName.IOS) {
            Assert.assertTrue("Timer value is not correct", audioPlayerScreen2.isTimerEqualTo(audioPlayerScreen2.getChapterLength()));
        }
    }

    private String getChapterName() {
        return RegExUtil.getStringFromFirstGroup(audioPlayerScreen2.getCurrentChapterInfo(), RegEx.AUDIO_BOOK_CURRENT_CHAPTER_TEXT_REGEX);
    }
}
