package stepdefinitions.holds.components.impl;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import factories.steps.StepsType;
import framework.utilities.ScenarioContext;
import models.android.CatalogBookModel;
import org.junit.Assert;
import stepdefinitions.holds.components.AbstractHoldsSteps;

@StepsType(platform = PlatformName.IOS)
public class IosHoldsSteps extends AbstractHoldsSteps {

    public IosHoldsSteps(ScenarioContext context) {
        super(context);
    }

    @Override
    public void checkBookBookInfoIsPresentInHoldsList(String bookInfoKey) {
        CatalogBookModel bookInfo = context.get(bookInfoKey);
        boolean isBookPresent = holdsScreen.isBookPresent(bookInfo.getTitle());
        if (!isBookPresent) {
            AqualityServices.getLogger().info(AqualityServices.getApplication().getDriver().getPageSource());
        }
        Assert.assertTrue(String.format("Book '%s' is not present in Books List", bookInfo), isBookPresent);
    }

    @Override
    public void checkBookBookInfoIsNotPresentInHoldsList(String bookInfoKey) {
        CatalogBookModel bookInfo = context.get(bookInfoKey);
        Assert.assertTrue(String.format("Book '%s' is not present in Books List", bookInfo), holdsScreen.isBookNotPresent(bookInfo.getTitle()));
    }
}
