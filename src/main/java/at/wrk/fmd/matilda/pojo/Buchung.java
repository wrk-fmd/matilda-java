package at.wrk.fmd.matilda.pojo;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import at.wrk.fmd.matilda.model.Material;
import at.wrk.fmd.matilda.model.Veranstaltung;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Buchung
{
	@ManyToOne
	@JoinColumn(name="material")
	private Material material;
	
	private int menge;
	
	private String von;
	
	private String bis;
	
	@ManyToOne
	@JoinColumn(name="veranstaltung")
	private Veranstaltung veranstaltung;
	
	@NotBlank
	@Size(min=3, max=125)
	private String beschreibung;
	
	public Buchung()
	{
		super();
	}
	
	public Buchung(Material material)
	{
		super();
		this.material = material;
	}

	public Buchung(Material material, int menge, Veranstaltung veranstaltung,
			@NotBlank @Size(min = 3, max = 25) String beschreibung)
	{
		super();
		this.material = material;
		this.menge = menge;
		this.veranstaltung = veranstaltung;
		this.beschreibung = beschreibung;
	}
}
