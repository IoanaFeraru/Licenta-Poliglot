package org.licentaCRMPoliglot.Entities.ClientReferences;

import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.licentaCRMPoliglot.Entities.ClientReferences.Enums.Judet;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Embeddable
public class AddressDetail {
    @Column
    @NotNull
    private Judet judet;

    @Column
    @Min(1000)
    @Max(9999)
    private int codPostal;

    @Column
    @NotBlank
    private String strada;

    @Column
    private String bloc;
}
