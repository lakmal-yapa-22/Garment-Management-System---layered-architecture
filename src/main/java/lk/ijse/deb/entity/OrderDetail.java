package lk.ijse.deb.entity;

import lombok.*;



    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    @ToString
    @EqualsAndHashCode
    public class OrderDetail {
        private String orderId;
        private String itemCode;
        private int qty;
        private double unitPrice;
        private double subTotal;
        private  int discountRate;
        private  double discount;
        private  double finalAmount;


        public OrderDetail(String finalAmount, String productId, int qty, double unitPrice, double subTotal, String discountRate, double discount, double amount) {
        }
    }
