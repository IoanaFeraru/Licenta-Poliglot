package org.licentaCRMPoliglot.Entities.ClientReferences;

import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Embeddable;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Embeddable
public class FeedbackItem {

    @Column
    @NotBlank
    private String codProdus;

    @Column
    @Min(0)
    @Max(5)
    private int rating;
}
