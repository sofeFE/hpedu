package com.hpedu.util.codeUtil;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

public class UploadUtil {
	@Value("${uploadAbsolutePath}")
	private static String uploadAbsolutePath ;
	
	public static String uploadThreeToOne(MultipartFile file,HttpServletRequest req)throws Exception{
		String fileName = "";
		// 服务器存储目录 E:\apache-tomcat-7.0.68\webapps\zhaopin\
		String realPath = uploadAbsolutePath;
		//路径为 path:zhaopin
		String path = req.getContextPath().substring(1);
		//路径为E:\apache-tomcat-7.0.68\webapps\ companyThreeToOne\
		realPath = realPath.replace(path, "companyThreeToOne");
		if (file != null) {
			fileName = UUIDUtil.getUUID() + file.getOriginalFilename();
			file.transferTo( new File(realPath, fileName) );
			return "\\companyThreeToOne\\"+fileName;
		}
		return null;
	}
	public static String uploadLogo(MultipartFile file,HttpServletRequest req)throws Exception{
		String fileName = "";
		// 服务器存储目录 E:\apache-tomcat-7.0.68\webapps\zhaopin\
		String realPath = uploadAbsolutePath;
		//路径为 path:zhaopin
		String path = req.getContextPath().substring(1);
		//路径为E:\apache-tomcat-7.0.68\webapps\ projectImgs\
		realPath = realPath.replace(path, "companyLogos");
		if (file != null) {
			fileName = UUIDUtil.getUUID() + file.getOriginalFilename();
			file.transferTo( new File(realPath, fileName) );
			return "\\companyLogos\\"+fileName;
		}
		return null;
	}
	public static String uploadVideo(MultipartFile file,HttpServletRequest req)throws Exception{
		String fileName = "";
		// 服务器存储目录 E:\apache-tomcat-7.0.68\webapps\zhaopin\
		String realPath = uploadAbsolutePath;
		//路径为 path:zhaopin
		String path = req.getContextPath().substring(1);
		//路径为E:\apache-tomcat-7.0.68\webapps\ projectImgs\
		realPath = realPath.replace(path, "companyVideo");
		if (file != null) {
			fileName = UUIDUtil.getUUID() + file.getOriginalFilename();
			file.transferTo( new File(realPath, fileName) );
			return "\\companyVideo\\"+fileName;
		}
		return null;
	}

}
