package at.wrk.fmd.web;

import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import at.wrk.fmd.model.Materialtyp;
import at.wrk.fmd.repository.MaterialtypRepository;

@Controller
public class MaterialtypController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    private MaterialtypRepository materialtypRepository;

    @Autowired
    private MaterialtypController(MaterialtypRepository materialtypRepository) {
        this.materialtypRepository = materialtypRepository;
    }

    // ************************************* MaterialtypenList + Hinzufügen ************************

    @RequestMapping(value = "/materialtyp", method = RequestMethod.GET)
    public String list(Model model) {
        logger.info("Method {} called in {}", new Object() {}.getClass().getEnclosingMethod().getName(), this.getClass().getName());
        
        model.addAttribute("materialtyp", new Materialtyp());

        List<Materialtyp> materialtypen = materialtypRepository.findAllByOrderByIdAsc();
        if (materialtypen != null) {
            model.addAttribute("materialtypen", materialtypen);
        }
        return "materialtyp";
    }

    @RequestMapping(value = "/materialtyp", method = RequestMethod.POST)
    public String addSpeichern(Model model, @ModelAttribute("materialtyp") @Valid Materialtyp materialtyp,
            BindingResult result) {
        logger.info("Method {} called in {}", new Object() {}.getClass().getEnclosingMethod().getName(), this.getClass().getName());
        
        Materialtyp existing = materialtypRepository.findByName(materialtyp.getName());

        if (existing != null) {
            result.rejectValue("name", null, "Es ist bereits ein Materialtyp mit gleichem Namen eingetragen");
        }
        if (result.hasErrors()) {
            List<Materialtyp> materialtypen = materialtypRepository.findAllByOrderByIdAsc();
            if (materialtypen != null) {
                model.addAttribute("materialtypen", materialtypen);
            }
            return "materialtyp";
        }

        materialtypRepository.save(materialtyp);
        return "redirect:/materialtyp?success";
    }

    // ************************************* Materialtyp Ändern   ************************************

    @RequestMapping(value = "/materialtypupdate/{id}", method = RequestMethod.GET)
    public String aendernForm(@PathVariable("id") long id, Model model) {
        logger.info("Method {} called in {}", new Object() {}.getClass().getEnclosingMethod().getName(), this.getClass().getName());
        
        Materialtyp exicting = materialtypRepository.findById(id);
        model.addAttribute("materialtyp", exicting);
        return "materialtypupdate";
    }

    @RequestMapping(value = "/materialtypupdate/{id}", method = RequestMethod.POST)
    public String aendernSpeichern(@PathVariable("id") long id,
            @ModelAttribute("materialtyp") @Valid Materialtyp materialtyp, BindingResult result) {

        logger.info("Method {} called in {}", new Object() {}.getClass().getEnclosingMethod().getName(), this.getClass().getName());
        
        Materialtyp existing = materialtypRepository.findById(id);
        Materialtyp andere = materialtypRepository.findByName(materialtyp.getName());

        if (materialtyp.getName().equals(existing.getName())) {
            existing.setName(materialtyp.getName());
            existing.setMenge(materialtyp.getMenge());
            existing.setBeschreibung(materialtyp.getBeschreibung());
            existing.setLink(materialtyp.getLink());

            if (result.hasErrors()) {
                logger.error("Error in method {}, called in {}", new Object() {}.getClass().getEnclosingMethod().getName(), this.getClass().getName());
                return "materialtypupdate";
            }

            materialtypRepository.save(existing);
            return "redirect:/materialtyp?success";
        }
        if (andere != null) {
            result.rejectValue("name", null, "Es ist bereits ein Materialtyp mit gleichem Namen eingetragen");
        }
        if (result.hasErrors()) {
            return "materialtypupdate";
        }

        existing.setName(materialtyp.getName());
        existing.setMenge(materialtyp.getMenge());
        existing.setBeschreibung(materialtyp.getBeschreibung());
        existing.setLink(materialtyp.getLink());

        materialtypRepository.save(existing);
        return "redirect:/materialtyp?success";
    }
}
