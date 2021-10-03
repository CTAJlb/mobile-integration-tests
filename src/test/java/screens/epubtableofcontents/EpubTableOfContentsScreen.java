package screens.epubtableofcontents;

import aquality.appium.mobile.screens.Screen;
import org.openqa.selenium.By;

import java.util.List;

public abstract class EpubTableOfContentsScreen extends Screen {
    protected EpubTableOfContentsScreen(By locator) {
        super(locator, "Table of Contents");
    }

    public abstract List<String> getListOfBookChapters();
}
