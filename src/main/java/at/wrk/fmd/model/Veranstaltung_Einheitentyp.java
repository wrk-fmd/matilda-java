package at.wrk.fmd.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Veranstaltung_Einheitentyp extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "veranstaltung")
    private Veranstaltung veranstaltung;

    @ManyToOne
    @JoinColumn(name = "einheitentyp")
    private Einheitentyp einheitentyp;

    private String bezeichnung;

    public Veranstaltung_Einheitentyp() {
        super();
    }
}
