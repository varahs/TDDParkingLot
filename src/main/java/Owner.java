public class Owner implements Iobserver {

    private int numberOfTimesFullInformed;
    private int numberOfTimesEmptyInformed;
    private boolean isFull;
    private Isubject isubject;

    @Override
    public void update() {
        this.isFull = isubject.getUpdate(this);
        if(this.isFull)
            fullInform();
        if(!this.isFull)
            emptyInform();
    }

    @Override
    public void setSubject(Isubject subject) {
        this.isubject = subject;
    }

       public void fullInform() {
        this.numberOfTimesFullInformed =this.numberOfTimesFullInformed+ 1;
    }

    public void emptyInform() {
        this.numberOfTimesEmptyInformed=this.numberOfTimesEmptyInformed + 1;
    }
}
