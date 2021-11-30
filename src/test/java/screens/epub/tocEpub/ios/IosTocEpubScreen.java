package screens.epub.tocEpub.ios;

import aquality.appium.mobile.actions.SwipeDirection;
import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.ElementType;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import aquality.selenium.core.elements.ElementState;
import aquality.selenium.core.elements.ElementsCount;
import aquality.selenium.core.elements.interfaces.IElement;
import org.openqa.selenium.By;
import screens.epub.tocEpub.TocEpubScreen;

import java.util.List;
import java.util.stream.Collectors;

@ScreenType(platform = PlatformName.IOS)
public class IosTocEpubScreen extends TocEpubScreen {
    private static final String CHAPTER_LOC = "//XCUIElementTypeTable//XCUIElementTypeCell/XCUIElementTypeStaticText";
    private static final String CHAPTER_BY_NAME_LOC = "//XCUIElementTypeTable//XCUIElementTypeCell/XCUIElementTypeStaticText[@name=\"%1$s\"]";

    public IosTocEpubScreen() {
        super(By.xpath("//XCUIElementTypeTable"));
    }

    @Override
    public void openChapter(String chapter) {
        ILabel lblChapter = getElementFactory().getLabel(By.xpath(String.format(CHAPTER_BY_NAME_LOC, chapter)), chapter);
        lblChapter.getTouchActions().scrollToElement(SwipeDirection.DOWN);
        lblChapter.click();
    }

    @Override
    public List<String> getListOfBookChapters() {
        List<String> listOfChapters = getChapters().stream().map(IElement::getText).collect(Collectors.toList());
        AqualityServices.getLogger().info("Found chapters on toc epub - " + listOfChapters.stream().map(Object::toString).collect(Collectors.joining(", ")));
        AqualityServices.getLogger().info("amountOfChapters on toc epub - " + listOfChapters.size());
        return listOfChapters;
    }

    private List<ILabel> getChapters() {
        return getElementFactory().findElements(By.xpath(CHAPTER_LOC), ElementType.LABEL, ElementsCount.ANY, ElementState.EXISTS_IN_ANY_STATE);
    }
}
