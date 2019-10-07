package attendent;

import parkinglot.ParkingLot;

import java.util.Comparator;

public class MostSpaceComparator implements Comparator<ParkingLot> {
    @Override
    public int compare(ParkingLot parkingLot, ParkingLot t1) {
        return (parkingLot.getCapacity()-parkingLot.getParkAreaSize())-(t1.getCapacity()-t1.getParkAreaSize());
    }
}
