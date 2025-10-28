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

    ObservableList<CustomerInfoDTO> customerInfoDTOS = FXCollections.observableArrayList(
            new CustomerInfoDTO("C001", "Mr", "Danapala", "1981-02-06", 40000.0, "No.20 Walana", "Panadura", "Western", "12500"),
            new CustomerInfoDTO("C002", "Mrs", "Inoka", "1999-05-30", 35000.0, "No.406 Alubomulla", "Panadura", "Western", "12500"),
            new CustomerInfoDTO("C003", "Mrs", "Darshani", "1978-07-01", 80000.0, "No.3/A Gamunu Mawatha", "Moratuwa", "Western", "12500"),
            new CustomerInfoDTO("C004", "Mr", "Kamal", "2000-02-14", 48000.0, "No.200/A S.Mahinda Road", "Bandaragama", "Western", "12500"),
            new CustomerInfoDTO("C005", "Mrs", "Nimali", "1995-12-09", 52000.0, "No.22 Church Road", "Kaluthara", "Western", "12500"),
            new CustomerInfoDTO("C006", "Mr", "Dasun", "1979-01-21", 100000.0, "No.456 Kiriberiya", "Panadura", "Western", "12500")
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

                PreparedStatement pstm = connection.prepareStatement("DELETE FROM Customer WHERE customer_id = ?");

                pstm.setObject(1, txtCustID.getText());
                pstm.executeUpdate();

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

            String SQL = "UPDATE Customer SET customerID = ?, title = ?, name = ?,dob = ?,salary = ?,address = ?,city = ?,province = ?,postalCode = ?";

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
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM item" );
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                CustomerInfoDTO customerInfoDTO = new CustomerInfoDTO(

                        // column name pass
                        resultSet.getString("customerID"),
                        resultSet.getString("title"),
                        resultSet.getString("name"),
                        resultSet.getString("dob"),
                        resultSet.getDouble("salary"),
                        resultSet.getString("address"),
                        resultSet.getString("city"),
                        resultSet.getString("province"),
                        resultSet.getString("postalCode")
                );
                System.out.println(customerInfoDTO);
                customerInfoDTOS.add(customerInfoDTO);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        txtTbl.setItems(customerInfoDTOS);
    }
}
