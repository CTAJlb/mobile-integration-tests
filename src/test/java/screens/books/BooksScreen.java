package screens.books;

import aquality.appium.mobile.screens.Screen;
import models.android.CatalogBookModel;
import org.openqa.selenium.By;

public abstract class BooksScreen extends Screen {
    protected BooksScreen(By locator) {
        super(locator, "Books");
    }

    public abstract boolean isNoBooksMessagePresent();

    public abstract boolean isBookPresent(CatalogBookModel bookInfo);

    public abstract int getCountOfBooks();

    public abstract void refreshList();

    public abstract void readBook(CatalogBookModel bookInfo);
}
