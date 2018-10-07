package at.wrk.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Btransaktion extends Audit
{
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	@JoinColumn(name="material")
	private Material material;
	
	private int menge;
		
	private String art;
	
	@ManyToOne
	@JoinColumn(name="veranstaltung")
	private Veranstaltung veranstaltung;

	private String beschreibung;
	
	public Btransaktion()
	{
		super();
	}
	
	public Btransaktion(Material material)
	{
		super();
		this.material = material;
	}
}
