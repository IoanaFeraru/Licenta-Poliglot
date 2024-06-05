package org.licentaCRMPoliglot.Entities.ClientReferences;

import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Embeddable;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@Embeddable
public class PuncteEntry {
    @Column
    @NotNull
    private int codAchizitie;

    @Column
    @NotNull
    private int valoarePuncte;

    @Column
    @NotNull
    private Date dataProcesare;
}