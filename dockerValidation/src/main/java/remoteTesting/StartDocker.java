package remoteTesting;

import org.testng.Assert;
import org.testng.annotations.Test;

import javax.swing.text.Style;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;

public class StartDocker {


    public void startFile() throws IOException, InterruptedException {
        boolean textFounded = false;

        Runtime runtime = Runtime.getRuntime();

        String pathProjectParent = Paths.get("").toAbsolutePath().normalize().toString();
        String pathDockerCompose = pathProjectParent + "\\windowsBatch\\startDockerContainers.bat";

        runtime.exec("cmd /c start " + pathDockerCompose);

        String logFile = pathProjectParent + "\\startDockerContainers_log.txt";

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 45);
        long stopReader = calendar.getTimeInMillis();

        Thread.sleep(1500);

        while(System.currentTimeMillis() < stopReader){
            if(textFounded){
                break;
            }
            BufferedReader reader = new BufferedReader(new FileReader(logFile));
            String currentLine = reader.readLine();

            while(currentLine != null && !textFounded){
                if(currentLine.contains("The node is registered to the hub and ready to use")){
                    System.out.println("Found Text.");
                    textFounded = true;
                    break;
                }
                currentLine = reader.readLine();
            }
            reader.close();
        }
        Assert.assertTrue(textFounded);

        String pathDockerScale = pathProjectParent + "\\windowsBatch\\scaleDockerChromeNodes.bat";
        runtime.exec("cmd /c start " + pathDockerScale);

        Thread.sleep(5000);
    }
}
