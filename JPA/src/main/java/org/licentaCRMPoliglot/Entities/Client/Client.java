package org.licentaCRMPoliglot.Entities.Client;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.licentaCRMPoliglot.MetaModels.AbstractEntity;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "client")
public class Client extends AbstractEntity {
    @Id
    @Column(name = "codclient", nullable = false)
    private String codclient;

    @Column(name = "nume", nullable = false)
    private String nume;

    @Column(name = "prenume", nullable = false)
    private String prenume;

    @Column(name = "datanastere", nullable = false)
    private LocalDate datanastere;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "numartelefon", nullable = false)
    private String numartelefon;

    @Column(name = "puncteloialitate", nullable = false)
    private Integer puncteloialitate=0;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'NONE'")
    @Column(name = "statusmembru", nullable = false)
    private StatusMembru statusmembru = StatusMembru.NONE;

    @Column(name = "lastactive", nullable = false)
    private Date lastactive;

    @Override
    public Object getId() {
        return codclient;
    }
}