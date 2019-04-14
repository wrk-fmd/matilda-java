package at.wrk.fmd.matilda.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import at.wrk.fmd.matilda.model.Lagerstandort;
import at.wrk.fmd.matilda.model.Lieferung;
import at.wrk.fmd.matilda.model.Material;
import at.wrk.fmd.matilda.model.Materialtyp;
import at.wrk.fmd.matilda.model.Veranstaltung;
import at.wrk.fmd.matilda.pojo.Buchung;
import at.wrk.fmd.matilda.repository.BuchungRepository;
import at.wrk.fmd.matilda.repository.LagerstandortRepository;
import at.wrk.fmd.matilda.repository.LieferungRepository;
import at.wrk.fmd.matilda.repository.MaterialRepository;
import at.wrk.fmd.matilda.repository.MaterialtypRepository;
import at.wrk.fmd.matilda.repository.VeranstaltungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MaterialController {
	
	private final MaterialRepository materialRepository;
	private final MaterialtypRepository materialtypRepository;
	private final LagerstandortRepository lagerstandortRepository;
	private final LieferungRepository lieferungRepository;
	private final BuchungRepository buchungRepository;
	private final VeranstaltungRepository veranstaltungRepository;

	// TODO SMELL: This can be a local variable.
	private List<Material> materialien;

	// TODO SMELL: All temporary changes MUST be saved in a user context, or provided to the user and then read again on the next request
	//             (stateful vs. stateless API)
	// die beide sind für hinzufügen verwendet:
	private Lagerstandort aktLagerstandort;
	private long aktLagerId;
	// die beide sind für lieferschein verwendet:
	private Material aktMaterial;
	private long aktMaterialId;
	
	@Autowired
	public MaterialController(MaterialRepository materialRepository, MaterialtypRepository materialtypRepository,
			LagerstandortRepository lagerstandortRepository , LieferungRepository lieferungRepository,
			BuchungRepository buchungRepository, VeranstaltungRepository veranstaltungRepository)
	{
		super();
		this.materialRepository = materialRepository;
		this.materialtypRepository = materialtypRepository;
		this.lagerstandortRepository = lagerstandortRepository;
		this.lieferungRepository = lieferungRepository;
		this.buchungRepository = buchungRepository;
		this.veranstaltungRepository = veranstaltungRepository;
	}

    // ************************************* Modelattribute ***************************************

    @ModelAttribute("alleLagerstandorten")
    public List<Lagerstandort> alleLagerstandorten() {
        return lagerstandortRepository.findAll();

    }

    @ModelAttribute("alleMaterialtypen")
    public List<Materialtyp> alleMaterialtypen() {
        return materialtypRepository.findAll();
    }

    @ModelAttribute("alleVeranstaltungen")
    public List<Veranstaltung> alleVeranstaltungen() {
        return veranstaltungRepository.findAll();
    }

    // ************************************* Lagerstandort List ************************************

    @RequestMapping(value = "/material", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPERVISOR')")
    public String list(Model model) {
		List<Lagerstandort> lagerstandorten = lagerstandortRepository.findAll();
		if(lagerstandorten!=null)
		{
			model.addAttribute("lagerstandorten",lagerstandorten);
		}
		
		return "material";
	}

    // ************************************* Materialien im Lager  ************************************

    @RequestMapping(value = "/materialverwaltung/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPERVISOR')")
    public String materialVerwaltungForm(@PathVariable("id") long id, Model model) {

		Lagerstandort lagerstandort = lagerstandortRepository.findById(id);
		materialien = materialRepository.findByLagerstandortOrderByMaterialtypAsc(lagerstandort);
		aktLagerstandort = lagerstandort;
		aktLagerId = lagerstandort.getId();
		
		model.addAttribute("material", new Material());
		
		if(materialien!=null)
		{
			model.addAttribute("materialien", materialien);
		}
		else
		{
			return "materialverwaltung?nomaterial";
		}
		
        return "materialverwaltung";
    }
   
    // ************************************* neues Material im Lager hinzufügen  ************************************
    
	@RequestMapping(value="/neumaterial", method=RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPERVISOR')")
    public String neuMaterial(Model model) {
			
		model.addAttribute("material", new Material());
				
        return "neumaterial";
    }
    	
    @RequestMapping(value="/neumaterial", method=RequestMethod.POST)
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPERVISOR')")
    public String addSpeichern(Model model, @ModelAttribute("material") @Valid Material material, BindingResult result) {
    	   	
		material.setLagerstandort(aktLagerstandort);
		
	    if (result.hasErrors()){
	    	return "neumaterial";
	    }
	    
		materialRepository.save(material);
        return "redirect:/materialverwaltung/"+aktLagerId; 

    }

    // ************************************* Lieferschein - Lieferung ************************************

    @RequestMapping(value = "/lieferung/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPERVISOR')")
    public String addLieferungForm(@PathVariable("id") long id, Model model) {

        aktMaterial = materialRepository.findById(id);
        aktMaterialId = aktMaterial.getId();

        model.addAttribute("lieferung", new Lieferung(aktMaterial));

        return "lieferung";
    }

    @RequestMapping(value = "/lieferung", method = RequestMethod.POST)
    public String lieferungSpeichern(
            @ModelAttribute("lieferung") @Valid Lieferung lieferung, BindingResult result) {

    	lieferung.setMaterial(aktMaterial);
	 	
		if(lieferung.getLieferungsDatum()==null)
		{
			result.rejectValue("lieferungsDatum", null, "Bitte wählen Sie ein Lieferungsdatum aus!");
		}			
    	if(result.hasErrors()) {
    		return "lieferung";
    	}  
		if(lieferung.getArt().equals("eingabe"))
		{
			int x = aktMaterial.getBestand();
			x = x + lieferung.getMenge();
			aktMaterial.setBestand(x);
			materialRepository.save(aktMaterial);
			      	
			lieferungRepository.save(lieferung);
			
			return "redirect:/materialverwaltung/"+aktLagerId;
		}
		
		int x = aktMaterial.getBestand();
		x = x - lieferung.getMenge();
		if(x<0)
		{
			result.rejectValue("menge", null, "Nicht genug Material im Lager!");
		}	
    	if(result.hasErrors()) {
    		return "lieferung";
    	}
		aktMaterial.setBestand(x);
		materialRepository.save(aktMaterial);
		      	
		lieferungRepository.save(lieferung);
		
		return "redirect:/materialverwaltung/"+aktLagerId;
	} 

    // ************************************* Buchungsschein - Buchung/Stornieren ************************************

    @RequestMapping(value = "/buchung/{id}", method = RequestMethod.GET)
    public String addBuchungForm(@PathVariable("id") long id, Model model) {

        aktMaterial = materialRepository.findById(id);
        aktMaterialId = aktMaterial.getId();

        model.addAttribute("buchung", new Buchung(aktMaterial));

        return "buchung";
    }

    @RequestMapping(value = "/buchung", method = RequestMethod.POST)
    public String buchungSpeichern(Model model,@ModelAttribute("buchung") @Valid Buchung buchung,
            BindingResult result) {
    	
    	buchung.setMaterial(aktMaterial);    
    	if(buchung.getVon().compareTo(buchung.getBis())>=0 && buchung.getVeranstaltung()==null)
    	{
    		result.rejectValue("bis", null, "Das Ende der Buchung darf nicht vor dem Anfang sein!");
    	}   	
    	if(result.hasErrors()) {
    		return "buchung";
    	} 	
    	int x = -1;
    	if(buchung.getVeranstaltung()==null)
    	{
    		x = aktBestand(aktMaterial, convertor(buchung.getVon()), convertor(buchung.getBis()));
    	}
    	else
    	{
    		x = aktBestand(aktMaterial, buchung.getVeranstaltung().getBeginn(), buchung.getVeranstaltung().getEnde());
    	}
		if(x<buchung.getMenge())
		{
			result.rejectValue("menge", null, "Zu dieser Zeit sind nur "+ x +" Stück zur Verfügung!");
		}		
    	if(result.hasErrors()) {
    		return "buchung";
    	}
    	at.wrk.fmd.matilda.model.Buchung b = new at.wrk.fmd.matilda.model.Buchung();
    	b.setMaterial(aktMaterial);
    	b.setMenge(buchung.getMenge());
    	b.setBeschreibung(buchung.getBeschreibung());
    	if(buchung.getVeranstaltung()==null)
    	{
    		b.setVon(convertor(buchung.getVon()));
    		b.setBis(convertor(buchung.getBis()));    		
    	}
    	else
    	{
    		b.setVon(buchung.getVeranstaltung().getBeginn());
    		b.setBis(buchung.getVeranstaltung().getEnde());
    		b.setVeranstaltung(buchung.getVeranstaltung());
    	}	
    	if(result.hasErrors()) {
    		return "buchung";
    	}
		buchungRepository.save(b);		
		return "redirect:/materialverwaltung/"+aktLagerId;   	
	} 

    // ************************************* Inventur ************************************

    @RequestMapping(value = "/inventur/{id}", method = RequestMethod.GET)
    public String aendernForm(@PathVariable("id") long id, Model model) {

        Material exicting = materialRepository.findById(id);
        aktMaterialId = exicting.getId();
        aktLagerId = exicting.getLagerstandort().getId();

        model.addAttribute("material", exicting);

        return "inventur";
    }

    @RequestMapping(value = "/inventur", method = RequestMethod.POST)
    public String aendernSpeichern(@ModelAttribute("material") @Valid Material material,
            BindingResult result) {

    	
		Material exicting = materialRepository.findById(aktMaterialId);
		
    	if(material.getBestand()<0)
    	{
    		result.rejectValue("bestand", null, "Bestand kann nicht ein negatives Wert haben!");
    	}
    	if(result.hasErrors()) {
    		return "inventur";
    	}
    	exicting.setBestand(material.getBestand());
    	exicting.setSeriennummer(material.getSeriennummer());
    	exicting.setEinkaufsdatum(material.getEinkaufsdatum());
    	exicting.setLetztesudatum(material.getLetztesudatum());
    	exicting.setNaechstesudatum(material.getNaechstesudatum());
    	exicting.setEinsatzbereitschaft(material.isEinsatzbereitschaft());
    	   
    	
    	materialRepository.save(exicting);
    	
    	return "redirect:/materialverwaltung/"+aktLagerId;
    }
    
    // ****************************** Lieferung Übersicht in letzten 30 Tagen **************
    
    @RequestMapping(value="/lieferungview/{id}", method=RequestMethod.GET)
    public String LieferungView(Model model, @PathVariable("id") long id)
    {
    	aktLagerId = id;
    	
		Calendar cal = Calendar.getInstance();
		cal.setTime(Calendar.getInstance().getTime());
		cal.add(Calendar.DATE, -29);
		Date dateBefore29Days = cal.getTime();
		
		List<Lieferung> lieferungs = lieferungRepository.findByCreatedAtGreaterThan(dateBefore29Days);
		List<Lieferung> lieferungsInLager = new ArrayList<>();
		
		if(!lieferungs.isEmpty())
		{
			for(Lieferung l: lieferungs)
			{
				if(l.getMaterial().getLagerstandort().getId()==aktLagerId)
				{
					lieferungsInLager.add(l);
				}
			}
		}
		
		if(!lieferungsInLager.isEmpty())
		{
			model.addAttribute("lieferungsInLager", lieferungsInLager);
		}
		
		return "lieferungview";
    }
    
    // ****************************** Buchung Übersicht in letzten 30 Tagen **************
    
    @RequestMapping(value="/buchungview/{id}", method=RequestMethod.GET)
    public String buchungView(Model model, @PathVariable("id") long id)
    {
    	aktLagerId = id;
    	
		Calendar cal = Calendar.getInstance();
		cal.setTime(Calendar.getInstance().getTime());
		cal.add(Calendar.DATE, -29);
		Date dateBefore29Days = cal.getTime();
		
		List<at.wrk.fmd.matilda.model.Buchung> buchungs = buchungRepository.findByCreatedAtGreaterThan(dateBefore29Days);
		List<at.wrk.fmd.matilda.model.Buchung> buchungsInLager = new ArrayList<>();
		
		if(!buchungs.isEmpty())
		{
			for(at.wrk.fmd.matilda.model.Buchung b: buchungs)
			{
				if(b.getMaterial().getLagerstandort().getId()==aktLagerId)
				{
					buchungsInLager.add(b);
				}
			}
		}
		
		if(!buchungsInLager.isEmpty())
		{
			model.addAttribute("buchungsInLager", buchungsInLager);
		}
		
		return "buchungview";
    }
    
    private LocalDateTime convertor(String dateTime)
    {
    	return LocalDateTime.parse(dateTime);
    }
    
    private int aktBestand(Material m, LocalDateTime begin, LocalDateTime ende)
    {
    	int aktImLager = m.getBestand();    	
    	int gebucht = 0;
    	int aktVorBuchung = aktImLager;

    	
		List<at.wrk.fmd.matilda.model.Buchung> buchungs = buchungRepository.findByMaterial(m);
		if(!buchungs.isEmpty())
		{	
			LocalDateTime i = begin;;  
			while (i.isBefore(ende))
			{
				gebucht = 0;
				for (at.wrk.fmd.matilda.model.Buchung b: buchungs)
				{
					if(b.getVeranstaltung()!=null)
					{
						if(i.isBefore(b.getVeranstaltung().getEnde())&&i.isAfter(b.getVeranstaltung().getBeginn()))
						{
							gebucht = gebucht + b.getMenge();
						}
						else if (i.isEqual(b.getVeranstaltung().getBeginn()))
						{
							gebucht = gebucht + b.getMenge();
						}
					}
					else
					{
						if(i.isBefore(b.getBis())&&i.isAfter(b.getVon()))
						{
							gebucht = gebucht + b.getMenge();
						}
						else if(i.isEqual(b.getVon()))
						{
							gebucht = gebucht + b.getMenge();
						}
					}
				}
				
				if(gebucht>aktImLager) {
					aktVorBuchung = 0;
					aktImLager = 0;
					break;
				}
				else if(gebucht<=aktImLager&&aktImLager-gebucht<aktVorBuchung)
				{
					aktVorBuchung = aktImLager-gebucht;
				}					
				i = i.plusDays(1);
			}					
		}		
		return aktVorBuchung;
    }
}