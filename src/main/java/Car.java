public class Car {
    private String type;

    @Override
    public String toString() {
        return "Car{" +
                "type='" + type + '\'' +
                '}';
    }

    public Car(String type) {
        this.type = type;
    }
}
