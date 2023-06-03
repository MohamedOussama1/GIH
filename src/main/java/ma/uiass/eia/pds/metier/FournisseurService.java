package ma.uiass.eia.pds.metier;

import ma.uiass.eia.pds.model.fournisseur.Fournisseur;

import java.util.List;

public interface FournisseurService {
    List<Fournisseur> getAll();
    void getFournisseurByName(String nom);
    void saveFournisseur(String nom, String ville, String email, String codeAPE, String formeJuridique, String numSiren, String tel);

    int deleteFournisseur(int id);
    void modifyFournisseur(int id,String nom, String ville, String email, String codeAPE, String formeJuridique, String numSiren, String tel);
}
