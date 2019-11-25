package remoteTesting;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ChromeTestOne {

    @BeforeTest
    public void startDocker() throws IOException, InterruptedException {
        String pathProjectParent = Paths.get("").toAbsolutePath().normalize().toString();
        String logFile = pathProjectParent + "\\startDockerContainers_log.txt";
        try{
            Files.deleteIfExists(Paths.get(logFile));
            System.out.println("Deletion successful.");
        }catch (Exception e) {
            System.out.println("Deletion unsuccessful: " + e.toString());
        }

        StartDocker startDocker = new StartDocker();
        startDocker.startFile();
    }

    @AfterTest
    public void stopDocker() throws IOException, InterruptedException {
        StopDocker stopDocker = new StopDocker();
        stopDocker.stopFile();
    }

    @Test
    public void Test1() throws MalformedURLException {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        URL url = new URL("http://localhost:4444/wd/hub");
        RemoteWebDriver driver = new RemoteWebDriver(url, capabilities);
        driver.get("http://google.com");
        System.out.println(driver.getTitle());
    }
}
