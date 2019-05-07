package com.hpedu.web.core.upload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @PostMapping("/upload")
    @ResponseBody
    public String AsynchronizeUpload(@RequestParam("relativePath") String relativePath,
                                     @RequestParam("uploadFile") MultipartFile file) throws Exception {

        String result = uploadService.AsynchronizeUploadFile(relativePath, file);

        return result;
    }

    
    @PostMapping(value={"/uploadMulti"})
    @ResponseBody
    public  Map<String,Object> upload(String dir,
                                      MultipartFile imgFile ,
                                      @RequestParam("relativePath") String relativePath) throws Exception{
        Map<String,Object> map = new HashMap<>() ;
        String resultUrl = uploadService.AsynchronizeUploadFile(relativePath, imgFile);
        map.put("url",resultUrl) ;
        map.put("message","success");
        map.put("error",0) ;
        return map ;
    }

}
