package lk.ijse.deb.view.tm;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class PaymentTm {
    private String payment_id;

    private String order_id;

    private  double cashTenderd;
    private  double balance;
    private  String cashier;
    private  String paymentMethod;
    private String paymentStatus;
}

