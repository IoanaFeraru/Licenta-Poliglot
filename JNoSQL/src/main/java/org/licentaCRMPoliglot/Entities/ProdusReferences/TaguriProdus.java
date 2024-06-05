package org.licentaCRMPoliglot.Entities.ProdusReferences;

import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import jakarta.nosql.mapping.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class TaguriProdus {
    @Id
    private String codProdus;

    @Column("Tag-uri")
    private List<String> tagUri;
}
