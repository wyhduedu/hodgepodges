package com.wy.hodgepodges.service.pdf;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

//import org.apache.commons.lang3.StringUtils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.Pipeline;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.exceptions.CssResolverException;
import com.itextpdf.tool.xml.html.CssAppliersImpl;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.net.FileRetrieve;
import com.itextpdf.tool.xml.net.ReadingProcessor;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.AbstractImageProvider;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;
import com.itextpdf.tool.xml.pipeline.html.ImageProvider;
import com.itextpdf.tool.xml.pipeline.html.NoImageProviderException;
import org.springframework.util.StringUtils;
import com.wy.hodgepodges.service.pdf.*;

public class PdfGenerator {

	private static final String CHARSET_NAME = "UTF-8";

	public void generate(String htmlStr, OutputStream out) throws IOException, DocumentException, CssResolverException {

		Document document = new Document(PageSize.A4, 30, 30, 30, 30);
		document.setMargins(30, 30, 30, 30);
		PdfWriter writer = PdfWriter.getInstance(document, out);
		// 页脚（第几页）
		PdfFileExportUtil pdfFileExportUtil = new  PdfFileExportUtil();
		writer.setPageEvent(pdfFileExportUtil.new HeadFootInfoPdfPageEvent());
		writer.setPageEvent(pdfFileExportUtil.new PictureWaterMarkPdfPageEvent("D:\\tmp\\1.png"));
		document.open();

		// html内容解析
		HtmlPipelineContext htmlContext = new HtmlPipelineContext(new CssAppliersImpl(new XMLWorkerFontProvider() {
			@Override
			public Font getFont(String fontName, String encoding, float size, final int style) {
				Font font = null;
				if (fontName == null || "宋体".equals(fontName) || "simsun".equals(fontName)) {
					// 字体
					String fontCn = getSIMSUNFont();
					BaseFont bf;
					try {
						// 注意这里有一个,1
						bf = BaseFont.createFont(fontCn + ",1", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
						font = new Font(bf, size, style);
					} catch (DocumentException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
				return font;
			}
		})) {
			@Override
			public HtmlPipelineContext clone() throws CloneNotSupportedException {
				HtmlPipelineContext context = super.clone();
				try {
					ImageProvider imageProvider = this.getImageProvider();
					context.setImageProvider(imageProvider);
				} catch (NoImageProviderException e) {
				}
				return context;
			}
		};

		// 图片解析
		htmlContext.setImageProvider(new AbstractImageProvider() {

			// String rootPath = servletContext.getRealPath("/");

			@Override
			public String getImageRootPath() {
				return "";
			}

			@Override
			public Image retrieve(String src) {
				if (StringUtils.isEmpty(src)) {
					return null;
				}
				try {
					// String imageFilePath = new File(rootPath,
					// src).toURI().toString();
					Image image = Image.getInstance(src);
					image.setAbsolutePosition(400, 400);
					if (image != null) {
						store(src, image);
						return image;
					}
				} catch (Throwable e) {
					e.printStackTrace();
				}
				return super.retrieve(src);
			}
		});
		htmlContext.setAcceptUnknown(true).autoBookmark(true).setTagFactory(Tags.getHtmlTagProcessorFactory());

		// css解析
		CSSResolver cssResolver = XMLWorkerHelper.getInstance().getDefaultCssResolver(true);
		cssResolver.setFileRetrieve(new FileRetrieve() {
			@Override
			public void processFromStream(InputStream in, ReadingProcessor processor) {
				try {
					InputStreamReader reader = new InputStreamReader(in, CHARSET_NAME);
					int i = -1;
					while (-1 != (i = reader.read())) {
						processor.process(i);
					}
				} catch (Throwable e) {

				}
			}

			// 解析href
			@Override
			public void processFromHref(String href, ReadingProcessor processor) throws IOException {
				URL url = new URL(href);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setConnectTimeout(5 * 1000);
				InputStream is = conn.getInputStream();
				InputStreamReader reader = new InputStreamReader(is, CHARSET_NAME);
				try {
					int i = -1;
					while (-1 != (i = reader.read())) {
						processor.process(i);
					}
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
		});
		// cssResolver.addCss(".div-0{font-size: 36px; text-align:
		// center;}",true);
		HtmlPipeline htmlPipeline = new HtmlPipeline(htmlContext, new PdfWriterPipeline(document, writer));
		Pipeline<?> pipeline = new CssResolverPipeline(cssResolver, htmlPipeline);
		XMLWorker worker = new XMLWorker(pipeline, true);
		XMLParser parser = new XMLParser(true, worker, Charset.forName(CHARSET_NAME));
		InputStream inputStream = new ByteArrayInputStream(htmlStr.getBytes(CHARSET_NAME));
		try {
			parser.parse(inputStream, Charset.forName(CHARSET_NAME));
		} catch (Throwable e) {
			e.printStackTrace();
		}
		document.close();
	}

	/**
	 * 获取中文字体位置
	 *
	 * @return
	 */
	private String getSIMSUNFont() {

		String font = this.getClass().getResource("/").getPath() + "template/SIMSUN.TTC";
		// String font = "D:/tmp/SIMSUN.TTC";
		// System.out.println(font);
		if (!new File(font).exists()) {
			throw new RuntimeException("字体文件不存在,影响导出pdf中文显示！" + font);
		}

		return font;
	}
}