package at.wrk.fmd.pojo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import at.wrk.fmd.model.Audit;
import at.wrk.fmd.model.Einheitentyp;
import at.wrk.fmd.model.Material;
import at.wrk.fmd.model.Materialtyp;
import at.wrk.fmd.model.Veranstaltung;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class VeranstaltungBuchung extends Audit
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
	private Veranstaltung veranstaltung;
	
	private int wunschMaterial;
	
	private int standardMenge;
	
	private int wunschMenge;
	
	private String status;	

	public VeranstaltungBuchung(Materialtyp materialtyp, Einheitentyp einheitentyp, int standardMenge, Veranstaltung veranstaltung)
	{
		super();
		this.materialtyp = materialtyp;
		this.einheitentyp = einheitentyp;
		this.standardMenge = standardMenge;
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
}
