package at.wrk.fmd.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import at.wrk.fmd.model.Mitarbeitertyp;
import at.wrk.fmd.repository.MitarbeitertypRepository;

@Controller
public class MitarbeitertypController {
    private MitarbeitertypRepository mitarbeitertypRepository;

    @Autowired
    public MitarbeitertypController(MitarbeitertypRepository mitarbeitertypRepository) {
        super();
        this.mitarbeitertypRepository = mitarbeitertypRepository;
    }

    // ************************************* MitarbeitertypList + hinzufügen
    // ************************************

    @RequestMapping(value = "/mitarbeitertyp", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPERVISOR')")
    public String list(Model model) {
        model.addAttribute("mitarbeitertyp", new Mitarbeitertyp());

        List<Mitarbeitertyp> mitarbeitertypen = mitarbeitertypRepository.findAll();
        if (mitarbeitertypen != null) {
            model.addAttribute("mitarbeitertypen", mitarbeitertypen);
        }
        return "mitarbeitertyp";
    }

    @RequestMapping(value = "/mitarbeitertyp", method = RequestMethod.POST)
    public String addSpeichern(@ModelAttribute("mitarbeitertyp") @Valid Mitarbeitertyp mitarbeitertyp,
            BindingResult result) {

        Mitarbeitertyp existing = mitarbeitertypRepository.findByKuerzel(mitarbeitertyp.getKuerzel());
        if (existing != null) {
            result.rejectValue("kuerzel", null, "Es ist bereits ein Mitarbeitertyp mit gleichem Kürzel eingetragen");
        }
        if (result.hasErrors()) {
            return "mitarbeitertyp";
        }

        mitarbeitertypRepository.save(mitarbeitertyp);
        return "redirect:/mitarbeitertyp?success";
    }

    // ************************************* Mitarbeitertyp Ändern
    // ************************************

    @RequestMapping(value = "/mitarbeitertypupdate/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPERVISOR')")
    public String aendernForm(@PathVariable("id") long id, Model model) {
        Mitarbeitertyp exicting = mitarbeitertypRepository.findById(id);
        model.addAttribute("mitarbeitertyp", exicting);
        return "mitarbeitertypupdate";
    }

    @RequestMapping(value = "/mitarbeitertypupdate/{id}", method = RequestMethod.POST)
    public String aendernSpeichern(@PathVariable("id") long id,
            @ModelAttribute("mitarbeitertyp") @Valid Mitarbeitertyp mitarbeitertyp, BindingResult result) {

        Mitarbeitertyp existing = mitarbeitertypRepository.findById(id);
        Mitarbeitertyp andere = mitarbeitertypRepository.findByKuerzel(mitarbeitertyp.getKuerzel());

        if (mitarbeitertyp.getKuerzel().equals(existing.getKuerzel())) {
            existing.setKuerzel(mitarbeitertyp.getKuerzel());
            existing.setName(mitarbeitertyp.getName());

            if (result.hasErrors()) {
                return "mitarbeitertypupdate";
            }

            mitarbeitertypRepository.save(existing);
            return "redirect:/mitarbeitertyp?success";
        }
        if (andere != null) {
            result.rejectValue("kuerzel", null, "Es ist bereits ein Mitarbeitertyp mit gleichem Kürzel eingetragen");
        }
        if (result.hasErrors()) {
            return "mitarbeitertypupdate";
        }

        existing.setKuerzel(mitarbeitertyp.getKuerzel());
        existing.setName(mitarbeitertyp.getName());

        mitarbeitertypRepository.save(existing);
        return "redirect:/mitarbeitertyp?success";
    }

    // ************************************* Einheitentyp Löschen
    // ************************************

    @RequestMapping(value = "/mitarbeitertypupdate/{id}/loeschen", method = RequestMethod.POST)
    public String loeschen(@PathVariable("id") long id) {
        mitarbeitertypRepository.deleteById(id);

        return "redirect:/mitarbeitertyp?loeschen";
    }
}