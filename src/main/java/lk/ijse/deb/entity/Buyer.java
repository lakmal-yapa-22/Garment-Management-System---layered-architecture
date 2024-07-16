package lk.ijse.deb.entity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Buyer {
    private  String id;
    private  String tel;
    private  String address;
    private  String email;
    private  String company;
}
