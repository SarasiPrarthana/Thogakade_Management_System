package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.dto.ItemInfoDTO;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemInformationController implements Initializable {

    ObservableList<ItemInfoDTO> itemInfoDTOS = FXCollections.observableArrayList(
            new ItemInfoDTO("1001","Red Rice 5kg","Groceries",40,1200.00),
            new ItemInfoDTO("1001","Red Rice 5kg","Groceries",40,1200.00),
            new ItemInfoDTO("1001","Red Rice 5kg","Groceries",40,1200.00),
            new ItemInfoDTO("1001","Red Rice 5kg","Groceries",40,1200.00),
            new ItemInfoDTO("1001","Red Rice 5kg","Groceries",40,1200.00),
            new ItemInfoDTO("1001","Red Rice 5kg","Groceries",40,1200.00),
            new ItemInfoDTO("1001","Red Rice 5kg","Groceries",40,1200.00)
    );

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
    private TableView<?> txtTbl;

    @FXML
    private TextField txtUnitPrice;

    @FXML
    void btnAddAction(ActionEvent event) {

    }

    @FXML
    void btnClearAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
