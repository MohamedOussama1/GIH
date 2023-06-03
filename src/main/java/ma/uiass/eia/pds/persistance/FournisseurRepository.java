package ma.uiass.eia.pds.persistance;

import ma.uiass.eia.pds.model.fournisseur.Fournisseur;

import java.util.List;

public interface FournisseurRepository {
    List<Fournisseur> getAll();
    Fournisseur getFournisseurByName(String fournisseur);
    void saveFournisseur(String nom, String ville, String email, String codeAPE, String formeJuridique, String numSiren, String tel);
    int deleteFournisseur( int id);

    void modifyFournisseur(int id,String nom, String ville, String email, String codeAPE, String formeJuridique, String numSiren, String tel);


}
