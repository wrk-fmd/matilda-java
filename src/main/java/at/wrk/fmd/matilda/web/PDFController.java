package at.wrk.fmd.matilda.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import at.wrk.fmd.matilda.pdf.MyPdfView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import at.wrk.fmd.matilda.service.PDFServiceImpl;

@Controller
public class PDFController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    PDFServiceImpl pdfServiceImpl;

    @RequestMapping(path = "/pdf", method = RequestMethod.POST)
    public ModelAndView createReport(
            @RequestParam("datePickerBeginn") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateBeginn,
            @RequestParam("datePickerEnde") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateEnde,
            @RequestParam("pageFormat") String pageFormat,
            @RequestParam("whichReportToPrint") String whichReport) {

        logger.info("Method {} called in {}", new Object() {}.getClass().getEnclosingMethod().getName(), this.getClass().getName());


        Map<String, Object> model = new HashMap<>();

        switch(whichReport) {
            case "ProStandort":
                model.put("ProStandort", pdfServiceImpl.findByDate(dateBeginn, dateEnde));
                break;
            case "ProVeranstaltungAusgabeschein":
                model.put("ProVeranstaltungAusgabeschein", pdfServiceImpl.findByAusgabeschein(dateBeginn, dateEnde));
                break;
            case "ProVeranstaltungPackliste":
                model.put("ProVeranstaltungPackliste", pdfServiceImpl.findByPackliste(dateBeginn, dateEnde));
                break;
        }
        model.put("pdfDocument", pageFormat);
        return new ModelAndView(new MyPdfView(), model);
    }

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public String report() {
        logger.info("Method {} called in {}", new Object() {}.getClass().getEnclosingMethod().getName(), this.getClass().getName());
        return "report";
    }
}