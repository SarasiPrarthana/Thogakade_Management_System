package model.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeInfoDTO {

    private String employeeID;

    private String name;

    private String nic;

    private String dob;

    private String position;

    private double salary;

    private String contactNumber;

    private String address;

    private String joinedDate;

    private  String status;
}
