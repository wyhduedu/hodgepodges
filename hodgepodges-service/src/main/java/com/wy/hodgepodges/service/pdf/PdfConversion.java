package com.wy.hodgepodges.service.pdf;


import com.itextpdf.awt.geom.Rectangle2D;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.ImageRenderInfo;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.RenderListener;
import com.itextpdf.text.pdf.parser.TextRenderInfo;

import java.io.IOException;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2020-03-02 13:43
 */

public class PdfConversion {
    // 定义关键字
    private static String KEY_WORD = "张三";
    // 定义返回值
    private static float[] resu = null;
    // 定义返回页码
    private static int i = 0;

    /*
     * 返回关键字所在的坐标和页数 float[0] >> X; float[1] >> Y; float[2] >> page;
     */

    public static float[] getKeyWords(String filePath) {
        try {
            PdfReader pdfReader = new PdfReader(filePath);
            int pageNum = pdfReader.getNumberOfPages();
            System.out.println(pageNum);
            PdfReaderContentParser pdfReaderContentParser = new PdfReaderContentParser(
                    pdfReader);

            // 下标从1开始
            for (i = 1; i <= pageNum; i++) {
                pdfReaderContentParser.processContent(i, new RenderListener() {

                    @Override
                    public void renderText(TextRenderInfo textRenderInfo) {
                        String text = textRenderInfo.getText();
                        if (null != text && text.contains("makeTime")) {
                            Rectangle2D.Float boundingRectange = textRenderInfo
                                    .getBaseline().getBoundingRectange();
                            resu = new float[3];
                            System.out.println("=======" + text);
                            resu[0] = boundingRectange.x;
                            resu[1] = boundingRectange.y;
                            resu[2] = i;
                        }
                    }

                    @Override
                    public void renderImage(ImageRenderInfo arg0) {
                    }

                    @Override
                    public void endTextBlock() {

                    }

                    @Override
                    public void beginTextBlock() {
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resu;
    }
}