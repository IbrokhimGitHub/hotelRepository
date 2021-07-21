package uz.pdp.hotelrepository.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDto {
    private Integer number;
    private Integer floor;
    private String size;
    private Integer hotelId;

}
