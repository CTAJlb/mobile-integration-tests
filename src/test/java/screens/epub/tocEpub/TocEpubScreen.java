package screens.epub.tocEpub;

import aquality.appium.mobile.screens.Screen;
import org.openqa.selenium.By;

import java.util.List;

public abstract class TocEpubScreen extends Screen {
    public TocEpubScreen(By locator) {
        super(locator, "Table of Contents");
    }

    public abstract void openChapter(String chapter);

    public abstract List<String> getListOfBookChapters();
}
