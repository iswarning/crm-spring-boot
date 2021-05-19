package com.example.demo.export;

import com.example.demo.entities.Customer;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CustomerExcelExport {

    private final XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private final List<Customer> customerList;

    public CustomerExcelExport(List<Customer> customerList){
        this.customerList = customerList;
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine(){
        sheet = workbook.createSheet("Customers");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        row.createCell(0).setCellValue("ID");
        row.createCell(1).setCellValue("Name");
        row.createCell(2).setCellValue("Cmnd");
        row.createCell(3).setCellValue("Birthday");
        row.createCell(4).setCellValue("Address");
        row.createCell(5).setCellValue("Household");
        row.createCell(6).setCellValue("Phone");
    }

    private void writeDataLines(){
        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for(Customer c : customerList){

            Row row = sheet.createRow(rowCount++);

            row.createCell(0).setCellValue(c.getId());
            row.createCell(1).setCellValue(c.getName());
            row.createCell(2).setCellValue(c.getCmnd());
            row.createCell(3).setCellValue(c.getBirthday().toString());
            row.createCell(4).setCellValue(c.getAddress());
            row.createCell(5).setCellValue(c.getHousehold());
            row.createCell(6).setCellValue(c.getPhone());
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
