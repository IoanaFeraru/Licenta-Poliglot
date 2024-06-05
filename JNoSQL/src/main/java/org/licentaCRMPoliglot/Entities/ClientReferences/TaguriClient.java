package org.licentaCRMPoliglot.Entities.ClientReferences;

import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import jakarta.nosql.mapping.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class TaguriClient {
    @Id
    String codClient;

    @Column
    List<String> taguriClient;
}
