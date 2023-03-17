package ma.uiass.eia.pds.model.fournisseur;

public enum NomFournisseur {
    HILL_ROOM("Hill-Room"),
    INVACARE("Invacare"),
    LINET("Linet"),
    ARJO("arjo");
    String nom;
    NomFournisseur(String nom){
        this.nom = nom;
    }

    @Override
    public String toString() {
        return nom;
    }
}
