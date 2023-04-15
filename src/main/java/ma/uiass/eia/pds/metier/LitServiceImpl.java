package ma.uiass.eia.pds.metier;

import ma.uiass.eia.pds.model.Lit.LitItem;
import ma.uiass.eia.pds.model.espace.Espace;
import ma.uiass.eia.pds.model.reservation.Reservation;
import ma.uiass.eia.pds.persistance.LitRepository;
import ma.uiass.eia.pds.persistance.LitRepositoryImpl;
import ma.uiass.eia.pds.persistance.ReservationRepository;
import ma.uiass.eia.pds.persistance.ReservationRepositoryImpl;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LitServiceImpl implements LitService {
    private LitRepository litRepository = new LitRepositoryImpl();
    private ReservationRepository reservationRepository = new ReservationRepositoryImpl();

    private LitManager litManager = new LitManagerImpl();

    @Override
    public List<String> getFonctionsLit() {
        return litRepository.getFonctionsLit();
    }

    @Override
    public List<String> getTypesLit() {
        return litRepository.getTypesLit();
    }

    @Override
    public List<String> getModelsLit() {
        return litRepository.getModelsLit();
    }

    @Override
    public int addLitDescription(String type, String modelLit, String dimensions, double chargeMax, Period garantie, double prix, List<String> fonctions, String frontColor, String description) {
        return litRepository.saveLit(type, modelLit, dimensions, chargeMax, garantie, prix, fonctions, frontColor, description);
    }

    @Override
    public void addLits(int quantity, int litDescriptionId) {
        litRepository.saveManyLit(quantity, litDescriptionId);
    }


    @Override
    public void annulerReservation(int idLit) {

    }

    @Override
    public Reservation findReservation(int idReservation) {
        return null;
    }


    @Override
    public List<Reservation> getCurrentReservation(Period period) {
        return null;
    }

    @Override
    public List<String> getLitsStock() {
        return litRepository.findAllLitStock();
    }

    @Override
    public List<String> findByEtat(String etatLit) {
        return null;
    }
    @Override
    public void deleteLit(int id) {
        litRepository.deleteLit(id);
    }

    // Rachid


    @Override
    public void reserverLit(int idLit) {
        reservationRepository.saveReservation(idLit);
        litRepository.occuperLit(idLit);
    }
    @Override
    public HashMap<Espace, List<LitItem>> getLitsEspace(String nomDepartement) {

        List<LitItem> lstallLit = this.getLits(nomDepartement);
        HashMap<Espace, List<LitItem>> mapchambre = new HashMap<>();

        for (LitItem lit : lstallLit) {
            if (!mapchambre.containsKey(lit.getEspace())) {
                mapchambre.put(lit.getEspace(), new ArrayList<>());
            }
            mapchambre.get(lit.getEspace()).add(lit);
        }

        return mapchambre;
    }

    @Override
    public List<LitItem> getLits(String nomDepartement) {
        return litRepository.findAllLit(nomDepartement);
    }

    @Override
    public List<LitItem> Chercher_lit_disponbile_a_Reserver(String service, String typeEspace) {

        List<LitItem> lstItem = new ArrayList<>();


        List<LitItem> allBeds = litManager.getAllLit(service);

        allBeds.forEach(elt -> {
            if (!elt.getOccupied()) {
                lstItem.add(elt);
            }
        });

        return lstItem;
    }

    @Override
    public void saveExitDate(int idLit) {
        reservationRepository.saveExitDate(idLit);
    }

    @Override
    public List<Reservation> getReservationHistorique() {
        return reservationRepository.getAll();
    }
    @Override
    public List<Reservation> getCurrentReservations() {
        return reservationRepository.getCurrentAll();
    }
    @Override
    public List<Reservation> findReservations(int idLit) {
        return reservationRepository.getReservationsLit(idLit);
    }

    @Override
    public void occuperLit(int idlit) {
        litRepository.occuperLit(idlit);
    }

}
