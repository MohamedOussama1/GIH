package ma.uiass.eia.pds.model.Lit.enums;

public enum EtatLit {
    NOUVEAU("Nouveau"),
    BON("Bon"),
    MAUVAIS("mauvais"),
    DEFECTUEUX("Défectueux");
    String name;
    EtatLit(String name){
        this.name = name;
    }
    // Convert String coming from the Front End to Enum
    public static EtatLit fromString(String s){
        EtatLit etatLit = null;
        switch (s.toLowerCase()) {
            case "nouveau":
                etatLit = NOUVEAU;
                break;
            case "bon":
                etatLit = BON;
                break;
            case "mauvais":
                etatLit = MAUVAIS;
                break;
            case "défectueux":
                etatLit = DEFECTUEUX;
                break;
        }
        return etatLit;
        }
    }

