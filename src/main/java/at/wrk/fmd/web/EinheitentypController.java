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
import at.wrk.fmd.repository.EinheitentypRepository;

@Controller
public class EinheitentypController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    private EinheitentypRepository einheitentypRepository;

    @Autowired
    public EinheitentypController(EinheitentypRepository einheitentypRepository) {
        super();
        this.einheitentypRepository = einheitentypRepository;
    }

    // ************************************* EinheitentypList + hinzufügen
    // ************************************

    @RequestMapping(value = "/einheitentyp", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPERVISOR')")
    public String list(Model model) {
        model.addAttribute("einheitentyp", new Einheitentyp());

        List<Einheitentyp> einheitentypen = einheitentypRepository.findAll();
        if (einheitentypen != null) {
            model.addAttribute("einheitentypen", einheitentypen);
        }
        logger.info("Method {} called in {}", new Object() {}.getClass().getEnclosingMethod().getName(), this.getClass().getName());
        return "einheitentyp";
    }

    @RequestMapping(value = "/einheitentyp", method = RequestMethod.POST)
    public String addSpeichern(Model model, @ModelAttribute("einheitentyp") @Valid Einheitentyp einheitentyp,
            BindingResult result) {

        Einheitentyp existing = einheitentypRepository.findByName(einheitentyp.getName());
        if (existing != null) {
            result.rejectValue("name", null, "Es ist bereits ein Einheitentyp mit gleichem Namen eingetragen");
        }
        if (result.hasErrors()) {
            List<Einheitentyp> einheitentypen = einheitentypRepository.findAll();
            if (einheitentypen != null) {
                model.addAttribute("einheitentypen", einheitentypen);
            }
            return "einheitentyp";
        }

        einheitentypRepository.save(einheitentyp);
        logger.info("Method {} called in {}", new Object() {}.getClass().getEnclosingMethod().getName(), this.getClass().getName());
        return "redirect:/einheitentyp?success";
    }

    // ************************************* Einheitentyp Ändern
    // ************************************

    @RequestMapping(value = "/einheitentypupdate/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPERVISOR')")
    public String aendernForm(@PathVariable("id") long id, Model model) {
        Einheitentyp exicting = einheitentypRepository.findById(id);
        model.addAttribute("einheitentyp", exicting);
        logger.info("Method {} called in {}", new Object() {}.getClass().getEnclosingMethod().getName(), this.getClass().getName());
        return "einheitentypupdate";
    }

    @RequestMapping(value = "/einheitentypupdate/{id}", method = RequestMethod.POST)
    public String aendernSepeichern(@PathVariable("id") long id,
            @ModelAttribute("einheitentyp") @Valid Einheitentyp einheitentyp, BindingResult result) {

        Einheitentyp existing = einheitentypRepository.findById(id);
        Einheitentyp andere = einheitentypRepository.findByName(einheitentyp.getName());

        if (einheitentyp.getName().equals(existing.getName())) {
            logger.info("Method {} called in {}", new Object() {}.getClass().getEnclosingMethod().getName(), this.getClass().getName());
            return "redirect:/einheitentyp?nochange";
        }
        if (andere != null) {
            result.rejectValue("name", null, "Es ist bereits ein Einheitentyp mit gleichem Namen eingetragen");
        }
        if (result.hasErrors()) {
            logger.error("Method {} called in {}", new Object() {}.getClass().getEnclosingMethod().getName(), this.getClass().getName());
            return "einheitentypupdate";
        }

        existing.setName(einheitentyp.getName());

        einheitentypRepository.save(existing);
        logger.info("Method {} called in {}", new Object() {}.getClass().getEnclosingMethod().getName(), this.getClass().getName());
        return "redirect:/einheitentyp?success";
    }

    // ************************************* Einheitentyp Löschen
    // ************************************

    @RequestMapping(value = "/einheitentypupdate/{id}/loeschen", method = RequestMethod.POST)
    public String loeschen(@PathVariable("id") long id) {
        einheitentypRepository.deleteById(id);

        logger.info("Method {} called in {}", new Object() {}.getClass().getEnclosingMethod().getName(), this.getClass().getName());
        return "redirect:/einheitentyp?loeschen";
    }
}
