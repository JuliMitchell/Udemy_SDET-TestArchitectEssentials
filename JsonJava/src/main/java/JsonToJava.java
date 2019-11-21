import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonToJava {
    public static void main(String args[]) throws ClassNotFoundException, SQLException, IOException {
        //Import the driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Create connection
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Business?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select * from CustomerInfo where purchasedDate=CURDATE() and Location ='Asia' LIMIT 1;");

        CustomerInfo customerInfo = new CustomerInfo();

        while(rs.next()){
            customerInfo.setCourseName(rs.getString(1));
            customerInfo.setPurchasedDate(rs.getString(2));
            customerInfo.setAmount(rs.getInt(3));
            customerInfo.setLocation(rs.getString(4));
        }

        conn.close();

        ObjectMapper objectMapper = new ObjectMapper();

        Path currentRelativePath = Paths.get("");
        String jsonPath = currentRelativePath.toAbsolutePath().toString() + "\\jsonfiles\\customerInfo.json";

        objectMapper.writeValue(new File(jsonPath), customerInfo);
    }
}
