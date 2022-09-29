package screens.tutorial.ios;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.ElementType;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import aquality.selenium.core.elements.ElementState;
import aquality.selenium.core.elements.ElementsCount;
import constants.applicationattributes.IosAttributes;
import framework.utilities.swipe.SwipeElementUtils;
import framework.utilities.swipe.directions.EntireElementSwipeDirection;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import screens.tutorial.TutorialScreen;

import java.util.List;
import java.util.stream.Collectors;

@ScreenType(platform = PlatformName.IOS)
public class IosTutorialScreen extends TutorialScreen {
    private final IButton btnCloseTutorial = getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@name=\"Close\"]"), "btnCloseTutorial");
    private final ILabel lblPage = getElementFactory().getLabel(By.xpath("//XCUIElementTypeWindow"), "lblPage");

    private static final String TUTORIAL_TAB_BY_NAME_LOC = "//XCUIElementTypeImage[contains(@name,\"%s\")]";
    private static final String TUTORIAL_TAB_LOC = "//XCUIElementTypeImage[contains(@name,\"Step\")]";

    public IosTutorialScreen() {
        super(By.xpath("//XCUIElementTypeButton[@name=\"Close\"]"));
    }

    @Override
    public void closeTutorial() {
        Point point = btnCloseTutorial.getElement().getCenter();
        TouchAction action = new TouchAction(AqualityServices.getApplication().getDriver());
        action.tap(PointOption.point(point)).perform();
    }

    @Override
    public boolean isTutorialPageOpened(String pageName) {
        return getElementFactory().getLabel(By.xpath(String.format(TUTORIAL_TAB_BY_NAME_LOC, pageName)), "lblTutorialTab").getAttribute("visible").equals("true");
    }

    @Override
    public void goToNextPage() {
        SwipeElementUtils.swipeThroughEntireElement(lblPage, EntireElementSwipeDirection.RIGHT);
    }

    @Override
    public List<String> getListOfPageNames() {
        return getListOfIlableOfTutorialTabs().stream().map(tab -> tab.getAttribute(IosAttributes.NAME)).collect(Collectors.toList());
    }

    private List<ILabel> getListOfIlableOfTutorialTabs() {
        return getElementFactory().findElements(By.xpath(TUTORIAL_TAB_LOC), ElementType.LABEL, ElementsCount.ANY, ElementState.EXISTS_IN_ANY_STATE);
    }
}
