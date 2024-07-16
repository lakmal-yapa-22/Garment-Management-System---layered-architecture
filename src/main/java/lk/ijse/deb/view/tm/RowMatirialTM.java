package lk.ijse.deb.view.tm;

import lk.ijse.deb.entity.RowMatirial;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class RowMatirialTM extends RowMatirial {
    private  String invoiceNumber;
    private  String location;
    private  String description;
    private  int qty;

}
