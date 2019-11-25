package remoteTesting;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;

public class StopDocker {


    public void stopFile() throws IOException, InterruptedException {
        boolean textFounded = false;

        Runtime runtime = Runtime.getRuntime();

        String pathProjectParent = Paths.get("").toAbsolutePath().normalize().toString();
        String pathDockerCompose = pathProjectParent + "\\windowsBatch\\stopDockerContainers.bat";

        runtime.exec("cmd /c start " + pathDockerCompose);

        String logFile = pathProjectParent + "\\startDockerContainers_log.txt";

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 60);
        long stopReader = calendar.getTimeInMillis();

        Thread.sleep(1500);

        while(System.currentTimeMillis() < stopReader){
            if(textFounded){
                break;
            }
            BufferedReader reader = new BufferedReader(new FileReader(logFile));
            String currentLine = reader.readLine();

            while(currentLine != null && !textFounded){
                if(currentLine.contains("Shutdown complete")){
                    System.out.println("Found Text.");
                    textFounded = true;
                    break;
                }
                currentLine = reader.readLine();
            }
            reader.close();
        }

        Assert.assertTrue(textFounded);

        Thread.sleep(5000);

        try{
            Files.deleteIfExists(Paths.get(logFile));
            System.out.println("Deletion successful.");
        }catch (Exception e) {
            System.out.println("Deletion unsuccessful: " + e.toString());
        }

        /*
        File file = new File(logFile);
        if(file.delete()){
            System.out.println("File deleted successfully.");
        }
        */
        Thread.sleep(5000);
    }
}
