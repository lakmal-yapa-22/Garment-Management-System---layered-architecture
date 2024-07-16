package lk.ijse.deb.DTO;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class PlaceOrderDTO {
    private OrderDTO order;
    private List<OrderDetailDTO> odList;

    public PlaceOrderDTO(Object order, List<OrderDetailDTO> odList) {
    }
}
