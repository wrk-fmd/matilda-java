package at.wrk.fmd.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.wrk.fmd.repository.IPDFRepository;

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
