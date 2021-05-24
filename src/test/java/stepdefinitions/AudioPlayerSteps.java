package stepdefinitions;

import aquality.appium.mobile.application.AqualityServices;
import com.google.inject.Inject;
import constants.RegEx;
import constants.application.timeouts.BooksTimeouts;
import constants.localization.application.catalog.TimerKeys;
import framework.utilities.DateUtils;
import framework.utilities.RegExUtil;
import framework.utilities.ScenarioContext;
import framework.utilities.SmartRandomUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import screens.audioplayer.AudioPlayerScreen;

import java.time.Duration;

public class AudioPlayerSteps {
    private static final int PING_COUNT_OF_SECONDS = 6;
    private final AudioPlayerScreen audioPlayerScreen;
    private final ScenarioContext context;

    @Inject
    public AudioPlayerSteps(ScenarioContext context) {
        audioPlayerScreen = AqualityServices.getScreenFactory().getScreen(AudioPlayerScreen.class);
        this.context = context;
    }

    @And("Remember current book chapter in {string}")
    public void saveCurrentBookChapterInContextByKey(String key) {
        audioPlayerScreen.waitForLoadingDisappearing();
        context.add(key, getChapterNumber());
    }

    @And("Open the menu-based position in the audiobook")
    @When("I open the menu-based position in the audiobook")
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

    @When("I select the chapter not equal to remembered {string} and remember selected chapter as {string}")
    public void selectChapterIsNotEqualToSavedInContextByKeyAndSaveSelectedChapter(String keySavedChapter, String keySelectedChapter) {
        int savedChapterNumber = context.get(keySavedChapter);
        int totalChapterCount = audioPlayerScreen.getCountOfChapters();
        int chapterToSelect =
                SmartRandomUtils.getRandomWithExclusion(0, totalChapterCount, savedChapterNumber - 1, totalChapterCount);

        audioPlayerScreen.selectChapterNumber(chapterToSelect);
        context.add(keySelectedChapter, chapterToSelect + 1);
    }

    @When("I select {int} chapter and remember selected chapter as {string}")
    public void selectSecondChapterAndSaveSelectedChapter(int chapterToSelect, String keySelectedChapter) {
        audioPlayerScreen.selectChapterNumber(chapterToSelect);
        audioPlayerScreen.waitForLoadingDisappearing();
        context.add(keySelectedChapter, chapterToSelect);
    }

    @Then("I check that current chapter equal to remembered {string}")
    public void selectChapterIsNotEqualToSavedInContextByKeyAndSaveSelectedChapter(String keyCurrentChapter) {
        int expectedChapterName = context.get(keyCurrentChapter);
        Assert.assertTrue(String.format("Current chapter number is not correct. Expected - %d; actual - %d", expectedChapterName, getChapterNumber()), AqualityServices.getConditionalWait().waitFor(() -> getChapterNumber() == expectedChapterName));
        AqualityServices.getConditionalWait().waitFor(() -> false, Duration.ofMillis(BooksTimeouts.SYSTEM_CHANGES_STATUS.getTimeoutMillis()));
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
        Assert.assertEquals("Book is still playing", audioPlayerScreen.getCurrentPlayTime(), firstTiming);
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

    @Then("Playback {string} moves forward by {int} seconds increment")
    public void checkPlaybackTimeAheadMovesForwardBySecondsIncrement(String timeKey, int secondsDiff) {
        Duration savedDate = context.get(timeKey);
        long secondsBefore = savedDate.getSeconds();
        AqualityServices.getConditionalWait().waitFor(() -> {
            long diffInSeconds = audioPlayerScreen.getCurrentPlayTime().getSeconds() - secondsBefore;
            return diffInSeconds >= secondsDiff && diffInSeconds <= secondsDiff + PING_COUNT_OF_SECONDS;
        });
        long diffInSeconds = audioPlayerScreen.getCurrentPlayTime().getSeconds() - secondsBefore;
        AqualityServices.getLogger().info("diff between times - " + diffInSeconds);
        Assert.assertTrue("Date is not moved forward by " + secondsDiff + " seconds", diffInSeconds >= secondsDiff && diffInSeconds <= secondsDiff + PING_COUNT_OF_SECONDS);
    }

    @Then("Playback {string} moves behind by {int} seconds increment")
    public void checkPlaybackTimeAheadMovesBehindBySecondsIncrement(String timeKey, int secondsDiff) {
        Duration savedDate = context.get(timeKey);
        AqualityServices.getConditionalWait().waitFor(() -> {
            long diffInSec = savedDate.getSeconds() - audioPlayerScreen.getCurrentPlayTime().getSeconds();
            return diffInSec > secondsDiff - PING_COUNT_OF_SECONDS && diffInSec <= secondsDiff;
        });
        long diffInSec = savedDate.getSeconds() - audioPlayerScreen.getCurrentPlayTime().getSeconds();
        AqualityServices.getLogger().info("diff between times - " + diffInSec);
        Assert.assertTrue("Date is not moved behind by " + secondsDiff + " seconds", diffInSec > secondsDiff - PING_COUNT_OF_SECONDS && diffInSec <= secondsDiff);
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

    private int getChapterNumber() {
        return Integer.parseInt(RegExUtil.getStringFromFirstGroup(audioPlayerScreen.getCurrentChapterInfo(), RegEx.AUDIO_BOOK_CURRENT_CHAPTER_REGEX));
    }
}
