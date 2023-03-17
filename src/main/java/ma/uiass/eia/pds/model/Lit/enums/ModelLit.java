package ma.uiass.eia.pds.model.Lit.enums;

public enum ModelLit {
    STANDARD("Standard"),
    ROTATION("À fonction de rotation"),
    PESEE("À fonction de pesée"),
    BARIATRIQUE("Bariatrique"),
    CIRCULATION("À fonction d'aide à la circulation"),
    TRAITEMENT("À fonction de traitement");
    String description;
    ModelLit(String description){
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
