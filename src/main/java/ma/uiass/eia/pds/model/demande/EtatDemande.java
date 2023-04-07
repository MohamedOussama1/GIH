package ma.uiass.eia.pds.model.demande;

public enum EtatDemande {
    TRAITÉE("traitée"),
    NONTRAITÉE("nontraitée"),

    ENCOURS("en cours de traitement"),

   REJETÉE ("rejetée");
    String name;
    EtatDemande(String name){
        this.name = name;
    }
    // Convert String coming from the Front End to Enum
    public static EtatDemande fromString(String s){
        EtatDemande etatDemande = null;
        switch (s.toLowerCase()) {
            case "traitée":
                etatDemande = TRAITÉE;
                break;
            case "nontraitée":
                etatDemande = NONTRAITÉE;
                break;
            case "encours":
                etatDemande = ENCOURS;
                break;
            case "rejetée":
                etatDemande = REJETÉE;
                break;

        }
        return etatDemande;
    }

}
