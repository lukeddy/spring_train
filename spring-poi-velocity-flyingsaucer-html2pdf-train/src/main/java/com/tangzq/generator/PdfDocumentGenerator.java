package com.tangzq.generator;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.tangzq.factory.ITextRendererObjectFactory;

/**
 * pdf 生成
 *
 * @ClassName: PdfGenerator
 * @Description:pdf 生成
 * @author lihengjun 修改时间： 2013年11月5日 下午3:27:22 修改内容：新建
 */
public class PdfDocumentGenerator {
	private final static Logger logger = Logger.getLogger(PdfDocumentGenerator.class);

	/**
	 * Output a pdf to the specified outputstream
	 *
	 * @param htmlContent
	 *            the htmlstr
	 * @param outputFile
	 *            the specified outputpath
	 * @throws Exception
	 */
	public void generate(String htmlContent, String outputFile)
			throws Exception {
		OutputStream out = null;
		ITextRenderer iTextRenderer = null;

		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.parse(new ByteArrayInputStream(htmlContent.getBytes("UTF-8")));
			File f = new File(outputFile);
			if (f != null &&f.getParentFile()!=null&& !f.getParentFile().exists()) {
				f.getParentFile().mkdir();
			}
			out = new FileOutputStream(outputFile);
			iTextRenderer = (ITextRenderer) ITextRendererObjectFactory.getObjectPool().borrowObject();//获取对象池中对象
			try {
				iTextRenderer.setDocument(doc, null);
				iTextRenderer.layout();
				iTextRenderer.createPDF(out);
			} catch (Exception e) {
				ITextRendererObjectFactory.getObjectPool().invalidateObject(
						iTextRenderer);
				iTextRenderer = null;
				throw e;
			}

		} catch (Exception e) {
			throw e;
		} finally {
			if (out != null)
				out.close();

			if (iTextRenderer != null) {
				try {
					ITextRendererObjectFactory.getObjectPool().returnObject(
							iTextRenderer);
				} catch (Exception ex) {
					logger.error("Cannot return object from pool.", ex);
				}
			}
		}
	}

	/**
	 * 以流的形式输出pdf
	 * @param htmlContent
	 * @param outputStream
	 * @throws Exception
	 */
	public void generate(String htmlContent,OutputStream outputStream)
			throws Exception {
		ITextRenderer iTextRenderer = null;

		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.parse(new ByteArrayInputStream(htmlContent.getBytes("UTF-8")));
			iTextRenderer = (ITextRenderer) ITextRendererObjectFactory.getObjectPool().borrowObject();//获取对象池中对象
			try {
				iTextRenderer.setDocument(doc, null);
				iTextRenderer.layout();
				iTextRenderer.createPDF(outputStream);
			} catch (Exception e) {
				ITextRendererObjectFactory.getObjectPool().invalidateObject(
						iTextRenderer);
				iTextRenderer = null;
				throw e;
			}

		} catch (Exception e) {
			throw e;
		} finally {
			if (iTextRenderer != null) {
				try {
					ITextRendererObjectFactory.getObjectPool().returnObject(
							iTextRenderer);
				} catch (Exception ex) {
					logger.error("Cannot return object from pool.", ex);
				}
			}
		}
	}

}