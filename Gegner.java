public class Gegner {
    private String name;
    private Frage frage;

    public Gegner (String name, Frage frage){
        this.name = name;
        this.frage = frage;
    }

    public String getName() {
        return name;
    }

    public Frage getFrage() {
        return frage;
    }


}
