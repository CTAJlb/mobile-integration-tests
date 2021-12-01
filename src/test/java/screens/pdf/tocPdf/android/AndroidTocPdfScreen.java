package screens.pdf.tocPdf.android;

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
import screens.pdf.tocPdf.TocPdfScreen;

import java.util.List;
import java.util.stream.Collectors;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidTocPdfScreen extends TocPdfScreen {
    private static final String CHAPTER_BY_NAME_LOC = "//android.widget.TextView[contains(@resource-id,\"reader_toc_element_title\") and @text=\"%s\"]";
    private static final String CHAPTER_NUMBER_BY_NAME_LOC = "//android.widget.TextView[contains(@resource-id,\"reader_toc_element_title\") and @text=\"%s\"]//following-sibling::android.widget.TextView";
    private static final String CHAPTER_LOC = "//android.widget.TextView[contains(@resource-id,\"reader_toc_element_title\")]";

    public AndroidTocPdfScreen() {
        super(By.id("pdf_reader_fragment_holder"));
    }

    public List<String> getListOfBookChapters() {
        List<String> listOfChapters = getChapters().stream().map(IElement::getText).collect(Collectors.toList());
        AqualityServices.getLogger().info("Found chapters on toc pdf - " + listOfChapters.stream().map(Object::toString).collect(Collectors.joining(", ")));
        AqualityServices.getLogger().info("amountOfChapters on toc pdf - " + listOfChapters.size());
        return listOfChapters;
    }

    @Override
    public void openChapter(String chapter) {
        ILabel lblChapter = getElementFactory().getLabel(By.xpath(String.format(CHAPTER_BY_NAME_LOC, chapter)), chapter);
        lblChapter.getTouchActions().scrollToElement(SwipeDirection.DOWN);
        lblChapter.click();
    }

    @Override
    public int getChapterNumber(String chapter) {
        ILabel lblChapterNumber = getElementFactory().getLabel(By.xpath(String.format(CHAPTER_NUMBER_BY_NAME_LOC, chapter)), chapter);
        lblChapterNumber.getTouchActions().scrollToElement(SwipeDirection.DOWN);
        return Integer.parseInt(lblChapterNumber.getText());
    }

    @Override
    public void returnToReaderPdfScreen() {
        AqualityServices.getApplication().getDriver().navigate().back();
    }

    private List<ILabel> getChapters() {
        return getElementFactory().findElements(By.xpath(CHAPTER_LOC), ElementType.LABEL, ElementsCount.ANY, ElementState.EXISTS_IN_ANY_STATE);
    }
}
