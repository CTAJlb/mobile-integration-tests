package screens.tutorial.android;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.ElementType;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import framework.utilities.swipe.SwipeElementUtils;
import framework.utilities.swipe.directions.EntireElementSwipeDirection;
import org.openqa.selenium.By;
import screens.tutorial.TutorialScreen;

import java.util.List;
import java.util.stream.Collectors;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidTutorialScreen extends TutorialScreen {
    private final IButton btnCloseTutorial = getElementFactory().getButton(By.xpath("//android.widget.ImageView[contains(@resource-id,\"skip_button\")]"), "btnCloseTutorial");
    private final ILabel lblPage = getElementFactory().getLabel(By.xpath("//android.widget.ImageView[@content-desc=\"Tutorial page\"]"), "lblPage");

    private static final String TUTORIAL_TAB_BY_NAME_LOC = "//android.widget.LinearLayout[contains(@content-desc,\"%s\")]";
    private static final String TUTORIAL_TAB_LOC = "//android.widget.LinearLayout[@content-desc]";

    public AndroidTutorialScreen() {
        super(By.xpath("//android.widget.ImageView[@content-desc=\"Tutorial page\"]"));
    }

    @Override
    public void closeTutorial() {
        btnCloseTutorial.click();
    }

    @Override
    public boolean isTutorialPageOpened(String pageName) {
        return getElementFactory().getLabel(By.xpath(String.format(TUTORIAL_TAB_BY_NAME_LOC, pageName)), "lblTutorialTab").getAttribute("selected").equals("true");
    }

    @Override
    public void goToNextPage() {
        SwipeElementUtils.swipeThroughEntireElement(lblPage, EntireElementSwipeDirection.RIGHT);
    }

    @Override
    public List<String> getListOfPageNames() {
        return getListOfIlableOfTutorialTabs().stream().map(tab -> tab.getAttribute("content-desc")).collect(Collectors.toList());
    }

    private List<ILabel> getListOfIlableOfTutorialTabs() {
        return getElementFactory().findElements(By.xpath(TUTORIAL_TAB_LOC), ElementType.LABEL);
    }
}
