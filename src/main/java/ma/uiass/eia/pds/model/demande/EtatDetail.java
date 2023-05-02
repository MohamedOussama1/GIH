package ma.uiass.eia.pds.model.demande;

public enum EtatDetail {
    CONFIRMÉE("confirmée"),

    ACCEPTÉE("acceptée"),

    REJETÉE ("rejetée");
    String name;
    EtatDetail(String name){
        this.name = name;
    }
    // Convert String coming from the Front End to Enum
    public static EtatDetail fromString(String s){
        EtatDetail etatDetail = null;
        switch (s.toLowerCase()) {
            case "confirmée":
                etatDetail = CONFIRMÉE;
                break;
            case "acceptée":
                etatDetail = ACCEPTÉE;
                break;
            case "rejetée":
                etatDetail = REJETÉE;
                break;
        }
        return etatDetail;
    }
}
