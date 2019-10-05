import exception.DuplicateException;
import exception.NotAvailableEception;
import exception.ParkingFullException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot implements Isubject {

    private Map<Object, Object> parkArea;
    private int capacity;
    private List<Iobserver> people;
    private boolean notifyFull;

    public ParkingLot(int capacity) {
        this.parkArea = new HashMap<>();
        this.people = new ArrayList<>();
        this.capacity = capacity;
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

        if (isParkingLotEmpty() && this.people != null) {
            notifyFull = true;

            notifySubscribers();
        }

    }


    public Object unPark(Object object1) throws NotAvailableEception {
        if (isDuplicate(object1))
            throw new NotAvailableEception();
        if (parkArea.size() == capacity) {

            notifyFull = false;
            notifySubscribers();
        }
        return parkArea.remove(object1);

    }

    @Override
    public void subscribe(Iobserver obj) {
        this.people.add(obj);
    }

    @Override
    public void unSubscribe(Iobserver obj) {
        this.people.remove(obj);
    }

    @Override
    public void notifySubscribers() {
        for (Iobserver person : people) {
            person.update();

        }
    }

    @Override
    public boolean getUpdate(Iobserver observer) {
        return notifyFull;
    }

}
