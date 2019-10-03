
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParkingLotTest {


    @Nested
    class Park{
        @Test
        void givenVehicleToParkingLot_WhenPark_ThenParked() {
            ParkingLot parkingLot = new ParkingLot(50);
            assertTrue(parkingLot.park(new Vehicle()));
        }

        @Test
        void givenVehicleToParkingLot_WhenPark_ThenAvailable() {
            ParkingLot parkingLot = new ParkingLot(1);
            parkingLot.park(new Vehicle());
            assertFalse(parkingLot.park(new Vehicle()));
        }

    }

}
