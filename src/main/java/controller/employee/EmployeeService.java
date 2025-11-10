package controller.employee;

public interface EmployeeService {

    void addEmployeeDetails(String EmployeeID, String name, String nic, String dob, String position, double salary, String contactNumber, String address, String joinedDate, String status);

    void deleteEmployeeDetails(String EmployeeId);

    void updateEmployeeDetails(String EmployeeID,String name,String nic,String dob,String position,double salary,String contactNumber,String address,String joinedDate,String status);

}