package screens.pdf.tocPdf;

import aquality.appium.mobile.screens.Screen;
import org.openqa.selenium.By;

public abstract class TocPdfScreen extends Screen {
    public TocPdfScreen(By locator) {
        super(locator, "TocPdf");
    }

    public abstract int openRandomChapter();

}
