package manager;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import util.DeviceConfig;
import util.Library;

import java.io.IOException;
import java.net.URL;

public class DriverManager {

    private static InheritableThreadLocal<AndroidDriver> driver
            =new InheritableThreadLocal<>();

    public void setDriver(DeviceConfig config) throws IOException {

        DesiredCapabilities caps = new DesiredCapabilities();

        // Set your access credentials
        caps.setCapability("browserstack.user", config.getBrowserStackUser());
        caps.setCapability("browserstack.key", config.getBrowserStackKey());

        // Set URL of the application under test
        caps.setCapability("app", config.getAppLocation());

        // Specify device and os_version for testing
        caps.setCapability("device", config.getDeviceName());
        caps.setCapability("os_version", config.getOsVersion());

        // Set other BrowserStack capabilities
        caps.setCapability("project", Library.getConfigData("$.project.project"));
        caps.setCapability("build", Library.getConfigData("$.project.build"));
        caps.setCapability("name", Library.getConfigData("$.project.name"));


        // Initialise the remote Webdriver using BrowserStack remote URL
        // and desired capabilities defined above
        driver.set(new AndroidDriver<AndroidElement>(
                new URL("http://hub.browserstack.com/wd/hub"), caps));


        getDriver().rotate(config.getOrientation());
    }

    public static AndroidDriver getDriver(){
        return driver.get();
    }

    public void removeDriver(){
        if(driver.get()!=null)
            driver.remove();
    }

}
