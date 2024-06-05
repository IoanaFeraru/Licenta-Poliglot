package org.licentaCRMPoliglot.Entities.Oferta;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.licentaCRMPoliglot.Entities.Campanie.Campanie;
import org.licentaCRMPoliglot.Entities.Produs.Produs;
import org.licentaCRMPoliglot.MetaModels.AbstractEntity;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "oferta")
@NamedQueries({
        @NamedQuery(name = "Oferta.findByTipreducere", query = "SELECT o FROM Oferta o WHERE o.tipreducere = :tipreducere")
})
public class Oferta extends AbstractEntity {
    @Id
    @Column(name = "codoferta", nullable = false)
    private String codoferta;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusOferta status;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipreducere", nullable = false)
    private Tipreducere tipreducere;

    @Column(name = "valoarereducere")
    private Double valoarereducere;

    @Column(name = "costpuncte")
    private Integer costpuncte=0;

    @OneToMany(mappedBy = "codoferta")
    private Set<Campanie> campanii = new LinkedHashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codprodus")
    private Produs codprodus;

    @Override
    public Object getId() {
        return codoferta;
    }
}