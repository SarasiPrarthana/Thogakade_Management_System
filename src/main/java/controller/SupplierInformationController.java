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
import model.dto.CustomerInfoDTO;
import model.dto.ItemInfoDTO;
import model.dto.SupplierInfoDTO;

import java.net.URL;
import java.util.ResourceBundle;

public class SupplierInformationController implements Initializable {

    ObservableList<SupplierInfoDTO> supplierInfoDTOS = FXCollections.observableArrayList(
            new SupplierInfoDTO("S001","Fernando","Agro Foods Pvt Ltd","No.45 Main Street","Matara","Southern","81000","0712345678","agrofoods@gmail.com"),
            new SupplierInfoDTO("S001","Fernando","Agro Foods Pvt Ltd","No.45 Main Street","Matara","Southern","81000","0712345678","agrofoods@gmail.com"),
            new SupplierInfoDTO("S001","Fernando","Agro Foods Pvt Ltd","No.45 Main Street","Matara","Southern","81000","0712345678","agrofoods@gmail.com"),
            new SupplierInfoDTO("S001","Fernando","Agro Foods Pvt Ltd","No.45 Main Street","Matara","Southern","81000","0712345678","agrofoods@gmail.com"),
            new SupplierInfoDTO("S001","Fernando","Agro Foods Pvt Ltd","No.45 Main Street","Matara","Southern","81000","0712345678","agrofoods@gmail.com"),
            new SupplierInfoDTO("S001","Fernando","Agro Foods Pvt Ltd","No.45 Main Street","Matara","Southern","81000","0712345678","agrofoods@gmail.com"),
            new SupplierInfoDTO("S001","Fernando","Agro Foods Pvt Ltd","No.45 Main Street","Matara","Southern","81000","0712345678","agrofoods@gmail.com")
    );

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colCity;

    @FXML
    private TableColumn<?, ?> colCompanyName;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPhone;

    @FXML
    private TableColumn<?, ?> colPostalCode;

    @FXML
    private TableColumn<?, ?> colProvince;

    @FXML
    private TableColumn<?, ?> colSupplierID;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtCity;

    @FXML
    private TextField txtCompanyName;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtPostalCode;

    @FXML
    private TextField txtProvince;

    @FXML
    private TextField txtSupplierID;

    @FXML
    private TableView<SupplierInfoDTO> txtTbl;

    @FXML
    void btnAddAction(ActionEvent event) {

        String supplierID = txtSupplierID.getText();
        String name = txtName.getText();
        String companyName = txtCompanyName.getText();
        String address = txtAddress.getText();
        String city = txtCity.getText();
        String province = txtProvince.getText();
        String postalCode = txtPostalCode.getText();
        String phone = txtPhone.getText();
        String email = txtEmail.getText();


        SupplierInfoDTO supplierInfoDTO = new SupplierInfoDTO(supplierID,name,companyName,address,city,province,postalCode,phone,email);
        supplierInfoDTOS.add(supplierInfoDTO);

        txtTbl.refresh();

        txtSupplierID.setText("");
        txtName.setText("");
        txtCompanyName.setText("");
        txtAddress.setText("");
        txtCity.setText("");
        txtProvince.setText("");
        txtPostalCode.setText("");
        txtPhone.setText("");
        txtEmail.setText("");
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

        colSupplierID.setCellValueFactory(new PropertyValueFactory<>("supplierID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCompanyName.setCellValueFactory(new PropertyValueFactory<>("CompanyName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
        colPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        txtTbl.setItems(supplierInfoDTOS);

        txtTbl.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null){
                txtSupplierID.setText(newValue.getSupplierID());
                txtName.setText(newValue.getName());
                txtCompanyName.setText(newValue.getCompanyName());
                txtAddress.setText(newValue.getAddress());
                txtCity.setText(newValue.getCity());
                txtProvince.setText(newValue.getProvince());
                txtPostalCode.setText(newValue.getPostalCode());
                txtPhone.setText(newValue.getPhone());
                txtEmail.setText(newValue.getEmail());
            }
        });


    }
    
}
