package screens.epubreader.ios;

import aquality.appium.mobile.actions.SwipeDirection;
import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import aquality.selenium.core.elements.ElementState;
import aquality.selenium.core.logging.Logger;
import constants.RegEx;
import constants.application.attributes.IosAttributes;
import framework.utilities.CoordinatesClickUtils;
import framework.utilities.RegExUtil;
import framework.utilities.swipe.SwipeElementUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import screens.epubreader.EpubReaderScreen;
import screens.epubtableofcontents.EpubTableOfContentsScreen;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ScreenType(platform = PlatformName.IOS)
public class IosEpubReaderScreen extends EpubReaderScreen {
    private static final String EPUB_CONTENT_IFRAME = "epubContentIframe";
    private static final String CHAPTER_ITEM_LOC = "//XCUIElementTypeTable//XCUIElementTypeCell//XCUIElementTypeStaticText[@name=\"%1$s\"]";

    private final ILabel lblBookName =
            getElementFactory().getLabel(By.xpath("//XCUIElementTypeStaticText[1]"), "Book Cover", ElementState.EXISTS_IN_ANY_STATE);
    private final ILabel lblPageNumber =
            getElementFactory().getLabel(By.xpath("//XCUIElementTypeStaticText[1]/parent::XCUIElementTypeOther/parent::XCUIElementTypeOther/XCUIElementTypeOther[3]/XCUIElementTypeStaticText"), "Page Number");
    private final ILabel lblPage =
            getElementFactory().getLabel(By.xpath("//XCUIElementTypeWebView"), "Page View");
    private final IButton btnFontSettings = getElementFactory().getButton(
            By.xpath("//XCUIElementTypeButton[@name=\"Toggle reader settings\"]"), "Chapters");
    private final IButton btnChapters =
            getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@name=\"Table of contents\"]"), "Chapters");

    public IosEpubReaderScreen() {
        super(By.xpath("//*[contains(@name,\"Page\")]"));
    }

    private void checkThatBookOpenedAndOpenMenus() {
        Assert.assertTrue("Book page does not displayed", AqualityServices.getConditionalWait().waitFor(() -> state().isDisplayed() || btnFontSettings.state().isDisplayed()));
        if (!btnFontSettings.state().isDisplayed()) {
            CoordinatesClickUtils.clickAtCenterOfScreen();
        }
    }

    @Override
    public String getBookName() {
        String text = lblBookName.getText();
        AqualityServices.getLogger().info("Book name - " + text);
        return text;
    }

    @Override
    public String getChapterName() {
        //only for android
        return null;
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
        Point upperLeftCorner = lblPage.getElement().getLocation();
        Point center = lblPage.getElement().getCenter();
        Dimension dimensions = lblPage.getElement().getSize();
        action.tap(PointOption.point(upperLeftCorner.x + dimensions.width - 1, center.y)).perform();
    }

    @Override
    public String getPageNumberInfo() {
        if (btnFontSettings.state().isDisplayed()) {
            CoordinatesClickUtils.clickAtCenterOfScreen();
        }
        lblPageNumber.state().waitForDisplayed();
        return lblPageNumber.getAttribute(IosAttributes.NAME);
    }

    @Override
    public List<String> getListOfChapters() {
        if (!btnFontSettings.state().isDisplayed()) {
            CoordinatesClickUtils.clickAtCenterOfScreen();
        }
        btnChapters.click();
        EpubTableOfContentsScreen epubTableOfContentsScreen = AqualityServices.getScreenFactory().getScreen(EpubTableOfContentsScreen.class);
        epubTableOfContentsScreen.state().waitForExist();
        List<String> bookNames = epubTableOfContentsScreen.getListOfBookChapters();
        AqualityServices.getApplication().getDriver().navigate().back();
        AqualityServices.getLogger().info("Found chapters - " + bookNames.stream().map(Object::toString).collect(Collectors.joining(", ")));
        AqualityServices.getLogger().info("countOfChapters-" + bookNames.size());
        return bookNames;
    }

    @Override
    public void openChapter(String chapter) {
        if (!btnFontSettings.state().isDisplayed()) {
            CoordinatesClickUtils.clickAtCenterOfScreen();
        }
        btnChapters.click();
        IButton button = getElementFactory().getButton(By.xpath(String.format(CHAPTER_ITEM_LOC, chapter)), chapter);
        button.getTouchActions().scrollToElement(SwipeDirection.DOWN);
        button.click();

        checkThatBookOpenedAndOpenMenus();
    }

    @Override
    public void openFontSettings() {
        checkThatBookOpenedAndOpenMenus();
        btnFontSettings.click();
    }

    @Override
    public void openTableOfContents() {
        checkThatBookOpenedAndOpenMenus();
        btnChapters.click();
    }

    @Override
    public double getFontSize() {
        return RegExUtil.getDoubleFromFirstMatchGroup(getBookSource(), RegEx.FONT_SIZE_REGEX_IOS);
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
        driver.switchTo().frame(EPUB_CONTENT_IFRAME);
        String frameSource = driver.getPageSource();
        logger.info(frameSource);
        driver.switchTo().defaultContent();
        driver.context((String) contextNames.toArray()[0]);
        return frameSource;
    }

    @Override
    public String getFontName() {
        return getReaderInfo(RegEx.FONT_NAME_REGEX_IOS);
    }

    @Override
    public String getFontColor() {
        return getReaderInfo(RegEx.FONT_COLOR_REGEX);
    }

    @Override
    public String getFontAndBackgroundColor() {
        return getReaderInfo(RegEx.BACKGROUND_COLOR_REGEX_IOS);
    }

    @Override
    public void waitForBookLoading() {
    }

    @Override
    public void returnToPreviousScreen() {
        if (!btnFontSettings.state().isDisplayed()) {
            CoordinatesClickUtils.clickAtCenterOfScreen();
        }
        AqualityServices.getElementFactory().getButton(By.xpath(String.format("//XCUIElementTypeButton[1]")), "arrowForComeBack").click();
    }

    private String getReaderInfo(String regex) {
        return RegExUtil.getStringFromFirstGroup(getBookSource(), regex);
    }
}
