package skamila.kapj.service;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import skamila.kapj.domain.Pesel;
import skamila.kapj.domain.Visit;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.time.format.DateTimeFormatter;

@Service
public class PdfServiceImpl implements PdfService {

    @Override
    public void generatePdf(Visit visit, HttpServletResponse response) {
        try {
            OutputStream o = response.getOutputStream();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=" + visit.getId() + ".pdf");
            Document pdf = new Document();
            PdfWriter.getInstance(pdf, o);
            pdf.open();
            pdf.add(new Paragraph("Rachunek"));
            pdf.add(new Paragraph(Chunk.NEWLINE));
            pdf.add(new Paragraph(Chunk.NEWLINE));

            pdf.add(new Paragraph("Dane pacjenta"));
            pdf.add(new Paragraph(Chunk.NEWLINE));
            pdf.add(tableWithPatientData(visit));

            pdf.add(new Paragraph("Szczegóły wizyty"));
            pdf.add(new Paragraph(Chunk.NEWLINE));
            pdf.add(tableWithVisitData(visit));

            pdf.close();
            o.close();
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
    }

    private PdfPTable tableWithPatientData(Visit visit) {
        PdfPTable table = new PdfPTable(2);
        table.addCell("Imie");
        table.addCell(visit.getPatient().getFirstName());
        table.addCell("Nazwisko");
        table.addCell(visit.getPatient().getLastName());
        table.addCell("PESEL");
        Pesel pesel = visit.getPatient().getPesel();
        if (pesel != null) {
            table.addCell(pesel.getPESEL());
        } else {
            table.addCell("");
        }
        return table;
    }

    private PdfPTable tableWithVisitData(Visit visit) {
        PdfPTable table = new PdfPTable(2);
        table.addCell("Lekarz");
        table.addCell(visit.getDoctor().getFirstName() + " " + visit.getDoctor().getLastName());

        table.addCell("Data");
        table.addCell(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(visit.getTime()));
        table.addCell("Godzina");
        table.addCell(DateTimeFormatter.ofPattern("HH:mm").format(visit.getTime()));
        table.addCell("Cena:");
        table.addCell(visit.getPrice() + "PLN");
        return table;
    }




}