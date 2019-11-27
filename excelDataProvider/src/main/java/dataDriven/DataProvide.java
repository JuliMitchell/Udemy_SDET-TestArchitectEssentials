package dataDriven;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DataProvide {
    DataFormatter dataFormatter = new DataFormatter();
    @DataProvider(name="driveTest")
    public Object[][] getData() throws IOException {
        /*
        Object[][] data = {
                {"hola", "texto", "1"},
                {"chau", "mensaje", "34"},
                {"solo", "llamada", "51"}
        };
        */

        FileInputStream excelFile = new FileInputStream("dataExcel/data.xlsx");
        XSSFWorkbook excelBook = new XSSFWorkbook(excelFile);
        XSSFSheet excelSheet = excelBook.getSheetAt(0);
        int rowCount = excelSheet.getPhysicalNumberOfRows();
        XSSFRow excelRow; //= excelSheet.getRow(0);
        int colCount = excelSheet.getLastRowNum();

        Object[][] data = new Object[rowCount-1][colCount];

        for(int i = 0; i<rowCount -1; i++){
            excelRow = excelSheet.getRow(i+1);
            for(int j = 0; j<colCount; j++){
                XSSFCell excelCell = excelRow.getCell(j);
                data[i][j] = dataFormatter.formatCellValue(excelCell);
            }
        }

        return data;
    }

    @Test(dataProvider = "driveTest")
    public void testCaseData(String greeting, String communication, String id){
        System.out.println(greeting + "," + communication + "," + id);
    }
}