package screens.epubtableofcontents.android;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.ElementType;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import aquality.selenium.core.elements.interfaces.IElement;
import framework.utilities.swipe.SwipeElementUtils;
import org.openqa.selenium.By;
import screens.epubtableofcontents.EpubTableOfContentsScreen;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidEpubTableOfContentsScreen extends EpubTableOfContentsScreen {
    private final ILabel lblTable =
            getElementFactory().getLabel(By.xpath("//androidx.recyclerview.widget.RecyclerView[contains(@resource-id,\"tocChaptersList\")]"), "Table");

    private List<ILabel> getChapters() {
        return getElementFactory().findElements(By.xpath("//android.widget.TextView[contains(@resource-id,\"chapterTitle\")]"), ElementType.LABEL);
    }

    public AndroidEpubTableOfContentsScreen() {
        super(By.xpath("//android.view.ViewGroup[contains(@resource-id,\"tocLayout\")]"));
    }

    public Set<String> getListOfBookChapters() {
        List<String> listOfChapters = getChapters().stream().map(IElement::getText).collect(Collectors.toList());
        Set<String> bookNames = new HashSet<>();
        do {
            bookNames.addAll(listOfChapters);
            SwipeElementUtils.swipeThroughEntireElementUp(lblTable);
            listOfChapters = getChapters().stream().map(IElement::getText).collect(Collectors.toList());
        } while (!bookNames.containsAll(listOfChapters));
        return bookNames;
    }
}
