
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {
    ParkingLot parkingLot=new ParkingLot();
    @Test
    void  givenParkingLot_WenCheck_ThenReturnAvailable(){
        parkingLot.park();
        assertEquals(19,parkingLot.ParkSize());
    }
}
