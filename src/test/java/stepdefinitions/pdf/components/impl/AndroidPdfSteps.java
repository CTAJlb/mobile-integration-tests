package stepdefinitions.pdf.components.impl;

import aquality.appium.mobile.application.PlatformName;
import factories.steps.StepsType;
import framework.utilities.ScenarioContext;
import stepdefinitions.pdf.components.AbstractPdfSteps;

@StepsType(platform = PlatformName.ANDROID)
public class AndroidPdfSteps extends AbstractPdfSteps {
    public AndroidPdfSteps(ScenarioContext context) {
        super(context);
    }
}
