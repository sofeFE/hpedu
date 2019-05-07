package com.hpedu.web.core.upload;

import com.hpedu.util.codeUtil.UUIDUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
//@Deprecated
public class UploadService {
    @Value("${uploadAbsolutePath}")
    private String uploadAbsolutePath;

    /**
     * 异步上传文件
     *
     * @param relativePath
     * @param file
     * @return
     * @throws Exception
     */
    public String AsynchronizeUploadFile(String relativePath, MultipartFile file) throws Exception {
        StringBuilder path = new StringBuilder();
        if (file.getSize() > 0) {
            path.append(relativePath);
            path.append(UUIDUtil.getUUID());
            path.append("." + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1));//后缀
            file.transferTo(new File(uploadAbsolutePath + path.toString()));
        }
        return path.toString();
    }

}
