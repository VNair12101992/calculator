package stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import manager.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import util.DeviceConfig;

import java.io.IOException;


public class Base {

    public static DeviceConfig config;
    public DriverManager manager;


    @Before
    public void beforeTest(Scenario scenario) throws IOException {
        manager = new DriverManager();
        manager.setDriver(config);
        scenario.log(config.toString());
    }

    @After
    public void afterTest(Scenario scenario) {
        takeScreenshot(scenario);
        if (DriverManager.getDriver() != null) {
            DriverManager.getDriver().quit();
        }
        manager.removeDriver();
    }

    @BeforeAll
    public static void beforeAll() throws IOException {
        config = new DeviceConfig();
    }


    public void takeScreenshot(Scenario scenario) {
        final byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver())
                .getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", scenario.getName());
    }


}
