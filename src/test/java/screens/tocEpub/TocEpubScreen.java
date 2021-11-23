package screens.tocEpub;

import aquality.appium.mobile.screens.Screen;
import org.openqa.selenium.By;

import java.util.List;

public abstract class TocEpubScreen extends Screen {
    public TocEpubScreen(By locator) {
        super(locator, "TocEpub");
    }

    public abstract void openChapter(String chapterName);

    public abstract List<String> openListOfChaptersAndGetListOfBookChapters();

    public abstract void returnToPreviousScreen();
}
