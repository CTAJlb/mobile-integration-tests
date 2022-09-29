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
import models.android.CatalogBookModel;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import screens.audiobook.audioPlayer.AudioPlayerScreen;

import java.time.Duration;

import static enums.localization.catalog.TimerKeys.END_OF_CHAPTER;

public class AudioPlayerSteps {
    private final AudioPlayerScreen audioPlayerScreen;
    private static final int PING_COUNT_OF_SECONDS = 6;
    private final ScenarioContext context;
    private long diffBetweenTimePointsWhenForward;
    private long diffBetweenTimePointsWhenBehind;

    @Inject
    public AudioPlayerSteps(ScenarioContext context) {
        audioPlayerScreen = AqualityServices.getScreenFactory().getScreen(AudioPlayerScreen.class);
        this.context = context;
    }

    @Then("Audio player screen of book {string} is opened")
    public void isPlayerOpened(String bookInfoKey) {
        CatalogBookModel bookInfo = context.get(bookInfoKey);
        String bookName = bookInfo.getTitle();
        Assert.assertTrue("Player of book " + bookName + " is not opened", audioPlayerScreen.isPlayerOpened(bookName));
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

    @Then("The speed by default is 1.0")
    public void isPlaySpeedNormal() {
        if(AqualityServices.getApplication().getPlatformName()==PlatformName.IOS) {
            Assert.assertEquals("Play speed is not default", "Normal speed.", audioPlayerScreen.getPlaySpeedValue());
        }
        else {
            Assert.assertEquals("Play speed is not default", "1.0x", audioPlayerScreen.getPlaySpeedValue());
        }
    }

    @When("Open playback speed on audio player screen")
    public void openPlaybackSpeed() {
        audioPlayerScreen.openPlaybackSpeed();
    }

    @When("Open sleep timer on audio player screen")
    public void openSleepTimer() {
        audioPlayerScreen.openSleepTimer();
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

    @And("Save chapter time as {string} on audio player screen")
    public void saveChapterTime(String chapterTimeKey) {
        context.add(chapterTimeKey, audioPlayerScreen.getRightTime());
    }

    @And("Skip ahead 15 seconds on audio player screen")
    public void skipAheadOnAudioPlayerScreen() {
        audioPlayerScreen.skipAhead();
    }

    @And("Skip behind 15 seconds on audio player screen")
    public void skipBehindOnAudioPlayerScreen() {
        audioPlayerScreen.skipBehind();
    }

    @When("I stretch slider on the time tracking line forward on audio player screen")
    public void stretchSliderForward() {
        audioPlayerScreen.stretchPlaySliderForward();
    }

    @When("I stretch slider on the time tracking line back on audio player screen")
    public void stretchSliderBack() {
        audioPlayerScreen.stretchPlaySliderBack();
    }

    @Then("Playing time is not equal to {string} on audio playing screen")
    public void compareTimes(String timeKey) {
        Duration time = context.get(timeKey);
        Assert.assertNotEquals("Times are equals", time.getSeconds(), audioPlayerScreen.getLeftTime().getSeconds());
    }

    @Then("Play times {string} and {string} are equals")
    public void playTimesAreEquals(String timeBehindKey, String timeAheadKey) {
        Duration timeBehind = context.get(timeBehindKey);
        Duration timeAhead = context.get(timeAheadKey);
        Assert.assertEquals("Time is changed", timeBehind.getSeconds(), timeAhead.getSeconds());
    }

    @And("Return to previous screen from audio player screen")
    public void returnToPreviousScreenFromAudioPlayerScreen() {
        audioPlayerScreen.returnToPreviousScreen();
    }

    @Then("Playback has been moved forward by {int} seconds from {string} seconds on audio player screen")
    public void checkThatPlaybackHasBeenMovedForwardOnAudioPlayerScreen(Integer secondsForward, String timeKey) {
        Duration savedDate = context.get(timeKey);
        long secondsBefore = savedDate.getSeconds();
        boolean isResultTrue = AqualityServices.getConditionalWait().waitFor(() -> {
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

    @Then("Sleep timer is set to endOfChapter on audio player screen")
    public void checkThatSleepTimerIsSetToEndOfChapterOnAudioPLayerScreen() {
        if (AqualityServices.getApplication().getPlatformName() == PlatformName.ANDROID) {
            Assert.assertTrue("Timer value is not correct", audioPlayerScreen.isTimerSetTo(END_OF_CHAPTER));
        } else if (AqualityServices.getApplication().getPlatformName() == PlatformName.IOS) {
            Assert.assertTrue("Timer value is not correct", audioPlayerScreen.isTimerEqualTo(audioPlayerScreen.getRightTime()));
        }
    }

    @When("I tap on the time bar forward on audio player screen")
    public void tapForward() {
        audioPlayerScreen.tapOnPlayBarForward();
    }

    @When("I tap on the time bar back on audio player screen")
    public void tapBackward() {
        audioPlayerScreen.tapOnPlayBarBackward();
    }

    @Then("Play time is the same with {string} play time before restart on books detail screen")
    public void checkPlayTimeAfterReload(String dateKey) {
        Duration playTimeBefore = context.get(dateKey);
        Duration playTimeAfter = audioPlayerScreen.getLeftTime();
        Assert.assertEquals("Play time is different. ", playTimeBefore.getSeconds(), playTimeAfter.getSeconds());
    }

    @And("Listen a chapter on audio player screen")
    public void waitTheEndOfChapter() {
        audioPlayerScreen.stretchPlaySliderForward();
        AqualityServices.getConditionalWait().waitFor(()-> {
            boolean isNull = false;
            long timer = audioPlayerScreen.getRightTime().getSeconds();
            if(timer==0 || audioPlayerScreen.isPlayButtonPresent())
                isNull = true;
            return  isNull;
        });
    }

    @When("Save the name of chapter as {string} on audio player screen")
    public void saveChapterName(String chapterNameKey) {
        String chapterName = audioPlayerScreen.getChapterName();
        context.add(chapterNameKey, chapterName);
    }

    @Then("Line for time remaining is displayed on audio player screen")
    public void isLineRemainingDisplayed() {
        Assert.assertTrue("Line for time remaining is not displayed", audioPlayerScreen.isLineRemainingDisplayed());
    }

    @Then("Next chapter play automatically and chapter name is not {string} on audio player screen")
    public void isChapterPlaying(String chapterNameKey) {
        String chapterName = context.get(chapterNameKey);
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(audioPlayerScreen.isPlayButtonPresent()).as("Play button is not displayed").isTrue();
        softAssertions.assertThat(audioPlayerScreen.getChapterName().equals(chapterName)).as("Chapter name does not change").isFalse();
    }

    @Then("Chapter number is {string} on audio player screen")
    public void checkChapterNumber(String chapterNumberKey) {
        int expectedChapterNumber = context.get(chapterNumberKey);
        String chapterName = audioPlayerScreen.getChapterName();
        int actualChapterNumber = Integer.parseInt(StringUtils.substringBetween(chapterName, "file ", " of"));
        Assert.assertEquals("Chapter number is not " + expectedChapterNumber, expectedChapterNumber, actualChapterNumber);
    }
}