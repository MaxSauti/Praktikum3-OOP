public class Praktikum extends Gegner{

    private Aufgabe aufgabe;

    /**
     * Konstruktor zum Erstellen eines Praktikums
     * @param name      Name des Praktikums
     * @param aufgabe   Aufgabe des Praktikums
     */
    public Praktikum (String name, Aufgabe aufgabe) {
        super (name);
        this.aufgabe = aufgabe;
    }

    /**
     * @return  Aufgabe des Praktikums
     */
    public Aufgabe getAufgabe() {
        return aufgabe;
    }


}
