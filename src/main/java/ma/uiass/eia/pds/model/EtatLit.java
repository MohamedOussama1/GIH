package ma.uiass.eia.pds.model;

public enum EtatLit {
    DISPONIBLE("Disponible"),
    OCCUPE("Occupé"),
    EN_STOCK("En Stock"),
    DEFECTUEUX("Défectueux");
    String name;
    EtatLit(String name){
        this.name = name;
    }
    // Convert String coming from the Front End to Enum
    public static EtatLit fromString(String s){
        EtatLit etatLit = null;
        switch (s.toLowerCase()) {
            case "en stock":
                etatLit = EN_STOCK;
                break;
            case "disponible":
                etatLit = DISPONIBLE;
                break;
            case "occupé":
                etatLit = OCCUPE;
                break;
            case "deféctueux":
                etatLit = DEFECTUEUX;
                break;
        }
        return etatLit;
        }
    }

