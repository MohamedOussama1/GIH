package ma.uiass.eia.pds.persistance;

import ma.uiass.eia.pds.model.Lit.enums.ModelLit;
import ma.uiass.eia.pds.model.Lit.enums.TypeLit;
import ma.uiass.eia.pds.model.demande.DemandeAffectation;
import ma.uiass.eia.pds.model.demande.EtatDemande;

import java.util.List;

public interface DemandeAffectationRepository {

    List<DemandeAffectation> getAllDemandes();
    List<DemandeAffectation> getDemandesByDepartement(String nomDepartement);

    void ajouterDemandeAffectation(TypeLit typeLit, ModelLit modelLit, String nomDepartement,  int qte);

    void updateEtatDemande(int id, EtatDemande etatDemande);


}
