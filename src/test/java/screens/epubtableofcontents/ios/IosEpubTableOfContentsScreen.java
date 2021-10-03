package screens.epubtableofcontents.ios;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.ElementType;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import aquality.selenium.core.elements.ElementState;
import aquality.selenium.core.elements.ElementsCount;
import aquality.selenium.core.elements.interfaces.IElement;
import org.openqa.selenium.By;
import screens.epubtableofcontents.EpubTableOfContentsScreen;

import java.util.List;
import java.util.stream.Collectors;

@ScreenType(platform = PlatformName.IOS)
public class IosEpubTableOfContentsScreen extends EpubTableOfContentsScreen {
    private static final String CHAPTERS_LIST_LOC = "//XCUIElementTypeTable";
    private static final String CHAPTER_TEXT_LOC = "//XCUIElementTypeCell/XCUIElementTypeStaticText";

    public IosEpubTableOfContentsScreen() {
        super(By.xpath(CHAPTERS_LIST_LOC));
    }

    public List<String> getListOfBookChapters() {
        List<String> listOfChapters = getChapters().stream().map(IElement::getText).collect(Collectors.toList());
        return listOfChapters;
    }

    private List<ILabel> getChapters() {
        return getElementFactory().findElements(By.xpath(CHAPTERS_LIST_LOC + CHAPTER_TEXT_LOC), ElementType.LABEL, ElementsCount.ANY, ElementState.EXISTS_IN_ANY_STATE);
    }
}
