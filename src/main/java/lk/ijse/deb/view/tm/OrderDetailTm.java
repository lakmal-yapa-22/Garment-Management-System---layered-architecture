package lk.ijse.deb.view.tm;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class OrderDetailTm {

private  String orderID;
private  String companyName;
private  String productType;
private  int qty;
private  String color;
private  String size;
private  String discountRate;
private  double finalAmount ;
private  String paymentStatus;



}
