package com.wy.hodgepodges.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author wy
 * @version V1.0
 * @desc 文件上传
 * @date 2020-03-22 18:09
 */
@Controller(value = "文件上传")
public class Upload {

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody
    String upload(MultipartFile file) throws IOException {
        FileUtils.writeByteArrayToFile(new File("/User/wy/uploadFiles"), file.getBytes());
        return "ok";
    }

}
