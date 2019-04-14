package at.wrk.fmd.matilda.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Buchung extends Audit
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name="material")
	private Material material;
	
	private int menge;
		
	private LocalDateTime von;
	
	private LocalDateTime bis;
	
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
	
	public Buchung(Material material, int menge, Veranstaltung veranstaltung,
			@NotBlank @Size(min = 3, max = 25) String beschreibung, LocalDateTime von, LocalDateTime bis)
	{
		super();
		this.material = material;
		this.menge = menge;
		this.veranstaltung = veranstaltung;
		this.beschreibung = beschreibung;
		this.von = von;
		this.bis = bis;
	}
}
