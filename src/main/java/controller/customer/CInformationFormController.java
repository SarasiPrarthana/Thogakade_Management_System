package controller.customer;

import db.DBConnection;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CInformationFormController implements Initializable {

    CustomerService customerService = new CustomerController();

    ObservableList<CustomerInfoDTO> customerInfoDTOS = FXCollections.observableArrayList();

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colID.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("DateOfBirth"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("Salary"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("City"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("Province"));
        colPostalCode.setCellValueFactory(new PropertyValueFactory<>("PostalCode"));

        loadCustomerDetails();

//        txtTbl.setItems(customerInfoDTOS);

        txtTbl.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
//
            if (newValue != null) {
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

    @FXML
    void btnAddAction(ActionEvent event) {

        String customerID = txtCustID.getText();
        String title = txtTitle.getText();
        String name = txtName.getText();
        String dob = txtDOB.getText();
        double salary = Double.parseDouble(txtSalary.getText());
        String address = txtAddress.getText();
        String city = txtCity.getText();
        String province = txtProvince.getText();
        String postalCode = txtPostalCode.getText();

//        CustomerController customerController  = new CustomerController();
        customerService.addCustomerDetails(customerID,title,name,dob,salary,address,city,province,postalCode);
        loadCustomerDetails();
        clearFields();

        }

    @FXML
    void btnDeleteAction(ActionEvent event) {

//        CustomerController customerController = new CustomerController();
        customerService.deleteCustomerDetails(txtCustID.getText());
        clearFields();
        loadCustomerDetails();

    }

    @FXML
    void btnUpdateAction(ActionEvent event) {


        String customerID = txtCustID.getText();
        String title = txtTitle.getText();
        String name = txtName.getText();
        String dob = txtDOB.getText();
        double salary = Double.parseDouble(txtSalary.getText());
        String address = txtAddress.getText();
        String city = txtCity.getText();
        String province = txtProvince.getText();
        String postalCode = txtPostalCode.getText();

        customerService.updateCustomerDetails(customerID,title,name,dob,salary,address,city,province,postalCode);
        loadCustomerDetails();
        clearFields();
    }

    @FXML
    void btnClearAction(ActionEvent event) {

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

    //load all rooms method
    private void loadCustomerDetails() {

        customerInfoDTOS.clear();

        ObservableList<CustomerInfoDTO> customerInfoDTOS = javafx.collections.FXCollections.observableArrayList();

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Customer");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                CustomerInfoDTO customerInfoDTO = new CustomerInfoDTO(
                        resultSet.getString("CustomerID"),
                        resultSet.getString("Title"),
                        resultSet.getString("Name"),
                        resultSet.getString("DateOfBirth"),
                        resultSet.getDouble("Salary"),
                        resultSet.getString("Address"),
                        resultSet.getString("City"),
                        resultSet.getString("Province"),
                        resultSet.getString("PostalCode")
                );
                customerInfoDTOS.add(customerInfoDTO);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        txtTbl.setItems(customerInfoDTOS);
    }

    public void clearFields(){
        txtCustID.clear();
        txtTitle.clear();
        txtName.clear();
        txtDOB.clear();
        txtSalary.clear();
        txtAddress.clear();
        txtCity.clear();
        txtProvince.clear();
        txtPostalCode.clear();
    }

}
