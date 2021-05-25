package com.example.demo.export;

import com.example.demo.entities.Customer;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class CustomerPdfExport {

    private final List<Customer> customerList;

    public CustomerPdfExport(List<Customer> customerList){
        this.customerList = customerList;
    }

    private void writeTableHeader(PdfPTable table){
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.GREEN);
        cell.setPadding(5);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Id", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Cmnd", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Birthday", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Address", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Household", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Phone", font));
        table.addCell(cell);

    }

    private void writeTableData(PdfPTable table){
        for(Customer c : customerList){
            table.addCell(String.valueOf(c.getId()));
            table.addCell(c.getName());
            table.addCell(String.valueOf(c.getCmnd()));
            table.addCell(String.valueOf(c.getBirthday()));
            table.addCell(c.getAddress());
            table.addCell(c.getHousehold());
            table.addCell(String.valueOf(c.getPhone()));
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document  document = new Document(PageSize.A3);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.GREEN);
        font.setSize(18);

        Paragraph paragraph = new Paragraph("List of Customers", font);
        paragraph.setAlignment(paragraph.ALIGN_CENTER);

        document.add(paragraph);

        PdfPTable table = new PdfPTable(7);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.0f, 4.0f, 3.0f, 3.0f, 4.0f, 4.0f, 3.0f});

        writeTableHeader(table);
        writeTableData(table);
        document.add(table);

        document.close();
    }
}
