package com.z.pdf;


import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class ReadTest {

    public static void main(String[] args) throws IOException {
        String outputPath = "C:\\Users\\dhc\\Desktop\\123.txt";
        PrintWriter writer = new PrintWriter(new FileOutputStream(outputPath));
        String fileName = "C:\\Users\\dhc\\Desktop\\test.pdf";

        readPdf(writer, fileName);//直接读全PDF面

        //readPdf_filter(fileName);//读取PDF面的某个区域

    }

    public static void readPdf(PrintWriter writer,String fileName){
        String pageContent = "";
        try {
            PdfReader reader = new PdfReader(fileName);
            int pageNum = reader.getNumberOfPages();
            for(int i=1;i<=pageNum;i++){
                pageContent += PdfTextExtractor.getTextFromPage(reader, i);//读取第i页的文档内容
            }
            writer.write(pageContent);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            writer.close();
        }
    }

    public static void readPdf_filter(PrintWriter writer,String fileName){
        String pageContent = "";
        try {
            Rectangle rect = new Rectangle(90, 0, 450, 40);
            RenderFilter filter = new RegionTextRenderFilter(rect);
            PdfReader reader = new PdfReader(fileName);
            int pageNum = reader.getNumberOfPages();
            TextExtractionStrategy strategy;
            for (int i = 1; i <= pageNum; i++) {
                strategy = new FilteredTextRenderListener(new LocationTextExtractionStrategy(), filter);
                pageContent +=PdfTextExtractor.getTextFromPage(reader, i, strategy);
            }
			/*String[] split = pageContent.split(" ");
			for(String ss : split){
				System.out.println(ss.substring(ss.lastIndexOf("：")+1, ss.length()));
			}*/
            writer.write(pageContent);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            writer.close();
        }
    }
}
