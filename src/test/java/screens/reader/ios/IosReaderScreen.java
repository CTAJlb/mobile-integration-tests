package screens.reader.ios;

import aquality.appium.mobile.actions.SwipeDirection;
import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.ElementType;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import aquality.selenium.core.elements.interfaces.IElement;
import aquality.selenium.core.logging.Logger;
import constants.RegEx;
import framework.utilities.RegExUtil;
import framework.utilities.swipe.SwipeElementUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import screens.reader.ReaderScreen;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ScreenType(platform = PlatformName.IOS)
public class IosReaderScreen extends ReaderScreen {
    private final ILabel lblBookName =
            getElementFactory().getLabel(By.xpath("//XCUIElementTypeWebView/parent::XCUIElementTypeOther"
                    + "/following-sibling::XCUIElementTypeOther[1]/XCUIElementTypeStaticText "), "Book Name");
    private final ILabel lblPageNumber =
            getElementFactory().getLabel(By.xpath("//XCUIElementTypeWebView/parent::XCUIElementTypeOther"
                    + "/following-sibling::XCUIElementTypeOther[2]/XCUIElementTypeStaticText "), "Page Number Info");
    private final ILabel lblPage =
            getElementFactory().getLabel(By.xpath(""), "Page View");
    private final IButton btnChapters =
            getElementFactory().getButton(By.xpath(""), "Chapters");
    private final ILabel lblTable =
            getElementFactory().getLabel(By.id(""), "Table");
    private final IButton btnFontSettings = getElementFactory().getButton(By.id(""), "Chapters");
    public static final String EPUB_CONTENT_IFRAME = "epubContentIframe";

    public IosReaderScreen() {
        super(By.xpath(""));
    }

    @Override
    public String getBookName() {
        String text = lblBookName.getText();
        AqualityServices.getLogger().info("Book name - " + text);
        return text;
    }

    @Override
    public void swipeFromLeftToRight() {
        Rectangle rectangle = lblPage.getElement().getRect();
        lblPage.getTouchActions().swipe(new Point(rectangle.x + rectangle.width - 1, lblPage.getElement().getCenter().y));
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
        return lblPageNumber.getText();
    }

    @Override
    public Set<String> getListOfChapters() {
        btnChapters.click();
        lblTable.state().waitForExist();
        List<String> listOfChapters = getChapters().stream().map(IElement::getText).collect(Collectors.toList());
        Set<String> bookNames = new HashSet<>();
        do {
            bookNames.addAll(listOfChapters);
            SwipeElementUtils.swipeThroughEntireElementUp(lblTable);
            listOfChapters = getChapters().stream().map(IElement::getText).collect(Collectors.toList());
        } while (!bookNames.containsAll(listOfChapters));
        AqualityServices.getApplication().getDriver().navigate().back();
        AqualityServices.getLogger().info("Found chapters - " + listOfChapters.stream().map(Object::toString).collect(Collectors.joining(", ")));
        return bookNames;
    }

    @Override
    public void openChapter(String chapter) {
        btnChapters.click();
        IButton button = getElementFactory().getButton(By.xpath("//android.widget.TextView[contains(@resource-id,\"reader_toc_element_text\") and @text=\"" + chapter + "\"]"), chapter);
        if (!button.state().isDisplayed()) {
            button.getTouchActions().scrollToElement(SwipeDirection.DOWN);
        }
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
        return RegExUtil.getDoubleFromFirstMatchGroup(getBookSource(), RegEx.FONT_SIZE_REGEX);
    }

    @Override
    public String getFontName() {
        return getReaderInfo(RegEx.FONT_NAME_REGEX);
    }

    @Override
    public String getFontColor() {
        return getReaderInfo(RegEx.FONT_COLOR_REGEX);
    }

    @Override
    public String getBackgroundColor() {
        return getReaderInfo(RegEx.BACKGROUND_COLOR_REGEX);
    }

    private String getReaderInfo(String regex) {
        return RegExUtil.getStringFromFirstGroup(getBookSource(), regex);
    }

    private List<ILabel> getChapters() {
        return getElementFactory().findElements(By.xpath("//android.widget.TextView[contains(@resource-id,\"reader_toc_element_text\")]"), ElementType.LABEL);
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
}
