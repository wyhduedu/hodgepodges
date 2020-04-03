package com.wy.hodgepodges.common.aaa;

import com.itextpdf.text.pdf.BaseFont;
import com.lowagie.text.DocumentException;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2020-04-01 17:29
 */
public class HtmlToString {
    static String DEST = "/Users/wy/notice31.pdf";
    static String HTML = "/Users/wy/haa.html";

    public static String htmlToString() {
        StringBuilder strline = new StringBuilder();
        File fin = new File(HTML);
        try (RandomAccessFile accessFile = new RandomAccessFile(fin, "r");
             FileChannel fcin = accessFile.getChannel()
        ) {
            Charset charset = Charset.forName("UTF-8");
            int bufSize = 100000;
            ByteBuffer rBuffer = ByteBuffer.allocate(bufSize);
            String enterStr = "\n";
            byte[] bs = new byte[bufSize];

            StringBuilder strBuf = new StringBuilder();
            while (fcin.read(rBuffer) != -1) {
                int rSize = rBuffer.position();
                rBuffer.rewind();
                rBuffer.get(bs);
                rBuffer.clear();
                String tempString = new String(bs, 0, rSize, charset);
                tempString = tempString.replaceAll("\r", "");

                int fromIndex = 0;
                int endIndex;
                while ((endIndex = tempString.indexOf(enterStr, fromIndex)) != -1) {
                    String line = tempString.substring(fromIndex, endIndex);
                    line = strBuf.toString() + line;
                    strline.append(line.trim());

                    strBuf.delete(0, strBuf.length());
                    fromIndex = endIndex + 1;
                }
                if (rSize > tempString.length()) {
                    strline.append(tempString.substring(fromIndex));
                    strBuf.append(tempString.substring(fromIndex));
                } else {
                    strline.append(tempString, fromIndex, rSize);
                    strBuf.append(tempString, fromIndex, rSize);
                }
            }
            System.out.println(strline.toString().replaceAll("\"", "'"));
        } catch (Exception e) {

        }
        return strline.toString();
    }

    private static void stringToPdf(String strline) throws IOException, DocumentException {
        //注意这里为啥要写这个，主要是替换成这样的字体，如果不设置中文有可能显示不出来。
        String htmlString = strline.replaceAll("\"", "'").replaceAll("<style>", "<style>body{font-family:SimSun;font-size:14px;}");

        //生成PDF文件的路径
        OutputStream os = new FileOutputStream(DEST);
        ITextRenderer renderer = new ITextRenderer();
        ITextFontResolver font = renderer.getFontResolver();
        //添加中文识别，这里是设置的宋体，Linux下要换成对应的字体
        font.addFont("/Users/wy/project/workspace/hodgepodges/hodgepodges-common/src/main/resources/template/SIMSUN.TTC", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        renderer.setDocumentFromString(htmlString);

        renderer.layout();
        renderer.createPDF(os);
        renderer.finishPDF();
    }

    public static void main(String[] args) {
        try {
            stringToPdf(htmlToString());
            System.out.println("处理完毕");
        } catch (Exception e) {
            System.out.println("sdjsjdjds");
        }
    }

}
