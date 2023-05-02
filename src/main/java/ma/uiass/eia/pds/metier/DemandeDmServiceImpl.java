package ma.uiass.eia.pds.metier;

import ma.uiass.eia.pds.model.demande.DemandeDm;
import ma.uiass.eia.pds.persistance.DemandeDmRepository;
import ma.uiass.eia.pds.persistance.DemandeDmRepositoryImpl;

import java.util.List;

public class DemandeDmServiceImpl implements DemandeDmService{
    DemandeDmRepository demandeDmRepository = new DemandeDmRepositoryImpl();
    @Override
    public int ajouterDemandeDm(String nomDepartement) {
        return demandeDmRepository.ajouterDemandeDm(nomDepartement);
    }
    public void ajouterDetailDemandeDm(String nomDM, int qte, int idDemandeDm){
        demandeDmRepository.ajouterDetailDemandeDM(nomDM, qte, idDemandeDm);
    };

    @Override
    public void updateEtatDemande(int id, String etatDemande) {
        demandeDmRepository.updateEtatDemande(id, etatDemande);
    }

    @Override
    public List<String> getAllDemandes() {
        return demandeDmRepository.getAllDemandes();
    }

    @Override
    public List<String> getDemandesByDepartement(String nomDepartement) {
        return demandeDmRepository.getDemandesByDepartement(nomDepartement);
    }

    @Override
    public void updateEtatDetail(int idDetail, String etatDetail) {
        demandeDmRepository.updateEtatDetail(idDetail, etatDetail);
    }
}
