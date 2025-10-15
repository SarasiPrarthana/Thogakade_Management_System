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

import java.net.URL;
import java.util.ResourceBundle;

public class CInformationController implements Initializable {

    ObservableList<CustomerInfoDTO> customerInfoDTOS = FXCollections.observableArrayList(
            new CustomerInfoDTO("C001","Mr","Danapala","1981-02-06",40000.0,"No.20 Walana","Panadura","Western","12500"),
            new CustomerInfoDTO("C001","Mr","Danapala","1981-02-06",40000.0,"No.20 Walana","Panadura","Western","12500")
    );

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colCity;

    @FXML
    private TableColumn<?, ?> colDOB;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPostalCode;

    @FXML
    private TableColumn<?, ?> colProvince;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtCity;

    @FXML
    private TextField txtCustID;

    @FXML
    private TextField txtDOB;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPostalCode;

    @FXML
    private TextField txtProvince;

    @FXML
    private TextField txtSalary;

    @FXML
    private TableView<CustomerInfoDTO> txtTbl;

    @FXML
    private TextField txtTitle;

    @FXML
    void btnAddAction(ActionEvent event) {

        String customerID = txtCustID.getText();
        String title = txtTitle.getText();
        String name = txtName.getText();
        String dob = txtDOB.getText();
        Double salary = Double.valueOf(txtSalary.getText());
        String address = txtAddress.getText();
        String city = txtCity.getText();
        String province = txtProvince.getText();
        String postalCode = txtPostalCode.getText();

        CustomerInfoDTO customerInfoDTO = new CustomerInfoDTO(customerID,title,name,dob,salary,address,city,province,postalCode);
        customerInfoDTOS.add(customerInfoDTO);

        txtTbl.refresh();

        txtCustID.setText("");
        txtTitle.setText("");
        txtName.setText("");
        txtDOB.setText("");
        txtSalary.setText("");
        txtAddress.setText("");
        txtCity.setText("");
        txtProvince.setText("");
        txtPostalCode.setText("");
    }

    @FXML
    void btnDeleteAction(ActionEvent event) {

        CustomerInfoDTO selectedCustomer = txtTbl.getSelectionModel().getSelectedItem();
        customerInfoDTOS.remove(selectedCustomer);
        txtTbl.refresh();

    }

    @FXML
    void btnUpdateAction(ActionEvent event) {

//        CustomerInfoDTO selectedCustomer = txtTbl.getSelectionModel().getSelectedItem();
//
//        selectedCustomer.setCustomerID(txtCustID.getText());
//        selectedCustomer.setCustomerID(txtCustID.getText());
//        selectedCustomer.setCustomerID(txtCustID.getText());
//        selectedCustomer.setCustomerID(txtCustID.getText());
//        selectedCustomer.setCustomerID(txtCustID.getText());
//        selectedCustomer.setCustomerID(txtCustID.getText());
//        selectedCustomer.setCustomerID(txtCustID.getText());
//        selectedCustomer.setCustomerID(txtCustID.getText());
//        selectedCustomer.setCustomerID(txtCustID.getText());

    }

    @FXML
    void btnViewActiom(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
        colPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));

        txtTbl.setItems(customerInfoDTOS);

        txtTbl.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null){
                txtCustID.setText(newValue.getCustomerID());
                txtTitle.setText(newValue.getTitle());
                txtName.setText(newValue.getName());
                txtDOB.setText(newValue.getDob());
                txtSalary.setText(String.valueOf(newValue.getSalary()));
                txtAddress.setText(newValue.getAddress());
                txtCity.setText(newValue.getCity());
                txtProvince.setText(newValue.getProvince());
                txtPostalCode.setText(newValue.getPostalCode());
            }
        });


    }
}
