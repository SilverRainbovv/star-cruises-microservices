import com.didenko.shipservice.ShipServiceApplication;
import com.didenko.shipservice.dto.ShipReadDto;
import com.didenko.shipservice.entity.Seat;
import com.didenko.shipservice.entity.SeatVacancy;
import com.didenko.shipservice.entity.Ship;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest(classes = ShipServiceApplication.class)
public class ShipMappingTest {

    @Autowired
    private ModelMapper modelMapper;

    private static final Seat SEAT_1 = Seat.builder()
            .price(new BigDecimal(100))
            .vacancy(SeatVacancy.VACANT)
            .build();
    private static final Seat SEAT_2 = Seat.builder()
            .price(new BigDecimal(200))
            .vacancy(SeatVacancy.BOOKED)
            .build();
    private static final Seat SEAT_3 = Seat.builder()
            .price(new BigDecimal(300))
            .vacancy(SeatVacancy.BOOKED)
            .build();
    public static final Ship SHIP = Ship.builder()
            .id(1L)
            .name("name")
            .seats(List.of(SEAT_1, SEAT_2, SEAT_3))
            .build();
    private static final ShipReadDto EXPECTED_SHIP_DTO = ShipReadDto.builder()
            .id("1")
            .name("name")
            .freeSeatsRemaining("1")
            .startingPrice("100")
            .build();

    @Test
    public void test() {

        ShipReadDto mappedShipDto = modelMapper.map(SHIP, ShipReadDto.class);

        Assertions.assertEquals(EXPECTED_SHIP_DTO.getId(), mappedShipDto.getId());
        Assertions.assertEquals(EXPECTED_SHIP_DTO.getName(), mappedShipDto.getName());
        Assertions.assertEquals(EXPECTED_SHIP_DTO.getFreeSeatsRemaining(), mappedShipDto.getFreeSeatsRemaining());
        Assertions.assertEquals(EXPECTED_SHIP_DTO.getStartingPrice(), mappedShipDto.getStartingPrice());

    }

}