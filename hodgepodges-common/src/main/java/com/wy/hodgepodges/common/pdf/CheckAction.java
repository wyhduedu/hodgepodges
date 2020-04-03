package com.wy.hodgepodges.common.pdf;

import com.google.common.collect.Maps;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Map;

/**
 * 函证流程
 *
 * @author admin
 */
public class CheckAction  {
    /**
     * 生成函证
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView build(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = Maps.newHashMap();
        OutputStream out;
        FileInputStream input;
        String path = "";

        String fileName = "sjddshds.pdf";
        // html转pdf
        out = new FileOutputStream(fileName);
        String content = TemplateUtils.getTranslateTemplate(path, model);
        new PdfGenerator().generate(content, out);
        // Html2pdf.htmlTopdf(content, new File(fileName));

        // 上传文件
        File file = new File(fileName);
        input = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile(file.getName(), input);
        // 保存记录
        return null;
    }

}
