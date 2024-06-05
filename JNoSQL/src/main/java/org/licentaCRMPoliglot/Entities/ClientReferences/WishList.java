package org.licentaCRMPoliglot.ClientReferences;

import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Embeddable;
import jakarta.nosql.mapping.Entity;
import jakarta.nosql.mapping.Id;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@Entity
public class WishList {
    @Id
    String codClient;

    @Column
    @NotBlank
    private String codProdus;

    @Column
    @NotNull
    private Date dataAdaugare;
}
