package at.wrk.fmd.web;

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
import at.wrk.fmd.model.Einheitentyp;
import at.wrk.fmd.model.Veranstaltung;
import at.wrk.fmd.model.Veranstaltung_Einheitentyp;
import at.wrk.fmd.pojo.VeranstaltungBuchung;
import at.wrk.fmd.repository.EinheitentypRepository;
import at.wrk.fmd.repository.VerEinRepository;
import at.wrk.fmd.repository.VeranstaltungBuchungRepository;
import at.wrk.fmd.repository.VeranstaltungRepository;

@Controller
public class VeranstaltungEinheitentypController {
    private VerEinRepository verEinRepository;
    private VeranstaltungRepository veranstaltungRepository;
    private EinheitentypRepository einheitentypRepository;
    private VeranstaltungBuchungRepository veranstaltungBuchungRepository;
    private long aktVeranstaltungId;
    private Veranstaltung aktVeranstaltung;

    @Autowired
    public VeranstaltungEinheitentypController(VerEinRepository verEinRepository, VeranstaltungRepository veranstaltungRepository,
            EinheitentypRepository einheitentypRepository, VeranstaltungBuchungRepository veranstaltungBuchungRepository) {
        super();
        this.verEinRepository = verEinRepository;
        this.veranstaltungRepository = veranstaltungRepository;
        this.einheitentypRepository = einheitentypRepository;
        this.veranstaltungBuchungRepository = veranstaltungBuchungRepository;
    }

    // ************************************* Modelattribute **************************************

    @ModelAttribute("alleEinheitentypen")
    public List<Einheitentyp> alleEinheitentypen() {
        return einheitentypRepository.findAll();
    }

    // ************************************* Ver-Ein List ***************************************

    @RequestMapping(value = "/veranstaltungeinheit/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPERVISOR')")
    public String addNtransaktionForm(@PathVariable("id") long id, Model model) {
    	
        model.addAttribute("veranstaltung_einheitentyp", new Veranstaltung_Einheitentyp());

        Veranstaltung existing = veranstaltungRepository.findById(id);
        aktVeranstaltung = existing;
        aktVeranstaltungId = existing.getId();
        if(!aktVeranstaltung.getZustand().equals("In Bearbeitung"))
        {
        	return "redirect:/veranstaltung?nozuweisung";
        }
        List<Veranstaltung_Einheitentyp> verein = verEinRepository.findByVeranstaltung(existing);

        if (verein != null) {
            model.addAttribute("verein", verein);
        }
        return "veranstaltungeinheit";
    }

    @RequestMapping(value = "/veranstaltungeinheit", method = RequestMethod.POST)
    public String addSpeichern(
            @ModelAttribute("veranstaltung_einheitentyp") @Valid Veranstaltung_Einheitentyp veranstaltung_einheitentyp,
            BindingResult result) {   	

        veranstaltung_einheitentyp.setVeranstaltung(aktVeranstaltung);

        if (veranstaltung_einheitentyp.getEinheitentyp() == null) {
            result.rejectValue("einheitentyp", null, "Der Einheitentyp kann nicht NULL sein");
        }
        if (result.hasErrors()) {
            return "veranstaltungeinheit";
        }
        
        List<VeranstaltungBuchung> veranstaltungBuchungs = veranstaltungBuchungRepository.findByVeranstaltung(aktVeranstaltung);
        veranstaltungBuchungRepository.deleteAll(veranstaltungBuchungs);
        
        verEinRepository.save(veranstaltung_einheitentyp);
        return "redirect:/veranstaltungeinheit/" + aktVeranstaltungId;
    }

}
