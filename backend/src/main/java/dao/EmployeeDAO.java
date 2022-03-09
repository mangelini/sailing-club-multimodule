package dao;


import common.DBUtil;
import entities.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAO {
  /**
   * Creates a new employee record in the Employee DB Table
   * 
   * @param employee Employee objects that will be transformed in a DB record
   */
  public static synchronized boolean insertEmployee(Employee employee) throws SQLException, ClassNotFoundException {
    boolean empAdded = false;

    String updateStmt = "INSERT INTO employee (Username, Password) "
        + "VALUES ('" + employee.getUsername() + "', '" + employee.getPassword() + "')";

    try {
      DBUtil.dbExecuteUpdate(updateStmt);
    } catch (SQLException e) {
      System.out.print("Error occurred while INSERT Operation: " + e);
      throw e;
    } finally {
      empAdded = true;
    }

    return empAdded;
  }

  /**
   * Creates a new admin record in the Employee DB Table
   *
   * @param employee Admin object that will be transformed in a DB record
   */
  public static synchronized boolean insertAdmin(Employee employee) throws SQLException, ClassNotFoundException {
    boolean empAdded = false;

    String updateStmt = "INSERT INTO employee (Username, Password, Admin) "
            + "VALUES ('" + employee.getUsername() + "', '" + employee.getPassword() +
            "', '" + employee.isAdmin() + "')";

    try {
      DBUtil.dbExecuteUpdate(updateStmt);
    } catch (SQLException e) {
      System.out.print("Error occurred while INSERT Operation: " + e);
      throw e;
    } finally {
      empAdded = true;
    }

    return empAdded;
  }

  public static synchronized boolean adminAlreadyExist()
          throws SQLException, ClassNotFoundException {
    String selectStmt = "SELECT * FROM employee WHERE Admin=1";

    try {
      // Get ResultSet from dbExecuteQuery method
      ResultSet rsEmployees = DBUtil.dbExecuteQuery(selectStmt);

      // Return employee object
      return !getEmployeeList(rsEmployees).isEmpty();
    } catch (SQLException e) {
      System.out.println("SQL select operation has been failed: " + e);
      // Return exception
      throw e;
    }
  }

  /**
   * Updates the username of specified Employee
   * 
   * @param ID          ID's of Member
   * @param newUsername Updated username
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public static synchronized void updateEmployeeUsername(Integer ID, String newUsername)
      throws SQLException, ClassNotFoundException {
    // Declare a UPDATE statement
    String updateStmt = "UPDATE employee" +
        " SET Username ='" + newUsername + "'" +
        " WHERE ID ='" + ID + "'";

    // Execute UPDATE operation
    try {
      DBUtil.dbExecuteUpdate(updateStmt);
    } catch (SQLException e) {
      System.out.print("Error occurred while UPDATE Operation: " + e);
      throw e;
    }
  }

  /**
   * Updates the password of specified Employee
   * 
   * @param ID          ID of Employee
   * @param newPassword Updated password
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public static synchronized void updateEmployeePassword(Integer ID, String newPassword)
      throws SQLException, ClassNotFoundException {
    // Declare a UPDATE statement
    String updateStmt = "UPDATE employee" +
        " SET Password ='" + newPassword + "'" +
        " WHERE ID ='" + ID + "'";

    // Execute UPDATE operation
    try {
      DBUtil.dbExecuteUpdate(updateStmt);
    } catch (SQLException e) {
      System.out.print("Error occurred while UPDATE Operation: " + e);
      throw e;
    }
  }

  /**
   * Gets all employees of the club
   * 
   * @return A List of members
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public static synchronized ArrayList<Employee> getAllEmployees() throws SQLException, ClassNotFoundException {
    String selectStmt = "SELECT * FROM employee";

    try {
      // Get ResultSet from dbExecuteQuery method
      ResultSet rsEmployees = DBUtil.dbExecuteQuery(selectStmt);

      // Send ResultSet to the getMemberList method and get member object
      ArrayList<Employee> empList = getEmployeeList(rsEmployees);

      // Return employee object
      return empList;
    } catch (SQLException e) {
      System.out.println("SQL select operation has been failed: " + e);
      // Return exception
      throw e;
    }
  }

  private static ArrayList<Employee> getEmployeeList(ResultSet rs) throws SQLException, ClassNotFoundException {
    // Declare an observable List which comprises of Employee objects
    ArrayList<Employee> empList = new ArrayList<>();

    while (rs.next()) {
      Employee employee = new Employee();
      employee.setUsername(rs.getString("Username"));
      employee.setPassword(rs.getString("Password"));
      employee.setID(rs.getInt("ID"));

      empList.add(employee);
    }

    return empList;
  }

  /**
   * Search for a specific employee inside Employee's DB table
   * 
   * @param Username Username of target employee
   * @return Employee object
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public static synchronized Employee searchEmployee(String Username) throws SQLException, ClassNotFoundException {
    String selectStmt = "SELECT * FROM employee WHERE Username='" + Username + "'";

    try {
      // Get ResultSet from dbExecuteQuery method
      ResultSet rsEmp = DBUtil.dbExecuteQuery(selectStmt);

      // Send ResultSet to the getEmployeeFromResultSet method and get employee object
      Employee employee = getEmployeeFromResultSet(rsEmp);

      return employee;
    } catch (SQLException e) {
      System.out.println("While searching an employee with " + Username + " username, an error occurred: " + e);
      // Return exception
      throw e;
    }
  }

  public static synchronized Employee logIn(Employee clientEmployee) throws SQLException, ClassNotFoundException {
    String selectStmt = "SELECT * FROM employee WHERE Username='" + clientEmployee.getUsername() +
            "' AND Password='" + clientEmployee.getPassword() + "'";

    try {
      // Get ResultSet from dbExecuteQuery method
      ResultSet rsEmp = DBUtil.dbExecuteQuery(selectStmt);

      // Send ResultSet to the getEmployeeFromResultSet method and get employee object
      Employee employee = getEmployeeFromResultSet(rsEmp);

      return employee;
    } catch (SQLException e) {
      System.out.println("While searching an employee with " + clientEmployee.getUsername() + " username, an error occurred: " + e);
      // Return exception
      throw e;
    }
  }

  private static Employee getEmployeeFromResultSet(ResultSet rsEmp) throws SQLException {
    Employee employee = null;

    if (rsEmp.next()) {
      employee = new Employee(rsEmp.getString("Username"), rsEmp.getString("Password"));
      employee.setID(rsEmp.getInt("ID"));
    }

    return employee;
  }
}
