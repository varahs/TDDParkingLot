package attendent;

import exception.DuplicateException;
import exception.ParkingFullException;
import parkinglot.ParkingLot;
import java.util.List;

public class MostSpace implements Parking {

    @Override
    public void park(List<ParkingLot> parkingLots, Object object) throws DuplicateException, ParkingFullException {
        parkingLots.sort(new MostSpaceComparator());
        parkingLots.get(parkingLots.size()-1).park(object);
    }
}
