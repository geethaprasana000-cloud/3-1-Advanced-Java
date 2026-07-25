import java.sql.*;
import java.util.Scanner;

public class InsertEmployee {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String url = "jdbc:mysql://localhost:3306/jdbc_lab";
        String user = "jdbcuser";
        String password = "jdbc123";

        try {
            Connection con = DriverManager.getConnection(url, user, password);

            String sql = "INSERT INTO employee VALUES(?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            System.out.print("Enter Employee ID: ");
            ps.setInt(1, sc.nextInt());

            sc.nextLine();

            System.out.print("Enter Employee Name: ");
            ps.setString(2, sc.nextLine());

            System.out.print("Enter Department: ");
            ps.setString(3, sc.nextLine());

            System.out.print("Enter Salary: ");
            ps.setDouble(4, sc.nextDouble());

            int rows = ps.executeUpdate();

            System.out.println(rows + " Record Inserted Successfully");

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
