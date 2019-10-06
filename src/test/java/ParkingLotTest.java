import exception.DuplicateException;
import exception.NotAvailableEception;
import exception.ParkingFullException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotTest {

    static class MockOwner extends Owner {

        int numberOfTimesFullInformed;
        int numberOfTimesEmptyInformed;
        boolean isFull;
        Isubject isubject;

        @Override
        public void update() {
            isFull = isubject.getUpdate(this);
            if (isFull)
                fullInform();
            if (!isFull)
                emptyInform();
        }

        @Override
        public void setSubject(Isubject subject) {
            this.isubject = subject;
        }

        public void fullInform() {
            this.numberOfTimesFullInformed += 1;
        }

        public void emptyInform() {
            this.numberOfTimesEmptyInformed += 1;
        }
    }

    static class MockSecurityGuard extends SecurityGuard {
        private int numberOfTimesFullInformed;
        private int numberOfTimesEmptyInformed;
        private boolean isFull;
        private Isubject isubject;

        @Override
        public void update() {
            isFull = isubject.getUpdate(this);
            if (isFull)
                fullInform();
            if (!isFull)
                emptyInform();
        }

        @Override
        public void setSubject(Isubject subject) {
            this.isubject = subject;
        }

        public void fullInform() {
            this.numberOfTimesFullInformed += 1;
        }

        public void emptyInform() {
            this.numberOfTimesEmptyInformed += 1;
        }
    }

    static class Park {
        @Test
        void givenVehicleToParkingLot_WhenPark_ThenParked() {
            ParkingLot parkingLot = new ParkingLot(50);
            assertDoesNotThrow(() -> parkingLot.park(new Object()));
        }

        @Test
        void givenVehiclesToParkingLot_WhenPark_ThenNotAvailable() throws DuplicateException, ParkingFullException {
            ParkingLot parkingLot = new ParkingLot(1);
            parkingLot.park(new Object());
            assertThrows(ParkingFullException.class, () -> parkingLot.park(new Object()));
        }

        @Test
        void givenVehicleToParkingLotWhichIsFull_WhenPark_ThenThrowException() throws DuplicateException, ParkingFullException {
            ParkingLot parkingLot = new ParkingLot(2);
            Object object1 = new Object();
            parkingLot.park(object1);
            assertThrows(DuplicateException.class, () -> parkingLot.park(object1));
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
        void givenVehicleToUnPark_WhenUnPark_ThenShouldUnPark() {
            ParkingLot parkingLot = new ParkingLot(2);
            assertThrows(NotAvailableEception.class, () -> parkingLot.unPark(new Object()));
        }

        @Test
        void givenDifferentVehicleToParkingLot_WhenUnPark_ThenShouldUnPark() throws DuplicateException, ParkingFullException {
            ParkingLot parkingLot = new ParkingLot(2);
            Object object1 = new Object();
            Object object2 = new Object();
            parkingLot.park(object1);
            parkingLot.park(object2);
            assertThrows(NotAvailableEception.class, () -> parkingLot.unPark(new Object()));

        }

        @Test
        void givenVehicleToPark_WhenUnParkSameVehicle_ThenShouldUnPark() throws DuplicateException, ParkingFullException, NotAvailableEception {
            ParkingLot parkingLot = new ParkingLot(2);
            Object object1 = new Object();
            Object object2 = new Object();
            parkingLot.park(object1);
            parkingLot.park(object2);
            parkingLot.unPark(object1);
            assertDoesNotThrow(() -> parkingLot.park(object1));
        }

    }

    static class OwnerTest {


        @Test
        void givenNotifyOwnerWhenParkingLotFull_WhenPark_ThenShouldNotify() throws DuplicateException, ParkingFullException {
            MockOwner owner = new MockOwner();
            ParkingLot parkingLot = new ParkingLot(1);
            parkingLot.subscribe(owner);
            owner.setSubject(parkingLot);
            Object object1 = new Object();
            parkingLot.park(object1);
            assertTrue(owner.isFull);

        }

        @Test
        void givenNotifyOwnerWhenParkingLotEmpty_WhenUnPark_ThenShouldNotify() throws NotAvailableEception, DuplicateException, ParkingFullException {
            MockOwner owner = new MockOwner();
            ParkingLot parkingLot = new ParkingLot(1);
            parkingLot.subscribe(owner);
            owner.setSubject(parkingLot);
            Object object1 = new Object();
            parkingLot.park(object1);
            parkingLot.unPark(object1);
            assertFalse(owner.isFull);
        }

        @Test
        void givenOwnerToParkingLot_WhenNoPark_ThenShouldNotNotify() {
            MockOwner owner = new MockOwner();
            assertFalse(owner.isFull);
        }

        @Test
        void givenNotifyOwnerWhenParkingLotFulCount_WhenPark_ThenShouldCount() throws DuplicateException, ParkingFullException {
            MockOwner owner = new MockOwner();
            ParkingLot parkingLot = new ParkingLot(1);
            parkingLot.subscribe(owner);
            owner.setSubject(parkingLot);
            Object object1 = new Object();
            parkingLot.park(object1);
            assertEquals(1, owner.numberOfTimesFullInformed);
        }

        @Test
        void givenNotifyOwnerWhenParkingLotFulCount_WhenParkAndUnPark_ThenShouldCount() throws DuplicateException, ParkingFullException, NotAvailableEception {
            MockOwner owner = new MockOwner();
            ParkingLot parkingLot = new ParkingLot(1);
            parkingLot.subscribe(owner);
            owner.setSubject(parkingLot);
            Object object1 = new Object();
            parkingLot.park(object1);
            parkingLot.unPark(object1);
            parkingLot.park(object1);
            assertEquals(2, owner.numberOfTimesFullInformed);
        }

        @Test
        void givenParkingLotFulInformAfterFirstUnParkInform_WhenParkAndUnPark_ThenShouldCount() throws DuplicateException, ParkingFullException, NotAvailableEception {
            MockOwner owner = new MockOwner();
            ParkingLot parkingLot = new ParkingLot(3);
            parkingLot.subscribe(owner);
            owner.setSubject(parkingLot);
            Object object1 = new Object();
            Object car = new Car("A");
            parkingLot.park(object1);
            parkingLot.park(car);
            parkingLot.park(new Object());
            parkingLot.unPark(object1);
            parkingLot.unPark(car);
            assertEquals(1, owner.numberOfTimesFullInformed);
            assertEquals(1, owner.numberOfTimesEmptyInformed);
        }
    }

    static class Guard {
        @Test
        void givenNotifyGuardWhenParkingLotFull_whenPark_ThenShouldInform() throws DuplicateException, ParkingFullException {
            MockSecurityGuard guard = new MockSecurityGuard();
            ParkingLot parkingLot = new ParkingLot(1);
            parkingLot.subscribe(guard);
            guard.setSubject(parkingLot);
            Object object1 = new Object();
            parkingLot.park(object1);
            assertTrue(guard.isFull);

        }

        @Test
        void givenNotifyGuardWhenParkingLotEmpty_WhenUnPark_ThenShouldNotify() throws NotAvailableEception, DuplicateException, ParkingFullException {
            MockSecurityGuard guard = new MockSecurityGuard();
            ParkingLot parkingLot = new ParkingLot(1);
            parkingLot.subscribe(guard);
            guard.setSubject(parkingLot);
            Object object1 = new Object();
            parkingLot.park(object1);
            parkingLot.unPark(object1);
            assertFalse(guard.isFull);

        }


        @Test
        void givenNotifyGuardWhenParkingLotFull_WhenNoPark_ThenShouldNotNotify() {
            MockSecurityGuard guard = new MockSecurityGuard();
            ParkingLot parkingLot = new ParkingLot(1);
            parkingLot.subscribe(guard);
            guard.setSubject(parkingLot);
            assertFalse(guard.isFull);
        }

        @Test
        void givenNotifyGuardWhenParkingLotFulCount_WhenPark_ThenShouldCount() throws DuplicateException, ParkingFullException {
            MockSecurityGuard guard = new MockSecurityGuard();
            ParkingLot parkingLot = new ParkingLot(1);
            parkingLot.subscribe(guard);
            guard.setSubject(parkingLot);
            Object object1 = new Object();
            parkingLot.park(object1);
            assertEquals(1, guard.numberOfTimesFullInformed);
        }

        @Test
        void givenNotifyGuardWhenParkingLotFulCount_WhenParkAndUnPark_ThenShouldCount() throws DuplicateException, ParkingFullException, NotAvailableEception {
            MockSecurityGuard guard = new MockSecurityGuard();
            ParkingLot parkingLot = new ParkingLot(1);
            parkingLot.subscribe(guard);
            guard.setSubject(parkingLot);
            Object object1 = new Object();
            parkingLot.park(object1);
            parkingLot.unPark(object1);
            parkingLot.park(object1);
            assertEquals(2, guard.numberOfTimesFullInformed);
        }

        @Test
        void givenParkingLotFulInformAfterFirstUnParkInform_WhenParkAndUnPark_ThenShouldCount() throws DuplicateException, ParkingFullException, NotAvailableEception {
            MockSecurityGuard guard = new MockSecurityGuard();
            ParkingLot parkingLot = new ParkingLot(3);
            parkingLot.subscribe(guard);
            guard.setSubject(parkingLot);
            Object object1 = new Object();
            Object car = new Car("A");
            parkingLot.park(object1);
            parkingLot.park(car);
            parkingLot.park(new Object());
            parkingLot.unPark(object1);
            parkingLot.unPark(car);
            assertEquals(1, guard.numberOfTimesFullInformed);
            assertEquals(1, guard.numberOfTimesEmptyInformed);

        }

    }

    static class ObserverTest {
        @Test
        void givenSubscribers_WhenParkingLotFull_ThenNotify() throws DuplicateException, ParkingFullException {
            MockSecurityGuard guard = new MockSecurityGuard();
            MockOwner owner = new MockOwner();
            ParkingLot parkingLot = new ParkingLot(1);
            parkingLot.subscribe(guard);
            parkingLot.subscribe(owner);
            guard.setSubject(parkingLot);
            owner.setSubject(parkingLot);
            parkingLot.park(new Object());
            assertEquals(1, guard.numberOfTimesFullInformed);
            assertEquals(1, owner.numberOfTimesFullInformed);
        }

        @Test
        void givenNullSubscribers_WhenParkingLotFull_ThenNoNotify() throws DuplicateException, ParkingFullException, NotAvailableEception {
            MockSecurityGuard guard = new MockSecurityGuard();
            ParkingLot parkingLot = new ParkingLot(1);
            parkingLot.subscribe(null);
            parkingLot.park(new Object());
            parkingLot.unPark(null);
            parkingLot.unSubscribe(null);
            assertEquals(0, guard.numberOfTimesFullInformed);
            assertEquals(0, guard.numberOfTimesEmptyInformed);
        }


        @Test
        void givenNoSubscribers_WhenParkingLotFull_ThenNoNotify() throws DuplicateException, ParkingFullException {
            MockSecurityGuard guard = new MockSecurityGuard();
            MockOwner owner = new MockOwner();
            ParkingLot parkingLot = new ParkingLot(1);
            parkingLot.park(new Object());
            assertEquals(0, guard.numberOfTimesFullInformed);
            assertEquals(0, owner.numberOfTimesFullInformed);
        }
        @Test
        void givenSubscribersThenUnsubscribe_WhenParkingLotFull_ThenNoNotify() throws DuplicateException, ParkingFullException, NotAvailableEception {
            MockSecurityGuard guard = new MockSecurityGuard();
            MockOwner owner = new MockOwner();
            ParkingLot parkingLot = new ParkingLot(2);
            parkingLot.subscribe(guard);
            parkingLot.subscribe(owner);
            guard.setSubject(parkingLot);
            owner.setSubject(parkingLot);
            Object object = new Object();

            parkingLot.park(object);
            parkingLot.park(new Object());

            assertTrue(guard.isFull);
            assertEquals(1, guard.numberOfTimesFullInformed);

            assertTrue(owner.isFull);
            assertEquals(1, owner.numberOfTimesFullInformed);

            parkingLot.unSubscribe(owner);
            assertTrue(owner.isFull);
            parkingLot.unPark(object);
            assertTrue(owner.isFull);//stores previous status
            parkingLot.park(new Object());
            assertEquals(2, guard.numberOfTimesFullInformed);
            assertEquals(1, guard.numberOfTimesEmptyInformed);
            assertEquals(1, owner.numberOfTimesFullInformed);
        }
    }
}
