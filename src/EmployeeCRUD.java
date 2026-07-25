import java.sql.*;
import java.util.Scanner;

public class EmployeeCRUD {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/jdbc_lab";
        String user = "jdbcuser";
        String password = "jdbc123";

        Scanner sc = new Scanner(System.in);

        try {

            Connection con = DriverManager.getConnection(url, user, password);

            System.out.println("Connected Successfully!");

int choice;


do {

    System.out.println("\n===== Employee Management System =====");
    System.out.println("1. Insert Employee");
    System.out.println("2. Display Employee");
    System.out.println("3. Update Employee");
    System.out.println("4. Delete Employee");
    System.out.println("5. Exit");

    System.out.print("Enter your choice: ");
    choice = sc.nextInt();
switch(choice) {

    case 1:

    System.out.print("Enter Employee ID: ");
    int empid = sc.nextInt();
    sc.nextLine();

    System.out.print("Enter Employee Name: ");
    String empname = sc.nextLine();

    System.out.print("Enter Department: ");
    String department = sc.nextLine();

    System.out.print("Enter Salary: ");
    double salary = sc.nextDouble();

    String sql = "INSERT INTO employee VALUES (?, ?, ?, ?)";

    PreparedStatement ps = con.prepareStatement(sql);

    ps.setInt(1, empid);
    ps.setString(2, empname);
    ps.setString(3, department);
    ps.setDouble(4, salary);

    int rows = ps.executeUpdate();

    if(rows > 0){
        System.out.println("Employee Inserted Successfully!");
    }

    ps.close();

    break;
case 2:

    Statement st = con.createStatement();

    ResultSet rs = st.executeQuery("SELECT * FROM employee");

    System.out.println("\nEmployee Records");
    System.out.println("--------------------------------------------");

    while(rs.next()) {

        System.out.println(
            rs.getInt("empid") + "  " +
            rs.getString("empname") + "  " +
            rs.getString("department") + "  " +
            rs.getDouble("salary")
        );

    }

    rs.close();
    st.close();

    break;

    case 3:

    System.out.print("Enter Employee ID to Update: ");
    int updateId = sc.nextInt();

    System.out.print("Enter New Salary: ");
    double newSalary = sc.nextDouble();

    String updateQuery = "UPDATE employee SET salary=? WHERE empid=?";

    PreparedStatement ps2 = con.prepareStatement(updateQuery);

    ps2.setDouble(1, newSalary);
    ps2.setInt(2, updateId);

    int updated = ps2.executeUpdate();

    if(updated > 0)
        System.out.println("Employee Updated Successfully!");
    else
        System.out.println("Employee Not Found!");

    ps2.close();

    break;
case 4:

    System.out.print("Enter Employee ID to Delete: ");
    int deleteId = sc.nextInt();

    String deleteQuery = "DELETE FROM employee WHERE empid=?";

    PreparedStatement ps3 = con.prepareStatement(deleteQuery);

    ps3.setInt(1, deleteId);

    int deleted = ps3.executeUpdate();

    if(deleted > 0)
        System.out.println("Employee Deleted Successfully!");
    else
        System.out.println("Employee Not Found!");

    ps3.close();

    break;
case 5:

    System.out.println("Thank You!");

    con.close();
    sc.close();

    break;

    default:
        System.out.println("Invalid Choice");
}
} while(choice != 5);
 } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

