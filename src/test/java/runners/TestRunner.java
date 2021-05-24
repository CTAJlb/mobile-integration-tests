package runners;


import framework.utilities.feedXMLUtil.GettingBookUtil;
import framework.utilities.feedXMLUtil.XMLUtil;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.BeforeClass;
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
        },
        tags = "@go1"
)
public class TestRunner{

        @BeforeClass
        public static void setup() {
                XMLUtil xmlUtil = new XMLUtil();
                GettingBookUtil.setXmlUtil(xmlUtil);
        }

}
