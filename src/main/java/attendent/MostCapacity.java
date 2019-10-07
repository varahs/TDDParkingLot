package attendent;

import exception.DuplicateException;
import exception.ParkingFullException;
import parkinglot.ParkingLot;

import java.util.Collections;
import java.util.List;

public class MostCapacity implements Parking {

    @Override
    public void park(List<ParkingLot> parkingLots, Object object) throws DuplicateException, ParkingFullException {
        Collections.sort(parkingLots,new MostCapacityComparator());
        parkingLots.get(parkingLots.size()-1).park(object);
    }
}
