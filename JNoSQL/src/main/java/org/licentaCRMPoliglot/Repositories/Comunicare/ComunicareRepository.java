package org.licentaCRMPoliglot.Repositories.Comunicare;

import jakarta.nosql.mapping.Param;
import jakarta.nosql.mapping.Query;
import jakarta.nosql.mapping.Repository;
import org.licentaCRMPoliglot.Entities.Comunicare.Comunicare;

import java.util.List;

public interface ComunicareRepository extends Repository<Comunicare, Integer> {

    @Query("select * from Comunicare where codComunicare = @codComunicare")
    List<Comunicare> findByCodComunicare(@Param("codComunicare") int codComunicare);

    @Query("select * from Comunicare where codCampanie = @codCampanie")
    List<Comunicare> findByCodCampanie(@Param("codCampanie") int codCampanie);

    @Query("select * from Comunicare where status = @status")
    List<Comunicare> findByStatus(@Param("status") String status);

    @Query("select * from Comunicare where metoda = @metoda")
    List<Comunicare> findByMetoda(@Param("metoda") String metoda);

    @Query("select * from Comunicare where codClient in @codClient")
    List<Comunicare> findByCodClient(@Param("codClient") List<String> codClient);

    @Query("select * from Comunicare")
    List<Comunicare> findAll();
}