package lk.ijse.deb.view.tm;
import lk.ijse.deb.entity.Employee;
import lombok.*;



@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class EmployeeTm extends Employee implements  Comparable<EmployeeTm> {
    private String id;
    private String name;
    private String contactNumber;
    private String salary;
    private String address;
    private String birthday;
    private String productId;


    @Override
    public int compareTo(EmployeeTm o) {
        return 0;
    }
}
