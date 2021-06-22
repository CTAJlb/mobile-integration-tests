package screens.epubreader.android;

import aquality.appium.mobile.actions.SwipeDirection;
import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import aquality.selenium.core.logging.Logger;
import constants.RegEx;
import constants.application.timeouts.AuthorizationTimeouts;
import constants.application.timeouts.BooksTimeouts;
import framework.utilities.RegExUtil;
import framework.utilities.swipe.SwipeElementUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import screens.epubreader.EpubReaderScreen;
import screens.epubtableofcontents.EpubTableOfContentsScreen;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidEpubReaderScreen extends EpubReaderScreen {
    public static final String EPUB_CONTENT_IFRAME = "epubContentIframe";
    private final ILabel lblBookName =
            getElementFactory().getLabel(By.xpath("//android.widget.TextView[1]"), "Book Cover");
    private final ILabel lblPageNumber =
            getElementFactory().getLabel(By.xpath("//android.widget.TextView[contains(@resource-id,\"reader2_position_page\")]"), "Page Number");
    private final ILabel lblPage =
            getElementFactory().getLabel(By.xpath("//android.webkit.WebView"), "Page View");
    private final IButton btnFontSettings = getElementFactory().getButton(By.xpath("//android.widget.TextView[contains(@resource-id,\"readerMenuSettings\")]"), "FontSettings");
    private final IButton btnChapters =
            getElementFactory().getButton(By.xpath("//android.widget.TextView[contains(@resource-id,\"readerMenuTOC\")]"), "Chapters");
    private final ILabel lblChapterName =
            getElementFactory().getLabel(By.xpath("//android.widget.TextView[contains(@resource-id,\"reader2_position_title\")]"), "Chapter Name");

    public AndroidEpubReaderScreen() {
        super(By.id("//android.view.View[@resource-id=\"reflowable-book-frame\"]"));
    }

    @Override
    public String getBookName() {
        String text = lblBookName.getText();
        AqualityServices.getLogger().info("Book name - " + text);
        return text;
    }

    @Override
    public String getChapterName() {
        return lblChapterName.getText();
    }

    @Override
    public boolean isBookNamePresent() {
        return lblBookName.state().waitForDisplayed();
    }

    @Override
    public void swipeFromLeftToRight() {
        SwipeElementUtils.swipeFromLeftToRight(lblPage);
    }

    @Override
    public void swipeFromRightToLeft() {
        SwipeElementUtils.swipeFromRightToLeft(lblPage);
    }

    @Override
    public void clickLeftCorner() {
        TouchAction action = new TouchAction(AqualityServices.getApplication().getDriver());
        action.tap(PointOption.point(0, lblPage.getElement().getCenter().y)).perform();
    }

    @Override
    public void clickRightCorner() {
        TouchAction action = new TouchAction(AqualityServices.getApplication().getDriver());
        int height = lblPage.getElement().getSize().height;
        int width =  lblPage.getElement().getSize().width;
        /*Point upperLeftCorner = lblPage.getElement().getLocation();
        Point center = lblPage.getElement().getCenter();
        Dimension dimensions = lblPage.getElement().getSize();
        action.tap(PointOption.point(upperLeftCorner.x + dimensions.width - 1, center.y)).perform();*/
        action.tap(PointOption.point(width - 10, height/2)).perform();
    }

    @Override
    public String getPageNumberInfo() {
        lblPageNumber.state().waitForDisplayed(Duration.ofMillis(BooksTimeouts.TIMEOUT_BOOK_CHANGES_STATUS.getTimeoutMillis()));
        return lblPageNumber.getText();
    }

    @Override
    public List<String> getListOfChapters() {
        btnChapters.click();
        EpubTableOfContentsScreen epubTableOfContentsScreen = AqualityServices.getScreenFactory().getScreen(EpubTableOfContentsScreen.class);
        epubTableOfContentsScreen.state().waitForExist();
        List<String> bookNames = epubTableOfContentsScreen.getListOfBookChapters();
        AqualityServices.getApplication().getDriver().navigate().back();
        AqualityServices.getLogger().info("Found chapters - " + bookNames.stream().map(Object::toString).collect(Collectors.joining(", ")));
        return bookNames;
    }

    @Override
    public void openChapter(String chapter) {
        btnChapters.click();
        IButton button = getElementFactory().getButton(By.xpath("//android.widget.TextView[contains(@resource-id,\"chapterTitle\") and @text=\"" + chapter + "\"]"), chapter);
        button.state().waitForDisplayed();
        button.getTouchActions().scrollToElement(SwipeDirection.DOWN);
        button.click();
    }

    @Override
    public void openFontSettings() {
        btnFontSettings.click();
    }

    @Override
    public void openTableOfContents() {
        btnChapters.click();
    }

    @Override
    public double getFontSize() {
        return RegExUtil.getDoubleFromFirstMatchGroup(getBookSource(), RegEx.FONT_SIZE_REGEX_ANDROID);
    }

    private String getBookSource() {
        AppiumDriver driver = AqualityServices.getApplication().getDriver();
        Logger logger = AqualityServices.getLogger();
        AqualityServices.getConditionalWait().waitFor(() -> {
            Set<String> contextNames = driver.getContextHandles();
            for (String contextName : contextNames) {
                logger.info("context - " + contextName);
            }
            return contextNames.size() > 1;
        });
        Set<String> contextNames = driver.getContextHandles();
        driver.context((String) contextNames.toArray()[1]);
        logger.info("pageSourceWithSettingsStart+++++++++++++++++++++++++");
        String pageSourceWithSettings = driver.getPageSource();
        logger.info(pageSourceWithSettings);
        logger.info("pageSourceWithSettingsEnd+++++++++++++++++++++++++");
        driver.switchTo().defaultContent();
        driver.context((String) contextNames.toArray()[0]);
        return pageSourceWithSettings;
    }

    @Override
    public String getFontName() {
        return getReaderInfo(RegEx.FONT_NAME_REGEX_ANDROID);
    }

    @Override
    public String getFontColor() {
        //only for IOS
        return "";
    }

    @Override
    public String getFontAndBackgroundColor() {
        return getReaderInfo(RegEx.FONT_AND_BACKGROUND_ANDROID);
    }

    @Override
    public void waitForBookLoading() {
        lblPageNumber.state().waitForDisplayed(Duration.ofMillis(AuthorizationTimeouts.USER_LOGGED_OUT.getTimeoutMillis()));
    }

    @Override
    public void returnToPreviousScreen() {

    }

    private String getReaderInfo(String regex) {
        return RegExUtil.getStringFromFirstGroup(getBookSource(), regex);
    }
}
