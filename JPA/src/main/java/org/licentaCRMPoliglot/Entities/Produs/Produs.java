package org.licentaCRMPoliglot.Entities.Produs;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.licentaCRMPoliglot.MetaModels.AbstractEntity;

@Getter
@Setter
@Entity
@Table(name = "produs")
public class Produs extends AbstractEntity {
    @Id
    @Column(name = "codprodus", nullable = false)
    private String codprodus;

    @Column(name = "nume", nullable = false)
    private String nume;

    @Column(name = "rating", nullable = false)
    private Double rating;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusProdus statusProdus;

    @Column(name = "pret", nullable = false)
    private Double pret;

    @Override
    public Object getId() {
        return codprodus;
    }
}