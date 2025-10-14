package model.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerInfoDTO {

    private String customerID;

    private  String title;

    private  String name;

    private  String dob;

    private  String salary;

    private  String address;

    private  String city;

    private  String province;

    private  String postalCode;
}
