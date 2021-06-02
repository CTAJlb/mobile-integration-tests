package stepdefinitions;

import aquality.appium.mobile.application.AqualityServices;
import com.google.inject.Inject;
import constants.RegEx;
import constants.localization.application.catalog.TimerKeys;
import framework.utilities.DateUtils;
import framework.utilities.RegExUtil;
import framework.utilities.ScenarioContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Assert;
import screens.audioplayer.AudioPlayerScreen;

import java.time.Duration;

public class AudioPlayerSteps {
    private static final int PING_COUNT_OF_SECONDS = 6;
    private final AudioPlayerScreen audioPlayerScreen;
    private final ScenarioContext context;
    private long diffBetweenTimePointsWhenForward;
    private long diffBetweenTimePointsWhenBehind;

    @Inject
    public AudioPlayerSteps(ScenarioContext context) {
        audioPlayerScreen = AqualityServices.getScreenFactory().getScreen(AudioPlayerScreen.class);
        this.context = context;
    }

    @And("Open chapter list for an audiobook")
    public void openMenuBasedPositionInAudiobook() {
        audioPlayerScreen.openMenu();
    }

    @Then("I check that chapters are visible")
    public void checkThatChaptersAreVisible() {
        audioPlayerScreen.checkThatChaptersVisible();
    }

    @And("Wait and check that all loaders are disappeared")
    public void waitAndCheckAllLoadersDisappeared() {
        audioPlayerScreen.waitAndCheckAllLoadersDisappeared();
    }

    @When("I select the chapter not equal to first chapter and remember selected chapter text as {string}")
    public void selectChapterIsNotEqualToSavedInContextByKeyAndSaveSelectedChapter(String keySelectedChapterText) {
        int totalChapterCount = audioPlayerScreen.getCountOfChapters();
        int chapterToSelect = RandomUtils.nextInt(2, totalChapterCount + 1);
        String chapterText = audioPlayerScreen.selectChapterAndGetText(chapterToSelect);
        context.add(keySelectedChapterText, chapterText);
    }

    @When("I select {int} chapter and remember selected chapter as {string}")
    public void selectSecondChapterAndSaveSelectedChapter(int chapterToSelect, String keySelectedChapter) {
        audioPlayerScreen.selectChapterAndGetText(chapterToSelect);
        audioPlayerScreen.waitForLoadingDisappearing();
        context.add(keySelectedChapter, chapterToSelect);
    }

    @Then("I check that current chapter text equal to remembered {string}")
    public void checkThatCurrentChapterEqualSavedChapter(String keySelectedChapterText) {
        String expectedChapterText = context.get(keySelectedChapterText);
        Assert.assertTrue(String.format("Current chapter text is not correct. Expected - %s; actual - %s", expectedChapterText, getChapterText()), AqualityServices.getConditionalWait().waitFor(() -> getChapterText().toLowerCase().equals(expectedChapterText.toLowerCase())));
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

    @Then("Book is playing")
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

    @When("I save book play time as {string}")
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
    public void checkPlaybackTimeAheadMovesForwardBySecondsIncrement(String timeKey, int secondsForward) {
        Duration savedDate = context.get(timeKey);
        long secondsBefore = savedDate.getSeconds();
        boolean isResultTrue = false;
        isResultTrue = AqualityServices.getConditionalWait().waitFor(() -> {
            long diffInSeconds = audioPlayerScreen.getCurrentPlayTime().getSeconds() - secondsBefore;
            boolean isConditionTrue = diffInSeconds >= secondsForward && diffInSeconds <= secondsForward + PING_COUNT_OF_SECONDS;
            setDiffBetweenTimePointsWhenForward(diffInSeconds);

            return isConditionTrue;
        }, Duration.ofSeconds(6));
        AqualityServices.getLogger().info("diff between times - " + diffBetweenTimePointsWhenForward);
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
            boolean isConditionTrue = false;
            isConditionTrue = diffInSeconds <= secondsBehind && diffInSeconds >= secondsBehind - PING_COUNT_OF_SECONDS;
            setDiffBetweenTimePointsWhenBehind(diffInSeconds);

            return isConditionTrue;
        }, Duration.ofSeconds(6));

        AqualityServices.getLogger().info("diff between times - " + diffBetweenTimePointsWhenBehind);
        Assert.assertTrue("Date is not moved behind by " + secondsBehind + " seconds, Date is moved behind by " + diffBetweenTimePointsWhenBehind, isResultTrue);
    }

    @And("I move to middle part of chapter")
    public void moveToMiddlePartOfChapter() {
        audioPlayerScreen.moveChapterToMiddle();
    }

    @Then("Play time is close to middle part of chapter")
    public void checkPlayTimeIsCloseToMiddlePartOfChapter() {
        Duration zeroDate = DateUtils.getDuration("00:00:00");
        Duration fullChapterLength = audioPlayerScreen.getChapterLength();
        long middleOfChapterImMillis = (fullChapterLength.getSeconds() - zeroDate.getSeconds()) / 2;
        AqualityServices.getLogger().info("middle im mills " + middleOfChapterImMillis);
        Assert.assertTrue("Middle of chapter wasn't opened", AqualityServices.getConditionalWait().waitFor(() -> {
            long currentTimeDifference = audioPlayerScreen.getCurrentPlayTime().getSeconds() - zeroDate.getSeconds();
            AqualityServices.getLogger().info("current time in mills " + currentTimeDifference);
            return middleOfChapterImMillis - 10 < currentTimeDifference && currentTimeDifference < middleOfChapterImMillis + 10;
        }));
    }

    @And("I wait for {int} seconds")
    public void waitForSeconds(int secondsCount) {
        AqualityServices.getConditionalWait().waitFor(() -> false, Duration.ofSeconds(secondsCount));
    }

    @And("I select playback speed {double}X")
    public void selectPlaybackSpeed(double playbackSpeed) {
        audioPlayerScreen.selectPlaybackSpeed(playbackSpeed);
    }

    @And("Current playback speed value is {double}X")
    public void checkCurrentPlaybackSpeedValueIs(double playbackSpeed) {
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
        Assert.assertTrue("Middle of chapter wasn't opened", AqualityServices.getConditionalWait().waitFor(() -> {
            long currentTimeDifference = audioPlayerScreen.getCurrentPlayTime().getSeconds();
            return middleOfChapterInSeconds - 10 < currentTimeDifference && currentTimeDifference < middleOfChapterInSeconds + 10;
        }));
    }

    @Then("Sleep timer is set to {}")
    public void checkSleepTimerIsSetTo(TimerKeys timerSetting) {
        Assert.assertTrue("Timer value is not correct", audioPlayerScreen.isTimerSetTo(timerSetting));
    }

    private String getChapterText() {
        return RegExUtil.getStringFromFirstGroup(audioPlayerScreen.getCurrentChapterInfo(), RegEx.AUDIO_BOOK_CURRENT_CHAPTER_TEXT_REGEX);
    }
}
