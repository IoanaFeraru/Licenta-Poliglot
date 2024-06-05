package org.licentaCRMPoliglot;

import org.licentaCRMPoliglot.Entities.Achizitie.Achizitie;
import org.licentaCRMPoliglot.Entities.Achizitie.LinieAchizitie;
import org.licentaCRMPoliglot.Entities.Client.Client;
import org.licentaCRMPoliglot.Entities.Produs.Produs;
import org.licentaCRMPoliglot.Entities.Oferta.Oferta;
import org.licentaCRMPoliglot.Repositories.AchizitieRepo;
import org.licentaCRMPoliglot.Repositories.ClientRepository;
import org.licentaCRMPoliglot.Repositories.ProdusRepository;
import org.licentaCRMPoliglot.Repositories.OfertaRepository;
import org.licentaCRMPoliglot.Repositories.IstoricPuncteRepo;
import org.licentaCRMPoliglot.Utilities.AchizitieUtil;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AchizitieTest {
    public static void main(String[] args) {
        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            ProdusRepository produsRepository = new ProdusRepository();
            OfertaRepository ofertaRepository = new OfertaRepository();
            AchizitieRepo achizitieRepo = container.select(AchizitieRepo.class).get();
            ClientRepository clientRepository = new ClientRepository();
            IstoricPuncteRepo istoricPuncteRepo = container.select(IstoricPuncteRepo.class).get();

            createAchizitii(produsRepository, ofertaRepository, achizitieRepo, clientRepository, istoricPuncteRepo);
        }
        System.exit(0);
    }

    public static void createAchizitii(ProdusRepository produsRepository, OfertaRepository ofertaRepository, AchizitieRepo achizitieRepo, ClientRepository clientRepository, IstoricPuncteRepo istoricPuncteRepo) {
        List<Client> allClients = clientRepository.findAll();
        List<Oferta> allOffers = ofertaRepository.findAll();
        List<Produs> allProducts = produsRepository.findAll();

        Optional<Integer> maxCodAchizitieOpt = achizitieRepo.findAll()
                .stream()
                .map(Achizitie::getCodAchizitie)
                .max(Integer::compare);
        int currentCodAchizitie = maxCodAchizitieOpt.orElse(1);

        Random random = new Random();

        for (Client client : allClients) {
            int numberOfAchizitii = random.nextInt(5) + 1;

            for (int j = 0; j < numberOfAchizitii; j++) {
                Achizitie achizitie = new Achizitie();
                currentCodAchizitie++;
                achizitie.setCodAchizitie(currentCodAchizitie);
                achizitie.setData(new Date());
                achizitie.setCodClient(client.getCodclient());

                int numberOfProducts = random.nextInt(allProducts.size()) + 1;
                List<LinieAchizitie> liniiAchizitie = IntStream.range(0, numberOfProducts)
                        .mapToObj(index -> {
                            Produs selectedProduct = allProducts.get(random.nextInt(allProducts.size()));
                            LinieAchizitie linie = new LinieAchizitie();
                            linie.setCodProdus(selectedProduct.getCodprodus());
                            linie.setCantitate(random.nextInt(10) + 1);
                            return linie;
                        })
                        .collect(Collectors.toList());

                achizitie.setLinieAchizitie(liniiAchizitie);

                if (!allOffers.isEmpty() && random.nextBoolean()) {
                    Oferta selectedOffer = allOffers.get(random.nextInt(allOffers.size()));
                    achizitie.setCodOferta(selectedOffer.getCodoferta());
                }

                AchizitieUtil.calculateValoareAchizitie(achizitie, produsRepository, ofertaRepository);
                AchizitieUtil.calculateValoarePuncte(achizitie, ofertaRepository);

                achizitieRepo.save(achizitie);

                AchizitieUtil.updatePuncteClient(achizitie, clientRepository, istoricPuncteRepo);

                System.out.println("Achizitie saved with total value and points calculated for client: " + client.getCodclient());
            }
        }
    }
}
