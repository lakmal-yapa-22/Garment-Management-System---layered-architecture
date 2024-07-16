package lk.ijse.deb.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class SupplierDTO {
    private String id;
    private String name;
    private String contactNumber;
    private String email;
    private String address;


}
