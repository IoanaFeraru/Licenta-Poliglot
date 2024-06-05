package org.licentaCRMPoliglot.Entities.ClientReferences;

import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import jakarta.nosql.mapping.Id;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@Entity
public class IstoricPuncte {
    @Id
    String codClient;

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
