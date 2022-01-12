package screens;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.IElementFactory;
import aquality.appium.mobile.elements.interfaces.ILabel;
import org.openqa.selenium.By;

public interface IWorkingWithListOfBooks {

    default ILabel getBookNameLabelFromListOfBooks(String bookNameLoc) {
        IElementFactory elementFactory = AqualityServices.getElementFactory();
        ILabel lblBookName = elementFactory.getLabel(By.xpath(bookNameLoc), "lblBookName");
        lblBookName.state().waitForDisplayed();
        return lblBookName;
    }

    default IButton getActionButtonFromListOfBooks(String actionButtonLoc) {
        IElementFactory elementFactory = AqualityServices.getElementFactory();
        IButton actionButton = elementFactory.getButton(By.xpath(actionButtonLoc), "actionButton");
        actionButton.state().waitForDisplayed();
        return actionButton;
    }
}
