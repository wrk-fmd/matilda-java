package at.wrk.model;


import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Lieferung extends Audit
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name="material")
	private Material material;
	
	@Positive
	private int menge;
	
	@NotBlank
	private String art;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate lieferungsDatum;
	
	@NotBlank
	private String beschreibung;

	public Lieferung(Material material)
	{
		super();
		this.material = material;
	}

	public Lieferung()
	{
		super();
	}
}
