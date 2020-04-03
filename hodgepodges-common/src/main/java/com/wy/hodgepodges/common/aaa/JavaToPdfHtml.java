package com.wy.hodgepodges.common.aaa;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2020-04-01 17:04
 */


import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.wy.hodgepodges.common.pdf.TemplateUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.util.HashMap;

/**
 * Created by lujianing on 2017/5/7.
 */
public class JavaToPdfHtml {

    static String DEST = "/Users/wy/Downloads/notice31.pdf";
    private static final String HTML = "/Users/wy/haa.html";


    public static void main(String[] args) throws Exception {
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(DEST));
        // step 3
        document.open();
        // step 4
        XMLWorkerFontProvider fontImp = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
        String c = TemplateUtils.getTranslateTemplate("SIMSUN.TTC", new HashMap<>(0));

        fontImp.register(       c);
        XMLWorkerHelper.getInstance().parseXHtml(writer, document,
                new FileInputStream(HTML), null, Charset.forName("UTF-8"), fontImp);
        // step 5
        document.close();
    }
}