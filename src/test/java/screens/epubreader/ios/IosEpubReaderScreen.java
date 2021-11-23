package screens.epubreader.ios;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import aquality.selenium.core.logging.Logger;
import constants.RegEx;
import constants.application.attributes.IosAttributes;
import framework.utilities.CoordinatesClickUtils;
import framework.utilities.RegExUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import screens.epubreader.EpubReaderScreen;
import screens.menuEpub.NavigationBarEpubScreen;
import screens.tocEpub.TocEpubScreen;

import java.util.Set;

@ScreenType(platform = PlatformName.IOS)
public class IosEpubReaderScreen extends EpubReaderScreen {
    private final NavigationBarEpubScreen navigationBarEpubScreen;
    private final TocEpubScreen tocEpubScreen;

    private final ILabel lblBookName =
            getElementFactory().getLabel(By.xpath("//XCUIElementTypeOther/XCUIElementTypeStaticText[1]"), "lblBookName");
    private final ILabel lblPageNumberAndChapterName =
            getElementFactory().getLabel(By.xpath("//XCUIElementTypeOther/XCUIElementTypeStaticText[contains(@name, \"Page\")]"), "lblPageNumberAndChapterName");
    private final ILabel lblPage =
            getElementFactory().getLabel(By.xpath("//XCUIElementTypeWebView"), "lblPage");

    public IosEpubReaderScreen() {
        super(By.xpath("//*[contains(@name,\"Page\")]"));
        navigationBarEpubScreen = AqualityServices.getScreenFactory().getScreen(NavigationBarEpubScreen.class);
        tocEpubScreen = AqualityServices.getScreenFactory().getScreen(TocEpubScreen.class);
    }

    @Override
    public void openNavigationBar() {
        if (!navigationBarEpubScreen.state().isDisplayed()) {
            CoordinatesClickUtils.clickAtCenterOfScreen();
        }
    }

    @Override
    public void hideNavigationBar() {
        if (navigationBarEpubScreen.state().isDisplayed()) {
            CoordinatesClickUtils.clickAtCenterOfScreen();
        }
    }

    @Override
    public String getBookName() {
        String text = lblBookName.getAttribute(IosAttributes.NAME);
        AqualityServices.getLogger().info("Book name on epub reader screen - " + text);
        return text;
    }

    @Override
    public String getChapterName() {
        String pageNumberAndChapterNameRegEx = lblPageNumberAndChapterName.getAttribute(IosAttributes.NAME);
        pageNumberAndChapterNameRegEx = deleteBracketsFromText(pageNumberAndChapterNameRegEx);
        return RegExUtil.getStringFromThirdGroup(pageNumberAndChapterNameRegEx, RegEx.PAGE_NUMBER_AND_CHAPTER_NAME_REGEX_FOR_IOS);
    }

    @Override
    public void clickLeftCorner() {
        TouchAction action = new TouchAction(AqualityServices.getApplication().getDriver());
        action.tap(PointOption.point(20, lblPage.getElement().getCenter().y)).perform();
    }

    @Override
    public void clickRightCorner() {
        TouchAction action = new TouchAction(AqualityServices.getApplication().getDriver());
        action.tap(PointOption.point(lblPage.getElement().getSize().getWidth(), lblPage.getElement().getCenter().y)).perform();
    }

    @Override
    public String getPageNumber() {
        String pageNumberAndChapterNameRegEx = lblPageNumberAndChapterName.getAttribute(IosAttributes.NAME);
        pageNumberAndChapterNameRegEx = deleteBracketsFromText(pageNumberAndChapterNameRegEx);
        return RegExUtil.getStringFromFirstGroup(pageNumberAndChapterNameRegEx, RegEx.PAGE_NUMBER_AND_CHAPTER_NAME_REGEX_FOR_IOS);
    }

    private static String deleteBracketsFromText(String text) {
        text = text.replaceAll("\\(", "");
        text = text.replaceAll("\\)", "");
        return text;
    }

    @Override
    public void openFontSettings() {
        openNavigationBar();
        navigationBarEpubScreen.clickFontSettingsButton();
    }

    @Override
    public void openToc() {
        openNavigationBar();
        navigationBarEpubScreen.clickTOCButton();
    }

    @Override
    public double getFontSize() {
        return RegExUtil.getDoubleFromFirstGroup(getBookSource(), RegEx.FONT_SIZE_REGEX_IOS);
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
        String pageSource = driver.getPageSource();
        AqualityServices.getLogger().info("contextNames.toArray()[1]PageSource-" + pageSource);
        driver.context((String) contextNames.toArray()[0]);
        return pageSource;
    }

    @Override
    public String getFontName() {
        return getReaderInfo(RegEx.FONT_NAME_REGEX_IOS);
    }

    @Override
    public String getBackgroundColor() {
        return getReaderInfo(RegEx.BACKGROUND_COLOR_REGEX_IOS);
    }

    @Override
    public void returnToPreviousScreen() {
        openNavigationBar();
        navigationBarEpubScreen.returnToPreviousScreen();
    }

    private String getReaderInfo(String regex) {
        return RegExUtil.getStringFromFirstGroup(getBookSource(), regex);
    }
}
