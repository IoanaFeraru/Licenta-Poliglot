package org.licentaCRMPoliglot.Entities.Comunicare;

import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.licentaCRMPoliglot.Entities.Comunicare.Enums.Metoda;
import org.licentaCRMPoliglot.Entities.Comunicare.Enums.Status;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class Comunicare {

    @Column
    private int codComunicare;

    @Column
    private String scop;

    @Column
    @NotNull
    private Status status;

    @Column
    @NotNull
    private Metoda metoda;

    @Column
    private List<String> CoduriCLienti;
}
