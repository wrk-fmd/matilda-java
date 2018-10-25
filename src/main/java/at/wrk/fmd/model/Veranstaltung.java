package at.wrk.fmd.model;

import java.time.LocalDate;
import java.time.Year;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate beginn;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate ende;

    private String zustand;

    public Veranstaltung() {
        super();
        zustand = "Frei";
    }
}
