package ma.uiass.eia.pds.model.Lit;

public enum EtatLit {
    DISPONIBLE("Disponible"),
    OCCUPE("Occupé"),
    DEFECTUEUX("Défectueux");
    String name;
    EtatLit(String name){
        this.name = name;
    }
    // Convert String coming from the Front End to Enum
    public static EtatLit fromString(String s){
        EtatLit etatLit = null;
        switch (s.toLowerCase()) {
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

