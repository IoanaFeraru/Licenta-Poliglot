package org.licentaCRMPoliglot.Repositories.TaguriClient;

import jakarta.nosql.mapping.Param;
import jakarta.nosql.mapping.Query;
import jakarta.nosql.mapping.Repository;
import org.licentaCRMPoliglot.Entities.ClientReferences.TaguriClient;

import java.util.List;

public interface TaguriClientRepository extends Repository<TaguriClient, String> {

    @Query("select * from TaguriClient where codclient = @codclient")
    List<TaguriClient> findByCodClient(@Param("codclient") String codclient);
}
