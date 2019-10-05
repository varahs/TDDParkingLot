import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotTest {

   static class MockOwner extends Owner {

       boolean message;
       int numberOfTimesInformed;
       @Override
       public void inform() {
           numberOfTimesInformed+=1;
           message=!message;
       }

       @Override
       public String toString() {
           return "MockOwner{" +
                   "message=" + message +
                   ", numberOfTimesInformed=" + numberOfTimesInformed +
                   '}';
       }
   }

   static class Park {
        @Test
        void givenVehicleToParkingLot_WhenPark_ThenParked()  {
            ParkingLot parkingLot = new ParkingLot(50,new Owner());
            assertDoesNotThrow(()->parkingLot.park(new Object()));
        }

        @Test
        void givenVehiclesToParkingLot_WhenPark_ThenNotAvailable() throws DuplicateException, ParkingFullException {
            ParkingLot parkingLot = new ParkingLot(1,new Owner());
            parkingLot.park(new Object());
           assertThrows(ParkingFullException.class, () -> parkingLot.park(new Object()));
        }

        @Test
        void givenVehicleToParkingLotWhichIsFull_WhenPark_ThenThrowException() throws DuplicateException, ParkingFullException {
            ParkingLot parkingLot = new ParkingLot(2,new Owner());
            Object object1 = new Object();
            parkingLot.park(object1);
            assertThrows(DuplicateException.class, () -> parkingLot.park(object1));
        }

        @Test
        void givenVehicleToParkingLot_WhenUnPark_ThenShouldUnPark() throws NotAvailableEception, DuplicateException, ParkingFullException {
            ParkingLot parkingLot = new ParkingLot(2,new Owner());
            Object object1 = new Object();
            Object object2 = new Object();
            parkingLot.park(object1);
            parkingLot.park(object2);
            assertEquals(object1, parkingLot.unPark(object1));
        }

        @Test
        void givenVehicleToUnPark_WhenUnPark_ThenShouldUnPark() {
            ParkingLot parkingLot = new ParkingLot(2,new Owner());
            assertThrows(NotAvailableEception.class, () -> parkingLot.unPark(new Object()));
        }

        @Test
        void givenDifferentVehicleToParkingLot_WhenUnPark_ThenShouldUnPark() throws DuplicateException, ParkingFullException {
            ParkingLot parkingLot = new ParkingLot(2,new Owner());
            Object object1 = new Object();
            Object object2 = new Object();
            parkingLot.park(object1);
            parkingLot.park(object2);
            assertThrows(NotAvailableEception.class, () -> parkingLot.unPark(new Object()));

        }

        @Test
        void givenVehicleToPark_WhenUnParkSameVehicle_ThenShouldUnPark() throws DuplicateException, ParkingFullException, NotAvailableEception {
            ParkingLot parkingLot = new ParkingLot(2,new Owner());
            Object object1 = new Object();
            Object object2 = new Object();
            parkingLot.park(object1);
            parkingLot.park(object2);
            parkingLot.unPark(object1);
            assertDoesNotThrow(()->parkingLot.park(object1));
        }

    }

    static class OwnerTest{
        MockOwner owner=new MockOwner();
        @Test
        void givenNotifyOwnerWhenParkingLotFull_WhenPark_ThenShouldNotify() throws DuplicateException, ParkingFullException {

            ParkingLot parkingLot = new ParkingLot(1,owner);
            Object object1 = new Object();
            parkingLot.park(object1);
            assertTrue(owner.message);
        }

        @Test
        void givenNotifyOwnerWhenParkingLotFull_WhenNoPark_ThenShouldNotNotify() {
             assertFalse(owner.message);
        }
        @Test
        void givenNotifyOwnerWhenParkingLotFulCount_WhenPark_ThenShouldCount() throws DuplicateException, ParkingFullException {
            ParkingLot parkingLot = new ParkingLot(1,owner);
            Object object1 = new Object();
            parkingLot.park(object1);
             assertEquals(1,owner.numberOfTimesInformed);
        }
        @Test
        void givenNotifyOwnerWhenParkingLotFulCount_WhenParkAndUnPark_ThenShouldCount() throws DuplicateException, ParkingFullException, NotAvailableEception {
            ParkingLot parkingLot = new ParkingLot(1,owner);
            Object object1 = new Object();
            parkingLot.park(object1);
            parkingLot.unPark(object1);
            parkingLot.park(object1);
             assertEquals(3,owner.numberOfTimesInformed);
        }
        @Test
        void givenParkingLotFulInformAfterFirstUnParkInform_WhenParkAndUnPark_ThenShouldCount() throws DuplicateException, ParkingFullException, NotAvailableEception {
            ParkingLot parkingLot = new ParkingLot(3,owner);
            Object object1 = new Object();
            Object car = new Car("A");
            parkingLot.park(object1);
            parkingLot.park(car);
            parkingLot.park(new Object());
            parkingLot.unPark(object1);
            parkingLot.unPark(car);
             assertEquals(2,owner.numberOfTimesInformed);
        }
    }
}
