package lk.ijse.deb.entity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class RowMatirial {
    private  String invoiceNumber;
    private  String location;
    private  String description;
    private  int qty;


}
