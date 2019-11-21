import java.sql.*;

public class JsonToJava {
    public static void main(String args[]) throws ClassNotFoundException, SQLException {
        //Import the driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Create connection
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Business?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select * from CustomerInfo where purchasedDate=CURDATE() and Location ='Asia';");

        while(rs.next()){
            System.out.println(rs.getString(1));
            System.out.println(rs.getString(2));
            System.out.println(rs.getInt(3));
            System.out.println(rs.getString(4));
        }

        conn.close();
    }
}
