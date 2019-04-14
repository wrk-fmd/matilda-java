package at.wrk.fmd.matilda.web;

import java.util.List;

import javax.validation.Valid;

import at.wrk.fmd.matilda.model.Benutzer;
import at.wrk.fmd.matilda.model.Lagerstandort;
import at.wrk.fmd.matilda.repository.LagerstandortRepository;
import at.wrk.fmd.matilda.repository.UserRepository;
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
public class LagerstandortController {
    @Autowired
    private LagerstandortRepository lagerstandortRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    public LagerstandortController(LagerstandortRepository lagerstandortRepository, UserRepository userRepository) {
        super();
        this.lagerstandortRepository = lagerstandortRepository;
        this.userRepository = userRepository;
    }

    // TODO SMELL: In an object oriented language you should not need any structuring-comments.
    //             Maybe a service is missing between the controller and the repositories?
    // ************************************* Modelattribute ***************************************

    @ModelAttribute("alleBenutzer")
    public List<Benutzer> alleBenutzer() {
        return userRepository.findAll();
    }

//  ****************************************   Lagerstandorten List +  Hinzufügen *****************************************

    @RequestMapping(value = "/lagerstandort", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPERVISOR')")
    public String list(Model model) {
        model.addAttribute("lagerstandort", new Lagerstandort());

        List<Lagerstandort> lagerstandorten = lagerstandortRepository.findAllByOrderByIdAsc();
        if (lagerstandorten != null) {
            model.addAttribute("lagerstandorten", lagerstandorten);
        }
        return "lagerstandort";
    }

    @RequestMapping(value = "/lagerstandort", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPERVISOR')")
    public String addSpeichern(Model model, @ModelAttribute("lagerstandort") @Valid Lagerstandort lagerstandort,
            BindingResult result) {

        Lagerstandort existing = lagerstandortRepository.findByName(lagerstandort.getName());
        if (existing != null) {
            result.rejectValue("name", null, "Es ist bereits ein Lagerstandort mit gleichem Namen eingetragen");
        }
        if (result.hasErrors()) {

            List<Lagerstandort> lagerstandorten = lagerstandortRepository.findAllByOrderByIdAsc();
            if (lagerstandorten != null) {
                model.addAttribute("lagerstandorten", lagerstandorten);
            }
            return "lagerstandort";
        } else {
            lagerstandortRepository.save(lagerstandort);
            return "redirect:/lagerstandort?success";
        }
    }

    // ************************************* Lagerstandort Ändern  ************************************

    @RequestMapping(value = "/lagerstandortupdate/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPERVISOR')")
    public String aendernForm(@PathVariable("id") long id, Model model) {
        Lagerstandort exicting = lagerstandortRepository.findById(id);
        model.addAttribute("lagerstandort", exicting);
        return "lagerstandortupdate";
    }

    @RequestMapping(value = "/lagerstandortupdate/{id}", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPERVISOR')")
    public String aendernSpeichern(@PathVariable("id") long id,
            @ModelAttribute("lagerstandort") @Valid Lagerstandort lagerstandort, BindingResult result) {

        Lagerstandort existing = lagerstandortRepository.findById(id);
        Lagerstandort andere = lagerstandortRepository.findByName(lagerstandort.getName());

        if (lagerstandort.getName().equals(existing.getName())) {
            existing.setName(lagerstandort.getName());
            existing.setAdresse(lagerstandort.getAdresse());
            existing.setBeschreibung(lagerstandort.getBeschreibung());
            existing.setBenutzer(lagerstandort.getBenutzer());

            if (result.hasErrors()) {
                return "lagerstandortupdate";
            }

            lagerstandortRepository.save(existing);
            return "redirect:/lagerstandort?success";
        }
        if (andere != null) {
            // TODO SMELL: A non-null parameter is called with 'null'. Does this work?
            result.rejectValue("name", null, "Es ist bereits ein Lagerstandort mit gleichem Namen eingetragen");
        }
        if (result.hasErrors()) {
            return "lagerstandortupdate";
        }

        existing.setName(lagerstandort.getName());
        existing.setAdresse(lagerstandort.getAdresse());
        existing.setBeschreibung(lagerstandort.getBeschreibung());
        existing.setBenutzer(lagerstandort.getBenutzer());

        lagerstandortRepository.save(existing);
        return "redirect:/lagerstandort?success";
    }
}