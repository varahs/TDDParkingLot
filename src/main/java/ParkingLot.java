import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ParkingLot {

 private int[] parkArea= new int[20];

    public  boolean park(){
        this.parkArea[0]=1;
        return true;

    }
    public int ParkSize(){
        return 20-this.parkArea.length;
    }
}
