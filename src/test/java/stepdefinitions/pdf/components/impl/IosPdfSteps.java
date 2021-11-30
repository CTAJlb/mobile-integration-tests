package stepdefinitions.pdf.components.impl;

import aquality.appium.mobile.application.PlatformName;
import factories.steps.StepsType;
import framework.utilities.ScenarioContext;
import stepdefinitions.pdf.components.AbstractPdfSteps;

@StepsType(platform = PlatformName.IOS)
public class IosPdfSteps extends AbstractPdfSteps {
    public IosPdfSteps(ScenarioContext context) {
        super(context);
    }
}
