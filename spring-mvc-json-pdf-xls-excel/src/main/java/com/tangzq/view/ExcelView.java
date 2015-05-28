package com.tangzq.view;

import com.tangzq.vo.Book;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.LocalizedResourceHelper;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Excel视图类，继承POI的AbstractExcelView类
 */
public class ExcelView  extends AbstractExcelView {

    private static final String CONTENT_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    private static final String EXTENSION = ".xlsx";

    public ExcelView() {
        setContentType(CONTENT_TYPE);
    }

    @Override
    protected HSSFWorkbook getTemplateSource(String url, HttpServletRequest request) throws Exception {
        LocalizedResourceHelper helper = new LocalizedResourceHelper(getApplicationContext());
        Locale userLocale = RequestContextUtils.getLocale(request);
        Resource inputFile = helper.findLocalizedResource(url, EXTENSION, userLocale);
        // Create the Excel document from the source.
        if (logger.isDebugEnabled()) {
            logger.debug("Loading Excel workbook from " + inputFile);
        }
        return new HSSFWorkbook(inputFile.getInputStream());
    }

    @Override
    protected void buildExcelDocument(Map<String, Object> map, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Book> bookList = (List<Book>) map.get("booksList");

        //创建一张新的sheet表
        HSSFSheet sheet = workbook.createSheet("Java Books");
        sheet.setDefaultColumnWidth(30);

        //创建标题单元格样式
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE.index);
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);

        //创建标题行
        HSSFRow header = sheet.createRow(0);

        header.createCell(0).setCellValue("Book Title");
        header.getCell(0).setCellStyle(style);

        header.createCell(1).setCellValue("Author");
        header.getCell(1).setCellStyle(style);

        header.createCell(2).setCellValue("ISBN");
        header.getCell(2).setCellStyle(style);

        header.createCell(3).setCellValue("Published Date");
        header.getCell(3).setCellStyle(style);

        header.createCell(4).setCellValue("Price");
        header.getCell(4).setCellStyle(style);

        //创建数据单元格
        int rowCount = 1;

        for (Book aBook : bookList) {
            HSSFRow aRow = sheet.createRow(rowCount++);
            short rowHeight=500;
            aRow.setHeight(rowHeight);
            aRow.createCell(0).setCellValue(aBook.getTitle());
            aRow.createCell(1).setCellValue(aBook.getAuthor());
            aRow.createCell(2).setCellValue(aBook.getIsbn());
            aRow.createCell(3).setCellValue(aBook.getPublishedDate());
            aRow.createCell(4).setCellValue(aBook.getPrice());
        }
    }
}
