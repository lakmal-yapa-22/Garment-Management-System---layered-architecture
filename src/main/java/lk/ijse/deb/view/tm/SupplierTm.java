package lk.ijse.deb.view.tm;
import lk.ijse.deb.entity.Supplier;
import lombok.*;



@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class SupplierTm extends Supplier {
    private String id;
    private String name;
    private String contactNumber;
    private String email;
    private String address;

}
