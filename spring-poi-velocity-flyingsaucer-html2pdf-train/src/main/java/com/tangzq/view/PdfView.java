package com.tangzq.view;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.tangzq.model.Book;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * PDF视图
 */
public class PdfView extends AbstractPdfView{
    @Override
    protected void buildPdfDocument(Map<String, Object> map, com.lowagie.text.Document document, com.lowagie.text.pdf.PdfWriter pdfWriter, HttpServletRequest request, HttpServletResponse response) throws Exception {


        //设置下载时客户端PDF的名称
        String filename =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+".pdf";
        //response.setContentType("application/pdf");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-disposition", "attachment;filename=" + filename);
        OutputStream ouputStream = response.getOutputStream();
        PdfWriter.getInstance(document,ouputStream );

        List<Book> bookList = (List<Book>) map.get("bookList");
        //打开文档
        document.open();
        Rectangle rectPageSize = new Rectangle(PageSize.A4);
        document.setPageSize(rectPageSize);
        document.setMargins(50,50,50,50);
        Font font=new Font(36,Font.BOLD);
        Paragraph header = new Paragraph(new Chunk("I am PDF header",font));
        document.add(header);

        Table table = new Table(5);
        table.addCell("Book Title");
        table.addCell("Author");
        table.addCell("ISBN");
        table.addCell("Published Date");
        table.addCell("Price");

        for(Book book:bookList){
            table.addCell(book.getTitle());
            table.addCell(book.getAuthor());
            table.addCell(book.getIsbn());
            table.addCell(book.getPublishedDate());
            table.addCell(book.getPrice()+"");
        }
        document.add(table);
        //关闭文档
        document.close();

        //输出到客户端
        ouputStream.flush();
        ouputStream.close();
    }
}
