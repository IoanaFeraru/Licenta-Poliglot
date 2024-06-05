package org.licentaCRMPoliglot.Repositories.IstoricPuncte;

import jakarta.nosql.mapping.Param;
import jakarta.nosql.mapping.Query;
import jakarta.nosql.mapping.Repository;
import org.licentaCRMPoliglot.Entities.ClientReferences.IstoricPuncte;

import java.util.List;

public interface IstoricPuncteRepository extends Repository<IstoricPuncte, String> {

    @Query("select * from IstoricPuncte where codclient = @codclient")
    List<IstoricPuncte> findByCodClient(@Param("codclient") String codclient);

    @Query("select * from IstoricPuncte where codAchizitie = @codAchizitie")
    List<IstoricPuncte> findByCodAchizitie(@Param("codAchizitie") int codAchizitie);
}