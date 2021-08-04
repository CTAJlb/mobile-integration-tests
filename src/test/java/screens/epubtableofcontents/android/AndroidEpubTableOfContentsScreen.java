package screens.epubtableofcontents.android;

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

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidEpubTableOfContentsScreen extends EpubTableOfContentsScreen {
    private final ILabel lblTable =
            getElementFactory().getLabel(By.xpath("//androidx.recyclerview.widget.RecyclerView[contains(@resource-id,\"tocChaptersList\")]"), "Table");

    private List<ILabel> getChapters() {
        return getElementFactory().findElements(By.xpath("//android.widget.TextView[contains(@resource-id,\"chapterTitle\")]"), ElementType.LABEL, ElementsCount.ANY, ElementState.EXISTS_IN_ANY_STATE);
    }

    public AndroidEpubTableOfContentsScreen() {
        super(By.xpath("//android.view.ViewGroup[contains(@resource-id,\"tocLayout\")]"));
    }

    public List<String> getListOfBookChapters() {
        List<String> listOfChapters = getChapters().stream().map(IElement::getText).collect(Collectors.toList());
        return listOfChapters;
    }
}
