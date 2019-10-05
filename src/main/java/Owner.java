public class Owner {

    private boolean message;
    private int numberOfTimesInformed;
    public void inform() {
        this.numberOfTimesInformed +=1;
        message=!message;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "message=" + message +
                ", numberOfTimesInformed=" + numberOfTimesInformed +
                '}';
    }
}
