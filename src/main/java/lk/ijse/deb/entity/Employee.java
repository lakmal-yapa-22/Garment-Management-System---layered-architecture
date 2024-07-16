package lk.ijse.deb.entity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode

public class Employee {
    private String id;
    private String name;
    private String contactNumber;
    private  String salary;
    private String address;
    private String birthday;
    private String productId;



}
