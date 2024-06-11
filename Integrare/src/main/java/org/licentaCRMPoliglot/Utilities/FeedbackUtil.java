package org.licentaCRMPoliglot.Utilities;

import jakarta.nosql.mapping.document.DocumentTemplate;
import org.licentaCRMPoliglot.Entities.ClientReferences.Feedback;
import org.licentaCRMPoliglot.Entities.Produs.Produs;
import org.licentaCRMPoliglot.Repositories.FeedbackRepo;
import org.licentaCRMPoliglot.Repositories.ProdusRepository;

public class FeedbackUtil {
    private final ProdusRepository produsRepository;
    private final DocumentTemplate template;
    private final FeedbackRepo feedbackRepo;

    public FeedbackUtil(ProdusRepository produsRepository, DocumentTemplate template, FeedbackRepo feedbackRepo) {
        this.produsRepository = produsRepository;
        this.template = template;
        this.feedbackRepo = feedbackRepo;
    }

    public void updateProductRating(String productId, int rating) {
        Produs produs = produsRepository.findById(productId);

        if (produs != null) {
            int newRatingCount = (produs.getRatingCount() == null) ? 1 : produs.getRatingCount() + 1;
            double newRatingSum = (produs.getRatingSum() == null) ? rating : produs.getRatingSum() + rating;

            produs.setRatingCount(newRatingCount);
            produs.setRatingSum(newRatingSum);
            produs.setRating(newRatingSum / newRatingCount);
            produsRepository.updateProdus(produs);
        }
    }
}
