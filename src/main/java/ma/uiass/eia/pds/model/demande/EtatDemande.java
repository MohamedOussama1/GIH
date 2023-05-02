package ma.uiass.eia.pds.model.demande;

public enum EtatDemande {
    INITIALISÉE("initialisée"),
    CONFIRMÉE("confirmée"),
    TRAITÉE("traitée"),

    ACCEPTÉE("acceptée"),

   REJETÉE ("rejetée");
    String name;
    EtatDemande(String name){
        this.name = name;
    }
    // Convert String coming from the Front End to Enum
    public static EtatDemande fromString(String s){
        EtatDemande etatDemande = null;
        switch (s.toLowerCase()) {
            case "initialisée":
                etatDemande = INITIALISÉE;
                break;
            case "confirmée":
                etatDemande = CONFIRMÉE;
                break;
            case "acceptée":
                etatDemande = ACCEPTÉE;
                break;
            case "rejetée":
                etatDemande = REJETÉE;
                break;
            case "traitée":
                etatDemande = TRAITÉE;
                break;

        }
        return etatDemande;
    }

}
