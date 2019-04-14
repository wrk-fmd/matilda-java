package at.wrk.fmd.matilda.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
//@Table(uniqueConstraints = @UniqueConstraint(columnNames = "bezeichnung"))
public class Rolle extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String bezeichnung;

    public Rolle() {

    }

    public Rolle(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public long getId() {
        return id;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }
}