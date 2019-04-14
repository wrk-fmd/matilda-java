package at.wrk.fmd.matilda.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "benutzername"))
public class Benutzer extends Audit {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String benutzername;
    private String passwort;
    private String anzeigename;
    private String dienstnummer;
    private Boolean active;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "benutzer_rolle", joinColumns = @JoinColumn(name = "benutzer_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "rolle_id", referencedColumnName = "id"))
    private Collection<Rolle> rollen;

    public Benutzer() {
        // TODO SMELL: Codestyle: Use style helper. Here are no blanks around the operator.
    	active=true;
    }

    public Benutzer(String benutzername, String dienstnummer) {
        this.benutzername = benutzername;
        this.dienstnummer = dienstnummer;
        active=true;
    }

    // TODO SMELL: Codestyle: Use consistent line breaks. For example break none, or break all.
    public Benutzer(String benutzername, String passwort, String anzeigename, String dienstnummer,
            Collection<Rolle> rollen) {
        this.benutzername = benutzername;
        this.passwort = passwort;
        this.anzeigename = anzeigename;
        this.dienstnummer = dienstnummer;
        this.rollen = rollen;
        active=true;
    }

    // TODO SMELL: Codestyle: Other entities use lombok to generate getters and setters. Why are the methods in this class explicitly defined?
    public Long getId() {
        return id;
    }

    public String getBenutzername() {
        return benutzername;
    }

    public String getPasswort() {
        return passwort;
    }

    public String getAnzeigename() {
        return anzeigename;
    }

    public String getDienstnummer() {
        return dienstnummer;
    }

    public Collection<Rolle> getRollen() {
        return rollen;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public void setAnzeigename(String anzeigename) {
        this.anzeigename = anzeigename;
    }

    public void setDienstnummer(String dienstnummer) {
        this.dienstnummer = dienstnummer;
    }

    public void setRollen(Collection<Rolle> rollen) {
        this.rollen = rollen;
    }

    public boolean isNew() {
        return (this.id == null);
    }

	public Boolean isActive()
	{
		return active;
	}

	public void setActive(Boolean active)
	{
		this.active = active;
	}
}