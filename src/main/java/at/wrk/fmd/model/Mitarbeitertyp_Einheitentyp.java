package at.wrk.fmd.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Mitarbeitertyp_Einheitentyp extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Positive
    private int manzahl;

    @ManyToOne
    @JoinColumn(name = "mitarbeitertyp")
    private Mitarbeitertyp mitarbeitertyp;

    @ManyToOne
    @JoinColumn(name = "einheitentyp")
    private Einheitentyp einheitentyp;

    public Mitarbeitertyp_Einheitentyp() {
        super();
    }

    public Mitarbeitertyp_Einheitentyp(Einheitentyp einheitentyp) {
        super();
        this.einheitentyp = einheitentyp;
    }
}
