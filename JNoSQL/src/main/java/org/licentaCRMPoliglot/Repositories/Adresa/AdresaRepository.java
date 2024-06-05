package org.licentaCRMPoliglot.Repositories.Adresa;

import jakarta.nosql.mapping.Param;
import jakarta.nosql.mapping.Query;
import jakarta.nosql.mapping.Repository;
import org.licentaCRMPoliglot.Entities.ClientReferences.Adresa;

import java.util.List;

public interface AdresaRepository extends Repository<Adresa, String> {

    @Query("select * from Adresa where codclient = @codclient")
    List<Adresa> findByCodClient(@Param("codclient") String codclient);

    @Query("select * from Adresa where judet = @judet")
    List<Adresa> findByJudet(@Param("judet") String judet);
}