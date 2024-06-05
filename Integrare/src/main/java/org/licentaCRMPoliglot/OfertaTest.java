package org.licentaCRMPoliglot;

import org.licentaCRMPoliglot.Entities.Oferta.Oferta;
import org.licentaCRMPoliglot.Entities.Oferta.StatusOferta;
import org.licentaCRMPoliglot.Entities.Oferta.Tipreducere;
import org.licentaCRMPoliglot.Entities.Produs.Produs;
import org.licentaCRMPoliglot.Repositories.OfertaRepository;
import org.licentaCRMPoliglot.Repositories.ProdusRepository;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Random;

public class OfertaTest {
    private OfertaRepository ofertaRepository;
    private ProdusRepository produsRepository;

    public OfertaTest() {
        this.ofertaRepository = new OfertaRepository();
        this.produsRepository = new ProdusRepository();
    }

    public static void main(String[] args) {
        OfertaTest ofertaTest = new OfertaTest();
        ofertaTest.insertOferte(10);
        ofertaTest.closeEm();
        System.exit(0);
    }

    public void insertOferte(int numberPerTypeOfOffers) {
        try {
            for (int i = 0; i < numberPerTypeOfOffers; i++) {
                Produs randomProdus = selectRandomProdus();
                Oferta ofertaP = new Oferta();
                ofertaP.setCodoferta("OfertaProdus" + i);
                ofertaP.setStatus(StatusOferta.values()[new Random().nextInt(StatusOferta.values().length)]);
                ofertaP.setTipreducere(Tipreducere.PRODUS);
                ofertaP.setValoarereducere(1.0);
                ofertaP.setCostpuncte(generateRandomIntWithStep(100, 600, 20));
                ofertaP.setCodprodus(randomProdus);

                Oferta ofertaV = new Oferta();
                ofertaV.setCodoferta("OfertaVoucher" + i);
                ofertaV.setStatus(StatusOferta.values()[new Random().nextInt(StatusOferta.values().length)]);
                ofertaV.setTipreducere(Tipreducere.VOUCHER);
                Double valoarereducere = generateRandomDoubleWithStep(50.0, 100.0, 10.0);
                ofertaV.setValoarereducere(valoarereducere);

                Oferta ofertaPr = new Oferta();
                ofertaPr.setCodoferta("OfertaProcentLaComanda" + i);
                ofertaPr.setStatus(StatusOferta.values()[new Random().nextInt(StatusOferta.values().length)]);
                ofertaPr.setTipreducere(Tipreducere.PROCENT);
                valoarereducere = generateRandomDoubleWithStep(0.1, 1.0, 0.1);
                ofertaPr.setValoarereducere(valoarereducere);

                ofertaRepository.addOferta(ofertaPr);
                ofertaRepository.addOferta(ofertaV);
                ofertaRepository.addOferta(ofertaP);

                System.out.println("Inserted Offers: " + ofertaP.getCodoferta() + ", " + ofertaV.getCodoferta() + ", " + ofertaPr.getCodoferta());
            }
        } catch (Exception e) {
            ofertaRepository.rollbackTransaction();
            throw e;
        }
    }

    private Produs selectRandomProdus() {
        List<Produs> allProducts = produsRepository.findAll();
        if (allProducts.isEmpty()) {
            throw new IllegalStateException("No products available in the database.");
        }
        return allProducts.get(new Random().nextInt(allProducts.size()));
    }

    private int generateRandomIntWithStep(int min, int max, int step) {
        return new Random().nextInt((max - min) / step + 1) * step + min;
    }

    private Double generateRandomDoubleWithStep(double min, double max, double step) {
        Random random = new Random();
        int numSteps = (int) ((max - min) / step);
        double randomValue = min + random.nextInt(numSteps + 1) * step;
        return BigDecimal.valueOf(randomValue).setScale(1, RoundingMode.HALF_UP).doubleValue();
    }

    public void closeEm() {
        ofertaRepository.closeEntityManager();
    }
}
