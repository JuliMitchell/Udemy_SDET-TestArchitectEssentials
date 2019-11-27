package dataDriven;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProvide {

    @DataProvider(name="driveTest")
    public Object[][] getData(){
        Object[][] data = {
                {"hola", "texto", 1},
                {"chau", "mensaje", 34},
                {"solo", "llamada", 51}
        };
        return data;
    }

    @Test(dataProvider = "driveTest")
    public void testCaseData(String greeting, String communication, int id){
        System.out.println(greeting + "," + communication + "," + id);
    }
}
