import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

    private Map<Object, Object> parkArea;
    private int capacity;
    private Owner owner;

    public ParkingLot(int capacity, Owner owner) {
        this.parkArea = new HashMap<>();
        this.capacity = capacity;
        this.owner = owner;
    }

    private boolean isParkingLotEmpty() {
        return this.parkArea.size() >= capacity;
    }

    private boolean isDuplicate(Object obj) {
        return !this.parkArea.containsKey(obj);
    }


    public void park(Object obj) throws DuplicateException, ParkingFullException {
        if (isParkingLotEmpty())
            throw new ParkingFullException();

        if (!isDuplicate(obj))
            throw new DuplicateException();

        this.parkArea.put(obj, obj);

        if (isParkingLotEmpty()) {
            owner.inform();
        }

    }


    public Object unPark(Object object1) throws NotAvailableEception {
        if (isDuplicate(object1))
            throw new NotAvailableEception();
        if (parkArea.size() == capacity)
            owner.inform();
        return parkArea.remove(object1);

    }


}
