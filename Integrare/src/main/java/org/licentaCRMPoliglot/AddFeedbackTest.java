package org.licentaCRMPoliglot;

import jakarta.nosql.mapping.document.DocumentTemplate;
import org.licentaCRMPoliglot.Entities.Client.Client;
import org.licentaCRMPoliglot.Entities.ClientReferences.Feedback;
import org.licentaCRMPoliglot.Entities.ClientReferences.FeedbackItem;
import org.licentaCRMPoliglot.Entities.Achizitie.Achizitie;
import org.licentaCRMPoliglot.Entities.Achizitie.LinieAchizitie;
import org.licentaCRMPoliglot.Repositories.AchizitieRepo;
import org.licentaCRMPoliglot.Repositories.ClientRepository;
import org.licentaCRMPoliglot.Repositories.ProdusRepository;
import org.licentaCRMPoliglot.Repositories.FeedbackRepo;
import org.licentaCRMPoliglot.Utilities.FeedbackUtil;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class AddFeedbackTest {

    private static final Random random = new Random();

    public static void main(String[] args) {
        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            createFeedbacks(container);
        }
        System.exit(0);
    }

    public static void createFeedbacks(SeContainer container) {
        DocumentTemplate template = container.select(DocumentTemplate.class).get();
        ProdusRepository produsRepository = new ProdusRepository();
        ClientRepository clientRepository = new ClientRepository();
        AchizitieRepo achizitieRepository = container.select(AchizitieRepo.class).get();
        FeedbackRepo feedbackRepo = container.select(FeedbackRepo.class).get();
        FeedbackUtil feedbackUtil = new FeedbackUtil(produsRepository, template, feedbackRepo);

        List<Client> allClients = clientRepository.findAll();

        for (Client client : allClients) {
            List<Achizitie> clientAcquisitions = achizitieRepository.findByCodClient(client.getCodclient());

            List<String> acquiredProducts = clientAcquisitions.stream()
                    .flatMap(achizitie -> achizitie.getLinieAchizitie().stream())
                    .map(LinieAchizitie::getCodProdus)
                    .distinct()
                    .collect(Collectors.toList());

            Feedback feedback = new Feedback();
            feedback.setCodClient(client.getCodclient());
            List<FeedbackItem> feedbackItems = new ArrayList<>();

            for (String productId : acquiredProducts) {
                if (random.nextBoolean()) {
                    int rating = random.nextInt(5) + 1;

                    FeedbackItem feedbackItem = new FeedbackItem();
                    feedbackItem.setCodProdus(productId);
                    feedbackItem.setRating(rating);

                    feedbackItems.add(feedbackItem);
                }
            }

            feedback.setFeedbackItems(feedbackItems);
            feedbackRepo.save(feedback);

            for (String productId : acquiredProducts) {
                feedbackUtil.updateProductRating(productId);
            }

            System.out.println("Feedback added and product ratings updated for client: " + client.getCodclient());
        }
    }
}
