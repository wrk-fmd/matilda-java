package at.wrk.fmd.pdf;

import static org.mockito.Mockito.mock;

import java.io.IOException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

public class ParsePDF {
    public static final String SRC = "./src/test/resources/pdf/parseCustom.pdf";
    public static final String EXPECTED_SIG_TEXT = "Signature: ";
    public static final String EXPECTED_TOOL_TEXT = "Tool(s): ";
    public static final String EXPECTED_LOCATION_TEXT = "Location: ";
    public static String RESULTANT_SIG_TEXT;
    public static String RESULTANT_TOOL_TEXT = "Tools";
    public static String RESULTANT_LOCATION_TEXT = "Location: ";
    public static final int EXPECTED_NUM_PAGE = 1;
  
    @BeforeClass
    public static void beforeClass() throws IOException {
    }
 
    @Test
    public void shouldReturnSignature() throws IOException {
        PdfReader reader = new PdfReader(SRC);
        PdfReaderContentParser parser = new PdfReaderContentParser(reader);
        TextExtractionStrategy strategy;
        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
            strategy = parser.processContent(i, new SimpleTextExtractionStrategy());
            RESULTANT_SIG_TEXT = strategy.getResultantText();
        }
        reader.close();
 
        Assert.assertEquals(EXPECTED_SIG_TEXT, RESULTANT_SIG_TEXT);
    }
    
    @Test
    public void shouldReturnPageCount() throws IOException {
        PdfReader reader = new PdfReader(SRC);
        PdfReaderContentParser parser = new PdfReaderContentParser(reader);
        TextExtractionStrategy strategy;
        reader.close();
 
        Assert.assertEquals(EXPECTED_NUM_PAGE, reader.getNumberOfPages());
    }
    
    @Test
    public void shouldReturnTool() throws IOException {
        PdfReader reader = new PdfReader(SRC);
        PdfReaderContentParser parser = new PdfReaderContentParser(reader);
        TextExtractionStrategy strategy;
        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
            strategy = parser.processContent(i, new SimpleTextExtractionStrategy());
            RESULTANT_TOOL_TEXT = strategy.getResultantText();
        }
        reader.close();
 
        Assert.assertNotEquals(EXPECTED_TOOL_TEXT, RESULTANT_TOOL_TEXT);
    }
    
    @Test
    public void shouldReturnLocation() throws IOException {
        PdfReader reader = new PdfReader(SRC);
        PdfReaderContentParser parser = new PdfReaderContentParser(reader);
        TextExtractionStrategy strategy;
        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
            strategy = parser.processContent(i, new SimpleTextExtractionStrategy());
            RESULTANT_LOCATION_TEXT = strategy.getResultantText();
        }
        reader.close();
 
        Assert.assertNotEquals(EXPECTED_LOCATION_TEXT, RESULTANT_LOCATION_TEXT);
    }
}