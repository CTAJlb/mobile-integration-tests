package screens.pdf.tocPdf.android;

import aquality.appium.mobile.actions.SwipeDirection;
import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.ElementType;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import aquality.selenium.core.elements.ElementState;
import aquality.selenium.core.elements.ElementsCount;
import aquality.selenium.core.elements.interfaces.IElement;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import screens.pdf.tocPdf.TocPdfScreen;

import java.util.List;
import java.util.stream.Collectors;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidTocPdfScreen extends TocPdfScreen {
    private final IButton btnMiniatures =
            getElementFactory().getButton(By.xpath("//android.widget.RadioButton[@resource-id=\"viewThumbnail\"]"), "TOC with miniatures");
    private final IButton btnChapters =
            getElementFactory().getButton(By.xpath("//android.widget.RadioButton[@resource-id=\"viewOutline\"]"), "Chapter content");
    private final ILabel lblPageNumber =
            getElementFactory().getLabel(By.xpath("//android.widget.EditText[@resource-id=\"pageNumber\"]"), "Page Number");

    private static final String THUMBNAIL_LOC = "//android.view.View[contains(@content-desc,\"Thumbnail of Page\")]";
    private static final String THUMBNAIL_NUMBER_LOC = "//android.view.View[@content-desc=\"Thumbnail of Page %d\"]";




    private static final String CHAPTER_BY_NAME_LOC = "//android.widget.TextView[contains(@resource-id,\"reader_toc_element_title\") and @text=\"%s\"]";
    private static final String CHAPTER_NUMBER_BY_NAME_LOC = "//android.widget.TextView[contains(@resource-id,\"reader_toc_element_title\") and @text=\"%s\"]//following-sibling::android.widget.TextView";

    public AndroidTocPdfScreen() {
        super(By.xpath("//android.view.View[@resource-id=\"sidebarContainer\"]"));
    }

    @Override
    public boolean isTOCOpened() {
        return btnMiniatures.state().waitForDisplayed() && btnChapters.state().waitForDisplayed();
    }

    @Override
    public void openThumbnails() {
        btnMiniatures.click();
    }

    @Override
    public boolean areThumbnailDisplayed() {
        return getThumbnails().size() != 0;
    }

    @Override
    public int openRandomThumbnail() {
        int thumbnailNumber = (int) (Math.random() * (getThumbnails().size()) + 1);
        ILabel thumbnail = getElementFactory().getLabel(By.xpath(String.format(THUMBNAIL_NUMBER_LOC, thumbnailNumber)), "Thumbnail");
        thumbnail.click();
        return thumbnailNumber;
    }

    @Override
    public void returnToReaderPdfScreen() {
        AqualityServices.getApplication().getDriver().navigate().back();
    }

    @Override
    public boolean isThumbnailOpened(int number) {
        int pageNumber = Integer.parseInt(StringUtils.getDigits(lblPageNumber.getText()));
        return pageNumber == number;
    }

    @Override
    public boolean isTOCClosed() {
        return btnMiniatures.state().waitForNotDisplayed();
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

    public List<String> getListOfBookThumbnails() {
        List<String> listOfThumbnails = getThumbnails().stream().map(IElement::getText).collect(Collectors.toList());
        AqualityServices.getLogger().info("Found thumbnails on toc pdf - " + listOfThumbnails.stream().map(Object::toString).collect(Collectors.joining(", ")));
        AqualityServices.getLogger().info("Amount of chapters on toc pdf - " + listOfThumbnails.size());
        return listOfThumbnails;
    }

    private List<ILabel> getThumbnails() {
        return getElementFactory().findElements(By.xpath(THUMBNAIL_LOC), ElementType.LABEL, ElementsCount.ANY, ElementState.EXISTS_IN_ANY_STATE);
    }
}
