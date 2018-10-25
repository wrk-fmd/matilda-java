package at.wrk.fmd.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Ntransaktion extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "material")
    private Material material;

    private int menge;

    private String art;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate lieferungsDatum;

    private String beschreibung;

    public Ntransaktion(Material material) {
        super();
        this.material = material;
    }

    public Ntransaktion() {
        super();
    }
}
