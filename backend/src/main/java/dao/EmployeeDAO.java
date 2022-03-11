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
  public static synchronized void insertEmployee(Employee employee) throws SQLException, ClassNotFoundException {
    String updateStmt = "INSERT INTO employee (Username, Password, Admin) "
            + "VALUES ('" + employee.getUsername() + "', '" + employee.getPassword() +
            "', '" + employee.isAdmin() + "')";

    try {
      DBUtil.dbExecuteUpdate(updateStmt);
    } catch (SQLException e) {
      System.out.print("Error occurred while INSERT Operation: " + e);
      throw e;
    }
  }

  /**
   * Check weather an administrator account already exist or not.
   * If admin exist go to Auth HomePage, if it doesn't go to Admin Sign Up process
   * @return True if exists, False otherwise
   */
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
   * @return A List of employees
   */
  public static synchronized ArrayList<Employee> getAllEmployees() throws SQLException, ClassNotFoundException {
    String selectStmt = "SELECT * FROM employee WHERE Admin=0";

    try {
      // Get ResultSet from dbExecuteQuery method
      ResultSet rsEmployees = DBUtil.dbExecuteQuery(selectStmt);

      // Return employee object
      return getEmployeeList(rsEmployees);
    } catch (SQLException e) {
      System.out.println("SQL select operation has been failed: " + e);
      // Return exception
      throw e;
    }
  }



  /**
   * Search for a specific employee inside Employee's DB table
   * 
   * @param Username Username of target employee
   * @return Employee object
   */
  public static synchronized Employee searchEmployee(String Username) throws SQLException, ClassNotFoundException {
    String selectStmt = "SELECT * FROM employee WHERE Username='" + Username + "'";

    try {
      // Get ResultSet from dbExecuteQuery method
      ResultSet rsEmp = DBUtil.dbExecuteQuery(selectStmt);

      return getEmployeeFromResultSet(rsEmp);
    } catch (SQLException e) {
      System.out.println("While searching an employee with " + Username + " username, an error occurred: " + e);
      // Return exception
      throw e;
    }
  }

  /**
   * Tries to authenticate the given user as an Employee
   * @param clientEmployee User from GUI
   * @return Employee filled with data if it exists in DB, null otherwise
   */
  public static synchronized Employee logIn(Employee clientEmployee) throws SQLException, ClassNotFoundException {
    String selectStmt = "SELECT * FROM employee WHERE Username='" + clientEmployee.getUsername() +
            "' AND Password='" + clientEmployee.getPassword() + "'";

    try {
      // Get ResultSet from dbExecuteQuery method
      ResultSet rsEmp = DBUtil.dbExecuteQuery(selectStmt);

      return getEmployeeFromResultSet(rsEmp);
    } catch (SQLException e) {
      System.out.println("While searching an employee with " + clientEmployee.getUsername() + " username, an error occurred: " + e);
      // Return exception
      throw e;
    }
  }

  private static ArrayList<Employee> getEmployeeList(ResultSet rs) throws SQLException {
    // Declare an observable List which comprises Employee objects
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

  private static Employee getEmployeeFromResultSet(ResultSet rsEmp) throws SQLException {
    Employee employee = null;

    if (rsEmp.next()) {
      employee = new Employee(rsEmp.getString("Username"), rsEmp.getString("Password"));
      employee.setID(rsEmp.getInt("ID"));
    }

    return employee;
  }
}
