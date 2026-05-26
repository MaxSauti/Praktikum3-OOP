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

    public void printKarte(Raum[][] raumArr, Raum aktuellerRaum, boolean bereit){
        for (Raum[] raumUA : raumArr){
            boolean raeumeEx = false;
            for (Raum raum : raumUA) {
                if (raum == null) {
                    System.out.print("     ");
                } else if (raum == aktuellerRaum) {
                    System.out.print(" POS ");
                    raeumeEx = true;
                } else if (!raum.isSecret()) {
                    System.out.print(" RAU ");
                    raeumeEx = true;
                } else if (raum.isSecret() && bereit) {
                    System.out.print(" SEC ");
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
