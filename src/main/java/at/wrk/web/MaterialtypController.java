package at.wrk.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import at.wrk.model.Materialtyp;
import at.wrk.repository.MaterialtypRepository;

@Controller
public class MaterialtypController
{
	private MaterialtypRepository materialtypRepository;
	
	@Autowired
	private MaterialtypController (MaterialtypRepository materialtypRepository)
	{
		this.materialtypRepository = materialtypRepository;
	}
	
	// ************************************* MaterialtypenList + Hinzufügen ************************************
	
	@RequestMapping(value="/materialtyp" , method=RequestMethod.GET)
	public String list(Model model)
	{
		model.addAttribute("materialtyp", new Materialtyp());
		
		List<Materialtyp> materialtypen = materialtypRepository.findAll();		
		if(materialtypen !=null)
		{
			model.addAttribute("materialtypen",materialtypen);
		}
		return "materialtyp";
	}	
	
    @RequestMapping(value="/materialtyp", method=RequestMethod.POST)
    public String addSpeichern(Model model, @ModelAttribute("materialtyp") @Valid Materialtyp materialtyp, BindingResult result) {
    	
    	Materialtyp existing = materialtypRepository.findByName(materialtyp.getName());
    	
		if(existing!=null)
		{	
			 result.rejectValue("name", null, "Es ist bereits ein Materialtyp mit gleichem Namen eingetragen");
		}
	    if (result.hasErrors()){
	    	
			List<Materialtyp> materialtypen = materialtypRepository.findAll();		
			if(materialtypen !=null)
			{
				model.addAttribute("materialtypen",materialtypen);
			}
			
	        return "materialtyp";
	    }
	    
    	materialtypRepository.save(materialtyp);
        return "redirect:/materialtyp?success";
    }
	
	// ************************************* Materialtyp Ändern ************************************
    
	@RequestMapping(value="/materialtypupdate/{id}", method=RequestMethod.GET)
    public String aendernForm(@PathVariable("id") long id, Model model) {
		
		Materialtyp exicting = materialtypRepository.findById(id);
        model.addAttribute("materialtyp", exicting);
        return "materialtypupdate";
    }
	
    @RequestMapping(value="/materialtypupdate/{id}", method=RequestMethod.POST)
    public String aendernSpeichern(@PathVariable("id") long id, @ModelAttribute("materialtyp") @Valid Materialtyp materialtyp,
    		BindingResult result) {
    	
    	Materialtyp existing = materialtypRepository.findById(id);
    	Materialtyp andere = materialtypRepository.findByName(materialtyp.getName());
     	
    	if(materialtyp.getName().equals(existing.getName()))		
    	{
    		existing.setName(materialtyp.getName());
    		existing.setMenge(materialtyp.getMenge());
    		existing.setBeschreibung(materialtyp.getBeschreibung());
    		existing.setLink(materialtyp.getLink());
    		
            if (result.hasErrors()){
                return "materialtypupdate";
            }
    		
        	materialtypRepository.save(existing);       	
        	return "redirect:/materialtyp?success";
    	}
    	if(andere!=null)
    	{
    		result.rejectValue("name", null, "Es ist bereits ein Materialtyp mit gleichem Namen eingetragen");
    	}
        if (result.hasErrors()){
            return "materialtypupdate";
        }

		existing.setName(materialtyp.getName());
		existing.setMenge(materialtyp.getMenge());
		existing.setBeschreibung(materialtyp.getBeschreibung());
		existing.setLink(materialtyp.getLink());
		
    	materialtypRepository.save(existing);       	
    	return "redirect:/materialtyp?success";
	}    
    
	// ************************************* Materialtyp Löschen ************************************
    
	@RequestMapping(value="/materialtypupdate/{id}/loeschen", method = RequestMethod.POST)
	public String loeschen(@PathVariable("id") long id) 
	{
		materialtypRepository.deleteById(id);
		
		return "redirect:/materialtyp?loeschen";
	}   
}
