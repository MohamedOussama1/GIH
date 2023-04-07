package ma.uiass.eia.pds.metier;

import ma.uiass.eia.pds.model.Lit.enums.ModelLit;
import ma.uiass.eia.pds.model.Lit.enums.TypeLit;
import ma.uiass.eia.pds.model.demande.DemandeAffectation;
import ma.uiass.eia.pds.model.demande.EtatDemande;
import ma.uiass.eia.pds.persistance.DemandeAffectationRepository;
import ma.uiass.eia.pds.persistance.DemandeAffectationRepositoryImpl;

import java.util.List;

public class DemandeAffectationServiceImpl implements DemandeAffectationService{

    public DemandeAffectationServiceImpl() {

    }

    DemandeAffectationRepository DAR = new DemandeAffectationRepositoryImpl();

    @Override
    public void ajouterDemandeAffectation(TypeLit typeLit, ModelLit modelLit, String nomDepartement,  int qte) {
        DAR.ajouterDemandeAffectation( typeLit, modelLit,  nomDepartement,  qte);
    }

    @Override
    public void updateEtatDemande(int id, EtatDemande etatDemande) {
        DAR.updateEtatDemande(id,etatDemande);
    }

    @Override
    public List<DemandeAffectation> getAllDemandes() {
        return DAR.getAllDemandes();
    }

    @Override
    public List<DemandeAffectation> getDemandesByDepartement(String nomDepartement) {
        return DAR.getDemandesByDepartement(nomDepartement);
    }

}

