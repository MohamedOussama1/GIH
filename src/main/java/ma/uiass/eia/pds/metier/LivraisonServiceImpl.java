package ma.uiass.eia.pds.metier;

import ma.uiass.eia.pds.model.livraison.DetailLivraison;
import ma.uiass.eia.pds.model.livraison.LivraisonDM;
import ma.uiass.eia.pds.persistance.LivraisonRepository;
import ma.uiass.eia.pds.persistance.LivraisonRepositoryImpl;
import org.json.JSONArray;

import java.util.List;

public class LivraisonServiceImpl implements LivraisonService{

    LivraisonRepository livraisonRepository = new LivraisonRepositoryImpl();
    @Override
    public void saveLivraison(String fournisseur, JSONArray detailLivraisonList) {
        livraisonRepository.saveLivraison(detailLivraisonList,fournisseur);
    }

        @Override
        public DetailLivraison saveDetailLivraison(int qte, String dm, int idLivraison) {
           return livraisonRepository.saveDetailLivraison(qte,dm, idLivraison);

        }

    @Override
    public List<LivraisonDM> getAllLivraison() {
        return livraisonRepository.getAllLivraison();
    }


}
