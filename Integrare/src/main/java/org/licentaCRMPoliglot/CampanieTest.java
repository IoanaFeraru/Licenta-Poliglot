package org.licentaCRMPoliglot;

import org.licentaCRMPoliglot.Entities.Campanie.Campanie;
import org.licentaCRMPoliglot.Entities.Campanie.TipCampanie;
import org.licentaCRMPoliglot.Entities.Oferta.Oferta;
import org.licentaCRMPoliglot.Repositories.CampanieRepository;
import org.licentaCRMPoliglot.Repositories.OfertaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class CampanieTest {
    private final CampanieRepository campanieRepository;
    private final OfertaRepository ofertaRepository;

    public CampanieTest() {
        this.campanieRepository = new CampanieRepository();
        this.ofertaRepository = new OfertaRepository();
    }

    public static void main(String[] args) {
        CampanieTest campanieTest = new CampanieTest();
        campanieTest.insertCampanii(10);
        campanieTest.closeEm();
        System.exit(0);
    }

    public void insertCampanii(int numberOfCampanii) {
        try {
            for (int i = 0; i < numberOfCampanii; i++) {
                Oferta randomOferta = selectRandomOferta();

                Campanie campanie = new Campanie();
                campanie.setNume("Campanie " + i);
                campanie.setDatastart(LocalDate.now().minusDays(new Random().nextInt(30)));
                campanie.setDatastop(LocalDate.now().plusDays(new Random().nextInt(30)));
                campanie.setTip(TipCampanie.values()[new Random().nextInt(TipCampanie.values().length)]);
                campanie.setCodoferta(randomOferta);

                campanieRepository.addCampanie(campanie);
                System.out.println("Inserted Campanie: " + campanie.getNume());
            }
        } catch (Exception e) {
            campanieRepository.rollbackTransaction();
            throw e;
        }
    }

    private Oferta selectRandomOferta() {
        List<Oferta> allOferte = ofertaRepository.findAll();
        if (allOferte.isEmpty()) {
            throw new IllegalStateException("No offers available.");
        }
        return allOferte.get(new Random().nextInt(allOferte.size()));
    }

    public void closeEm() {
        campanieRepository.closeEntityManager();
    }
}
