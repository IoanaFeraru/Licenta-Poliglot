package org.licentaCRMPoliglot.Comunicare;

import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.licentaCRMPoliglot.Achizitie.LinieAchizitie;
import org.licentaCRMPoliglot.Comunicare.Enums.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Embeddable
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
