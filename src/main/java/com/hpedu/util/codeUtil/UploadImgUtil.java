package com.hpedu.util.codeUtil;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.UUID;

public class UploadImgUtil {

	public String uploadImage(HttpServletRequest request, MultipartFile image) {
		String json = "{\"state\":\"fail\"}";
		try {
			if (image != null && image.getSize() > 0) {

				// 服务器存储目录
				String realPath = request.getSession().getServletContext()
						.getRealPath("/");
				String path = request.getContextPath().substring(1);
				realPath = realPath.replace(path, "uploadImages");
				// 获取文件名后缀
				String sname = image.getOriginalFilename()
						.substring(
								image.getOriginalFilename().toString()
										.lastIndexOf("."));
				// 文件重命名
				String picId = UUID.randomUUID().toString();
				String fileName = picId + sname;
				// 图片完整路径
				String logoUrl = realPath + fileName;
			
				realPath = realPath.replace("yiqunyiqun", "uploadImages");
				System.out.println("realPath"+realPath);
				File destFile = new File(realPath, fileName);
				image.transferTo(destFile);
				System.out.println("destFile="+destFile);
				try {
					String url  = realPath+fileName;
					System.out.println("url"+url);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				json = "{\"state\":\"success\",\"picName\":\"" + fileName
						+ "\"}";
				
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return json;
	}

}
