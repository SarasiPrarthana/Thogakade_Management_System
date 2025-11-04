package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dto.ItemInfoDTO;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ItemInformationController implements Initializable {

    ObservableList<ItemInfoDTO> itemInfoDTOS = FXCollections.observableArrayList();
//            new ItemInfoDTO("1001","Red Rice 5kg","Groceries",40,1200.00),
//            new ItemInfoDTO("1002","Red Rice 5kg","Groceries",40,1200.00),
//            new ItemInfoDTO("1003","Red Rice 5kg","Groceries",40,1200.00),
//            new ItemInfoDTO("1004","Red Rice 5kg","Groceries",40,1200.00),
//            new ItemInfoDTO("1005","Red Rice 5kg","Groceries",40,1200.00),
//            new ItemInfoDTO("1006","Red Rice 5kg","Groceries",40,1200.00),
//            new ItemInfoDTO("1007","Red Rice 5kg","Groceries",40,1200.00)

    @FXML
    private TableColumn<?, ?> colCategory;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colQtyOnHand;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private TextField txtCategory;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtItemCode;

    @FXML
    private TextField txtQtyOnHand;

    @FXML
    private TableView<ItemInfoDTO> txtTbl;

    @FXML
    private TextField txtUnitPrice;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));

        txtTbl.setItems(itemInfoDTOS);

        txtTbl.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null){
                txtItemCode.setText(newValue.getItemCode());
                txtDescription.setText(newValue.getDescription());
                txtCategory.setText(newValue.getCategory());
                txtQtyOnHand.setText(String.valueOf(newValue.getQtyOnHand()));
                txtUnitPrice.setText(String.valueOf(newValue.getUnitPrice()));
            }

        });
    }

    @FXML
    void btnAddAction(ActionEvent event) {

        String itemCode = txtItemCode.getText();
        String description = txtDescription.getText();
        String category = txtCategory.getText();
        int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());
        Double unitPrice = Double.valueOf(txtUnitPrice.getText());

        ItemInfoDTO itemInfoDTO = new ItemInfoDTO(itemCode,description,category,qtyOnHand,unitPrice);
        itemInfoDTOS.add(itemInfoDTO);

        txtTbl.refresh();

        txtItemCode.setText("");
        txtDescription.setText("");
        txtCategory.setText("");
        txtQtyOnHand.setText("");
        txtUnitPrice.setText("");

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade_management_system", "root", "1234");

            String SQL = "Insert INTO Item VALUES(?,?,?,?,?,?,?,?,?)";

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

    @FXML
    void btnClearAction(ActionEvent event) {

        txtItemCode.setText("");
        txtDescription.setText("");
        txtCategory.setText("");
        txtQtyOnHand.setText("");
        txtUnitPrice.setText("");

    }

    @FXML
    void btnDeleteAction(ActionEvent event) {

        ItemInfoDTO selectedItem = txtTbl.getSelectionModel().getSelectedItem();
        itemInfoDTOS.remove(selectedItem);
        txtTbl.refresh();

    }

    @FXML
    void btnUpdateAction(ActionEvent event) {

        ItemInfoDTO selectedItem = txtTbl.getSelectionModel().getSelectedItem();

        selectedItem.setItemCode(txtItemCode.getText());
        selectedItem.setDescription(txtDescription.getText());
        selectedItem.setCategory(txtCategory.getText());
        selectedItem.setQtyOnHand(Integer.parseInt(txtQtyOnHand.getText()));
        selectedItem.setUnitPrice(Double.parseDouble(txtUnitPrice.getText()));

        txtTbl.refresh();

    }
}
