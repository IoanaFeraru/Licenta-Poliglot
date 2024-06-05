package org.licentaCRMPoliglot.Repositories.TaguriProdus;

import jakarta.nosql.mapping.Param;
import jakarta.nosql.mapping.Query;
import jakarta.nosql.mapping.Repository;
import org.licentaCRMPoliglot.Entities.ProdusReferences.TaguriProdus;

import java.util.List;

public interface TaguriProdusRepository extends Repository<TaguriProdus, String> {

    @Query("select * from TaguriProdus where codprodus = @codprodus")
    List<TaguriProdus> findByCodProdus(@Param("codprodus") String codprodus);
}
