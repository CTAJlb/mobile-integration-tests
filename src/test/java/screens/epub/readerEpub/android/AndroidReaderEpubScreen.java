package screens.epub.readerEpub.android;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import aquality.selenium.core.logging.Logger;
import constants.RegEx;
import framework.utilities.CoordinatesClickUtils;
import framework.utilities.RegExUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import screens.epub.readerEpub.ReaderEpubScreen;
import screens.epub.navigationBarEpub.NavigationBarEpubScreen;

import java.util.Set;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidReaderEpubScreen extends ReaderEpubScreen {
    private final ILabel lblBookName =
            getElementFactory().getLabel(By.xpath("//android.widget.TextView[contains(@resource-id,\"titleText\")]"), "lblBookName");
    private final ILabel lblPageNumber =
            getElementFactory().getLabel(By.xpath("//android.widget.TextView[contains(@resource-id,\"reader2_position_page\")]"), "lblPageNumber");
    private final ILabel lblPage =
            getElementFactory().getLabel(By.xpath("//android.webkit.WebView[1]"), "lblPage");
    private final ILabel lblChapterName =
            getElementFactory().getLabel(By.xpath("//android.widget.TextView[contains(@resource-id,\"reader2_position_title\")]"), "lblChapterName");

    public AndroidReaderEpubScreen() {
        super(By.xpath("//android.view.ViewGroup[contains(@resource-id,\"readerToolbar\")]"));
        navigationBarEpubScreen = AqualityServices.getScreenFactory().getScreen(NavigationBarEpubScreen.class);
    }

    @Override
    public String getBookName() {
        hideNavigationBar();
        String text = lblBookName.getText();
        AqualityServices.getLogger().info("Book name on epub reader screen - " + text);
        return text;
    }

    @Override
    public NavigationBarEpubScreen getNavigationBarEpubScreen() {
        return navigationBarEpubScreen;
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
    public String getChapterName() {
        return lblChapterName.getText();
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
        int width = lblPage.getElement().getSize().width;
        action.tap(PointOption.point(width - 10, height / 2)).perform();
    }

    @Override
    public String getPageNumber() {
        String pageNumberRegEx = lblPageNumber.getText();
        return RegExUtil.getStringFromFirstGroup(pageNumberRegEx, RegEx.PAGE_NUMBER_REGEX_FOR_ANDROID);
    }

    @Override
    public double getFontSize() {
        return RegExUtil.getDoubleFromFirstGroup(getBookSource(), RegEx.FONT_SIZE_REGEX_ANDROID);
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
    public String getBackgroundColor() {
        return getReaderInfo(RegEx.BACKGROUND_COLOR_REGEX_ANDROID);
    }

    private String getReaderInfo(String regex) {
        return RegExUtil.getStringFromFirstGroup(getBookSource(), regex);
    }
}
