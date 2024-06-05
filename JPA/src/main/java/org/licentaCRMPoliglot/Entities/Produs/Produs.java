package org.licentaCRMPoliglot.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.licentaCRMPoliglot.MetaModels.AbstractEntity;

import java.util.LinkedHashSet;
import java.util.Set;

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

    @Enumerated
    @Column(name = "status", nullable = false)
    private StatusProdus statusProdus;

    @Column(name = "pret", nullable = false)
    private Double pret;

    @OneToMany(mappedBy = "codclient")
    private Set<Oferta> oferte = new LinkedHashSet<>();

    @Override
    public Object getId() {
        return codprodus;
    }
}