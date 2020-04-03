package com.wy.hodgepodges.common.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2020-03-30 10:12
 */
public class CreatePdf {

    private static final String DEST = "/Users/wy/Downloads/notice31.pdf";
    private static final String HTML = "/Users/wy/Downloads/notice31.htm";
    private static final String FONT = "simhei.ttf";

    public static void main(String[] args) throws IOException, DocumentException {
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(DEST));
        // step 3
        document.open();
        // step 4
        XMLWorkerFontProvider fontImp = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
//        fontImp.register(FONT);
        XMLWorkerHelper.getInstance().parseXHtml(writer, document,
                new FileInputStream(HTML), null, Charset.forName("UTF-8"), fontImp);
        // step 5
        document.close();
    }

    /*
     pdf转换html
      */
    @Test
    public void pdfToHtmlTest() {
        String outputPath = "D:\\code\\pdf\\HashMap.html";
        byte[] bytes = getBytes("D:\\code\\pdf\\HashMap.pdf");
//        try() 写在()里面会自动关闭流
        try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(outputPath)), "UTF-8"));) {
            //加载PDF文档
//            PDDocument document = PDDocument.load(bytes);
//            PDFDomTree pdfDomTree = new PDFDomTree();
//            pdfDomTree.writeText(document,out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    将文件转换为byte数组
     */
    private byte[] getBytes(String filePath) {
        byte[] buffer = null;
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }


}



