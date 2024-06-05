package org.licentaCRMPoliglot.ClientReferences;

import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import jakarta.nosql.mapping.Id;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
public class Feedback {
    @Id
    String codClient;

    @Column
    @NotBlank
    private String codProdus;

    @Column
    @Min(0)
    @Max(5)
    private int rating;
}
