public class Praktikum extends Gegner{

    private Aufgabe aufgabe;

    public Praktikum (String name, Aufgabe aufgabe) {
        super (name);
        this.aufgabe = aufgabe;
    }

    public Aufgabe getAufgabe() {
        return aufgabe;
    }


}
