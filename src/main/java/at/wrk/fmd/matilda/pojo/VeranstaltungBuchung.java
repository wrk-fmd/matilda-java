package at.wrk.fmd.matilda.pojo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import at.wrk.fmd.matilda.model.Audit;
import at.wrk.fmd.matilda.model.Einheitentyp;
import at.wrk.fmd.matilda.model.Material;
import at.wrk.fmd.matilda.model.Materialtyp;
import at.wrk.fmd.matilda.model.Veranstaltung;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class VeranstaltungBuchung extends Audit implements Comparable<VeranstaltungBuchung>
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="materialtyp")
	private Materialtyp materialtyp;
	
	@ManyToMany
	@JoinColumn(name="material")
	private List<Material> Materialien;
	
	@ManyToOne
	@JoinColumn(name="einheitentyp")
	private Einheitentyp einheitentyp;
	
	@ManyToOne
	@JoinColumn(name="veranstaltung")
	private at.wrk.fmd.matilda.model.Veranstaltung veranstaltung;
	
	private String wunschMaterial;
	
	private int standardMenge;
	
	private int wunschMenge;
	
	private String status;	

	public VeranstaltungBuchung(Materialtyp materialtyp, Einheitentyp einheitentyp, int standardMenge, Veranstaltung veranstaltung)
	{
		super();
		this.materialtyp = materialtyp;
		this.einheitentyp = einheitentyp;
		this.standardMenge = standardMenge;
		this.wunschMenge = standardMenge;
		this.veranstaltung = veranstaltung;
	}		
	
	public VeranstaltungBuchung()
	{
		super();
	}	
	
	public VeranstaltungBuchung(Einheitentyp einheitentyp)
	{
		super();
		this.einheitentyp = einheitentyp;
	}	
	
    @Override
    public int compareTo(VeranstaltungBuchung andere) {
        return this.getEinheitentyp().getName().compareTo(andere.getEinheitentyp().getName());
    }
	
}
