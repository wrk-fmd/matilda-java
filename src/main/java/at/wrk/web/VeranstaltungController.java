package at.wrk.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import at.wrk.model.Veranstaltung;
import at.wrk.repository.VerEinRepository;
import at.wrk.repository.VeranstaltungRepository;

@Controller
public class VeranstaltungController
{
	private VeranstaltungRepository veranstaltungRepository;
	private VerEinRepository verEinRepository;

	@Autowired
	public VeranstaltungController(VeranstaltungRepository veranstaltungRepository)
	{
		super();
		this.veranstaltungRepository = veranstaltungRepository;
	}
	
	// ************************************* VeranstaltungList + hinzufügen ************************************
	
	@RequestMapping(value="/veranstaltung" , method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPERVISOR')")
	public String list(Model model)
	{
		model.addAttribute("veranstaltung", new Veranstaltung());
		
		List<Veranstaltung> veranstaltungen = veranstaltungRepository.findAll();
		
		if(veranstaltungen!=null)
		{
			model.addAttribute("veranstaltungen",veranstaltungen);
		}
		return "veranstaltung";
	}
		
    @RequestMapping(value="/veranstaltung", method=RequestMethod.POST)
    public String addSpeichern(Model model,@ModelAttribute("veranstaltung") @Valid Veranstaltung veranstaltung, BindingResult result) {
    	
    	Veranstaltung existing = veranstaltungRepository.findByName(veranstaltung.getName());
    	if(existing!=null)
    	{
    		result.rejectValue("name", null, "Es ist bereits eine Veranstaltung mit gleichem Namen eingetragen");
    	}
    	if(veranstaltung.getBeginn()==null)
    	{
    		result.rejectValue("beginn", null, "Das Beginndatum der Veranstaltung kann nicht Null sein!");
    	}   	
    	if(veranstaltung.getEnde()==null)
    	{
    		result.rejectValue("ende", null, "Das Enddatum der Veranstaltung kann nicht Null sein!");
    	}   
    	if(result.hasErrors()) {
    		
    		List<Veranstaltung> veranstaltungen = veranstaltungRepository.findAll();  		
    		if(veranstaltungen!=null)
    		{
    			model.addAttribute("veranstaltungen",veranstaltungen);
    		}
    		
    		return "veranstaltung";
    	}
	    veranstaltungRepository.save(veranstaltung);
	    return "redirect:/veranstaltung?success";
		
    }
	
	// ************************************* Veranstaltung Ändern ************************************
    
	@RequestMapping(value="/veranstaltungupdate/{id}", method=RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPERVISOR')")
    public String aendernForm(@PathVariable("id") long id, Model model) {
		Veranstaltung exicting = veranstaltungRepository.findById(id);
        model.addAttribute("veranstaltung", exicting);
        return "veranstaltungupdate";
    }
	
    @RequestMapping(value="/veranstaltungupdate/{id}", method=RequestMethod.POST)
    public String aendernSepeichern(@PathVariable("id") long id, @ModelAttribute("veranstaltung") @Valid Veranstaltung veranstaltung,
    		BindingResult result) {
    	
    	Veranstaltung existing = veranstaltungRepository.findById(id);
    	Veranstaltung andere = veranstaltungRepository.findByName(veranstaltung.getName());
     	
    	if(veranstaltung.getName()==null)
    	{
    		result.rejectValue("name", null, "Der Name der Veranstaltung kann nicht Null sein!");
    	}
    	if(result.hasErrors()) {
    		return "veranstaltungupdate";
    	}
    	if(veranstaltung.getBeginn()==null)
    	{
    		result.rejectValue("beginn", null, "Das Beginndatum der Veranstaltung kann nicht Null sein!");
    	}   	
    	if(veranstaltung.getEnde()==null)
    	{
    		result.rejectValue("ende", null, "Das Enddatum der Veranstaltung kann nicht Null sein!");
    	}  
    	if(veranstaltung.getName().equals(existing.getName()))		
    	{     		
    		existing.setBeginn(veranstaltung.getBeginn());
    		existing.setEnde(veranstaltung.getEnde());
    		
        	if(result.hasErrors()) {
        		return "veranstaltungupdate";
        	}
    		
        	veranstaltungRepository.save(existing);
        	return "redirect:/veranstaltung?success";
    	}
    	if(andere!=null)
    	{
    		result.rejectValue("name", null, "Eine Veranstaltung ist schon mit diesem Namen eingetragen!");
    	}
    		
		existing.setName(veranstaltung.getName());
		existing.setBeginn(veranstaltung.getBeginn());
		existing.setEnde(veranstaltung.getEnde());
		
    	if(result.hasErrors()) {
    		return "veranstaltungupdate";
    	}
    	
		veranstaltungRepository.save(existing);       	
    	return "redirect:/veranstaltung?success";    	    	
	}  
    
	// ************************************* Veranstaltung Löschen ************************************
    
	@RequestMapping(value="/veranstaltung/{id}/loeschen", method = RequestMethod.POST)
	public String loeschen(@PathVariable("id") long id, BindingResult result) 
	{ 		
		
    	if(result.hasErrors()) {
    		return "veranstaltungupdate";   	
    	}
  	
		veranstaltungRepository.deleteById(id);
		
		return "redirect:/veranstaltung?loeschen";
	}
	
	// *********************************************************************************************
	
//    @RequestMapping(value="/veranstaltung/{id}/buchen", method=RequestMethod.POST)
//    public String buchen(@PathVariable("id") long id,
//    		BindingResult result) {
//    	
//
//    }
}
