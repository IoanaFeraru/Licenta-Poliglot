package org.licentaCRMPoliglot.Entities.Campanie;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.licentaCRMPoliglot.Entities.Oferta.Oferta;
import org.licentaCRMPoliglot.MetaModels.AbstractEntity;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "campanie")
public class Campanie extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "campanie_id_gen")
    @SequenceGenerator(name = "campanie_id_gen", sequenceName = "campanie_codcampanie_seq", allocationSize = 1)
    @Column(name = "codcampanie", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "codoferta", nullable = false)
    private Oferta codoferta;

    @Column(name = "nume", nullable = false)
    private String nume;

    @Column(name = "datastart", nullable = false)
    private LocalDate datastart;

    @Column(name = "datastop")
    private LocalDate datastop;

    @Enumerated(EnumType.STRING)
    @Column(name = "tip", nullable = false)
    private TipCampanie tip;
}