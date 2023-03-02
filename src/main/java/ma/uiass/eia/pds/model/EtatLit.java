package ma.uiass.eia.pds.model;

public enum EtatLit {
    DISPONIBLE("disponible"),
    OCCUPE("occupé"),
    EN_STOCK("en stock"),
    DEFECTUEUX("défectueux");
    String name;
    EtatLit(String name){
        this.name = name;
    }
    public static EtatLit fromString(String s){
        EtatLit etatLit = EN_STOCK;
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

