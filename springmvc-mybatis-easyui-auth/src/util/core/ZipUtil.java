package util.core;

import org.apache.tools.zip.ZipEntry;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipOutputStream;

public class ZipUtil {
	public void zip(String inputFileName, String outputPath) throws Exception {
		String zipFileName = outputPath;
		System.out.println(zipFileName);
		zip(zipFileName, new File(inputFileName));
	}

	private void zip(String zipFileName, File inputFile) throws Exception {
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));
		zip(out, inputFile, "");
		System.out.println("zip done");
		out.close();
	}

	private void zip(ZipOutputStream out, File f, String base) throws Exception {
		if (f.isDirectory()) {
			File[] fl = f.listFiles();
			out.putNextEntry(new ZipEntry(base + "/"));
			base = base + "/";
			for (int i = 0; i < fl.length; i++)
				zip(out, fl[i], base + fl[i].getName());
		} else {
			out.putNextEntry(new ZipEntry(base));
			FileInputStream in = new FileInputStream(f);
			System.out.println(base);
			int b;
			while ((b = in.read()) != -1) {
				out.write(b);
			}
			in.close();
		}
	}

	public static void main(String[] temp) {
		ZipUtil book = new ZipUtil();
		try {
			book.zip("D:\\apache-tomcat-6.0.36\\webapps\\cms\\class\\", "d:\\zzz.zip");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}