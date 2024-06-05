package org.licentaCRMPoliglot;

import org.licentaCRMPoliglot.Entities.Produs.Produs;
import org.licentaCRMPoliglot.Entities.ProdusReferences.TaguriProdus;
import org.licentaCRMPoliglot.Repositories.ProdusRepository;
import org.licentaCRMPoliglot.Repositories.TaguriProdusRepo;
import org.licentaCRMPoliglot.Entities.Produs.StatusProdus;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.util.List;
import java.util.Random;

public class ProdusTest {
    public static void main(String[] args) {
        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            ProdusRepository produsRepository = new ProdusRepository();
            TaguriProdusRepo taguriProdusRepo = container.select(TaguriProdusRepo.class).get();
            addMultipleProductsWithTags(produsRepository, taguriProdusRepo, 10);
        }
        System.exit(0);
    }

    public static void addMultipleProductsWithTags(ProdusRepository produsRepository, TaguriProdusRepo taguriProdusRepo, int numberOfProducts) {
        Random random = new Random();

        for (int i = 1; i <= numberOfProducts; i++) {
            Produs produs = new Produs();
            produs.setCodprodus("P" + i);
            produs.setNume("Produs" + i);
            produs.setRating(random.nextDouble() * 5);
            produs.setStatusProdus(StatusProdus.INSTOCK);
            produs.setPret(10 + (8000 - 10) * random.nextDouble());

            try {
                produsRepository.addProdus(produs);
                System.out.println("Inserted product: " + produs.getCodprodus());

                int numberOfTags = random.nextInt(5) + 1;
                for (int j = 1; j <= numberOfTags; j++) {
                    TaguriProdus taguriProdus = new TaguriProdus();
                    taguriProdus.setCodProdus(produs.getCodprodus());
                    taguriProdus.setTagUri(List.of("Tag" + j, "CommonTag"));

                    try {
                        taguriProdusRepo.save(taguriProdus);
                        System.out.println("Inserted taguriProdus for product: " + taguriProdus.getCodProdus() + " with tags: " + taguriProdus.getTagUri());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Failed to insert taguriProdus: " + e.getMessage());
                    }
                }
            } catch (Exception e) {
                System.out.println("Failed to insert product: " + e.getMessage());
            }
        }
    }
}
