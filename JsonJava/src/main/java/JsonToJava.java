import java.sql.*;

public class JsonToJava {
    public static void main(String args[]) throws ClassNotFoundException, SQLException {
        //Import the driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Create connection
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Business?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select * from CustomerInfo where purchasedDate=CURDATE() and Location ='Asia' LIMIT 1;");

        while(rs.next()){

            CustomerInfo customerInfo = new CustomerInfo();

            customerInfo.setCourseName(rs.getString(1));
            customerInfo.setPurchasedDate(rs.getString(2));
            customerInfo.setAmount(rs.getInt(3));
            customerInfo.setLocation(rs.getString(4));

            System.out.println(customerInfo.getCourseName());
            System.out.println(customerInfo.getPurchasedDate());
            System.out.println(customerInfo.getAmount());
            System.out.println(customerInfo.getLocation());
        }

        conn.close();
    }
}
