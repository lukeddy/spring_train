package com.tangzq.view;

import com.tangzq.vo.Book;
import com.lowagie.text.*;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * PDF视图
 */
public class PDFView extends AbstractPdfView{

    public PDFView() {
        setContentType("application/pdf");
    }

    @Override
    protected boolean generatesDownloadContent() {
        return true;
    }

    @Override
    protected void buildPdfDocument(Map<String, Object> map, com.lowagie.text.Document document, com.lowagie.text.pdf.PdfWriter pdfWriter, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        List<Book> bookList = (List<Book>) map.get("booksList");
        //打开文档
        Rectangle rectPageSize = new Rectangle(PageSize.A4);
        document.setPageSize(rectPageSize);
        document.setMargins(50,50,50,50);
        Font font=new Font(36,Font.BOLD);
        Paragraph header = new Paragraph(new Chunk("I am PDF header",font));
        document.add(header);

        Table table = new Table(5);
        table.setAlignment(Element.ALIGN_CENTER);
        //table.setPadding((float)60);
        //table.setCellsFitPage(true);
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
    }
}
