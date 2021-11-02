package util;

import org.openqa.selenium.ScreenOrientation;

import java.io.IOException;

public class DeviceConfig {

    public DeviceConfig() throws IOException {
        setBrowserStackUser(System.getProperty("browserstackuser") != null ? System.getProperty("browserstackuser") :
                Library.getConfigData("$.browserstack.user"));
        setBrowserStackKey(System.getProperty("browserstackkey") != null ? System.getProperty("browserstackkey") :
                Library.getConfigData("$.browserstack.key"));
        setAppLocation(System.getProperty("app") != null ? System.getProperty("app") :
                Library.getConfigData("$.browserstack.app"));
        setDeviceName(System.getProperty("devicename") != null ? System.getProperty("devicename") :
                Library.getConfigData("$.device.name"));
        setOsVersion(System.getProperty("osVersion") != null ? System.getProperty("osVersion") :
                Library.getConfigData("$.device.os_version"));
        setOrientation(System.getProperty("orientation") != null ? System.getProperty("orientation") :
                "Portrait");
    }

    private String browserStackUser;
    private String browserStackKey;

    public ScreenOrientation getOrientation() {
        return orientation;
    }

    private void setOrientation(String orientation) {
        if (orientation.equals("Landscape"))
            this.orientation = ScreenOrientation.LANDSCAPE;
        else
            this.orientation = ScreenOrientation.PORTRAIT;
    }

    private ScreenOrientation orientation;

    public String getBrowserStackUser() {
        return browserStackUser;
    }

    private void setBrowserStackUser(String browserStackUser) {
        this.browserStackUser = browserStackUser;
    }

    public String getBrowserStackKey() {
        return browserStackKey;
    }

    private void setBrowserStackKey(String browserStackKey) {
        this.browserStackKey = browserStackKey;
    }

    public String getAppLocation() {
        return appLocation;
    }

    private void setAppLocation(String appLocation) {
        this.appLocation = appLocation;
    }

    public String getDeviceName() {
        return deviceName;
    }

    private void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getOsVersion() {
        return osVersion;
    }

    private void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    private String appLocation;

    private String deviceName;
    private String osVersion;

    @Override
    public String toString() {
        return "DeviceConfig{" +
                "browserStackUser='" + browserStackUser + '\'' +
                ", browserStackKey='" + browserStackKey + '\'' +
                ", orientation=" + orientation +
                ", appLocation='" + appLocation + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", osVersion='" + osVersion + '\'' +
                '}';
    }
}
