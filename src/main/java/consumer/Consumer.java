package consumer;

import exception.DuplicateException;
import exception.NotAvailableEception;
import exception.ParkingFullException;
import parkinglot.ParkingLot;


public class Consumer {

    private ParkingLot personalParkingLot;
    private ParkingLot guestParkingLot;

    private Consumer(ParkingLot parkingLot1, ParkingLot parkingLot2) {
        this.personalParkingLot = parkingLot1;
        this.guestParkingLot = parkingLot2;
    }

    private void parking(Car car) {
        try {
            this.personalParkingLot.park(car);
            System.out.println("Parking consumer.Car " + car.toString() + " added to Personal parkinglot");
        } catch (DuplicateException e) {
            System.out.println("Parking consumer.Car " + car.toString() + " is a duplicate in Personal parkinglot");
        } catch (ParkingFullException e) {
            System.out.println("Parking consumer.Car " + car.toString() + " can't be added to Personal parkinglot");
            try {
                this.guestParkingLot.park(car);
                System.out.println("Parking consumer.Car " + car.toString() + " added to Guest parkinglot");
            } catch (DuplicateException ex) {
                System.out.println("Parking consumer.Car " + car.toString() + " is a duplicate in Guest parkinglot");
            } catch (ParkingFullException ex) {
                System.out.println("Parking consumer.Car " + car.toString() + " can't be added to Guest parkinglot");
            }
        }
    }

    private void unPark(Car car) {


        try {
            this.personalParkingLot.unPark(car);
            System.out.println("Unparking consumer.Car " + car.toString() + " in Personal Parkinglot");
        } catch (NotAvailableEception notAvailableEception) {
            System.out.println("Unparking consumer.Car " + car.toString() + " not available in Personal Parkinglot");
            try {
                this.guestParkingLot.unPark(car);
                System.out.println("Unparking car " + car.toString() + " in guest parkinglot");
            } catch (NotAvailableEception availableEception) {
                System.out.println("Unparking car " + car.toString() + " not available in guest parkinglot");
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
        consumer.parking(C);
        consumer.unPark(C);
        consumer.unPark(B);
        consumer.unPark(A);
    }
}
