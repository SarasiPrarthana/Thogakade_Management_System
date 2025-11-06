package controller.employee;

import db.DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeController implements EmployeeService{

    @Override
    public void addEmployeeDetails(String EmployeeID, String name, String nic, String dob, String position, double salary, String contactNumber, String address, String joinedDate, String status) {

        try {
            Connection connection = DBConnection.getInstance().getConnection();

            String SQL = "Insert INTO Employee VALUES(?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setObject(1, EmployeeID);
            preparedStatement.setObject(2, name);
            preparedStatement.setObject(3, nic);
            preparedStatement.setObject(4, dob);
            preparedStatement.setObject(5, position);
            preparedStatement.setObject(6, salary);
            preparedStatement.setObject(7, contactNumber);
            preparedStatement.setObject(8, address);
            preparedStatement.setObject(9, joinedDate);
            preparedStatement.setObject(10, status);

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteEmployeeDetails(String employeeId) {

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM Customer WHERE EmployeeID = ?");

            pstm.setObject(1, employeeId);
            pstm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void updateEmployeeDetails(String EmployeeID, String name, String nic, String dob, String position, double salary, String contactNumber, String address, String joinedDate, String status) {

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String SQL = "UPDATE Customer SET WHERE EmployeeID = ?, Name = ?, NIC = ?,DateOfBirth = ?,Position = ?,Salary = ?,ContactNumber = ?,Address = ?,JoinedDate = ?,Status = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setObject(1, EmployeeID);
            preparedStatement.setObject(2, name);
            preparedStatement.setObject(3, nic);
            preparedStatement.setObject(4, dob);
            preparedStatement.setObject(5, position);
            preparedStatement.setObject(6, salary);
            preparedStatement.setObject(7, contactNumber);
            preparedStatement.setObject(8, address);
            preparedStatement.setObject(9, joinedDate);
            preparedStatement.setObject(10, status);

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
