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
import model.dto.EmployeeInfoDTO;
import model.dto.SupplierInfoDTO;

import javax.naming.Name;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class EmployeeInfoController implements Initializable {

    ObservableList<EmployeeInfoDTO> employeeInfoDTOS = FXCollections.observableArrayList(
//            new EmployeeInfoDTO("E001","Sunil Perera", "832451230V", "1983-07-12", "Manager", 75000.0, "0712456789", "No.15 Temple Road, Kalutara", "2018-05-10", "Active"),
//            new EmployeeInfoDTO("E001","Sunil Perera", "832451230V", "1983-07-12", "Manager", 75000.0, "0712456789", "No.15 Temple Road, Kalutara", "2018-05-10", "Active"),
//            new EmployeeInfoDTO("E001","Sunil Perera", "832451230V", "1983-07-12", "Manager", 75000.0, "0712456789", "No.15 Temple Road, Kalutara", "2018-05-10", "Active"),
//            new EmployeeInfoDTO("E001","Sunil Perera", "832451230V", "1983-07-12", "Manager", 75000.0, "0712456789", "No.15 Temple Road, Kalutara", "2018-05-10", "Active"),
//            new EmployeeInfoDTO("E001","Sunil Perera", "832451230V", "1983-07-12", "Manager", 75000.0, "0712456789", "No.15 Temple Road, Kalutara", "2018-05-10", "Active"),
//            new EmployeeInfoDTO("E001","Sunil Perera", "832451230V", "1983-07-12", "Manager", 75000.0, "0712456789", "No.15 Temple Road, Kalutara", "2018-05-10", "Active")
            );

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

        txtTbl.setItems(employeeInfoDTOS);

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
        Double salary = Double.valueOf(txtSalary.getText());
        String contactNumber = txtContactNumber.getText();
        String address = txtAddress.getText();
        String joinedDate = txtJoinedDate.getText();
        String status = txtStatus.getText();


        EmployeeInfoDTO employeeInfoDTO = new EmployeeInfoDTO(EmployeeID,name,nic,dob,position,salary,contactNumber,address,joinedDate,status);
        employeeInfoDTOS.add(employeeInfoDTO);

        txtTbl.refresh();

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

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade_management_system", "root", "1234");

            String SQL = "Insert INTO Employee VALUES(?,?,?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setObject(1, EmployeeID);
            preparedStatement.setObject(2, name);
            preparedStatement.setObject(3, nic);
            preparedStatement.setObject(4, dob);
            preparedStatement.setObject(5, position);
            preparedStatement.setObject(6, salary);
            preparedStatement.setObject(7, contactNumber);
            preparedStatement.setObject(8, address);
            preparedStatement.setObject(9, joinedDate);
            preparedStatement.setObject(10, status);

            preparedStatement.execute();
            loadEmployeeDetails();
            clearFields();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

        EmployeeInfoDTO selectedEmployee = txtTbl.getSelectionModel().getSelectedItem();
        employeeInfoDTOS.remove(selectedEmployee);
        txtTbl.refresh();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade_management_system", "root", "1234");

            PreparedStatement pstm = connection.prepareStatement("DELETE FROM Customer WHERE CustomerID = ?");

            pstm.setObject(1, txtEmployeeID.getText());
            pstm.executeUpdate();
            clearFields();
            loadEmployeeDetails();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnUpdateAction(ActionEvent event) {

        EmployeeInfoDTO selectedEmployee = txtTbl.getSelectionModel().getSelectedItem();

        selectedEmployee.setEmployeeID(txtEmployeeID.getText());
        selectedEmployee.setName(txtName.getText());
        selectedEmployee.setNic(txtNIC.getText());
        selectedEmployee.setDob(txtDOB.getText());
        selectedEmployee.setPosition(txtPosition.getText());
        selectedEmployee.setSalary(Double.valueOf(txtSalary.getText()));
        selectedEmployee.setContactNumber(txtContactNumber.getText());
        selectedEmployee.setAddress(txtAddress.getText());
        selectedEmployee.setJoinedDate(txtJoinedDate.getText());
        selectedEmployee.setStatus(txtStatus.getText());

        txtTbl.refresh();

        String EmployeeID = txtEmployeeID.getText();
        String name = txtName.getText();
        String nic = txtNIC.getText();
        String dob = txtDOB.getText();
        String position = txtPosition.getText();
        Double salary = Double.valueOf(txtSalary.getText());
        String contactNumber = txtContactNumber.getText();
        String address = txtAddress.getText();
        String joinedDate = txtJoinedDate.getText();
        String status = txtStatus.getText();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade_management_system", "root", "1234");

            String SQL = "UPDATE Customer SET WHERE EmployeeID = ?, Name = ?, NIC = ?,DateOfBirth = ?,Position = ?,Salary = ?,ContactNumber = ?,Address = ?,JoinedDate = ?,Status = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setObject(1, EmployeeID);
            preparedStatement.setObject(2, name);
            preparedStatement.setObject(3, nic);
            preparedStatement.setObject(4, dob);
            preparedStatement.setObject(5, position);
            preparedStatement.setObject(6, salary);
            preparedStatement.setObject(7, contactNumber);
            preparedStatement.setObject(8, address);
            preparedStatement.setObject(9, joinedDate);
            preparedStatement.setObject(10, status);

            preparedStatement.execute();
            loadEmployeeDetails();
            clearFields();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void loadEmployeeDetails() {

        employeeInfoDTOS.clear();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade_management_system", "root", "1234");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Employee" );
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                EmployeeInfoDTO employeeInfoDTO = new EmployeeInfoDTO(

                        // column name pass
                        resultSet.getString("EmployeeID"),
                        resultSet.getString("Name"),
                        resultSet.getString("NIC"),
                        resultSet.getString("DateOfBirth"),
                        resultSet.getString("Position"),
                        resultSet.getDouble("Salary"),
                        resultSet.getString("ContactNumber"),
                        resultSet.getString("Address"),
                        resultSet.getString("JoinedDate"),
                        resultSet.getString("Status")
                );
                System.out.println(employeeInfoDTO);
                employeeInfoDTOS.add(employeeInfoDTO);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        txtTbl.setItems(employeeInfoDTOS);
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
