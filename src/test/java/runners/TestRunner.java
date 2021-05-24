package runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/java/features"},
        glue = {
                "hooks",
                "transformers",
                "stepdefinitions"
        },
        plugin = {
                "io.qameta.allure.cucumber5jvm.AllureCucumber5Jvm"
                //todo Aquality Tracking is temporary turned off until AqualityTrackingCucumber6Jvm is released
                //"aquality.tracking.integrations.cucumber5jvm.AqualityTrackingCucumber5Jvm",
        }
)
public class TestRunner{
}
