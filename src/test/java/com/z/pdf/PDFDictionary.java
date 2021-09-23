package com.z.pdf;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.SimpleBookmark;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PDFDictionary {
    public static void main(String[] args) throws IOException {

        String filename = "\\C:\\Users\\Thomas.Zhao\\Documents\\Java面试题必知必会-V2版.pdf";

        PdfReader reader = new PdfReader(filename);

        List list = SimpleBookmark.getBookmark ( reader ) ;

        for ( Iterator i = list.iterator () ; i.hasNext () ; ) {
            showBookmark (( Map ) i.next ()) ;
        }

    }

    private static void showBookmark(Map bookmark) {
        System.out.println(bookmark.get("Title"));

        ArrayList kids = (ArrayList) bookmark.get("Kids");

        if (kids == null)

            return;

        for (Iterator i = kids.iterator(); i.hasNext(); ) {
            showBookmark((Map) i.next());
        }

    }

}

