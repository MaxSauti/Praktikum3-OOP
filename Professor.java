public class Professor extends Gegner{

    private Frage frage;

    public Professor (String name, Frage frage){
        super(name);
        this.frage = frage;
    }

    public Frage getFrage() {
        return frage;
    }
}
