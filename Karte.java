public class Karte {
    public void erzeugeKarte(Raum raum) {

    }

    public void sucheRaueme(Raum raum) {
        if (raum.isBesucht()) {
            return;
        }

        System.out.println(raum.gibKurzbeschreibung());
        raum.setBesucht(true);

        for (Object ausgang : raum.getAusgaenge().values()){
            if (ausgang != null) {
                sucheRaueme((Raum) ausgang);
            }
        }
    }
}
