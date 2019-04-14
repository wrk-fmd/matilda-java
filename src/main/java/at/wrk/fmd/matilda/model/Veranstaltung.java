package at.wrk.fmd.matilda.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Veranstaltung extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(min = 2, max = 20)
    private String name;

//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private LocalDate beginn;
//
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private LocalDate ende;
    
    private LocalDateTime beginn;
    
    private LocalDateTime ende;
    
    @ManyToOne
    @JoinColumn(name="lagerstandort")
    private Lagerstandort lagerstandort;

    private String zustand;

    public Veranstaltung() {
        super();
        zustand = "In Bearbeitung";
    }
}
