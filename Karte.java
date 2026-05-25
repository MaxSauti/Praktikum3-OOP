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

    public void printKarte(Raum[][] raumArr, Raum aktuellerRaum){
        for (Raum[] raumUA : raumArr){
            boolean raeumeEx = false;
            for (Raum raum : raumUA) {
                if (raum == null) {
                    System.out.print("     ");
                } else if (raum == aktuellerRaum) {
                    System.out.print(" POS ");
                    raeumeEx = true;
                } else {
                    System.out.print(" RAU ");
                    raeumeEx = true;
                }
            }
            if (raeumeEx) {
                System.out.println();
            }
        }
        System.out.println();
    }
}
