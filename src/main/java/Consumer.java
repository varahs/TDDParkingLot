public class Consumer {

    ParkingLot personalParkingLot;
    ParkingLot guestParkingLot;

    public Consumer(ParkingLot parkingLot1, ParkingLot parkingLot2) {
        this.personalParkingLot = parkingLot1;
        this.guestParkingLot = parkingLot2;
    }
    public  void parking(Car car)  {
        try {
            this.personalParkingLot.park(car);
            System.out.println("Car "+car.toString()+" added to Personal parkinglot");
        } catch (DuplicateException e) {
            System.out.println("Car "+car.toString()+" is a duplicate in Personal parkinglot");
        } catch (ParkingFullException e) {
            System.out.println("Car "+car.toString()+" can't be added to Personal parkinglot");
            try {
                this.guestParkingLot.park(car.toString());
                System.out.println("Car "+car.toString()+" added to Guest parking ot");
            } catch (DuplicateException ex) {
                System.out.println("Car "+car.toString()+" is a duplicate in Guest parkinglot");
            } catch (ParkingFullException ex) {
                System.out.println("Car "+car.toString()+" can't be added to Guest parkinglot");
            }
        }
    }

    public static void main(String[] args) {
       Consumer consumer = new Consumer(new ParkingLot(2), new ParkingLot(3));
        Car A = new Car("A");
        Car B = new Car("B");
        Car C = new Car("C");
       consumer.parking(A);
       consumer.parking(B);
       consumer.parking(B);
    }
}
