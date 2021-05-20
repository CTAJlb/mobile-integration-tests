package runners;

import courgette.api.CourgetteOptions;
import courgette.api.CourgetteRunLevel;
import courgette.api.CucumberOptions;
import courgette.api.testng.TestNGCourgette;
import framework.utilities.feedXMLUtil.XMLUtil;
import org.testng.annotations.Test;

@Test
@CourgetteOptions(
        threads = 1,
        runLevel = CourgetteRunLevel.FEATURE,
        cucumberOptions = @CucumberOptions(
                features = {"src/test/java/features"},
                glue = {
                        "hooks",
                        "transformers",
                        "stepdefinitions"
                },
                plugin = {
                        "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm",
                        //todo Aquality Tracking is temporary turned off until AqualityTrackingCucumber6Jvm is released
                        //"aquality.tracking.integrations.cucumber5jvm.AqualityTrackingCucumber5Jvm",
                }
        ))
public class TestRunner extends TestNGCourgette {
    public static final XMLUtil xmlUtil;
    public static int sch = 0;
    public static int randomNumber = 0;

    static {
        xmlUtil = new XMLUtil();
        xmlUtil.getHashMapsForEBooksAndAudioBooks();
        randomNumber = xmlUtil.getRandomNumber();
    }

    public static synchronized int getRandomNumber(){
        return randomNumber;
    }

    public static synchronized XMLUtil get(){
        return xmlUtil;
    }

    public static synchronized void improveSch(){
        sch++;
    }

    public static synchronized int getSch(){
        return sch;
    }
}
