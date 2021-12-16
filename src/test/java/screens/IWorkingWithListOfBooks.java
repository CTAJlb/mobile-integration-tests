package screens;

import aquality.appium.mobile.actions.SwipeDirection;
import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.IElementFactory;
import org.openqa.selenium.By;

public interface IWorkingWithListOfBooks {

    default IButton getBookNameButtonFromListOfBooks(String bookNameLoc) {
        IElementFactory elementFactory = AqualityServices.getElementFactory();
        IButton actionButton = elementFactory.getButton(By.xpath(bookNameLoc), "button");
        if (!actionButton.state().waitForDisplayed()) {
            actionButton.getTouchActions().scrollToElement(SwipeDirection.DOWN);
        }
        actionButton.state().waitForDisplayed();
        return actionButton;
    }

    default IButton getActionButtonFromListOfBooks(String actionButtonLoc) {
        IElementFactory elementFactory = AqualityServices.getElementFactory();
        IButton actionButton = elementFactory.getButton(By.xpath(actionButtonLoc), "button");
        if (!actionButton.state().waitForDisplayed()) {
            actionButton.getTouchActions().scrollToElement(SwipeDirection.DOWN);
        }
        actionButton.state().waitForDisplayed();
        return actionButton;
    }
}
