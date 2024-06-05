package org.licentaCRMPoliglot.Utilities;

import jakarta.nosql.mapping.document.DocumentTemplate;
import org.licentaCRMPoliglot.Entities.ClientReferences.Feedback;
import org.licentaCRMPoliglot.Entities.ClientReferences.FeedbackItem;
import org.licentaCRMPoliglot.Entities.Produs.Produs;
import org.licentaCRMPoliglot.Repositories.FeedbackRepo;
import org.licentaCRMPoliglot.Repositories.ProdusRepository;

import java.util.List;
import java.util.stream.Collectors;

public class FeedbackUtil {

    private final ProdusRepository produsRepository;
    private final DocumentTemplate template;
    private final FeedbackRepo feedbackRepo;

    public FeedbackUtil(ProdusRepository produsRepository, DocumentTemplate template, FeedbackRepo feedbackRepo) {
        this.produsRepository = produsRepository;
        this.template = template;
        this.feedbackRepo = feedbackRepo;
    }

    public void updateProductRating(String codProdus) {
        List<Feedback> feedbackList = feedbackRepo.findByCodProdus(codProdus);

        if (feedbackList.isEmpty()) {
            System.out.println("No feedback found for product: " + codProdus);
            return;
        }

        List<Integer> ratings = feedbackList.stream()
                .flatMap(feedback -> feedback.getFeedbackItems().stream())
                .filter(item -> codProdus.equals(item.getCodProdus()))
                .map(FeedbackItem::getRating)
                .collect(Collectors.toList());

        double averageRating = ratings.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0);

        Produs produs = produsRepository.findById(codProdus);

        if (produs != null) {
            produs.setRating(averageRating);
            produsRepository.updateProdus(produs);
        } else {
            System.out.println("Product not found with code: " + codProdus);
        }
    }
}
