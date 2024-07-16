package lk.ijse.deb.entity;

import lk.ijse.deb.DTO.ProductDTO;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Product extends ProductDTO {
    private String id;
    private int qty;
    private double price;
    private  String type;
    private  String color;
    private  String size;


    public Product(String id, int qty, String type, String color, String size) {
    }
}
