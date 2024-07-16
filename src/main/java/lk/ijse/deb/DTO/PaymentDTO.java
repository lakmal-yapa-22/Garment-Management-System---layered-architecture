package lk.ijse.deb.DTO;

import lk.ijse.deb.dao.SQLUtil;
import lk.ijse.deb.db.DbConnection;
import lombok.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class PaymentDTO {
    private String payment_id;

    private String order_id;

    private  double cashTenderd;
    private  double balance;
    private  String cashier;
    private  String paymentMethod;
    private String paymentStatus;


    public PaymentDTO(String paymentId, String orderId, double cashTenderd, String cashier, String paymentMethod, String paymentStatus) {
    }


}
