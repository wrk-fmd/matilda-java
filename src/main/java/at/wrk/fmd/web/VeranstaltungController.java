package at.wrk.fmd.web;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import at.wrk.fmd.model.Buchung;
import at.wrk.fmd.model.Einheitentyp;
import at.wrk.fmd.model.Lagerstandort;
import at.wrk.fmd.model.Material;
import at.wrk.fmd.model.Materialtyp;
import at.wrk.fmd.model.Materialtyp_Einheitentyp;
import at.wrk.fmd.model.Veranstaltung;
import at.wrk.fmd.model.Veranstaltung_Einheitentyp;
import at.wrk.fmd.pojo.VeranstaltungBuchung;
import at.wrk.fmd.pojo.VeranstaltungBuchungWrapper;
import at.wrk.fmd.repository.BuchungRepository;
import at.wrk.fmd.repository.LagerstandortRepository;
import at.wrk.fmd.repository.MatEinRepository;
import at.wrk.fmd.repository.MaterialRepository;
import at.wrk.fmd.repository.VerEinRepository;
import at.wrk.fmd.repository.VeranstaltungBuchungRepository;
import at.wrk.fmd.repository.VeranstaltungRepository;

@Controller
public class VeranstaltungController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
	private Veranstaltung aktVeranstaltung;
	private VeranstaltungRepository veranstaltungRepository;
	private VerEinRepository verEinRepository;
	private LagerstandortRepository lagerstandortRepository;
	private MatEinRepository matEinRepository;
	private MaterialRepository materialRepository;
	private BuchungRepository buchungRepository;
	private VeranstaltungBuchungRepository veranstaltungBuchungRepository;
	private ArrayList<VeranstaltungBuchung> veranstaltungBuchungs =  new ArrayList<>();
	private VeranstaltungBuchungWrapper veranstaltungBuchungWrapper = new VeranstaltungBuchungWrapper();
	private Long aktVeranstaltungId;
	private List<String> aktWunschMaterialien = new ArrayList<>();

    @Autowired
	public VeranstaltungController(VeranstaltungRepository veranstaltungRepository, VerEinRepository verEinRepository,
			LagerstandortRepository lagerstandortRepository, MatEinRepository matEinRepository, MaterialRepository materialRepository, 
			BuchungRepository buchungRepository, VeranstaltungBuchungRepository veranstaltungBuchungRepository)
	{
		super();
		this.veranstaltungRepository = veranstaltungRepository;
		this.lagerstandortRepository = lagerstandortRepository;
		this.verEinRepository = verEinRepository;
		this.matEinRepository = matEinRepository;
		this.materialRepository = materialRepository;
		this.buchungRepository = buchungRepository;
		this.veranstaltungBuchungRepository = veranstaltungBuchungRepository;
	}
    
    // ************************************* Modelattribute ***************************************
    
	@ModelAttribute("alleLagerstandorten")
	public List<Lagerstandort> alleLagerstandorten() 
	{	
		return lagerstandortRepository.findAll();
	}
	
	@ModelAttribute("wunschMaterialien")
	public List<String> wunschMaterialien() 
	{	
		return aktWunschMaterialien;
	}
	
	
    // ************************************* VeranstaltungList  ************************

    @RequestMapping(value = "/veranstaltung", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPERVISOR')")
    public String list(Model model) {
        logger.info("Method {} called in {}", new Object() {}.getClass().getEnclosingMethod().getName(), this.getClass().getName());        
        		
        List<Veranstaltung> veranstaltungen = veranstaltungRepository.findAll();
        
        if (veranstaltungen != null) {
            model.addAttribute("veranstaltungen", veranstaltungen);
        }
        return "veranstaltung";
    }

    // ************************************** neue Veranstaltung hinzufügen ********************
    
	@RequestMapping(value="/neuveranstaltung", method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPERVISOR')")
    public String neuVeranstaltung(Model model) {
		logger.info("Method {} called in {}", new Object() {}.getClass().getEnclosingMethod().getName(), this.getClass().getName()); 
				
		model.addAttribute("veranstaltung", new at.wrk.fmd.pojo.Veranstaltung());				
        return "neuveranstaltung";
    }
    
    @RequestMapping(value = "/neuveranstaltung", method = RequestMethod.POST)
    public String addSpeichern(Model model, @ModelAttribute("veranstaltung") @Valid at.wrk.fmd.pojo.Veranstaltung veranstaltung,
            BindingResult result) {
        logger.info("Method {} called in {}", new Object() {}.getClass().getEnclosingMethod().getName(), this.getClass().getName());
        
    	Veranstaltung existing = veranstaltungRepository.findByName(veranstaltung.getName());
    	
    	if(existing!=null)
    	{
    		result.rejectValue("name", null, "Es ist bereits eine Veranstaltung mit gleichem Namen eingetragen");
    	}
    	if(veranstaltung.getLagerstandort()==null)
    	{
    		result.rejectValue("lagerstandort", null, "Der Lagerstandort der Veranstaltung kann nicht Null sein!");
    	}  
    	if(veranstaltung.getBeginn().compareTo(veranstaltung.getEnde())>0)
    	{
    		result.rejectValue("ende", null, "Wählen Sie richtiges Datum aus!");
    	}
    	if(result.hasErrors()) {
    		return "neuveranstaltung";
    	}    
    	
    	Veranstaltung neuVeranstaltung = new Veranstaltung();
    	
    	neuVeranstaltung.setName(veranstaltung.getName());
    	neuVeranstaltung.setBeginn(convertor(veranstaltung.getBeginn()));
    	neuVeranstaltung.setEnde(convertor(veranstaltung.getEnde()));
    	neuVeranstaltung.setLagerstandort(veranstaltung.getLagerstandort());
    	
    	veranstaltungRepository.save(neuVeranstaltung);
    	
	    return "redirect:/veranstaltung?success";		
    }

    // ************************************* Veranstaltung Ändern ***************************************


    @RequestMapping(value = "/veranstaltungupdate/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPERVISOR')")
    public String aendernForm(@PathVariable("id") long id, Model model) {
        logger.info("Method {} called in {}", new Object() {}.getClass().getEnclosingMethod().getName(), this.getClass().getName());
        
        aktVeranstaltung = veranstaltungRepository.findById(id);
        
        at.wrk.fmd.pojo.Veranstaltung veranstaltung = new at.wrk.fmd.pojo.Veranstaltung();
        veranstaltung.setName(aktVeranstaltung.getName());
        veranstaltung.setBeginn(aktVeranstaltung.getBeginn().toString());
        veranstaltung.setEnde(aktVeranstaltung.getEnde().toString());
        
        model.addAttribute("veranstaltung", veranstaltung);
        return "veranstaltungupdate";
    }

    @RequestMapping(value = "/veranstaltungupdate/{id}", method = RequestMethod.POST)
    public String aendernSepeichern(@ModelAttribute("veranstaltung") @Valid at.wrk.fmd.pojo.Veranstaltung veranstaltung, BindingResult result) {

        logger.info("Method {} called in {}", new Object() {}.getClass().getEnclosingMethod().getName(), this.getClass().getName());
        
    	Veranstaltung andere = veranstaltungRepository.findByName(veranstaltung.getName());
     	
    	if(result.hasErrors()) {
    		return "veranstaltungupdate";
    	}
    	if(veranstaltung.getBeginn().compareTo(veranstaltung.getEnde())>0)
    	{
    		result.rejectValue("ende", null, "Wählen Sie richtiges Datum aus!");
    	}
    	if(veranstaltung.getName().equals(aktVeranstaltung.getName()))		
    	{     		
    		aktVeranstaltung.setBeginn(convertor(veranstaltung.getBeginn()));
    		aktVeranstaltung.setEnde(convertor(veranstaltung.getEnde()));
    		
        	if(result.hasErrors()) {
        		return "veranstaltungupdate";
        	}
    		
        	veranstaltungRepository.save(aktVeranstaltung);
        	return "redirect:/veranstaltung?success";
    	}
    	if(andere!=null)
    	{
    		result.rejectValue("name", null, "Eine Veranstaltung ist schon mit diesem Namen eingetragen!");
    	}
    		
		aktVeranstaltung.setName(veranstaltung.getName());
		aktVeranstaltung.setBeginn(convertor(veranstaltung.getBeginn()));
		aktVeranstaltung.setEnde(convertor(veranstaltung.getEnde()));
		
    	if(result.hasErrors()) {
    		return "veranstaltungupdate";
    	}
    	
		veranstaltungRepository.save(aktVeranstaltung);      
    	return "redirect:/veranstaltung?success";    	    	
	}  

	// ************************************* Veranstaltung - Buchen  ************************************

	@RequestMapping(value="/veranstaltungbuchung/{id}", method=RequestMethod.GET)
    public String buchen(@PathVariable("id") long id, Model model) {
				
		Veranstaltung existing = veranstaltungRepository.findById(id);
		
		aktVeranstaltungId = id;		
		aktVeranstaltung = existing;
		
		if(existing.getZustand().equals("In Bearbeitung"))
		{
			List<VeranstaltungBuchung> ver = veranstaltungBuchungRepository.findByVeranstaltung(existing);
			//wenn noch kein Pojosklasse erstellt ist
			if(ver.isEmpty())
			{
				List<Veranstaltung_Einheitentyp> verein = verEinRepository.findByVeranstaltung(existing);
				
		    	if(!verein.isEmpty())
		    	{	    
		    		List<Einheitentyp> einheitentyps = new ArrayList<>();
		    		
		    		// alle Einheitentypen die der Veranstaltung zugewiesen sind
		    		for(Veranstaltung_Einheitentyp ve : verein)
		    		{
		    			einheitentyps.add(ve.getEinheitentyp());
		    		}
		    		
		    		List<Materialtyp_Einheitentyp> matein = new ArrayList<>();
		    		
		    		for(Einheitentyp e : einheitentyps)
		    		{
		    			matein.addAll(matEinRepository.findByEinheitentyp(e));
		    		}	
		    		
		    		ArrayList<VeranstaltungBuchung> verbuch = new ArrayList<>();
		    		
		    		if(!matein.isEmpty())
		    		{	    
			    		// Buchungsliste (auswahllist) befüllen(Pojosklasse)
		    			for(Materialtyp_Einheitentyp me : matein)
		    			{	   
		    				
		    				verbuch.add(new VeranstaltungBuchung(me.getMaterialtyp(),me.getEinheitentyp(), me.getManzahl(), existing));
		    			}	    				    			
		    			
		    			for(VeranstaltungBuchung vb : verbuch)
		    			{	 
		    				
		    				List<Material> materials = materialRepository.findByMaterialtyp(vb.getMaterialtyp());
		    					    				
		    				if(!materials.isEmpty())
		    				{
		    					// alle Materialtypen die der Veranstaltung zugewiesen sind
		    					for (Iterator<Material> iterator = materials.iterator(); iterator.hasNext();) {
		    					    Material ma = iterator.next();
		    					    if(ma.getLagerstandort().getId()!=aktVeranstaltung.getLagerstandort().getId()||!ma.isEinsatzbereitschaft()
		    					    		|| ma.getBestand()==0) {
		    					        iterator.remove();
		    					    }
		    					}
		    					
		    					// alle Materialien die der Veranstaltung zugewiesen sind und Einsatzbereit und im Standardlager sind
		    					for(Material ma : materials)
		    					{
		    						if(ma.getLagerstandort().getId()!=aktVeranstaltung.getLagerstandort().getId()||!ma.isEinsatzbereitschaft())
		    						{
		    							materials.remove(ma);
		    						}	    					
		    					}
			    				if(!materials.isEmpty())
			    				{			    					
			    					vb.setMaterialien(materials);
			    					vb.setWunschMaterial(materials.get(0).getBezeichnung()+" ("+aktBestand(materials.get(0))+")");
			    				}
		    				}  
		    				List<Material> m = vb.getMaterialien();
		    				if(m == null)
		    				{
		    					vb.setStatus("Nicht vorhanden im Lager");
		    				}
		    				else
		    				{
		    					vb.setStatus("vorhanden im Lager");
		    				}
		    			}	  	    				    	
		    			//wenn noch eine Pojosklasse (veranstaltungbuchung) erstellt ist		    			
		    			if(!verbuch.isEmpty())
		    			{	
		    				veranstaltungBuchungs = verbuch;
		    				veranstaltungBuchungRepository.saveAll(veranstaltungBuchungs);
		    				veranstaltungBuchungWrapper.setBuchungList(veranstaltungBuchungs);
		    				
		    				
		    				model.addAttribute("veranstaltungBuchungWrapper",veranstaltungBuchungWrapper);
		    				
		    				return "veranstaltungbuchung"; 	    				
		    			}	    				    			
		    			else
		    			{
		    				return "redirect:/veranstaltung?lager";
		    			}
		    		}
		    		else
		    		{
		    			return "redirect:/veranstaltung?material";
		    		}
		    		
		    	}
		    	else
		    	{
		    		return "redirect:/veranstaltung?einheit"; 
		    	}
			}
			else
			{
				veranstaltungBuchungs = (ArrayList<VeranstaltungBuchung>) veranstaltungBuchungRepository.findByVeranstaltung(existing);
				veranstaltungBuchungWrapper.setBuchungList(veranstaltungBuchungs);
				model.addAttribute("veranstaltungBuchungWrapper",veranstaltungBuchungWrapper);
				return "veranstaltungbuchung"; 	 
			}
		}
		else
		{
	    	return "redirect:/veranstaltung?gebucht"; 
		}
    }
	
	@RequestMapping(value="/veranstaltungbuchung", method=RequestMethod.POST)
    public String buchenSpeichern(Model model) {
		boolean gebucht = false;
		
		List<VeranstaltungBuchung> veranstaltungBuchungs = veranstaltungBuchungRepository.findByVeranstaltung(aktVeranstaltung);
		
		for(VeranstaltungBuchung vb : veranstaltungBuchungs)
		{
			if(vb.getWunschMaterial()!=null & vb.getWunschMenge()!=0)
			{
				Material m = new Material();
				List<Material> materialien = vb.getMaterialien();
				for(Material ma : materialien)
				{
		    		if(vb.getWunschMaterial().contains(ma.getBezeichnung()))
		    		{
		    			m = ma;
		    		}
				}		
				if(m.getBestand()>=vb.getWunschMenge())
				{
//					m.setBestand(m.getBestand()-vb.getWunschMenge());
//					materialRepository.save(m);
					Buchung buchung = new Buchung(m, vb.getWunschMenge() , "buchen", aktVeranstaltung, "AUTO-BUCHUNG");
					buchungRepository.save(buchung);
					gebucht = true;
				}
			}
		}		
		if(gebucht==true)
		{
			aktVeranstaltung.setZustand("Gebucht");
			veranstaltungRepository.save(aktVeranstaltung);
		}
		else
		{
			List<Veranstaltung> veranstaltungen = veranstaltungRepository.findAll();
	        if (veranstaltungen != null) {
	            model.addAttribute("veranstaltungen", veranstaltungen);
	        }
			return "redirect:/veranstaltung?nobuchung"; 
		}
		
		List<Veranstaltung> veranstaltungen = veranstaltungRepository.findAll();				
        if (veranstaltungen != null) {
            model.addAttribute("veranstaltungen", veranstaltungen);
        }
		return "veranstaltung";
	}
	
	// Wunschmenge ändern (Standardvalue ändern)
	
    @RequestMapping(value = "/veranstaltungbuchungupdate/{id}", method = RequestMethod.GET)
    public String veranstaltungBuchungAendern(Model model, @PathVariable("id") int id) {
    	
		VeranstaltungBuchung veranstaltungBuchung = veranstaltungBuchungRepository.findById(id);
		if(veranstaltungBuchung.getMaterialien().isEmpty())
		{
			return "redirect:/veranstaltungbuchung/"+aktVeranstaltungId+"?nomaterial"; 
		}
		aktWunschMaterialien.clear();
		List<Material> m = veranstaltungBuchung.getMaterialien();
		for(Material ma: m)
		{
			aktWunschMaterialien.add(ma.getBezeichnung()+" ("+aktBestand(ma)+")");
		}		

		model.addAttribute("veranstaltungBuchung",veranstaltungBuchung);
		
    	return "veranstaltungbuchungupdate";    
    }
    
    @RequestMapping(value = "/veranstaltungbuchungupdate/{id}", method = RequestMethod.POST)
    public String veranstaltungBuchungAendernSpeichern(@PathVariable("id") int id, @ModelAttribute("veranstaltungBuchung") @Valid VeranstaltungBuchung veranstaltungBuchung,
    		Model model, BindingResult result) {
    	
    	VeranstaltungBuchung vb =  veranstaltungBuchungRepository.findById(id);    
    	if(veranstaltungBuchung.getWunschMaterial().isEmpty()||veranstaltungBuchung.getWunschMenge()==0)
    	{
    		vb.setWunschMaterial(null);
    		vb.setWunschMenge(0);
    		veranstaltungBuchungRepository.save(vb);
    		return "redirect:/veranstaltungbuchung/"+aktVeranstaltungId;   
    	}   	   	
    	if(result.hasErrors()) {
    		return "veranstaltungbuchungupdate";
    	}
    	List<Material> m = vb.getMaterialien();   			
    	int aktBestand = 0;
    	
    	for(Material ma:m)
    	{
    		if(veranstaltungBuchung.getWunschMaterial().contains(ma.getBezeichnung()))
    		{
    			aktBestand= aktBestand(ma);
    		}
    	}
    	if(aktBestand < veranstaltungBuchung.getWunschMenge())
    	{
    		result.rejectValue("wunschMenge", null, "Nicht genüg Material im Lager!");
    	}
    	if(result.hasErrors()) {
    		return "veranstaltungbuchungupdate";
    	}
    	
    	vb.setWunschMenge(veranstaltungBuchung.getWunschMenge());
    	vb.setWunschMaterial(veranstaltungBuchung.getWunschMaterial());
    	veranstaltungBuchungRepository.save(vb);
		
		
		model.addAttribute("veranstaltungBuchungWrapper",veranstaltungBuchungWrapper);
		
		return "redirect:/veranstaltungbuchung/"+aktVeranstaltungId;   
    }
    
    // Liste der Materialien, die für die Veranstaltung gebucht sind   
    @RequestMapping(value="/veranstaltunggebucht/{id}", method=RequestMethod.GET)
    public String buchungView(Model model, @PathVariable("id") long id)
    {
    	aktVeranstaltung = veranstaltungRepository.findById(id);
    	aktVeranstaltungId = id;
//    	if(aktVeranstaltung.getZustand().equals("In Bearbeitung"))
//    	{
//    		return "redirect:/veranstaltung?nogebucht"; 
//    	}
		List<Buchung> veranstaltunggebucht = buchungRepository.findByVeranstaltung(aktVeranstaltung);
		
		if(!veranstaltunggebucht.isEmpty())
		{
			model.addAttribute("veranstaltunggebucht", veranstaltunggebucht);
		}
		
		return "veranstaltunggebucht";
    }
        
    private LocalDateTime convertor(String dateTime)
    {
    	return LocalDateTime.parse(dateTime);
    }
    
    private int aktBestand(Material m)
    {
    	int akt = m.getBestand();
		List<Veranstaltung> doppelteVer = veranstaltungRepository.findByEndeGreaterThan(aktVeranstaltung.getBeginn());

		if(!doppelteVer.isEmpty())
		{
			for(Veranstaltung v : doppelteVer)
			{
				if(!v.getBeginn().isAfter(aktVeranstaltung.getEnde())||v.equals(aktVeranstaltung))
				{
					List<Buchung> buchungs = buchungRepository.findByVeranstaltung(v);
					if(!buchungs.isEmpty())
					{
						for(Buchung bu : buchungs)
						{
							if(bu.getMaterial().equals(m))
							{
								akt=akt-bu.getMenge();
							}
						}
					}
				}
			}			    			
		}		
    	
		return akt;
    }
}
