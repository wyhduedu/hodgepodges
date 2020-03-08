package com.wy.hodgepodges.service.pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2020-03-02 13:44
 */
public class PdfConversion1 {

    public static final String SRC = "/Users/wy/Downloads/notice11.pdf";
    public static final String DEST = "/Users/wy/Downloads/notice1.pdf";

    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(SRC);
        file.getParentFile().mkdirs();
        new PdfConversion1().manipulatePdf(SRC, DEST);
    }

    public void manipulatePdf(String src, String dest) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(src);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        //1
        float[] result = PdfConversion.getKeyWords(src);
        PdfContentByte canvas = stamper.getOverContent((int) result[2]);
//        float height=595;
//        System.out.println(canvas.getHorizontalScaling());
        float x, y;
        //2
        x = result[0];
        //3
        y = result[1];
        canvas.saveState();
        canvas.setColorFill(BaseColor.WHITE);
        canvas.rectangle(x, y, 40, 20);//设置覆盖面的大小

        canvas.fill();
        canvas.restoreState();
        //开始写入文本
        canvas.beginText();
        //BaseFont bf = BaseFont.createFont(URLDecoder.decode(CutAndPaste.class.getResource("/AdobeSongStd-Light.otf").getFile()), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
        Font font = new Font(bf, 10, Font.BOLD);
        //设置字体和大小
        canvas.setFontAndSize(font.getBaseFont(), 15);
        //设置字体的输出位置
        canvas.setTextMatrix(x, y - 1);
        //要输出的text
        canvas.showText("田田田");

        //设置字体的输出位置
        canvas.setFontAndSize(font.getBaseFont(), 20);
        canvas.setTextMatrix(x, y - 90);
        //要输出的text
        canvas.showText("多退少补");

        canvas.endText();
        stamper.close();
        reader.close();
        System.out.println("complete");
    }

}