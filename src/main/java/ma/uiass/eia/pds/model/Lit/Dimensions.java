package ma.uiass.eia.pds.model.Lit;

public class Dimensions {
    double longeur;
    double largeur;
    double hauteur;

    public Dimensions(double longeur, double largeur, double hauteur) {
        this.longeur = longeur;
        this.largeur = largeur;
        this.hauteur = hauteur;
    }

    public double getLongeur() {
        return longeur;
    }

    public void setLongeur(double longeur) {
        this.longeur = longeur;
    }

    public double getLargeur() {
        return largeur;
    }

    public void setLargeur(double largeur) {
        this.largeur = largeur;
    }

    public double getHauteur() {
        return hauteur;
    }

    public void setHauteur(double hauteur) {
        this.hauteur = hauteur;
    }

    @Override
    public String toString() {
        return longeur +
                "x" +
                largeur +
                "x"
                + hauteur;

    }
}
