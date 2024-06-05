package org.licentaCRMPoliglot.Repositories.Feedback;

import jakarta.nosql.mapping.Param;
import jakarta.nosql.mapping.Query;
import jakarta.nosql.mapping.Repository;
import org.licentaCRMPoliglot.Entities.ClientReferences.Feedback;

import java.util.List;

public interface FeedbackRepository extends Repository<Feedback, String> {

    @Query("select * from Feedback where codclient = @codclient")
    List<Feedback> findByCodClient(@Param("codclient") String codclient);

    @Query("select * from Feedback where codProdus = @codProdus")
    List<Feedback> findByCodProdus(@Param("codProdus") String codProdus);

    @Query("select * from Feedback where rating = @rating")
    List<Feedback> findByRating(@Param("rating") int rating);
}