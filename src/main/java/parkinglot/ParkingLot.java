package parkinglot;

import exception.DuplicateException;
import exception.NotAvailableEception;
import exception.ParkingFullException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot implements Isubject {

    private Map<Object, Object> parkArea;

    public int getCapacity() {
        return capacity;
    }

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


    public int getParkAreaSize() {
        return parkArea.size();
    }

    @Override
    public String toString() {
        return "ParkingLot{" +
                "parkArea=" + parkArea +
                ", capacity=" + capacity +
                ", people=" + people +
                ", notifyFull=" + notifyFull +
                '}';
    }

    public void park(Object obj) throws DuplicateException, ParkingFullException {

        if (isParkingLotEmpty())
            throw new ParkingFullException();

        if (obj != null && !isDuplicate(obj))
            throw new DuplicateException();

        this.parkArea.put(obj, obj);

        if (isParkingLotEmpty() && this.people != null) {
            notifyFull = true;

            notifySubscribers();
        }


    }


    public Object unPark(Object object1) throws NotAvailableEception {
        if (object1 != null && isDuplicate(object1))
            throw new NotAvailableEception();
        if (parkArea.size() == capacity) {

            notifyFull = false;
            notifySubscribers();
        }
        return parkArea.remove(object1);

    }

    @Override
    public void subscribe(Iobserver obj) {
        if (obj != null)
            this.people.add(obj);
    }

    @Override
    public void unSubscribe(Iobserver obj) {
        if (obj != null)
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
