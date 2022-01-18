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
import screens.audioplayer.AudioPlayerScreen;

import java.time.Duration;

import static constants.localization.application.catalog.TimerKeys.END_OF_CHAPTER;

public class AudioPlayerSteps {
    private static final int PING_COUNT_OF_SECONDS = 6;
    private final AudioPlayerScreen audioPlayerScreen;
    private final ScenarioContext context;
    private long diffBetweenTimePointsWhenForward;
    private long diffBetweenTimePointsWhenBehind;
    private long diffForMiddleOfChapter;

    @Inject
    public AudioPlayerSteps(ScenarioContext context) {
        audioPlayerScreen = AqualityServices.getScreenFactory().getScreen(AudioPlayerScreen.class);
        this.context = context;
    }

    @And("Open chapter list for an audiobook")
    public void openMenuBasedPositionInAudiobook() {
        audioPlayerScreen.openMenu();
    }

    @Then("Download has started and percentage value increased")
    public void downloadHasStarted() {
        if (AqualityServices.getApplication().getPlatformName() == PlatformName.IOS) {
            Integer percentageValue = audioPlayerScreen.getPercentageValue();
            boolean isPercentageValueNew = AqualityServices.getConditionalWait().waitFor(() -> percentageValue != 0, Duration.ofMillis(10000));
            Assert.assertTrue("percentageValue did not change", isPercentageValueNew);
        }
    }

    @And("All chapters loaded")
    public void waitAndCheckAllChaptersLoaded() {
        audioPlayerScreen.waitAndCheckAllChaptersLoaded();
    }

    @When("Select a random chapter that is not equal to the first chapter and save the chapter as {string}")
    public void selectRandomChapterAndSaveChapter(String keyChapter) {
        String chapterName = audioPlayerScreen.selectChapterAndGetText(RandomUtils.nextInt(1, audioPlayerScreen.getCountOfChapters()));
        AqualityServices.getLogger().info("Selected chapter for audiobook: " + chapterName);
        context.add(keyChapter, chapterName);
    }

    @When("Select the {int} chapter and save the chapter as {string}")
    public void selectChapterAndSaveChapter(int chapterNumber, String keyChapter) {
        String chapter = audioPlayerScreen.selectChapterAndGetText(chapterNumber - 1);
        context.add(keyChapter, chapter);
    }

    @Then("Chapter is equal to {string} saved chapter")
    public void checkThatChapterEqualToSavedChapter(String keyChapter) {
        String expectedChapter = context.get(keyChapter);
        Assert.assertTrue(String.format("Chapter is not equal to saved chapter. Expected - %s; actual - %s", expectedChapter, getChapter()), expectedChapter.toLowerCase().equals(getChapter().toLowerCase()));
    }

    @And("I click play button on player screen")
    public void clickPlayButtonOnPlayerScreen() {
        audioPlayerScreen.playBook();
    }

    @When("I click pause button on player screen")
    public void clickPauseButtonOnPlayerScreen() {
        audioPlayerScreen.pauseBook();
    }

    @Then("Pause button is present")
    public void checkPauseButtonIsPresent() {
        Assert.assertTrue("Pause button is not present", audioPlayerScreen.isPauseButtonPresent());
    }

    @Then("Play button is present")
    public void checkPlayButtonIsPresent() {
        Assert.assertTrue("Play button is not present", audioPlayerScreen.isPlayButtonPresent());
    }

    @And("Book is playing")
    public void checkBookIsPlaying() {
        audioPlayerScreen.waitForBookLoading();
        Duration firstTiming = audioPlayerScreen.getCurrentPlayTime();
        Assert.assertTrue("Book is not playing. Error (if present) - " + audioPlayerScreen.getLoadingStatus(), AqualityServices.getConditionalWait().waitFor(() -> !firstTiming.equals(audioPlayerScreen.getCurrentPlayTime())));
    }

    @And("Book is not playing")
    public void checkBookIsNotPlaying() {
        Duration firstTiming = audioPlayerScreen.getCurrentPlayTime();
        //todo tread sleep
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals("Book is still playing", firstTiming, audioPlayerScreen.getCurrentPlayTime());
    }

    @And("I save book play time as {string}")
    public void saveBookPlayTimeAs(String dateKey) {
        context.add(dateKey, audioPlayerScreen.getCurrentPlayTime());
    }

    @And("I skip ahead 15 seconds")
    public void skipAhead() {
        audioPlayerScreen.skipAhead();
    }

    @And("I skip behind 15 seconds")
    public void skipBehind() {
        audioPlayerScreen.skipBehind();
    }

    @And("I return to previous screen for audiobook")
    public void returnToPreviousScreen() {
        audioPlayerScreen.goBack();
    }

    @Then("Playback {string} moves forward by {int} seconds increment")
    public void checkPlaybackTimeAheadMovesForwardBySecondsIncrement(String timeKey, Integer secondsForward) {
        Duration savedDate = context.get(timeKey);
        long secondsBefore = savedDate.getSeconds();
        boolean isResultTrue = false;
        isResultTrue = AqualityServices.getConditionalWait().waitFor(() -> {
            long diffInSeconds = audioPlayerScreen.getCurrentPlayTime().getSeconds() - secondsBefore;
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

    @Then("Playback {string} moves behind by {int} seconds increment")
    public void checkPlaybackTimeAheadMovesBehindBySecondsIncrement(String timeKey, int secondsBehind) {
        Duration savedDate = context.get(timeKey);
        long secondsBefore = savedDate.getSeconds();
        boolean isResultTrue = AqualityServices.getConditionalWait().waitFor(() -> {
            long diffInSeconds = secondsBefore - audioPlayerScreen.getCurrentPlayTime().getSeconds();
            boolean isConditionTrue = diffInSeconds <= secondsBehind && diffInSeconds >= secondsBehind - PING_COUNT_OF_SECONDS;
            setDiffBetweenTimePointsWhenBehind(diffInSeconds);

            return isConditionTrue;
        }, Duration.ofSeconds(PING_COUNT_OF_SECONDS));

        AqualityServices.getLogger().info("diff between times for move behind - " + diffBetweenTimePointsWhenBehind);
        Assert.assertTrue("Date is not moved behind by " + secondsBehind + " seconds, Date is moved behind by " + diffBetweenTimePointsWhenBehind, isResultTrue);
    }

    @And("I move to middle part of chapter")
    public void moveToMiddlePartOfChapter() {
        audioPlayerScreen.moveChapterToMiddle();
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

    @And("I select playback speed {double}X")
    public void selectPlaybackSpeed(Double playbackSpeedDouble) {
        String playbackSpeed = String.valueOf(playbackSpeedDouble);
        audioPlayerScreen.selectPlaybackSpeed(playbackSpeed);
    }

    @And("Current playback speed value is {double}X")
    public void checkCurrentPlaybackSpeedValueIs(Double playbackSpeedDouble) {
        String playbackSpeed = String.valueOf(playbackSpeedDouble);
        Assert.assertTrue("Current playback speed value is not correct", audioPlayerScreen.isSpeedOptionSelected(playbackSpeed));
    }

    @When("I set sleep timer as {}")
    public void setSleepTimerAs(TimerKeys timerSetting) {
        audioPlayerScreen.setTimer(timerSetting);
    }

    @Then("Sleep timer shows time till chapter finish")
    public void checkSleepTimerShowsTimeTillChapterFinish() {
        Assert.assertTrue("Timer value is not correct", audioPlayerScreen.isTimerEqualTo(audioPlayerScreen.getChapterLength()));
    }

    @And("I save chapter length as {string}")
    public void saveChapterLengthAs(String chapterLength) {
        context.add(chapterLength, audioPlayerScreen.getChapterLength());
    }

    @Then("Saved play time {string} is close to middle part of chapter")
    public void checkSavedPlayTimeChapterLengthIsCloseToMiddlePartOfChapter(String chapterLengthKey) {
        Duration fullChapterLength = context.get(chapterLengthKey);
        long middleOfChapterInSeconds = fullChapterLength.getSeconds() / 2;
        boolean isResultTrue = false;
        isResultTrue = AqualityServices.getConditionalWait().waitFor(() -> {
            long currentTimeDifference = audioPlayerScreen.getCurrentPlayTime().getSeconds();
            boolean isConditionTrue = currentTimeDifference >= middleOfChapterInSeconds && currentTimeDifference <= middleOfChapterInSeconds + PING_COUNT_OF_SECONDS;
            setDiffForMiddleOfChapter(currentTimeDifference);
            return isConditionTrue;
        }, Duration.ofSeconds(PING_COUNT_OF_SECONDS));

        AqualityServices.getLogger().info("diff between times for middle of chapter - " + diffForMiddleOfChapter);
        Assert.assertTrue("Middle of chapter wasn't opened, middleOfChapterInSeconds - " + middleOfChapterInSeconds + " currentTimeDifference - " + diffForMiddleOfChapter, isResultTrue);

    }

    @Then("Sleep timer is set to endOfChapter")
    public void checkSleepTimerIsSetTo() {
        if (AqualityServices.getApplication().getPlatformName() == PlatformName.ANDROID) {
            Assert.assertTrue("Timer value is not correct", audioPlayerScreen.isTimerSetTo(END_OF_CHAPTER));
        } else if (AqualityServices.getApplication().getPlatformName() == PlatformName.IOS) {
            Assert.assertTrue("Timer value is not correct", audioPlayerScreen.isTimerEqualTo(audioPlayerScreen.getChapterLength()));
        }
    }

    private String getChapter() {
        return RegExUtil.getStringFromFirstGroup(audioPlayerScreen.getCurrentChapterInfo(), RegEx.AUDIO_BOOK_CURRENT_CHAPTER_TEXT_REGEX);
    }
}
