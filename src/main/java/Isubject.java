
public interface Isubject {

    void subscribe(Iobserver obj) ;
    void unSubscribe(Iobserver obj);
    void notifySubscribers();
    boolean getUpdate(Iobserver iobserver);

}
