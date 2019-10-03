
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

 class ParkingLotTest {


    @Nested
    static class Park{
        @Test
        void givenVehicleToParkingLot_WhenPark_ThenParked() throws ParkingLotException {
            ParkingLot parkingLot = new ParkingLot(50);
            assertTrue(parkingLot.park(new Object()));
        }

        @Test
        void givenVehiclesToParkingLot_WhenPark_ThenNotAvailable() throws ParkingLotException {
            ParkingLot parkingLot = new ParkingLot(1);
            parkingLot.park(new Object());
            Throwable exception = assertThrows(ParkingFullException.class, () -> parkingLot.park(new Object()));
            assertEquals("ParkingLot Full", exception.getMessage());
        }

        @Test
        void givenVehicleToParkingLotWhichIsFull_WhenPark_ThenThrowException() throws ParkingLotException {
            ParkingLot parkingLot = new ParkingLot(2);
            Object object1 = new Object();
            parkingLot.park(object1);
            Throwable exception = assertThrows(DuplicateException.class, () -> parkingLot.park(object1));
            assertEquals("Parking Duplicate", exception.getMessage());
        }

    }

//    @Nested
//    static class Sanjay {
//        Consumer consumer;
//
//        @Test
//        void givenSameCarToParkingLot1_whenPark_SouldThrowException() throws ParkingLotException {
//            ParkingLot parkingLot1 = new ParkingLot(2);
//            ParkingLot parkingLot2 = new ParkingLot(3);
//            Car car = new Car();
//            consumer = new Consumer(parkingLot1, parkingLot2);
//            consumer.personalParking(car);
//            Throwable exception = assertThrows(ParkingLotException.class, () -> consumer.personalParking(car));
//            assertEquals("Parking Duplicate", exception.getMessage());
//        }
//
//        @Test
//        void givenToThreeCars_whenPark_returnsException() throws ParkingLotException {
//            ParkingLot parkingLot1 = new ParkingLot(2);
//            ParkingLot parkingLot2 = new ParkingLot(3);
//            Car A = new Car();
//            Car B = new Car();
//            Car C = new Car();
//
//            consumer = new Consumer(parkingLot1, parkingLot2);
//            consumer.personalParking(A);
//            consumer.personalParking(B);
//            Throwable exception = assertThrows(ParkingLotException.class, () -> consumer.personalParking(C));
//            assertEquals("ParkingLot Full", exception.getMessage());
//        }
//
//        @Test
//        void givenToTwoCars_whenPark_returnsTrue() throws ParkingLotException {
//            ParkingLot parkingLot1 = new ParkingLot(2);
//            ParkingLot parkingLot2 = new ParkingLot(3);
//            Car A = new Car();
//            Car B = new Car();
//            consumer = new Consumer(parkingLot1, parkingLot2);
//            assertTrue(consumer.personalParking(A));
//            assertTrue(consumer.personalParking(B));
//        }
//    }

}
