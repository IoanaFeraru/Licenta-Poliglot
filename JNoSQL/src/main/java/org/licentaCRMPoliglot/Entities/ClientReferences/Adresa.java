package org.licentaCRMPoliglot.Entities.ClientReferences;

import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import jakarta.nosql.mapping.Id;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Entity
public class Adresa {
    @Id
    private String codClient;

    @Column
    @Valid
    @NotNull
    private List<AddressDetail> addressDetails;
}
