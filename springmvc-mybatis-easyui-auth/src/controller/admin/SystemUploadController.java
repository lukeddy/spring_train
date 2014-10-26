package controller.admin;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import util.core.MethodUtil;

@Controller
public class SystemUploadController {
	public static MethodUtil util = new MethodUtil();

	@RequestMapping(value = "/admin/uploadIndex.html")
	public String index() {
		return "/admin/upload";
	}

	/**
	 * 上传文件
	 * 
	 * @return
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	@RequestMapping(value = "/admin/uploadify.html", method = RequestMethod.POST)
	@ResponseBody
	public String upload(HttpServletRequest request, HttpServletResponse response) {
		String responseStr = "";
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		// 获取前台传值
		// String[] userNames = multipartRequest.getParameterValues("userName");
		// String[] contents = multipartRequest.getParameterValues("content");
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		// 文件保存路径
		String ctxPath = request.getSession().getServletContext().getRealPath("/") + File.separator + "upload";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String ymd = sdf.format(new Date());
		ctxPath += File.separator + ymd + File.separator;
		// 创建文件夹
		File file = new File(ctxPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		String fileName = null;
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			// 上传文件
			MultipartFile mf = entity.getValue();
			fileName = mf.getOriginalFilename();
			String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
			// 重命名文件
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
			File uploadFile = new File(ctxPath + newFileName);
			try {
				System.out.println("upload:" + uploadFile);
				FileCopyUtils.copy(mf.getBytes(), uploadFile);
				responseStr = "上传成功";
			} catch (IOException e) {
				responseStr = "上传失败";
				e.printStackTrace();
			}
		}
		return responseStr;
	}

	/**
	 * 
	 * <br>
	 * <b>功能：</b>文件下载<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-5-22 <br>
	 * 
	 * @param response
	 * @param url
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@RequestMapping("/downloadDir.html")
	public void download(HttpServletResponse response, String url) throws FileNotFoundException, IOException {
		File file = new File(SystemUploadController.class.getResource("/").getPath());
		String rootPath = file.getParentFile().getParentFile().toURI().getPath();
		String path = rootPath + "download" + "/";
		System.out.println("path:" + path);
		String dirPath = null;
		if ((url == null) || (url.trim().length() == 0)) {
			dirPath = path;
		} else {
			System.out.println("url:" + url);
			try {
				dirPath = path + URLDecoder.decode(url, "utf-8");
			} catch (UnsupportedEncodingException e) {
				System.out.println("e:" + e.toString());
				e.printStackTrace();
				return;
			}
		}
		System.out.println("defaultPath:" + path);
		System.out.println("dirPath:" + dirPath);
		file = new File(dirPath);
		if (!file.exists()) {
			System.out.println("error");
			return;
		}
		boolean isFile = file.isFile();
		if (isFile) {
			System.out.println("isFile:" + file.getName());

			return;
		}
		boolean isDir = file.isDirectory();
		if (isDir) {
			File[] fileList = file.listFiles(new FileFilter() {
				public boolean accept(File pathname) {
					/*String fileNameLower = pathname.getName().toLowerCase();
					if (pathname.isHidden()) {
						return false;
					}
					if (fileNameLower.matches(".*(meta-inf|templates)$|.*.(gif|jpg|png|ico|class|.jar)$")) {
						return false;
					}*/
					return true;
				}
			});
			if (fileList.length > 0) {
				StringBuffer sb = new StringBuffer();
				sb.append("[");
				for (int i = 0; i < fileList.length; i++) {
					sb.append("{");
					String fileName = fileList[i].getName();
					String filePath = fileList[i].toURI().getPath();
					String fileSize = "";
					String virPath = filePath.substring(path.length(), filePath.length());
					if (fileList[i].isDirectory()) {
						fileName = fileName + "/";
					}
					if (fileList[i].isFile()) {
						FileInputStream fis = new FileInputStream(new File(filePath));
						fileSize = formetFileSize(fis.available());
						fis.close();
					}
					String parentDir = null;
					String topDirTemp = fileList[i].getParentFile().getParentFile().toURI().getPath();
					System.out.println("topDirTemp:" + topDirTemp);
					if (topDirTemp.length() > path.length()) {
						parentDir = topDirTemp.substring(path.length(), topDirTemp.length());
						System.out.println("parentDir:" + parentDir);
					}
					sb.append("\"name\":").append("\"" + fileName + "\",");
					sb.append("\"parentDir\":").append("\"" + parentDir + "\",");
					sb.append("\"size\":").append("\"" + fileSize + "\",");
					sb.append("\"time\":").append("\"" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(file.lastModified())) + "\",");
					sb.append("\"path\":").append("\"" + virPath + "\",");
					sb.append("\"isFile\":").append(fileList[i].isFile());
					sb.append("},");
				}
				sb = sb.delete(sb.length() - 1, sb.length());
				sb.append("]");
				util.toJsonPrint(response, sb.toString());
				System.out.println("Json:" + sb.toString());
			}
		}
	}

	/**
	 * 
	 * <br>
	 * <b>功能：</b>文件下载<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-5-22 <br>
	 * 
	 * @param url
	 * @param response
	 */
	@RequestMapping("/downloadFile.html")
	public void download(String url, HttpServletResponse response) {
		File file = new File(SystemUploadController.class.getResource("/").getPath());
		String rootPath = file.getParentFile().getParentFile().toURI().getPath();
		String path = rootPath + "download" + "/";
		String dirPath = null;
		try {
			System.out.println("下载url:" + url);
			try {
				dirPath = path + URLDecoder.decode(url, "utf-8");
				System.out.println("下载dirPath:" + dirPath);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			file = new File(dirPath);
			String filename = file.getName();
			System.out.println("filename:" + filename);
			InputStream fis = new BufferedInputStream(new FileInputStream(dirPath));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			response.reset();
			response.addHeader("Content-Disposition", "attachment;filename=" + filename);
			response.addHeader("Content-Length", "" + file.length());
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/octet-stream");
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void downloadLocal(HttpServletResponse response) throws FileNotFoundException {
		String fileName = "Operator.doc".toString();
		InputStream inStream = new FileInputStream("c:/Operator.doc");
		response.reset();
		response.setContentType("bin");
		response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		byte[] b = new byte[100];
		try {
			int len;
			while ((len = inStream.read(b)) > 0) {
				response.getOutputStream().write(b, 0, len);
			}
			inStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void downloadNet(HttpServletResponse response) throws MalformedURLException {
		int bytesum = 0;
		int byteread = 0;
		URL url = new URL("windine.blogdriver.com/logo.gif");
		try {
			URLConnection conn = url.openConnection();
			InputStream inStream = conn.getInputStream();
			FileOutputStream fs = new FileOutputStream("c:/abc.gif");
			byte[] buffer = new byte[1204];
			while ((byteread = inStream.read(buffer)) != -1) {
				bytesum += byteread;
				System.out.println(bytesum);
				fs.write(buffer, 0, byteread);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * <br>
	 * <b>功能：</b>文件大小<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-5-22 <br>
	 * 
	 * @param fileS
	 * @return
	 */
	public String formetFileSize(long fileS) {
		DecimalFormat df = new DecimalFormat("#.00");
		String fileSizeString = "";
		if (fileS < 1024L)
			fileSizeString = df.format(fileS) + "B";
		else if (fileS < 1048576L)
			fileSizeString = df.format(fileS / 1024.0D) + "K";
		else if (fileS < 1073741824L)
			fileSizeString = df.format(fileS / 1048576.0D) + "M";
		else {
			fileSizeString = df.format(fileS / 1073741824.0D) + "G";
		}
		return fileSizeString;
	}

	/**
	 * 
	 * <br>
	 * <b>功能：</b>在线预览<br>
	 * <b>作者：</b>肖财高<br>
	 * <b>日期：</b> 2013-5-22 <br>
	 * 
	 * @param filePath
	 * @param response
	 * @param isOnLine
	 * @throws Exception
	 */
	@RequestMapping({ "/openDownloadFile.html" })
	public void downLoad(String filePath, HttpServletResponse response, boolean isOnLine) throws Exception {
		File f = new File(filePath);
		if (!f.exists()) {
			response.sendError(404, "File not found!");
			return;
		}
		BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
		byte[] buf = new byte[1024];
		int len = 0;
		response.reset();
		if (isOnLine) {
			URL u = new URL("file:///" + filePath);
			response.setContentType(u.openConnection().getContentType());
			response.setHeader("Content-Disposition", "inline; filename=" + f.getName());
		} else {
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition", "attachment; filename=" + f.getName());
		}
		OutputStream out = response.getOutputStream();
		while ((len = br.read(buf)) > 0) {
			out.write(buf, 0, len);
		}
		br.close();
		out.close();
	}
}
