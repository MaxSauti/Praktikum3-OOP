public class Professor extends Gegner{

    private Frage frage;
    private int tryCount;

    public Professor (String name, Frage frage){
        super(name);
        this.frage = frage;
    }

    public Frage getFrage() {
        return frage;
    }

    public int tryCount() { return tryCount; }

    public void setTryCount() {
        this.tryCount++;
    }
}
