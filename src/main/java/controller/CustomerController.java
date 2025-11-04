package controller;

import db.DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerController {

    public void addCustomerDetails(String customerID, String title, String name,String dob,double salary,String address,String city,String province,String postalCode){
        try {
            Connection connection = DBConnection.getInstance().getConnection();

            String SQL = "Insert INTO Customer VALUES(?,?,?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setObject(1, customerID);
            preparedStatement.setObject(2, title);
            preparedStatement.setObject(3, name);
            preparedStatement.setObject(4, dob);
            preparedStatement.setObject(5, salary);
            preparedStatement.setObject(6, address);
            preparedStatement.setObject(7, city);
            preparedStatement.setObject(8, province);
            preparedStatement.setObject(9, postalCode);

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteCustomerDetails(String customerId){
        try {
            Connection connection = DBConnection.getInstance().getConnection();

            PreparedStatement pstm = connection.prepareStatement("DELETE FROM Customer WHERE CustomerID = ?");

            pstm.setObject(1,customerId);
            pstm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateCustomerDetails(String customerID,String title,String name,String dob,double salary,String address,String city,String province,String postalCode){
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String SQL = "UPDATE Customer SET Title=?, Name=?, DateOfBirth=?, Salary=?, Address=?, City=?, Province=?, PostalCode=? WHERE CustomerID=?";

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setObject(1, customerID);
            preparedStatement.setObject(2, title);
            preparedStatement.setObject(3, name);
            preparedStatement.setObject(4, dob);
            preparedStatement.setObject(5, salary);
            preparedStatement.setObject(6, address);
            preparedStatement.setObject(7, city);
            preparedStatement.setObject(8, province);
            preparedStatement.setObject(9, postalCode);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
