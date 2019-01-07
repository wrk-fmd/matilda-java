package at.wrk.fmd.web;

import java.util.List;
import javax.validation.Valid;
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
import at.wrk.fmd.model.Einheitentyp;
import at.wrk.fmd.model.Materialtyp;
import at.wrk.fmd.model.Materialtyp_Einheitentyp;
import at.wrk.fmd.repository.EinheitentypRepository;
import at.wrk.fmd.repository.MatEinRepository;
import at.wrk.fmd.repository.MaterialtypRepository;

@Controller
public class MaterialtypEinheitentypController {
    private MatEinRepository matEinRepository;
    private EinheitentypRepository einheitentypRepository;
    private MaterialtypRepository materialtypRepository;
    private long aktEinheitentypId;
    private Einheitentyp aktEinheitentyp;
    private List<Materialtyp_Einheitentyp> materialeinheiten;
    
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public MaterialtypEinheitentypController(MatEinRepository matEinRepository, EinheitentypRepository einheitentypRepository,
            MaterialtypRepository materialtypRepository) {
        super();
        this.matEinRepository = matEinRepository;
        this.einheitentypRepository = einheitentypRepository;
        this.materialtypRepository = materialtypRepository;
    }

    // ************************************* Modelattribute ***************************

    @ModelAttribute("alleEinheitentypen")
    public List<Einheitentyp> alleEinheitentypen() {
        logger.info("Method {} called in {}", new Object() {}.getClass().getEnclosingMethod().getName(), this.getClass().getName());
        return einheitentypRepository.findAll();
    }

    @ModelAttribute("alleMaterialtypen")
    public List<Materialtyp> alleMaterialtypen() {
        logger.info("Method {} called in {}", new Object() {}.getClass().getEnclosingMethod().getName(), this.getClass().getName());
        return materialtypRepository.findAll();
    }

    // ************************************* MaterialtypEintentyp List + Hinzufügen ************************

    @RequestMapping(value = "/materialeinheit/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPERVISOR')")
    public String list(@PathVariable("id") long id, Model model) {
        logger.info("Method {} called in {}", new Object() {}.getClass().getEnclosingMethod().getName(), this.getClass().getName());
        
		Einheitentyp existing = einheitentypRepository.findById(id);
		aktEinheitentyp = existing;
		model.addAttribute("materialtyp_einheitentyp", new Materialtyp_Einheitentyp(existing));			
		materialeinheiten = matEinRepository.findByEinheitentyp(existing);
		aktEinheitentypId = existing.getId();
		
		if(materialeinheiten !=null)
		{
			model.addAttribute("materialeinheiten",materialeinheiten);
		}
		return "materialeinheit";
	}

    @RequestMapping(value = "/materialeinheit", method = RequestMethod.POST)
    public String addSpeichern(Model model,
            @ModelAttribute("materialtyp_einheitentyp") @Valid Materialtyp_Einheitentyp materialtyp_einheitentyp,
            BindingResult result) {
        logger.info("Method {} called in {}", new Object() {}.getClass().getEnclosingMethod().getName(), this.getClass().getName());
	    materialtyp_einheitentyp.setEinheitentyp(aktEinheitentyp);
	    
	    boolean eindeutig = true;
	    List<Materialtyp_Einheitentyp> matein = matEinRepository.findByEinheitentyp(aktEinheitentyp);
	    for(Materialtyp_Einheitentyp me: matein)
	    {
	    	if(me.getMaterialtyp()==materialtyp_einheitentyp.getMaterialtyp())
	    		eindeutig = false;
	    }
	    if(!eindeutig)
	    {
	    	result.rejectValue("materialtyp", null, "Es gibt schon ein Einheitentyp mit gleichem Materialtyp!");
	    }
    	if(materialtyp_einheitentyp.getMaterialtyp()==null)
    	{
    		result.rejectValue("materialtyp", null, "Der Materialtyp kann nicht Null sein!");
    	}
	    if (result.hasErrors()){	    	
	    	
			if(materialeinheiten !=null)
			{
				model.addAttribute("materialeinheiten",materialeinheiten);
			}
	        return "materialeinheit";
	    }
	    
		matEinRepository.save(materialtyp_einheitentyp);
        return "redirect:/materialeinheit/"+aktEinheitentypId;		
    }	

    // ************************************* MaterialtypEintentyp Ändern  ************************************

    @RequestMapping(value = "/mateinupdate/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPERVISOR')")
    public String aendernForm(@PathVariable("id") long id, Model model) {
        
        logger.info("Method {} called in {}", new Object() {}.getClass().getEnclosingMethod().getName(), this.getClass().getName());        

        Materialtyp_Einheitentyp existing = matEinRepository.findById(id);
        model.addAttribute("materialtyp_einheitentyp", existing);
        return "mateinupdate";
    }

    @RequestMapping(value = "/mateinupdate/{id}", method = RequestMethod.POST)
    public String aendernSpeichern(@PathVariable("id") long id,
            @ModelAttribute("materialtyp_einheitentyp") @Valid Materialtyp_Einheitentyp materialtyp_einheitentyp,
            BindingResult result) {

        logger.info("Method {} called in {}", new Object() {}.getClass().getEnclosingMethod().getName(), this.getClass().getName());
        
    	Materialtyp_Einheitentyp existing = matEinRepository.findById(id);   	  
		
		existing.setManzahl(materialtyp_einheitentyp.getManzahl());
		existing.setMaterialtyp(materialtyp_einheitentyp.getMaterialtyp());

	    if (result.hasErrors()){
	    	
	    	return "mateinupdate";
	    }   
	    	    
    	matEinRepository.save(existing);       	
    	return "redirect:/materialeinheit/"+aktEinheitentypId;
	}   
}
