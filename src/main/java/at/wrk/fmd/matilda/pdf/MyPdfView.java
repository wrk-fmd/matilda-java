package at.wrk.fmd.matilda.pdf;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class MyPdfView extends AbstractPdfView {

    // TODO SMELL: fields should be always private, or at most protected. Since there are no subclasses, private should be fine.
    boolean counter = true;
    String temp;

    @Override
    protected void buildPdfDocument(Map<String, Object> model, com.itextpdf.text.Document document, PdfWriter writer,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception {
        // TODO SMELL: Please reduce 'magic'. Here are strings parsed for startsWith, and then retrieved from an untyped model object.
        //             If it is really needed to work in this untyped way here, create methods with describing names of the steps.
        Set<String> set = model.keySet()
                .stream()
                .filter(s -> s.startsWith("Pro"))
                .collect(Collectors.toSet());

        String whichReportToCreate = set.stream().findFirst().get();
        List<Object[]> rows = (List<Object[]>) model.get(set.stream().findFirst().get());

        selectPDFTable(rows, document, writer, whichReportToCreate);
    }

    private void selectPDFTable(List<Object[]> rows, Document document, PdfWriter writer, String whichReportToCreate) throws DocumentException {
        switch (whichReportToCreate) {
            case "ProStandort":
                createStandort(rows, document);
                break;
            case "ProVeranstaltungAusgabeschein":
                createProVeranstaltungAusgabeschein(rows, document);
                createSignature(writer, document);
                break;
            case "ProVeranstaltungPackliste":
                createProVeranstaltungPackliste(rows, document);
                break;
        }
    }

    private void createProVeranstaltungPackliste(List<Object[]> rows, Document document) throws DocumentException {
        if (rows.isEmpty()) {
            emtpyPage(document);
        } else {
            for (Object[] row : rows) {

                String veranstaltung = (String) row[0];
                String lagerstandort = (String) row[1];
                String stueck = (String) row[2];

                if (counter == true) {
                    temp = veranstaltung;
                    counter = false;
                    Paragraph preface = new Paragraph();
                    preface.setAlignment(Element.ALIGN_CENTER);
                    preface.add(veranstaltung);
                    document.add(preface);
                    document.add(new Paragraph(lagerstandort + " " + stueck));
                } else if (veranstaltung.equals(temp)) {
                    document.add(new Paragraph(lagerstandort + " " + stueck));
                } else {
                    temp = veranstaltung;
                    document.newPage();
                    Paragraph preface = new Paragraph();
                    preface.setAlignment(Element.ALIGN_CENTER);
                    preface.add(veranstaltung);
                    document.add(preface);
                    document.add(new Paragraph(lagerstandort + " " + stueck));
                }
            }
        }
    }

    private void emtpyPage(Document document) throws DocumentException {
        Paragraph preface = new Paragraph();
        preface.setAlignment(Element.ALIGN_CENTER);
        preface.add("I am soryy, there is nothing to show for you");
        document.add(preface);
    }

    private void createProVeranstaltungAusgabeschein(List<Object[]> rows, Document document) throws DocumentException {
        if (rows.isEmpty()) {
            emtpyPage(document);
        } else {
            for (Object[] row : rows) {

                String veranstaltung = (String) row[0];
                String stueck = (String) row[1];
                String material = (String) row[2];
                String einheit = (String) row[3];

                if (counter == true) {
                    temp = veranstaltung;
                    counter = false;
                    Paragraph preface = new Paragraph();
                    preface.setAlignment(Element.ALIGN_CENTER);
                    preface.add(veranstaltung);
                    document.add(preface);
                    document.add(new Paragraph(einheit));
                    document.add(new Paragraph(material + " " + stueck));
                } else if (veranstaltung.equals(temp)) {
                    document.add(new Paragraph(material + " " + stueck));
                } else {
                    temp = veranstaltung;
                    document.newPage();
                    Paragraph preface = new Paragraph();
                    preface.setAlignment(Element.ALIGN_CENTER);
                    preface.add(veranstaltung);
                    document.add(preface);
                    document.add(new Paragraph(einheit));
                    document.add(new Paragraph(material + " " + stueck));
                }
            }
        }
    }

    private void createStandort(List<Object[]> rows, Document document) throws DocumentException {
        if (rows.isEmpty()) {
            emtpyPage(document);
        } else {
            for (Object[] row : rows) {

                String veranstaltung = (String) row[0];
                String lagerstandort = (String) row[1];
                String stueck = (String) row[2];

                if (counter == true) {
                    temp = veranstaltung;
                    counter = false;
                    Paragraph preface = new Paragraph();
                    preface.setAlignment(Element.ALIGN_CENTER);
                    preface.add(veranstaltung);
                    document.add(preface);
                    document.add(new Paragraph(lagerstandort + " " + stueck));
                } else if (veranstaltung.equals(temp)) {
                    document.add(new Paragraph(lagerstandort + " " + stueck));
                } else {
                    temp = veranstaltung;
                    document.newPage();
                    Paragraph preface = new Paragraph();
                    preface.setAlignment(Element.ALIGN_CENTER);
                    preface.add(veranstaltung);
                    document.add(preface);
                    document.add(new Paragraph(lagerstandort + " " + stueck));
                }
            }
        }
    }

    private void createSignature(PdfWriter writer, Document document) throws DocumentException {
        Paragraph p = new Paragraph("Signature: ");
        p.add(new Chunk(new DottedLineSeparator()));
        document.add(p);
    }
}