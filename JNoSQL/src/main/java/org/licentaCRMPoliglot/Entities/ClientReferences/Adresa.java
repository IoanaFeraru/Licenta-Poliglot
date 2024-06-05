package org.licentaCRMPoliglot.ClientReferences;

import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import jakarta.nosql.mapping.Id;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.licentaCRMPoliglot.ClientReferences.Enums.*;

@Getter
@Setter
@Entity
public class Adresa {

    @Id
    String codClient;

    @Column
    @NotNull
    private Judet judet;

    @Column
    @Min(999)
    @Max(10000)
    private int codPostal;

    @Column
    @NotBlank
    private String strada;

    @Column
    private String bloc;
}
