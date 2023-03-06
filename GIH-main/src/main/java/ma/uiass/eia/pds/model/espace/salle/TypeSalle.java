package ma.uiass.eia.pds.model.espace.salle;

public enum TypeSalle {
    SALLE_EXAMINATION("Examination"),
    SALLE_PROCEDURE("Procedure"),
    SALLE_REVEIL("Reveil"),
    SALLE_RADIOLOGIE("Radiologie"),
    SALLE_INFUSION("Infusion"),
    SALLE_RADIATION("Radiation");
    String name;
    TypeSalle(String name){
        this.name = name;
    }

    // Convert String coming from the Front End to Enum
    public static TypeSalle fromString(String s){
        TypeSalle typeSalle = null;
        switch (s.toLowerCase()) {
            case "examination":
                typeSalle = SALLE_EXAMINATION;
                break;
            case "procedure":
                typeSalle = SALLE_PROCEDURE;
                break;
            case "reveil":
                typeSalle = SALLE_REVEIL;
                break;
            case "radiologie":
                typeSalle = SALLE_RADIOLOGIE;
                break;
            case "infusion":
                typeSalle = SALLE_INFUSION;
                break;
            case "radiation":
                typeSalle = SALLE_RADIATION;
                break;
        }
        return typeSalle;
    }
}
