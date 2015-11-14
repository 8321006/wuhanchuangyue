package com.cy.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.cy.common.util.DateUtil;
import com.cy.common.util.MD5;
import com.cy.common.util.UID;
//import com.cy.commons.ftp.client.FtpClinet;
import com.cy.exception.ServiceException;
import com.cy.model.MCFile;
import com.cy.service.MCFileService;

/**
 * Spring MVC Controler - 表：t_file
 * 
 * @since 2015-06-08 15:47:16
 */
@Controller
@RequestMapping(value = "/file")
public class MCFileAction {
	private static final Logger logger = Logger.getLogger(MCFileAction.class);

	@Autowired
	private MCFileService fileService;

	/**
	 * 列表数据
	 * 
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/file")
	@ResponseBody
	public ModelAndView listData() throws ServiceException {
		if (logger.isDebugEnabled()) {
			logger.debug("listData() - start");
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("file");
		try {
			List<MCFile> files = fileService.listAll();
			// resultMap.put("result", 1);
			mav.addObject("files", files);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("listData() - end");
		}
		return mav;
	}

	/**
	 * 新增页面
	 */
	@RequestMapping(value = "/fileadd", method = RequestMethod.GET)
	public Object addPage() {
		String fileName = "oa.txt";
		String localPath = "/usr/local/resource/upload/oa1.sql";
		java.io.File localFile = new java.io.File(localPath);
		// FtpClinet client = new FtpClinet();
		// try {
		// client.connect("192.168.1.122", 2121, "root", "111111q", false);
		// client.upload(fileName, localFile);
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// System.out.println(client);
		return "";
		//
		// if (logger.isDebugEnabled()) {
		// logger.debug("addPage() - start");
		// }
		//
		// if (logger.isDebugEnabled()) {
		// logger.debug("addPage() - end");
		// }
		// return "file_add";
	}

	/**
	 * 新增保存
	 * 
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/fileadd", method = RequestMethod.POST)
	@ResponseBody
	public Object doAdd(MultipartFile file, String desc,
			HttpServletRequest request) throws ServiceException {
		System.out.println("FileAction.doAdd()");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			String basePath = request.getSession().getServletContext()
					.getRealPath("/");
			String now = DateUtil.getStringDateShort();
			String[] yymmdd = now.split("-");
			String path = basePath + "upload/" + yymmdd[0] + "/" + yymmdd[1]
					+ "/" + yymmdd[2] + "/";
			File folder = new File(path);
			if (!folder.exists() && !folder.isDirectory()) {
				folder.mkdirs();
			}
			String fileSuffix = file.getOriginalFilename().substring(
					file.getOriginalFilename().lastIndexOf("."));
			String fileName = MD5.toMd5(UID.nextUid()) + "-"
					+ file.getOriginalFilename();
			String filePath = path + fileName;
			File upload = new File(filePath);
			InputStream is = file.getInputStream();

			FileOutputStream fos = new FileOutputStream(upload);
			byte[] buffer = new byte[8 * 1024]; // 每次读8K字节
			int len = 0;
			// 开始读取上传文件的字节，并将其输出到服务端的上传文件输出流中
			while ((len = is.read(buffer)) > 0) {
				fos.write(buffer, 0, len); // 向服务端文件写入字节流
			}
			fos.close(); // 关闭FileOutputStream对象
			is.close(); // InputStream对象
			String fileId = MD5.toMd5(UID.nextUid());
			Integer fileSize = (int) file.getSize();
			Date fileTime = new Date();
			String fileUpload = "abc";
			System.out.println("desc : " + desc);
			MCFile f = new MCFile(fileId, fileName, fileSize, fileTime,
					fileSuffix, filePath, fileUpload, desc);
			fileService.insert(f);
			resultMap.put("result", "上传成功");
		} catch (Exception e) {
			resultMap.put("result", "出现错误");
			resultMap.put("message", e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return "redirect:/file/file.action";
	}

	@RequestMapping("/upload1")
	public String upload1(@RequestParam("file") CommonsMultipartFile[] files,
			HttpServletRequest request) {
		System.out.println("FileAction.upload1()");
		for (int i = 0; i < files.length; i++) {
			System.out.println("fileName---->"
					+ files[i].getOriginalFilename());

			if (!files[i].isEmpty()) {
				int pre = (int) System.currentTimeMillis();
				try {
					// 拿到输出流，同时重命名上传的文件
					FileOutputStream os = new FileOutputStream("F:/"
							+ new Date().getTime()
							+ files[i].getOriginalFilename());
					// 拿到上传文件的输入流
					FileInputStream in = (FileInputStream) files[i]
							.getInputStream();

					// 以写字节的方式写文件
					int b = 0;
					while ((b = in.read()) != -1) {
						os.write(b);
					}
					os.flush();
					os.close();
					in.close();
					int finaltime = (int) System.currentTimeMillis();
					System.out.println(finaltime - pre);

				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("上传出错");
				}
			}
		}
		return "redirect:/file/file.action";
	}

	@RequestMapping("/upload2")
	public String upload2(HttpServletRequest request,
			HttpServletResponse response) throws Exception, ServiceException {
		// 创建一个通用的多部分解析器
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 判断 request 是否有文件上传,即多部分请求
		if (multipartResolver.isMultipart(request)) {
			// 转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				// 记录上传过程起始时的时间，用来计算上传时间
				int pre = (int) System.currentTimeMillis();
				// 取得上传文件
				MultipartFile file = multiRequest.getFile(iter.next());
				if (file != null) {
					// 取得当前上传文件的文件名称
					String myFileName = file.getOriginalFilename();
					// 如果名称不为"",说明该文件存在，否则说明该文件不存在
					if (myFileName.trim() != "") {
						System.out
								.println("==========>fileName : "
										+ myFileName);
						// 重命名上传后的文件名
						String fileName = MD5.toMd5(UID.nextUid()) + "-"
								+ file.getOriginalFilename();
						String fileSuffix = file.getOriginalFilename()
								.substring(
										file.getOriginalFilename().lastIndexOf(
												"."));
						// 定义上传路径
						String now = DateUtil.getStringDateShort();
						String[] ymd = now.split("-");
						String path = "F:/upload/" + ymd[0] + "/" + ymd[1]
								+ "/" + ymd[2] + "/";
						java.io.File folder = new java.io.File(path);
						if (!folder.exists() && !folder.isDirectory()) {
							folder.mkdirs();
						}
						String filePath = path + fileName;
						File localFile = new File(filePath);
						file.transferTo(localFile);
						String fileId = MD5.toMd5(UID.nextUid());
						Integer fileSize = (int) file.getSize();
						Date fileTime = new Date();
						String fileUpload = "abc";
						MCFile f = new MCFile(fileId, fileName, fileSize,
								fileTime, fileSuffix, filePath, fileUpload,
								"test");
						fileService.insert(f);
					}
				}
				// 记录上传该文件后的时间
				int finaltime = (int) System.currentTimeMillis();
				System.out.println(finaltime - pre);
			}
		}
		return "redirect:/file/file.action";
	}

	/**
	 * 修改页面
	 * 
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/fileedit", method = RequestMethod.GET)
	public Object editPage(String fileId) throws ServiceException {
		ModelAndView model = new ModelAndView();
		try {
			MCFile file = fileService.findByPrimaryKey(fileId);
			model.addObject("file", file);
			model.setViewName("file_edit");
		} catch (Exception e) {
			model.addObject("exception", e.getMessage());
			model.setViewName("/error");
			logger.error(e.getMessage(), e);
		}
		return model;
	}

	/**
	 * 修改保存
	 * 
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/fileedit", method = RequestMethod.POST)
	@ResponseBody
	public Object doEdit(MCFile file) throws ServiceException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			fileService.update(file);
			resultMap.put("result", 1);
		} catch (Exception e) {
			resultMap.put("result", 0);
			resultMap.put("message", e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return resultMap;
	}

	/**
	 * 删除
	 * 
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/filedelete")
	@ResponseBody
	public String doDelete(@RequestParam("fileId") String fileId)
			throws ServiceException {
		try {
			MCFile file = fileService.findByPrimaryKey(fileId);
			String filePath = file.getFilePath();
			java.io.File filer = new java.io.File(filePath);
			System.out.println("filePath : " + filePath);
			if (filer.exists()) {
				filer.delete();
			}
			fileService.delete(fileId);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "redirect:/file/file.action";
	}

	@RequestMapping("/filedown")
	public ModelAndView filedown(@RequestParam("fileId") String fileId,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, ServiceException {

		MCFile file = fileService.findByPrimaryKey(fileId);
		String fileName = file.getFileName();
		String filePath = file.getFilePath();
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		fileName = fileName.substring(33);
		System.out.println(fileName);
		System.out.println(filePath);
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		try {
			long fileLength = new java.io.File(filePath).length();
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition", "attachment; filename="
					+ fileName);
			response.setHeader("Content-Length", String.valueOf(fileLength));
			bis = new BufferedInputStream(new FileInputStream(filePath));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
		return null;
	}
}