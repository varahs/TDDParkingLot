import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

    private List parkArea ;
   private int capacity ;

    public ParkingLot(int capacity) {
        this.parkArea= new ArrayList();
        this.capacity = capacity;
    }

    public boolean park(Vehicle vehicle) {
        if (this.parkArea.size() < capacity)
            return this.parkArea.add(vehicle);

        return false;
    }

}
