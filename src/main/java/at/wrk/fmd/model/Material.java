package at.wrk.fmd.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.apache.tomcat.jni.Local;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Material extends Audit {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name="lagerstandort")
	private Lagerstandort lagerstandort;
	
	@ManyToOne
	@JoinColumn(name="materialtyp")
	private Materialtyp materialtyp;
	
	@NotBlank
	private String bezeichnung;
	
	private String seriennummer;
		
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate einkaufsdatum;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate letztesudatum;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate naechstesudatum;
	
	private boolean einsatzbereitschaft;
	
	private int bestand;

	public Material()
	{
		super();
	}	
}