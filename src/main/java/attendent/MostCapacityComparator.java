package attendent;

import parkinglot.ParkingLot;

import java.util.Comparator;

public class MostCapacityComparator implements Comparator<ParkingLot> {
       public int compare(ParkingLot o, ParkingLot t1) {
         return   o.getCapacity()-t1.getCapacity();
    }
}
