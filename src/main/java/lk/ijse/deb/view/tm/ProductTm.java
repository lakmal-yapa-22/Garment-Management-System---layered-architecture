package lk.ijse.deb.view.tm;


import lk.ijse.deb.entity.Product;
import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class ProductTm extends Product {
    private String id;
    private int qty;
    private double price;
    private  String type;
    private  String color;
    private  String size;

}
