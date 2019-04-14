package at.wrk.fmd.matilda.web;

import java.util.List;

import javax.validation.Valid;

import at.wrk.fmd.matilda.model.Einheitentyp;
import at.wrk.fmd.matilda.model.Mitarbeitertyp;
import at.wrk.fmd.matilda.model.Mitarbeitertyp_Einheitentyp;
import at.wrk.fmd.matilda.repository.EinheitentypRepository;
import at.wrk.fmd.matilda.repository.MitEinRepository;
import at.wrk.fmd.matilda.repository.MitarbeitertypRepository;
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
public class MitarbeitertypEinheitentypController {
    private MitEinRepository mitEinRepository;
    private MitarbeitertypRepository mitarbeitertypRepository;
    private EinheitentypRepository einheitentypRepository;

    private long aktEinheitentypId;
    private Einheitentyp aktEinheitentyp;
    private List<Mitarbeitertyp_Einheitentyp> mitarbeitereinheiten;

    @Autowired
    public MitarbeitertypEinheitentypController(MitEinRepository mitEinRepository, MitarbeitertypRepository mitarbeitertypRepository,
            EinheitentypRepository einheitentypRepository) {
        super();
        this.mitEinRepository = mitEinRepository;
        this.mitarbeitertypRepository = mitarbeitertypRepository;
        this.einheitentypRepository = einheitentypRepository;
    }

    // ************************************* Modelattribute ***************************************

    @ModelAttribute("alleEinheitentypen")
    public List<Einheitentyp> alleEinheitentypen() {
        return einheitentypRepository.findAll();
    }

    @ModelAttribute("alleMitarbeitertypen")
    public List<Mitarbeitertyp> alleMitarbeitertypen() {
        return mitarbeitertypRepository.findAll();
    }

    // ************************************* MitarbeitertypEinheitentyp List + hinzufügen   ************************************

    @RequestMapping(value = "/mitarbeitereinheit/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPERVISOR')")
    public String list(@PathVariable("id") long id, Model model) {
        Einheitentyp existing = einheitentypRepository.findById(id);
        aktEinheitentyp = existing;
        model.addAttribute("mitarbeitertyp_einheitentyp", new Mitarbeitertyp_Einheitentyp(existing));
        aktEinheitentypId = existing.getId();
        mitarbeitereinheiten = mitEinRepository.findByEinheitentyp(existing);

        if (mitarbeitereinheiten != null) {
            model.addAttribute("mitarbeitereinheiten", mitarbeitereinheiten);
        }
        return "mitarbeitereinheit";
    }

    @RequestMapping(value = "/mitarbeitereinheit", method = RequestMethod.POST)
    public String addSpeichern(Model model,
            @ModelAttribute("mitarbeitertyp_einheitentyp") @Valid Mitarbeitertyp_Einheitentyp mitarbeitertyp_einheitentyp,
            BindingResult result) {

        mitarbeitertyp_einheitentyp.setEinheitentyp(aktEinheitentyp);

        if (mitarbeitertyp_einheitentyp.getMitarbeitertyp() == null) {
            result.rejectValue("mitarbeitertyp", null, "Der Miterbeitertyp kann nicht Null sein!");
        }
        if (result.hasErrors()) {
            if (mitarbeitereinheiten != null) {
                model.addAttribute("mitarbeitereinheiten", mitarbeitereinheiten);
            }
            return "mitarbeitereinheit";
        }

        mitEinRepository.save(mitarbeitertyp_einheitentyp);
        return "redirect:/mitarbeitereinheit/" + aktEinheitentypId;
    }

    // ************************************* MitarbeitertypEinheitentyp Ändern  ************************************

    @RequestMapping(value = "/miteinupdate/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPERVISOR')")
    public String aendernForm(@PathVariable("id") long id, Model model) {

        Mitarbeitertyp_Einheitentyp exicting = mitEinRepository.findById(id);
        model.addAttribute("mitarbeitertyp_einheitentyp", exicting);
        return "miteinupdate";
    }

    @RequestMapping(value = "/miteinupdate/{id}", method = RequestMethod.POST)
    public String aendernSpeichern(@PathVariable("id") long id,
            @ModelAttribute("mitarbeitertyp_einheitentyp") @Valid Mitarbeitertyp_Einheitentyp mitarbeitertyp_einheitentyp,
            BindingResult result) {

        Mitarbeitertyp_Einheitentyp existing = mitEinRepository.findById(id);

        existing.setManzahl(mitarbeitertyp_einheitentyp.getManzahl());
        existing.setMitarbeitertyp(mitarbeitertyp_einheitentyp.getMitarbeitertyp());
        if (result.hasErrors()) {
            return "miteinupdate";
        }
        mitEinRepository.save(existing);
        return "redirect:/mitarbeitereinheit/" + aktEinheitentypId;
    }
    
	@RequestMapping(value="/mitarbeitereinheitupdate/{id}/loeschen", method = RequestMethod.POST)
	public String loeschen(@PathVariable("id") long id) 
	{
		mitEinRepository.deleteById(id);
		
		return "redirect:/mitarbeitereinheit/"+aktEinheitentypId;
	}
}