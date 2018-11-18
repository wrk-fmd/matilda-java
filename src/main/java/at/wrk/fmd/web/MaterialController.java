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
import at.wrk.fmd.model.Buchung;
import at.wrk.fmd.model.Lagerstandort;
import at.wrk.fmd.model.Lieferung;
import at.wrk.fmd.model.Material;
import at.wrk.fmd.model.Materialtyp;
import at.wrk.fmd.model.Veranstaltung;
import at.wrk.fmd.repository.BuchungRepository;
import at.wrk.fmd.repository.LagerstandortRepository;
import at.wrk.fmd.repository.LieferungRepository;
import at.wrk.fmd.repository.MaterialRepository;
import at.wrk.fmd.repository.MaterialtypRepository;
import at.wrk.fmd.repository.VeranstaltungRepository;

@Controller
public class MaterialController {
    private MaterialRepository materialRepository;
    private MaterialtypRepository materialtypRepository;
    private LagerstandortRepository lagerstandortRepository;
    private LieferungRepository lieferungRepository;
    private BuchungRepository buchungRepository;
    private VeranstaltungRepository veranstaltungRepository;

    // beide werden für hinzufügen verwendet
    private Lagerstandort aktLagerstandort;
    private long aktLagerId;
    // beide werden für lieferschein verwendet
    private Material aktMaterial;
    private long aktMaterialId;

    @Autowired
    public MaterialController(MaterialRepository materialRepository, MaterialtypRepository materialtypRepository,
            LagerstandortRepository lagerstandortRepository, LieferungRepository lieferungRepository,
            BuchungRepository buchungRepository, VeranstaltungRepository veranstaltungRepository) {
        super();
        this.materialRepository = materialRepository;
        this.materialtypRepository = materialtypRepository;
        this.lagerstandortRepository = lagerstandortRepository;
        this.lieferungRepository = lieferungRepository;
        this.buchungRepository = buchungRepository;
        this.veranstaltungRepository = veranstaltungRepository;
    }

    // ************************************* Modelattribute
    // ***************************************

    @ModelAttribute("alleLagerstandorten")
    public List<Lagerstandort> alleLagerstandorten() {
        return lagerstandortRepository.findAll();

    }

    @ModelAttribute("alleMaterialtypen")
    public List<Materialtyp> alleMaterialtypen() {
        return materialtypRepository.findAll();
    }

    @ModelAttribute("alleVeranstaltungen")
    public List<Veranstaltung> alleVeranstaltungen() {
        return veranstaltungRepository.findAll();
    }

    // ************************************* Lagerstandort List
    // ************************************

    @RequestMapping(value = "/material", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPERVISOR')")
    public String list(Model model) {
        List<Lagerstandort> lagerstandorten = lagerstandortRepository.findAll();
        if (lagerstandorten != null) {
            model.addAttribute("lagerstandorten", lagerstandorten);
        }
        return "material";
    }

    // ************************************* Materialverwaltung List + Hinzufügen
    // ************************************

    @RequestMapping(value = "/materialverwaltung/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPERVISOR')")
    public String materialVerwaltungForm(@PathVariable("id") long id, Model model) {

        Lagerstandort lagerstandort = lagerstandortRepository.findById(id);
        List<Material> materialien = materialRepository.findByLagerstandort(lagerstandort);
        aktLagerstandort = lagerstandort;
        aktLagerId = lagerstandort.getId();

        model.addAttribute("material", new Material());

        if (materialien != null) {
            model.addAttribute("materialien", materialien);
        } else {
            return "materialverwaltung?nomaterial";
        }

        return "materialverwaltung";
    }

    @RequestMapping(value = "/material", method = RequestMethod.POST)
    public String addSpeichern(@ModelAttribute Material material) {

        material.setLagerstandort(aktLagerstandort);
        if (material.getBestand() < 1) {
            return "redirect:/materialverwaltung/" + aktLagerId + "?nobestand";
        } else {
            materialRepository.save(material);
            return "redirect:/materialverwaltung/" + aktLagerId;
        }
    }

    // ************************************* Lieferschein - Lieferung
    // ************************************

    @RequestMapping(value = "/lieferung/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPERVISOR')")
    public String addNtransaktionForm(@PathVariable("id") long id, Model model) {

        aktMaterial = materialRepository.findById(id);
        aktMaterialId = aktMaterial.getId();

        model.addAttribute("lieferung", new Lieferung(aktMaterial));

        return "lieferung";
    }

    @RequestMapping(value = "/lieferung/{id}", method = RequestMethod.POST)
    public String ntransaktionSpeichern(@PathVariable("id") long id,
            @ModelAttribute("lieferung") @Valid Lieferung lieferung, BindingResult result) {

        lieferung.setMaterial(aktMaterial);

        if (lieferung.getLieferungsDatum() == null) {
            result.rejectValue("lieferungsDatum", null, "Bitte wählen Sie ein Lieferungsdatum aus!");
        }
        if (result.hasErrors()) {
            return "lieferung";
        }
        if (lieferung.getArt().equals("eingabe")) {
            int x = aktMaterial.getBestand();
            x = x + lieferung.getMenge();
            aktMaterial.setBestand(x);
            materialRepository.save(aktMaterial);

            lieferungRepository.save(lieferung);

            return "redirect:/materialverwaltung/" + aktLagerId;
        }

        int x = aktMaterial.getBestand();
        x = x - lieferung.getMenge();
        if (x < 0) {
            result.rejectValue("menge", null, "Nicht genug Material im Lager!");
        }
        if (result.hasErrors()) {
            return "lieferung";
        }
        aktMaterial.setBestand(x);
        materialRepository.save(aktMaterial);

        lieferungRepository.save(lieferung);

        return "redirect:/materialverwaltung/" + aktLagerId;
    }

    // ************************************* Buchungsschein - Buchung
    // ************************************

    @RequestMapping(value = "/buchung/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPERVISOR')")
    public String addBuchungForm(@PathVariable("id") long id, Model model) {

        aktMaterial = materialRepository.findById(id);
        aktMaterialId = aktMaterial.getId();

        model.addAttribute("buchung", new Buchung(aktMaterial));

        return "buchung";
    }

    @RequestMapping(value = "/buchung/{id}", method = RequestMethod.POST)
    public String btransaktionSpeichern(@PathVariable("id") long id, @ModelAttribute("buchung") @Valid Buchung buchung,
            BindingResult result) {

        buchung.setMaterial(aktMaterial);

        if (result.hasErrors()) {
            return "buchung";
        }
        if (buchung.getArt().equals("stornieren")) {
            int x = aktMaterial.getBestand();
            x = x + buchung.getMenge();
            aktMaterial.setBestand(x);
            materialRepository.save(aktMaterial);

            buchungRepository.save(buchung);

            return "redirect:/materialverwaltung/" + aktLagerId;
        }

        int x = aktMaterial.getBestand();
        x = x - buchung.getMenge();
        if (x < 0) {
            result.rejectValue("menge", null, "Nicht genug Material im Lager!");
        }
        if (result.hasErrors()) {
            return "buchung";
        }
        aktMaterial.setBestand(x);
        materialRepository.save(aktMaterial);

        buchungRepository.save(buchung);

        return "redirect:/materialverwaltung/" + aktLagerId;

    }

    // ************************************* Inventur
    // ************************************

    @RequestMapping(value = "/inventur/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPERVISOR')")
    public String aendernForm(@PathVariable("id") long id, Model model) {

        Material exicting = materialRepository.findById(id);
        aktLagerId = exicting.getLagerstandort().getId();

        model.addAttribute("material", exicting);

        return "inventur";
    }

    @RequestMapping(value = "/inventur/{id}", method = RequestMethod.POST)
    public String aendernSpeichern(@PathVariable("id") long id, @ModelAttribute("material") @Valid Material material,
            BindingResult result) {

        Material exicting = materialRepository.findById(id);

        if (material.getBestand() < 0) {
            result.rejectValue("bestand", null, "Bestand kann nicht ein negatives Wert haben!");
        }
        if (result.hasErrors()) {
            return "inventur";
        }

        exicting.setBestand(material.getBestand());
        exicting.setSeriennummer(material.getSeriennummer());
        exicting.setEinkaufsdatum(material.getEinkaufsdatum());
        exicting.setLetztesudatum(material.getLetztesudatum());
        exicting.setNaechstesudatum(material.getNaechstesudatum());
        exicting.setEinsatzbereitschaft(material.isEinsatzbereitschaft());

        materialRepository.save(exicting);

        return "redirect:/materialverwaltung/" + aktLagerId;
    }
}