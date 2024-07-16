package lk.ijse.deb.DTO;

import lk.ijse.deb.entity.OrderDetail;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class OrderDetailDTO extends OrderDetail {
    private String orderId;
    private String itemCode;
    private int qty;
    private double unitPrice;


   private double subTotal;
  private  int discountRate;
    private  double discount;
   private  double finalAmount;


    public OrderDetailDTO(String orderId, String productId, double finalAmount, double unitPrice, double subTotal, String discountRate, double disscount, double amount) {
    }

    public OrderDetailDTO(OrderDetail orderDetail) {
    }
}
