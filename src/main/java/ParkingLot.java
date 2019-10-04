import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

    private Map parkArea;
    private int capacity;

    public ParkingLot(int capacity) {
        this.parkArea = new HashMap();
        this.capacity = capacity;
    }

    public boolean park(Object obj) throws DuplicateException, ParkingFullException {
        if (isParkingLotEmpty()) {
            if (!isDuplicate(obj)) {
                this.parkArea.put(obj, obj);
                return true;
            }
            throw new DuplicateException("Parking Duplicate");
        }
        throw new ParkingFullException("ParkingLot Full");
    }

    private boolean isParkingLotEmpty() {
        return this.parkArea.size() < capacity;
    }

    private boolean isDuplicate(Object obj) {
        return this.parkArea.containsKey(obj);
    }

    public Object unPark(Object object1) throws NotAvailableEception {
        if (!isDuplicate(object1)||isParkingLotEmpty())
            throw new NotAvailableEception("Doesn't Exists");

        return parkArea.remove(object1);

    }
}
