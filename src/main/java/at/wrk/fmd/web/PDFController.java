package at.wrk.fmd.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import at.wrk.fmd.pdf.MyPdfView;
import at.wrk.fmd.repository.IPDFRepository;

@Controller
public class PDFController {

    @Autowired
    private IPDFRepository pdfRepository;

    @RequestMapping(path = "/pdf", method = RequestMethod.POST)
    public ModelAndView report(
            @RequestParam("datePickerBeginn") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateBeginn,
            @RequestParam("datePickerEnde") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateEnde,
            @RequestParam("pageFormat") String pageFormat) {

        Map<String, Object> model = new HashMap<>();

        List<?> benutzer = pdfRepository.findByDate(dateBeginn, dateEnde);
        
        model.put("benutzer", benutzer);
        model.put("pdfDocument", pageFormat);

        return new ModelAndView(new MyPdfView(), model);
    }

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public String report() {
        return "report";
    }
}