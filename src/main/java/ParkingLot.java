import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

    private List parkArea ;
   private int capacity ;

    public ParkingLot(int capacity) {
        this.parkArea= new ArrayList();
        this.capacity = capacity;
    }

    public boolean park(Object obj) throws DuplicateException, ParkingFullException {
        if (isParkingLotEmpty()) {
            if (!isDuplicate(obj)) {
                return this.parkArea.add(obj);
            }
            throw new DuplicateException("Parking Duplicate");
        }
        throw new ParkingFullException("ParkingLot Full");
    }

    private boolean isParkingLotEmpty() {
        return this.parkArea.size() < capacity;
    }

    private boolean isDuplicate(Object obj) {
        return this.parkArea.contains(obj);
    }

}
