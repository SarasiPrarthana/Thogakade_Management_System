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
import java.sql.*;
import java.util.ResourceBundle;

public class CInformationController implements Initializable {

    ObservableList<CustomerInfoDTO> customerInfoDTOS = FXCollections.observableArrayList();
//            new CustomerInfoDTO("C001", "Mr.", "Danapala", "1981-02-06", 40000, "No.20 Walana", "Panadura", "Western", "12500"),
//            new CustomerInfoDTO("C002", "Ms.", "Samanthi", "1990-05-12", 55000, "No.15 Galle", "Galle", "Southern", "8000"),
//            new CustomerInfoDTO("C003", "Mrs.", "Kumari", "1985-11-23", 72000, "No.5 Kandy", "Kandy", "Central", "20000"),
//            new CustomerInfoDTO("C004", "Miss.", "Niluka", "1978-07-30", 95000, "No.8 Jaffna", "Jaffna", "Northern", "15000"),
//            new CustomerInfoDTO("C005", "Mr.", "Perera", "1992-03-15", 48000, "No.12 Matara", "Matara", "Southern", "9000"),
//            new CustomerInfoDTO("C006", "Ms.", "Lakshmi", "1988-09-09", 67000, "No.3 Negombo", "Negombo", "Western", "11000"),
//            new CustomerInfoDTO("C007", "Mrs.", "Fernando", "1975-12-01", 83000, "No.18 Trincomalee", "Trincomalee", "Eastern", "13000"),
//            new CustomerInfoDTO("C008", "Miss.", "Jayathilaka", "1983-06-21", 76000, "No.7 Anuradhapura", "Anuradhapura", "North Central", "14000"),
//            new CustomerInfoDTO("C009", "Mr.", "Silva", "1995-04-10", 52000, "No.22 Kurunegala", "Kurunegala", "North Western", "10000"),
//            new CustomerInfoDTO("C010", "Ms.", "Wijesinghe", "1980-08-18", 88000, "No.9 Badulla", "Badulla", "Uva", "16000"


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

        CustomerInfoDTO customerInfoDTO = new CustomerInfoDTO(customerID, title, name, dob, salary, address, city, province, postalCode);
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

            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade_management_system", "root", "1234");

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
                loadCustomerDetails();
                clearFields();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    @FXML
    void btnDeleteAction(ActionEvent event) {

        CustomerInfoDTO selectedCustomer = txtTbl.getSelectionModel().getSelectedItem();
        customerInfoDTOS.remove(selectedCustomer);
        txtTbl.refresh();

            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade_management_system", "root", "1234");

                PreparedStatement pstm = connection.prepareStatement("DELETE FROM Customer WHERE CustomerID = ?");

                pstm.setObject(1, txtCustID.getText());
                pstm.executeUpdate();
                clearFields();
                loadCustomerDetails();

            } catch (SQLException e) {
                throw new RuntimeException(e);
    }

    }

    @FXML
    void btnUpdateAction(ActionEvent event) {

        CustomerInfoDTO selectedCustomer = txtTbl.getSelectionModel().getSelectedItem();

        selectedCustomer.setCustomerID(txtCustID.getText());
        selectedCustomer.setTitle(txtTitle.getText());
        selectedCustomer.setName(txtName.getText());
        selectedCustomer.setDob(txtDOB.getText());
        selectedCustomer.setSalary(Double.parseDouble(txtSalary.getText()));
        selectedCustomer.setAddress(txtAddress.getText());
        selectedCustomer.setCity(txtCity.getText());
        selectedCustomer.setProvince(txtProvince.getText());
        selectedCustomer.setPostalCode(txtPostalCode.getText());

        txtTbl.refresh();

        String customerID = txtCustID.getText();
        String title = txtTitle.getText();
        String name = txtName.getText();
        String dob = txtDOB.getText();
        Double salary = Double.valueOf(txtSalary.getText());
        String address = txtAddress.getText();
        String city = txtCity.getText();
        String province = txtProvince.getText();
        String postalCode = txtPostalCode.getText();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade_management_system", "root", "1234");

            String SQL = "UPDATE Customer SET WHERE CustomerID = ?, Title = ?, Name = ?,DateOfBirth = ?,Salary = ?,Address = ?,City = ?,Province = ?,PostalCode = ?";

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
            loadCustomerDetails();
            clearFields();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

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

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade_management_system", "root", "1234");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Customer" );
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                CustomerInfoDTO customerInfoDTO = new CustomerInfoDTO(

                        // column name pass
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
                System.out.println(customerInfoDTO);
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
