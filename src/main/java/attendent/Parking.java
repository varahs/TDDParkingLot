package attendent;

import exception.DuplicateException;
import exception.ParkingFullException;
import parkinglot.ParkingLot;

import java.util.List;

public interface Parking {
    void park(List<ParkingLot> parkingLots, Object object) throws DuplicateException, ParkingFullException;
}
