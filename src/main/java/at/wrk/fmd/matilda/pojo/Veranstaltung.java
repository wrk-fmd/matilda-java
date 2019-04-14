package at.wrk.fmd.matilda.pojo;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import at.wrk.fmd.matilda.model.Lagerstandort;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Veranstaltung
{
    private long id;

	@NotBlank
	@Size(min=2, max=20)
	private String name;
	
	@NotBlank
	private String beginn;
	
	@NotBlank
	private String ende;
	
	
	@ManyToOne
	@JoinColumn(name="lagerstandort")
	private Lagerstandort lagerstandort;
	
	private String zustand;
	
	public Veranstaltung()
	{
		super();
		zustand = "In Bearbeitung";
	}
}
