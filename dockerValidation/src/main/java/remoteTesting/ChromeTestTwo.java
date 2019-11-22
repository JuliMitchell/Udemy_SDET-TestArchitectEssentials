package remoteTesting;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class ChromeTestTwo {
    @Test
    public void Test2() throws MalformedURLException {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        URL url = new URL("http://localhost:4444/wd/hub");
        RemoteWebDriver driver = new RemoteWebDriver(url, capabilities);
        driver.get("http://promiedos.com.ar");
        System.out.println(driver.getTitle());
    }

}
