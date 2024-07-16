package lk.ijse.deb.DTO;

import lk.ijse.deb.entity.Employee;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode

public class EmployeeDTO extends Employee {
    private String id;
    private String name;
    private String contactNumber;
    private  String salary;
    private String address;
    private String birthday;
    private String productId;




    public EmployeeDTO(String id, String qty, String price, String type) {
    }
}