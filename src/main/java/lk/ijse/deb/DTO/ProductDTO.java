package lk.ijse.deb.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class ProductDTO {
    private String id;
    private int qty;
    private double price;
    private  String type;
    private  String color;
    private String size;


    public ProductDTO(String id, int qty, String type, String color, String size) {
    }
}
