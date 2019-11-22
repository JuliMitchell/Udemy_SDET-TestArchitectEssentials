package remoteTesting;

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StartDocker {

    @Test
    public void startFile() throws IOException {
        Runtime runtime = Runtime.getRuntime();

        String pathProjectParent = Paths.get("..").toAbsolutePath().normalize().toString();
        String pathDockerCompose = pathProjectParent + "\\startDockerContainers.bat";

        runtime.exec("cmd /c start " + pathDockerCompose);

    }
}
