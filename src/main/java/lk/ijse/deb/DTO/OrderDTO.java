package lk.ijse.deb.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDTO {

    private String orderId;
    private String BuyerId;
    private Date date;
    private Time time;

    private double subTotal;
    private  int discountRate;
    private  double discount;
    private  double finalAmount;


    public OrderDTO(String id, String bId, java.util.Date date, Time time) {
    }
}