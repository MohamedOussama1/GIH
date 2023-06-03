package ma.uiass.eia.pds.metier;

import ma.uiass.eia.pds.model.livraison.DetailLivraison;
import ma.uiass.eia.pds.model.livraison.LivraisonDM;
import org.json.JSONArray;

import java.util.List;

public interface LivraisonService {
    void saveLivraison(String fournisseur, JSONArray detailLivraisonList);


    DetailLivraison saveDetailLivraison(int qte, String dm, int idLivraison);

    List<LivraisonDM> getAllLivraison();
}
