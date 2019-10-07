package attendent;

import exception.DuplicateException;
import exception.ParkingFullException;
import parkinglot.ParkingLot;

import java.util.List;

public class Attendent {
    private List<ParkingLot> parkingLots;
    private Parking parking;

    public Attendent(List<ParkingLot> parkingLots, Parking parking) {
        this.parkingLots = parkingLots;
        this.parking = parking;
    }

    public void park(Object obj) throws DuplicateException, ParkingFullException {
        parking.park(parkingLots, obj);
    }
}
