package screens.epub.tocEpub.android;

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

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidTocEpubScreen extends TocEpubScreen {
    private static final String CHAPTER_BY_NAME_LOC = "//android.widget.TextView[contains(@resource-id,\"chapterTitle\") and @text=\"%s\"]";
    private static final String CHAPTER_LOC = "//android.widget.TextView[contains(@resource-id,\"chapterTitle\")]";

    public AndroidTocEpubScreen() {
        super(By.xpath("//android.view.ViewGroup[contains(@resource-id,\"tocLayout\")]"));
    }

    @Override
    public void openChapter(String chapter) {
        ILabel lblChapter = getElementFactory().getLabel(By.xpath(String.format(CHAPTER_BY_NAME_LOC, chapter)), chapter);
        lblChapter.click();
    }

    @Override
    public List<String> getListOfBookChapters() {
        List<String> listOfChapters = getChapters().stream().map(IElement::getText).collect(Collectors.toList());
        AqualityServices.getLogger().info("Found chapters on toc epub - " + listOfChapters.stream().map(Object::toString).collect(Collectors.joining(", ")));
        AqualityServices.getLogger().info("amountOfChapters on toc epub - " + listOfChapters.size());
        return listOfChapters;
    }

    private List<IElement> getChapters() {
        return getElementFactory().findElements(By.xpath(CHAPTER_LOC), ElementType.LABEL).stream().limit(5).collect(Collectors.toList());
    }
}
