package at.wrk.fmd.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
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
public class Einheitentyp extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(min = 2, max = 20)
    private String name;

    public Einheitentyp() {
        super();
    }
}