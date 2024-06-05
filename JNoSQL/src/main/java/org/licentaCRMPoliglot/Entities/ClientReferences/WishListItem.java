package org.licentaCRMPoliglot.Entities.ClientReferences;

import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Embeddable;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@Embeddable
public class WishListItem {
    @Column
    @NotBlank
    private String codProdus;

    @Column
    @NotNull
    private Date dataAdaugare;
}
