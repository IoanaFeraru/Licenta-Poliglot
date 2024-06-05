package org.licentaCRMPoliglot.Repositories.Achizitie;

import jakarta.nosql.mapping.Param;
import jakarta.nosql.mapping.Query;
import jakarta.nosql.mapping.Repository;
import org.licentaCRMPoliglot.Entities.Achizitie.Achizitie;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AchizitieRepository extends Repository<Achizitie, Integer> {

    @Query("select * from Achizitie where codClient = @codClient")
    List<Achizitie> findByCodClient(@Param("codClient") String codClient);

    @Query("select * from Achizitie where codAchizitie = @codAchizitie")
    Optional<Achizitie> findByCodAchizitie(@Param("codAchizitie") int codAchizitie);

    @Query("select * from Achizitie where data = @data")
    List<Achizitie> findByData(@Param("data") Date data);

    @Query("select * from Achizitie where codOferta = @codOferta")
    List<Achizitie> findByCodOferta(@Param("codOferta") String codOferta);

    @Query("select * from Achizitie")
    List<Achizitie> findAll();
}
