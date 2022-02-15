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
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public static void insertEmployee(Employee employee) throws SQLException, ClassNotFoundException {
    String updateStmt = "INSERT INTO employee (Username, Password) "
        + "VALUES ('" + employee.getUsername() + "', '" + employee.getPassword() + "')";

    try {
      DBUtil.dbExecuteUpdate(updateStmt);
    } catch (SQLException e) {
      System.out.print("Error occurred while INSERT Operation: " + e);
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
  public static void updateEmployeeUsername(Integer ID, String newUsername)
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
  public static void updateEmployeePassword(Integer ID, String newPassword)
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
  public static ArrayList<Employee> getAllEmployees() throws SQLException, ClassNotFoundException {
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
  public static Employee searchEmployee(String Username) throws SQLException, ClassNotFoundException {
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

  private static Employee getEmployeeFromResultSet(ResultSet rsEmp) throws SQLException {
    Employee employee = new Employee();

    if (rsEmp.next()) {
      employee.setUsername(rsEmp.getString("Username"));
      employee.setPassword(rsEmp.getString("Password"));
      employee.setID(rsEmp.getInt("ID"));
    }

    return employee;
  }
}
