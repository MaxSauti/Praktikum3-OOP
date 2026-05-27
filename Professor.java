public class Professor extends Gegner{

    private Frage frage;
    private int tryCount;

    /**
     * Konstruktor zum Erstellen eines Professors
     * @param name      Name des Professors
     * @param frage     Frage des Professors
     */
    public Professor (String name, Frage frage){
        super(name);
        this.frage = frage;
    }

    /**
     *
     * @return  Frage des Professors
     */
    public Frage getFrage() {
        return frage;
    }

    /**
     *
     * @return  Versuche die gebraucht wurden um die Frage zu beantworten
     */
    public int tryCount() { return tryCount; }

    /**
     * Inkrementiere Versuchscounter
     */
    public void setTryCount() {
        this.tryCount++;
    }
}
