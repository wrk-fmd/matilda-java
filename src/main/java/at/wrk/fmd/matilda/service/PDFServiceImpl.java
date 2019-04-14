package at.wrk.fmd.matilda.service;

import java.util.Date;
import java.util.List;

import at.wrk.fmd.matilda.repository.IPDFRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// TODO SMELL: This class has no interface, so it should not need the (ugly) 'Impl' suffix, and also no named wiring.
@Service("pdfServiceImpl")
public class PDFServiceImpl {

    @Autowired
    private IPDFRepository pdfRepository;
    
    public List<?> findByDate(Date dateBeginn, Date dateEnde) {
        return pdfRepository.findByDate(dateBeginn, dateEnde);
    }
    
    public List<?> findByAusgabeschein(Date dateBeginn, Date dateEnde) {
        return pdfRepository.findByAusgabeschein(dateBeginn, dateEnde);
    }
    
    public List<?> findByPackliste(Date dateBeginn, Date dateEnde) {
        return pdfRepository.findByPackliste(dateBeginn, dateEnde);
    }
    
}
