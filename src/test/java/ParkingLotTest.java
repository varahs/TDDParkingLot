
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

 class ParkingLotTest {


    @Nested
    static class Park{
        @Test
        void givenVehicleToParkingLot_WhenPark_ThenParked() throws DuplicateException, ParkingFullException {
            ParkingLot parkingLot = new ParkingLot(50);
            assertTrue(parkingLot.park(new Object()));
        }

        @Test
        void givenVehiclesToParkingLot_WhenPark_ThenNotAvailable() throws  DuplicateException, ParkingFullException {
            ParkingLot parkingLot = new ParkingLot(1);
            parkingLot.park(new Object());
            Throwable exception = assertThrows(ParkingFullException.class, () -> parkingLot.park(new Object()));
            assertEquals("ParkingLot Full", exception.getMessage());
        }

        @Test
        void givenVehicleToParkingLotWhichIsFull_WhenPark_ThenThrowException() throws  DuplicateException, ParkingFullException {
            ParkingLot parkingLot = new ParkingLot(2);
            Object object1 = new Object();
            parkingLot.park(object1);
            Throwable exception = assertThrows(DuplicateException.class, () -> parkingLot.park(object1));
            assertEquals("Parking Duplicate", exception.getMessage());
        }

        @Test
        void givenVehicleToParkingLot_WhenUnPark_ThenShouldUnPark() throws NotAvailableEception, DuplicateException, ParkingFullException {
            ParkingLot parkingLot = new ParkingLot(2);
            Object object1 = new Object();
            Object object2 = new Object();
            parkingLot.park(object1);
            parkingLot.park(object2);
            assertEquals(object1, parkingLot.unPark(object1));
        }
 @Test
        void givenDifferentVehicleToParkingLot_WhenUnPark_ThenShouldUnPark() throws NotAvailableEception, DuplicateException, ParkingFullException {
            ParkingLot parkingLot = new ParkingLot(2);
            Object object1 = new Object();
            Object object2 = new Object();
            parkingLot.park(object1);
            parkingLot.park(object2);
            Throwable exceptin=assertThrows(NotAvailableEception.class,()->parkingLot.unPark(new Object()));
            assertEquals("Doesn't Exists",exceptin.getMessage());
        }

    }
}
