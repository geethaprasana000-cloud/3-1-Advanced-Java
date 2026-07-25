import java.sql.*;

public class JDBCStoredProcDemo {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/testdb";
        String user = "testuser";
        String password = "testpass";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {

            System.out.println("Database connected.");

            // Insert employee
            CallableStatement insertStmt =
                    conn.prepareCall("{call insert_employee(?,?,?)}");

            insertStmt.setInt(1, 101);
            insertStmt.setString(2, "John Doe");
            insertStmt.setDouble(3, 55000.00);

            insertStmt.execute();

            System.out.println("Record inserted successfully.");


            // Get salary
            CallableStatement salaryStmt =
                    conn.prepareCall("{call get_salary_by_id(?,?)}");

            salaryStmt.setInt(1, 101);

            salaryStmt.registerOutParameter(2, Types.DECIMAL);

            salaryStmt.execute();

            double salary = salaryStmt.getDouble(2);

            System.out.println("Salary: " + salary);


            insertStmt.close();
            salaryStmt.close();

        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
