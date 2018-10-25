package at.wrk.fmd.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Buchung extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "material")
    private Material material;

    private int menge;

    @NotBlank
    private String art;

    @ManyToOne
    @JoinColumn(name = "veranstaltung")
    private Veranstaltung veranstaltung;

    @NotBlank
    private String beschreibung;

    public Buchung() {
        super();
    }

    public Buchung(Material material) {
        super();
        this.material = material;
    }
}
