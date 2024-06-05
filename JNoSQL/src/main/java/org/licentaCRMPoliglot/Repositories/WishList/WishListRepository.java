package org.licentaCRMPoliglot.Repositories.WishList;

import jakarta.nosql.mapping.Param;
import jakarta.nosql.mapping.Query;
import jakarta.nosql.mapping.Repository;
import org.licentaCRMPoliglot.Entities.ClientReferences.WishList;

import java.util.Date;
import java.util.List;

public interface WishListRepository extends Repository<WishList, String> {

    @Query("select * from WishList where codProdus = @codProdus")
    List<WishList> findByCodProdus(@Param("codProdus") String codProdus);

    @Query("select * from WishList where dataAdaugare = @dataAdaugare")
    List<WishList> findByDataAdaugare(@Param("dataAdaugare") Date dataAdaugare);
}
