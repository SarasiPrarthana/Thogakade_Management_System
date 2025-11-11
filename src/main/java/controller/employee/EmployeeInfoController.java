package controller.employee;

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
import model.dto.EmployeeInfoDTO;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class EmployeeInfoController implements Initializable {

    EmployeeService employeeService = new EmployeeController();
    ObservableList<EmployeeInfoDTO> employeeInfoDTOS = FXCollections.observableArrayList();

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContactNumber;

    @FXML
    private TableColumn<?, ?> colDOB;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colJoinedDate;

    @FXML
    private TableColumn<?, ?> colNIC;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPosition;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContactNumber;

    @FXML
    private TextField txtDOB;

    @FXML
    private TextField txtEmployeeID;

    @FXML
    private TextField txtJoinedDate;

    @FXML
    private TextField txtNIC;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPosition;

    @FXML
    private TextField txtSalary;

    @FXML
    private TextField txtStatus;

    @FXML
    private TableView<EmployeeInfoDTO> txtTbl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colID.setCellValueFactory(new PropertyValueFactory<>("employeeID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colPosition.setCellValueFactory(new PropertyValueFactory<>("position"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colContactNumber.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colJoinedDate.setCellValueFactory(new PropertyValueFactory<>("joinedDate"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        loadEmployeeDetails();


        txtTbl.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue != null){
                txtEmployeeID.setText(newValue.getEmployeeID());
                txtName.setText(newValue.getName());
                txtNIC.setText(newValue.getNic());
                txtDOB.setText(newValue.getDob());
                txtPosition.setText(newValue.getPosition());
                txtSalary.setText(String.valueOf(newValue.getSalary()));
                txtContactNumber.setText(newValue.getContactNumber());
                txtAddress.setText(newValue.getAddress());
                txtJoinedDate.setText(newValue.getJoinedDate());
                txtStatus.setText(newValue.getStatus());
            }
        });

    }

    @FXML
    void btnAddAction(ActionEvent event) {

        String EmployeeID = txtEmployeeID.getText();
        String name = txtName.getText();
        String nic = txtNIC.getText();
        String dob = txtDOB.getText();
        String position = txtPosition.getText();
        double salary = Double.parseDouble(txtSalary.getText());
        String contactNumber = txtContactNumber.getText();
        String address = txtAddress.getText();
        String joinedDate = txtJoinedDate.getText();
        String status = txtStatus.getText();

        employeeService.addEmployeeDetails(EmployeeID,name,nic,dob,position,salary,contactNumber,address,joinedDate,status);
        loadEmployeeDetails();
        clearFields();
    }

    @FXML
    void btnClearAction(ActionEvent event) {

        txtEmployeeID.setText("");
        txtName.setText("");
        txtNIC.setText("");
        txtDOB.setText("");
        txtPosition.setText("");
        txtSalary.setText("");
        txtContactNumber.setText("");
        txtAddress.setText("");
        txtJoinedDate.setText("");
        txtStatus.setText("");

    }

    @FXML
    void btnDeleteAction(ActionEvent event) {

        employeeService.deleteEmployeeDetails(txtEmployeeID.getText());
        clearFields();
        loadEmployeeDetails();

    }

    @FXML
    void btnUpdateAction(ActionEvent event) {

        String EmployeeID = txtEmployeeID.getText();
        String name = txtName.getText();
        String nic = txtNIC.getText();
        String dob = txtDOB.getText();
        String position = txtPosition.getText();
        double salary = Double.parseDouble(txtSalary.getText());
        String contactNumber = txtContactNumber.getText();
        String address = txtAddress.getText();
        String joinedDate = txtJoinedDate.getText();
        String status = txtStatus.getText();

        employeeService.updateEmployeeDetails(EmployeeID,name,nic,dob,position,salary,contactNumber,address,joinedDate,status);
        clearFields();
        loadEmployeeDetails();

    }

    private void loadEmployeeDetails() {

        employeeInfoDTOS.clear();
        txtTbl.setItems(employeeService.loadEmployeeDetails());
    }
    public void clearFields(){
        txtEmployeeID.clear();
        txtName.clear();
        txtNIC.clear();
        txtDOB.clear();
        txtPosition.clear();
        txtSalary.clear();
        txtContactNumber.clear();
        txtAddress.clear();
        txtJoinedDate.clear();
        txtStatus.clear();
    }
}
