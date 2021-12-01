package screens.pdf.tocPdf.ios;

import aquality.appium.mobile.actions.SwipeDirection;
import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.ElementType;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import aquality.selenium.core.elements.ElementState;
import aquality.selenium.core.elements.ElementsCount;
import constants.application.attributes.IosAttributes;
import org.openqa.selenium.By;
import screens.pdf.tocBookmarksGalleryPdf.TocBookmarksGalleryPdfScreen;
import screens.pdf.tocPdf.TocPdfScreen;

import java.util.List;
import java.util.stream.Collectors;

@ScreenType(platform = PlatformName.IOS)
public class IosTocPdfScreen extends TocPdfScreen {
    private static final String CHAPTER_BY_NAME_LOC = "//XCUIElementTypeTable//XCUIElementTypeCell/XCUIElementTypeStaticText[@name=\"%1$s\"]";
    private static final String CHAPTER_NUMBER_BY_NAME_LOC =
            "//XCUIElementTypeTable//XCUIElementTypeCell/XCUIElementTypeStaticText[@name=\"%1$s\"]/following-sibling::XCUIElementTypeStaticText";
    private static final String CHAPTER_LOC = "//XCUIElementTypeTable//XCUIElementTypeCell/XCUIElementTypeStaticText[1]";

    public IosTocPdfScreen() {
        super(By.xpath("//XCUIElementTypeTable"));
    }

    public List<String> getListOfBookChapters() {
        List<String> listOfChapters = getChapters().stream().map(chapter -> chapter.getAttribute(IosAttributes.NAME)).collect(Collectors.toList());
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
        return Integer.parseInt(lblChapterNumber.getAttribute(IosAttributes.NAME));
    }

    @Override
    public void returnToReaderPdfScreen() {
        AqualityServices.getScreenFactory().getScreen(TocBookmarksGalleryPdfScreen.class).tapResumeButton();
    }

    private List<ILabel> getChapters() {
        return getElementFactory().findElements(By.xpath(CHAPTER_LOC), ElementType.LABEL, ElementsCount.ANY, ElementState.EXISTS_IN_ANY_STATE);
    }
}
