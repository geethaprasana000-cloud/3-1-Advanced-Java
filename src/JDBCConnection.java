import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCConnection {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/jdbc_lab";
        String username = "jdbcuser";
        String password = "jdbc123";

        try {
            Connection con = DriverManager.getConnection(url, username, password);

            System.out.println("Connected Successfully!");

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
