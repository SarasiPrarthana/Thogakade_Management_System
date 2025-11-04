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
import model.dto.SupplierInfoDTO;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class SupplierInformationController implements Initializable {

    ObservableList<SupplierInfoDTO> supplierInfoDTOS = FXCollections.observableArrayList();
//            new SupplierInfoDTO("S001","Fernando","Agro Foods Pvt Ltd","No.45 Main Street","Matara","Southern","81000","0712345678","agrofoods@gmail.com"),
//            new SupplierInfoDTO("S002","Fernando","Agro Foods Pvt Ltd","No.45 Main Street","Matara","Southern","81000","0712345678","agrofoods@gmail.com"),
//            new SupplierInfoDTO("S003","Fernando","Agro Foods Pvt Ltd","No.45 Main Street","Matara","Southern","81000","0712345678","agrofoods@gmail.com"),
//            new SupplierInfoDTO("S004","Fernando","Agro Foods Pvt Ltd","No.45 Main Street","Matara","Southern","81000","0712345678","agrofoods@gmail.com"),
//            new SupplierInfoDTO("S005","Fernando","Agro Foods Pvt Ltd","No.45 Main Street","Matara","Southern","81000","0712345678","agrofoods@gmail.com"),
//            new SupplierInfoDTO("S006","Fernando","Agro Foods Pvt Ltd","No.45 Main Street","Matara","Southern","81000","0712345678","agrofoods@gmail.com"),
//            new SupplierInfoDTO("S007","Fernando","Agro Foods Pvt Ltd","No.45 Main Street","Matara","Southern","81000","0712345678","agrofoods@gmail.com")

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

        loadSupplierDetails();

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


        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade_management_system", "root", "1234");

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
            loadSupplierDetails();
            clearFields();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnClearAction(ActionEvent event) {

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
    void btnDeleteAction(ActionEvent event) {

        SupplierInfoDTO selectedSupplier = txtTbl.getSelectionModel().getSelectedItem();
        supplierInfoDTOS.remove(selectedSupplier);
        txtTbl.refresh();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade_management_system", "root", "1234");

            PreparedStatement pstm = connection.prepareStatement("DELETE FROM Supplier WHERE supplierID = ?");

            pstm.setObject(1, txtSupplierID.getText());
            pstm.executeUpdate();
            clearFields();
            loadSupplierDetails();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnUpdateAction(ActionEvent event) {

        SupplierInfoDTO selectedSupplier = txtTbl.getSelectionModel().getSelectedItem();

        selectedSupplier.setSupplierID(txtSupplierID.getText());
        selectedSupplier.setName(txtName.getText());
        selectedSupplier.setCompanyName(txtCompanyName.getText());
        selectedSupplier.setAddress(txtAddress.getText());
        selectedSupplier.setCity(txtCity.getText());
        selectedSupplier.setProvince(txtProvince.getText());
        selectedSupplier.setPostalCode(txtPostalCode.getText());
        selectedSupplier.setPhone(txtPhone.getText());
        selectedSupplier.setEmail(txtEmail.getText());

        txtTbl.refresh();
    }

    //load all rooms method
    private void loadSupplierDetails() {

        supplierInfoDTOS.clear();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade_management_system", "root", "1234");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Supplier" );
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                SupplierInfoDTO supplierInfoDTO = new SupplierInfoDTO(

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
                );
                System.out.println(supplierInfoDTO);
                supplierInfoDTOS.add(supplierInfoDTO);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        txtTbl.setItems(supplierInfoDTOS);
    }
    public void clearFields(){
        txtSupplierID.clear();
        txtName.clear();
        txtCompanyName.clear();
        txtAddress.clear();
        txtCity.clear();
        txtProvince.clear();
        txtPostalCode.clear();
        txtPhone.clear();
        txtEmail.clear();
    }
}
