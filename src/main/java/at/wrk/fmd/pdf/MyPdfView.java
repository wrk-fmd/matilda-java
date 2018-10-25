package at.wrk.fmd.pdf;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;

public class MyPdfView extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        List<Object[]> rows = (List<Object[]>) model.get("benutzer");

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(60);
        table.setWidths(new int[] { 1, 3 });

        Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

        PdfPCell hcell;
        hcell = new PdfPCell(new Phrase("Veranstaltung", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("Anzeigename", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

        hcell = new PdfPCell(new Phrase("Lagerstandort", headFont));
        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(hcell);

//        hcell = new PdfPCell(new Phrase("Standort", headFont));
//        hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        table.addCell(hcell);
        writePDFTable(rows, table, document);
        createSignature(writer, document);
    }

    private void createSignature(PdfWriter writer, Document document) throws DocumentException {
        Paragraph p = new Paragraph("Signature: ");
        p.add(new Chunk(new DottedLineSeparator()));
        document.add(p);
    }

    private void writePDFTable(List<Object[]> rows, PdfPTable table, Document document) throws DocumentException {
        for (Object[] row : rows) {
            PdfPCell cell;

            String veranstaltung = (String) row[0];
            String anzeigename = (String) row[1];
            String lagerstandort = (String) row[2];

            cell = new PdfPCell(new Phrase(veranstaltung));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(anzeigename));
            cell.setPaddingLeft(5);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(String.valueOf(lagerstandort)));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setPaddingRight(5);
            table.addCell(cell);
//            
//            cell = new PdfPCell(new Phrase(String.valueOf(row.getDienstnummer())));
//            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//            cell.setPaddingRight(5);
//            table.addCell(cell);
            document.add(table);
        }
    }
}