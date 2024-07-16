package lk.ijse.deb.DTO;
import lk.ijse.deb.entity.RowMatirial;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class RowMatirialDTO extends RowMatirial {
    private  String invoiceNumber;
    private  String location;
    private  String description;
    private  int qty;
}
