package remoteTesting;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class ChromeTestOne {
    @Test
    public void Test1() throws MalformedURLException {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        URL url = new URL("http://localhost:4444/wd/hub");
        RemoteWebDriver driver = new RemoteWebDriver(url, capabilities);
        driver.get("http://google.com");
        System.out.println(driver.getTitle());
    }
}
