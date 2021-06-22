package screens.epubtableofcontents;

import aquality.appium.mobile.screens.Screen;
import org.openqa.selenium.By;

import java.util.List;
import java.util.Set;

public abstract class EpubTableOfContentsScreen extends Screen {
    protected EpubTableOfContentsScreen(By locator) {
        super(locator, "Table of Contents");
    }

    public abstract List<String> getListOfBookChapters();
}
