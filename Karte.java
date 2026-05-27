public class Karte {

    /**
     * Wird benutzt um die Landkarte auszugeben
     * @param raumArr           Raum Array aus dem die Karte erstellt wird
     * @param aktuellerRaum     Aktuelle Position
     * @param bereit            Ob alle Praktika bestanden sind
     */
    public void printKarte(Raum[][] raumArr, Raum aktuellerRaum, boolean bereit){
        System.out.println();
        for (Raum[] raumUA : raumArr){
            boolean raeumeEx = false;
            for (Raum raum : raumUA) {
                if (raum != null) {
                    raeumeEx = true;
                    break;
                }
            }
            if (!raeumeEx) {
                continue;
            }
            for (Raum raum : raumUA) {
                if (raum == null) {
                    System.out.print("     ");
                } else if (raum == aktuellerRaum) {
                    System.out.print(" POS ");
                } else if (!raum.isSecret()) {
                    if (raum.getGegner() != null) {
                        if (raum.getGegner() instanceof Professor prof && raum.isBesiegt()) {
                            System.out.print(" RAU ");
                        } else if (raum.getGegner() instanceof Praktikum prak && prak.getAufgabe().isErfuellt()) {
                            System.out.print(" RAU ");
                        } else {
                            System.out.print("!RAU!");
                        }
                    } /*else if (raum.getHilfsmittel() != null) {
                        System.out.print("*RAU*");
                    }*/ else {
                        System.out.print(" RAU ");
                    }
                } else {
                    if (bereit && raum.getGegner() == null) {
                        System.out.print(" SEC ");
                    } else if (bereit && raum.getGegner() instanceof Abschlusspruefung) {
                        System.out.print(" FIN ");
                    } else {
                        System.out.print("     ");
                    }

                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
