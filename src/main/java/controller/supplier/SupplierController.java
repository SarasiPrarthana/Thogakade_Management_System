package controller.supplier;

import db.DBConnection;
import javafx.collections.ObservableList;
import model.dto.SupplierInfoDTO;

import java.sql.*;

public class SupplierController implements SupplierService{
    @Override
    public void addSupplierDetails(String supplierID, String name, String companyName, String address, String city, String province, String postalCode, String phone, String email) {

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String SQL = "Insert INTO Supplier VALUES(?,?,?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setObject(1, supplierID);
            preparedStatement.setObject(2, name);
            preparedStatement.setObject(3, companyName);
            preparedStatement.setObject(4, address);
            preparedStatement.setObject(5, city);
            preparedStatement.setObject(6, province);
            preparedStatement.setObject(7, postalCode);
            preparedStatement.setObject(8, phone);
            preparedStatement.setObject(9, email);

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteSupplierDetails(String supplierID) {

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM Supplier WHERE supplierID = ?");

            pstm.setObject(1, supplierID);
            pstm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateSupplierDetails(String supplierID, String name, String companyName, String address, String city, String province, String postalCode, String phone, String email) {

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String SQL = "UPDATE Supplier SET email = ? , name = ?, companyName = ?,address = ?,city = ?,province = ?,postalCode = ?,phone = ? WHERE supplierID = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setObject(1, email);
            preparedStatement.setObject(2, name);
            preparedStatement.setObject(3, companyName);
            preparedStatement.setObject(4, address);
            preparedStatement.setObject(5, city);
            preparedStatement.setObject(6, province);
            preparedStatement.setObject(7, postalCode);
            preparedStatement.setObject(8, phone);
            preparedStatement.setObject(9, supplierID);

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<SupplierInfoDTO> loadSupplierDetails() {

        ObservableList<SupplierInfoDTO> supplierDetails = javafx.collections.FXCollections.observableArrayList();

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String SQL = "SELECT * FROM Supplier";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                supplierDetails.add(new SupplierInfoDTO(

                                // column name pass
                                resultSet.getString("supplierID"),
                                resultSet.getString("name"),
                                resultSet.getString("companyName"),
                                resultSet.getString("address"),
                                resultSet.getString("city"),
                                resultSet.getString("province"),
                                resultSet.getString("postalCode"),
                                resultSet.getString("phone"),
                                resultSet.getString("email")
                        )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return supplierDetails;
    }



}
