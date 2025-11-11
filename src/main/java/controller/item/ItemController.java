package controller.item;

import db.DBConnection;
import javafx.collections.ObservableList;
import model.dto.ItemInfoDTO;
import model.dto.SupplierInfoDTO;

import java.sql.*;

public class ItemController implements ItemService{
    @Override
    public void addItemDetails(String itemCode, String description, String category, int qtyOnHand, double unitPrice) {

        try {
            Connection connection = DBConnection.getInstance().getConnection();

            String SQL = "Insert INTO Item VALUES(?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setObject(1, itemCode);
            preparedStatement.setObject(2, description);
            preparedStatement.setObject(3, category);
            preparedStatement.setObject(4, qtyOnHand);
            preparedStatement.setObject(5, unitPrice);

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteItemDetails(String ItemCode) {

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM Item WHERE ItemCode = ?");

            pstm.setObject(1, ItemCode);
            pstm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void updateItemDetails(String itemCode, String description, String category, int qtyOnHand, double unitPrice) {

        try {
            Connection connection = DBConnection.getInstance().getConnection();

            String SQL = "UPDATE Item SET UnitPrice = ?, Description = ?, Category = ?,QtyOnHand = ? WHERE ItemCode = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setObject(1, unitPrice);
            preparedStatement.setObject(2, description);
            preparedStatement.setObject(3, category);
            preparedStatement.setObject(4, qtyOnHand);
            preparedStatement.setObject(5, itemCode);

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<ItemInfoDTO> loadItemDetails() {

        ObservableList<ItemInfoDTO> itemDetails = javafx.collections.FXCollections.observableArrayList();

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String SQL = ("SELECT * FROM Item" );
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                itemDetails.add(new ItemInfoDTO(

                        // column name pass
                        resultSet.getString("ItemCode"),
                        resultSet.getString("Description"),
                        resultSet.getString("Category"),
                        resultSet.getInt("QtyOnHand"),
                        resultSet.getDouble("UnitPrice")
                    )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return itemDetails;
    }
}
