//package com.wy.hodgepodges.common.pdf;
//
//
//import java.io.File;
//
///**
// * @author wy
// * @version V1.0
// * @desc
// * @date 2020-03-31 10:28
// */
//public class dasd {
//
//
//    private static final String DEST = "/Users/wy/Downloads/notice31.pdf";
//    private static final String HTML = "/Users/wy/Downloads/notice31.htm";
//
//    private static final String FONT = "simhei.ttf";
//
//
//
//    // wkhtmltopdf在系统中的路径
//    private static String toPdfTool = Consts.WEB.CONVERSION_PLUGSTOOL_PATH_WINDOW;
//
//
//    public static boolean convert(String srcPath, String destPath) {
//        File file = new File(destPath);
//        File parent = file.getParentFile();
//        // 如果pdf保存路径不存在，则创建路径
//        if (!parent.exists()) {
//            parent.mkdirs();
//        }
//        StringBuilder cmd = new StringBuilder();
//        if (System.getProperty("os.name").indexOf("Windows") == -1) {
//            // 非windows 系统
//            toPdfTool = Consts.WEB.CONVERSION_PLUGSTOOL_PATH_LINUX;
//        }
//        cmd.append(toPdfTool);
//        cmd.append(" ");
//        cmd.append(" \"");
//        cmd.append(srcPath);
//        cmd.append("\" ");
//        cmd.append(" ");
//        cmd.append(destPath);
//
//        System.out.println(cmd.toString());
//        boolean result = true;
//        try {
//            Process proc = Runtime.getRuntime().exec(cmd.toString());
//            HtmlToPdfInterceptor error = new HtmlToPdfInterceptor(proc.getErrorStream());
//            HtmlToPdfInterceptor output = new HtmlToPdfInterceptor(proc.getInputStream());
//            error.start();
//            output.start();
//            proc.waitFor();
//        } catch (Exception e) {
//            result = false;
//            e.printStackTrace();
//        }
//
//        return result;
//    }
//
//    public static void main(String[] args) {
////        HtmlToPdf.convert("http://www.baidu.com", "F:/pdf/baidu.pdf");
//        String filename = "JAVA将图片转换成pdf文件-CSDN博客";
//        HtmlToPdf.convert("F:/pdf/"+filename+".html", "F:/pdf/"+filename+".pdf");
////        HtmlToPdf.convert("http://api.gyingyuan.com/", "F:/pdf/"+ UUID.randomUUID().toString()+".pdf");
////        HtmlToPdf.convert("https://www.aliyun.com/jiaocheng/285649.html", "F:/pdf/baidu.pdf");
//    }
//}
