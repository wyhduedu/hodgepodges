package com.wy.hodgepodges.io;

import com.google.common.collect.Lists;

import java.io.*;
import java.util.List;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2020-04-03 11:27
 */
public class WriteTest {

    public static void writeTest() throws FileNotFoundException {

        FileInputStream in = new FileInputStream(new File("/Users/wy/qq.html"));
        FileOutputStream out = new FileOutputStream("/Users/wy/qq11.html");
        String outFile = "/Users/wy/qq11.html";
        List<String> read = read();
        writer(outFile, read);

    }

    public static void main(String[] args) throws FileNotFoundException {
        writeTest();
    }

    public static List<String> read() {
        String filePath = "/Users/wy/qq.html";
        String line;
        List<String> list = Lists.newArrayList();
        StringBuffer buf = new StringBuffer();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            //根据文件路径创建缓冲输入流
            //循环读取文件的每一行,对需要进行修改的行进行修改,放入缓存对象中

            while ((line = br.readLine()) != null) {
                //此处根据实际需要修改某些行的内容
                buf .append( line);
                //使用list记录所有的数据
                list.add(buf.toString());
                System.out.println(buf.toString());
                //清空可变字符串,重新记录数据
                buf.delete(0, buf.length());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void writer(String filePath, List<String> content) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
//根据文件路径创建缓冲输出流
//将内容写入到文件中
            for (int i = 0; i < content.size(); i++) {
                bw.write(content.get(i));
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
