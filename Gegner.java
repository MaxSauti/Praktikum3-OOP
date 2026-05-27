public class Gegner {
    private String name;

    /**
     * Konstruktor zum erstellen eines generischen Gegners
     * @param name  Name des Gegners
     */
    public Gegner (String name){
        this.name = name;
    }

    /**
     *
     * @return Name des Gegners
     */
    public String getName() {
        return name;
    }
}
