package com.example.grmlogbook.app;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import com.example.grmlogbook.summary.Summarybean;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import androidx.core.content.FileProvider;

public class SummaryPdfConfig {

    private static Font catFont = new Font(Font.FontFamily.HELVETICA, 12,
            Font.BOLD);
    private static Font subFont = new Font(Font.FontFamily.HELVETICA, 10,
            Font.NORMAL);



    public static void printFunction(Context context, Summarybean summarybean) {

        try {
            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/PDF";
            File dir = new File(path);
            if (!dir.exists())
                dir.mkdirs();
            Log.d("PDFCreator", "PDF Path: " + path);
            File file = new File(dir, "Grievance Registration" + ".pdf");
            FileOutputStream fOut = new FileOutputStream(file);


            Document document = new Document();
            PdfWriter pdfWriter = PdfWriter.getInstance(document, fOut);
            Rectangle rect = new Rectangle(175, 20, 530, 800);
            pdfWriter.setBoxSize("art", rect);

//            Bitmap icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.cst_pdf);
//            Bitmap bu = BitmapFactory.decodeResource(context.getResources(), R.drawable.cst_pdf);
//
//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            icon.compress(Bitmap.CompressFormat.PNG, 100, stream);
//            byte[] byteArray = stream.toByteArray();
//
//            ByteArrayOutputStream stream1 = new ByteArrayOutputStream();
//            bu.compress(Bitmap.CompressFormat.PNG, 100, stream1);
//            byte[] byteArray1 = stream1.toByteArray();
//
//            HeaderFooterPageEvent event = new HeaderFooterPageEvent(Image.getInstance(byteArray),
//                    Image.getInstance(byteArray1));
//            pdfWriter.setPageEvent(event);

            document.open();
            SummaryPdfConfig.addMetaData(document);
            // AppConfig.addTitlePage(document);
            SummaryPdfConfig.addContent(document,summarybean);
            document.close();


        } catch (Error | Exception e) {
            e.printStackTrace();
        }

        Uri photoURI = FileProvider.getUriForFile(context, context.getPackageName() + ".provider", new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/PDF/" + "Grievance Registration" + ".pdf"));

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(photoURI
                , "application/pdf");
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        context.startActivity(intent);

    }

    public static void addMetaData(Document document) {
        document.addTitle("My first PDF");
        document.addSubject("Using iText");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("Lars Vogel");
        document.addCreator("Lars Vogel");
    }

    public static void addContent(Document document,Summarybean summarybean) throws Exception {

        PdfPTable table1 = new PdfPTable(1);
        table1.setWidthPercentage(100);
        table1.setWidths(new int[]{1});
        table1.addCell(createTextCell("\n", subFont));
        table1.addCell(createTextCellBorderLeft("PROJECTS GRIEVANCES  SUMMARY", catFont));
        document.add(table1);

        PdfPTable table2 = new PdfPTable(14);
        table2.setWidthPercentage(100);
        table2.setWidths(new float[]{6,1,1,1,1,1,1,1,1,1,1,1,1,1});
        table2.addCell(createTextCellBorderLeft("YEAR",catFont));
        table2.addCell(createTextCellBorderLeft("Jan",catFont));
        table2.addCell(createTextCellBorderLeft("Feb",catFont));
        table2.addCell(createTextCellBorderLeft("Mar",catFont));
        table2.addCell(createTextCellBorderLeft("Apr",catFont));
        table2.addCell(createTextCellBorderLeft("May",catFont));
        table2.addCell(createTextCellBorderLeft("Jun",catFont));
        table2.addCell(createTextCellBorderLeft("Jul",catFont));
        table2.addCell(createTextCellBorderLeft("Aug",catFont));
        table2.addCell(createTextCellBorderLeft("Sep",catFont));
        table2.addCell(createTextCellBorderLeft("Oct",catFont));
        table2.addCell(createTextCellBorderLeft("Nov",catFont));
        table2.addCell(createTextCellBorderLeft("Dec",catFont));
        table2.addCell(createTextCellBorderLeft("Total",catFont));

        table2.addCell(createTextCellBorderLeft("1) Theme 1",catFont));
        table2.addCell(createTextCellBorderLeft("",catFont));
        table2.addCell(createTextCellBorderLeft("",catFont));
        table2.addCell(createTextCellBorderLeft("",catFont));
        table2.addCell(createTextCellBorderLeft("",catFont));
        table2.addCell(createTextCellBorderLeft("",catFont));
        table2.addCell(createTextCellBorderLeft("",catFont));
        table2.addCell(createTextCellBorderLeft("",catFont));
        table2.addCell(createTextCellBorderLeft("",catFont));
        table2.addCell(createTextCellBorderLeft("",catFont));
        table2.addCell(createTextCellBorderLeft("",catFont));
        table2.addCell(createTextCellBorderLeft("",catFont));
        table2.addCell(createTextCellBorderLeft("",catFont));
        table2.addCell(createTextCellBorderLeft("",catFont));

        table2.addCell(createTextCellBorderLeft("Number of new complaints registered  \n" +
                "for reporting month\n ",subFont));
        table2.addCell(createTextCellBorderLeft(summarybean.jancomplaint,subFont));
        table2.addCell(createTextCellBorderLeft(summarybean.febcomplaint,subFont));
        table2.addCell(createTextCellBorderLeft(summarybean.marcomplaint,subFont));
        table2.addCell(createTextCellBorderLeft(" ",subFont));
        table2.addCell(createTextCellBorderLeft(" ",subFont));
        table2.addCell(createTextCellBorderLeft(" ",subFont));
        table2.addCell(createTextCellBorderLeft(" ",subFont));
        table2.addCell(createTextCellBorderLeft(" ",subFont));
        table2.addCell(createTextCellBorderLeft(" ",subFont));
        table2.addCell(createTextCellBorderLeft(" ",subFont));
        table2.addCell(createTextCellBorderLeft(" ",subFont));
        table2.addCell(createTextCellBorderLeft(" ",subFont));
        table2.addCell(createTextCellBorderLeft(summarybean.subcomplaint,subFont));

        table2.addCell(createTextCellBorderLeft("Number of complaints resolved locally by GRG/PIU/ \n"+
                "Consultant/ local government/etc.",subFont));
        table2.addCell(createTextCellBorderLeft(summarybean.jangovernment,subFont));
        table2.addCell(createTextCellBorderLeft(summarybean.febgovernment,subFont));
        table2.addCell(createTextCellBorderLeft(summarybean.margovernment,subFont));
        table2.addCell(createTextCellBorderLeft(" ",subFont));
        table2.addCell(createTextCellBorderLeft(" ",subFont));
        table2.addCell(createTextCellBorderLeft(" ",subFont));
        table2.addCell(createTextCellBorderLeft(" ",subFont));
        table2.addCell(createTextCellBorderLeft(" ",subFont));
        table2.addCell(createTextCellBorderLeft(" ",subFont));
        table2.addCell(createTextCellBorderLeft(" ",subFont));
        table2.addCell(createTextCellBorderLeft(" ",subFont));
        table2.addCell(createTextCellBorderLeft(" ",subFont));
        table2.addCell(createTextCellBorderLeft(summarybean.subgovernment,subFont));

        table2.addCell(createTextCellBorderLeft("Number of complaints submitted to court/ ADB AM/, bypassing"+
                " project level GRM",subFont));
        table2.addCell(createTextCellBorderLeft(" ",subFont));
        table2.addCell(createTextCellBorderLeft(" ",subFont));
        table2.addCell(createTextCellBorderLeft(" ",subFont));
        table2.addCell(createTextCellBorderLeft(" ",subFont));
        table2.addCell(createTextCellBorderLeft(" ",subFont));
        table2.addCell(createTextCellBorderLeft(" ",subFont));
        table2.addCell(createTextCellBorderLeft(" ",subFont));
        table2.addCell(createTextCellBorderLeft(" ",subFont));
        table2.addCell(createTextCellBorderLeft(" ",subFont));
        table2.addCell(createTextCellBorderLeft(" ",subFont));
        table2.addCell(createTextCellBorderLeft(" ",subFont));
        table2.addCell(createTextCellBorderLeft(" ",subFont));
        table2.addCell(createTextCellBorderLeft(" ",subFont));

        table2.addCell(createTextCellBorderLeft("Total number of complaints registered in all Grievance Logs",subFont));
        table2.addCell(createTextCellBorderLeft(String.valueOf(
                Integer.parseInt(summarybean.jancomplaint)+Integer.parseInt(summarybean.jangovernment)),subFont));
        table2.addCell(createTextCellBorderLeft(String.valueOf(
                Integer.parseInt(summarybean.febcomplaint)+Integer.parseInt(summarybean.febgovernment)),subFont));
        table2.addCell(createTextCellBorderLeft(String.valueOf(
                Integer.parseInt(summarybean.marcomplaint)+Integer.parseInt(summarybean.margovernment)),subFont));
        table2.addCell(createTextCellBorderLeft(" ",subFont));
        table2.addCell(createTextCellBorderLeft(" ",subFont));
        table2.addCell(createTextCellBorderLeft(" ",subFont));
        table2.addCell(createTextCellBorderLeft(" ",subFont));
        table2.addCell(createTextCellBorderLeft(" ",subFont));
        table2.addCell(createTextCellBorderLeft(" ",subFont));
        table2.addCell(createTextCellBorderLeft(" ",subFont));
        table2.addCell(createTextCellBorderLeft(" ",subFont));
        table2.addCell(createTextCellBorderLeft(" ",subFont));
        table2.addCell(createTextCellBorderLeft(String.valueOf(
                Integer.parseInt(summarybean.subcomplaint)+Integer.parseInt(summarybean.subgovernment)),subFont));

//        table2.addCell(createTextCellBorderRight("SUBTOTAL",catFont));
//        table2.addCell(createTextCellBorderLeft(" ",subFont));
//        table2.addCell(createTextCellBorderLeft(" ",subFont));
//        table2.addCell(createTextCellBorderLeft(" ",subFont));
//        table2.addCell(createTextCellBorderLeft(" ",subFont));
//        table2.addCell(createTextCellBorderLeft(" ",subFont));
//        table2.addCell(createTextCellBorderLeft(" ",subFont));
//        table2.addCell(createTextCellBorderLeft(" ",subFont));
//        table2.addCell(createTextCellBorderLeft(" ",subFont));
//        table2.addCell(createTextCellBorderLeft(" ",subFont));
//        table2.addCell(createTextCellBorderLeft(" ",subFont));
//        table2.addCell(createTextCellBorderLeft(" ",subFont));
//        table2.addCell(createTextCellBorderLeft(" ",subFont));
//        table2.addCell(createTextCellBorderLeft(" ",subFont));
//
        table2.setKeepTogether(true);
        document.add(table2);
//
//
//
//        PdfPTable table6 = new PdfPTable(14);
//        table6.setWidthPercentage(100);
//        table6.setWidths(new float[]{6,1,1,1,1,1,1,1,1,1,1,1,1,1});
//        table6.addCell(createTextCellBorderLeft("2) CAREC Corridor 1 (Bishkek-Torugart) Project 3  (L2755) ",catFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//
//        table6.addCell(createTextCellBorderLeft("Number of new complaints registered for reporting month",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//
//        table6.addCell(createTextCellBorderLeft("Number of complaints resolved locally by GRG/PIU/ \n"+
//                "Consultant/ local government/etc.",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//
//        table6.addCell(createTextCellBorderLeft("Number of complaints submitted to court/ ADB AM/, bypassing"+
//                " project level GRM",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//
//        table6.addCell(createTextCellBorderLeft("Total number of complaints registered in all Grievance Logs",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//
//        table6.addCell(createTextCellBorderRight("SUBTOTAL",catFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.addCell(createTextCellBorderLeft(" ",subFont));
//        table6.setKeepTogether(true);
//        document.add(table6);
//
//
//        PdfPTable table7 = new PdfPTable(14);
//        table7.setWidthPercentage(100);
//        table7.setWidths(new float[]{6,1,1,1,1,1,1,1,1,1,1,1,1,1});
//        table7.addCell(createTextCellBorderLeft("3) ISSYK-KUL Sustainable Development Project (L2556) ",catFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//
//        table7.addCell(createTextCellBorderLeft("Number of new complaints registered  \n" +
//                "for reporting month\n ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//
//        table7.addCell(createTextCellBorderLeft("Number of complaints resolved locally by GRG/PIU/ \n"+
//                "Consultant/ local government/etc.",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//
//        table7.addCell(createTextCellBorderLeft("Number of complaints submitted to court/ ADB AM/, bypassing"+
//                " project level GRM",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//
//        table7.addCell(createTextCellBorderLeft("Total number of complaints registered in all Grievance Logs",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//
//        table7.addCell(createTextCellBorderRight("SUBTOTAL",catFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//
//        table7.addCell(createTextCellBorderRight("GRAND TOTALS ",catFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.addCell(createTextCellBorderLeft(" ",subFont));
//        table7.setKeepTogether(true);
//        document.add(table7);


    }


    public static PdfPCell createTextCell(String text, Font font) throws DocumentException, IOException {
        PdfPCell cell = new PdfPCell();
        Paragraph p = new Paragraph(text, font);
        p.setAlignment(Element.ALIGN_LEFT);
        cell.addElement(p);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.setBorder(Rectangle.NO_BORDER);
        return cell;
    }
    public static PdfPCell createTextCellCenter(String text, Font font) throws DocumentException, IOException {
        PdfPCell cell = new PdfPCell();
        Paragraph p = new Paragraph(text, font);
        p.setAlignment(Element.ALIGN_CENTER);
        cell.addElement(p);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.setBorder(Rectangle.NO_BORDER);
        return cell;
    }
    public static PdfPCell createTextCellRight(String text, Font font) throws DocumentException, IOException {
        PdfPCell cell = new PdfPCell();
        Paragraph p = new Paragraph(text, font);
        p.setAlignment(Element.ALIGN_RIGHT);
        cell.addElement(p);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.setBorder(Rectangle.NO_BORDER);
        return cell;
    }

    public static PdfPCell createTextCellPara(Paragraph text) throws DocumentException, IOException {
        PdfPCell cell = new PdfPCell();
        text.setAlignment(Element.ALIGN_LEFT);
        cell.addElement(text);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.setBorder(Rectangle.NO_BORDER);
        return cell;
    }

    public static PdfPCell createTextCellParaBorder(Paragraph text) throws DocumentException, IOException {
        PdfPCell cell = new PdfPCell();
        text.setAlignment(Element.ALIGN_LEFT);
        cell.addElement(text);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.setBorder(Rectangle.BOX);
        return cell;
    }

    public static PdfPCell createTextCellParaCenter(Paragraph text) throws DocumentException, IOException {
        PdfPCell cell = new PdfPCell();
        text.setAlignment(Element.ALIGN_CENTER);
        cell.addElement(text);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.setBorder(Rectangle.NO_BORDER);
        return cell;
    }

    public static PdfPCell createTextCellParaRight(Paragraph text) throws DocumentException, IOException {
        PdfPCell cell = new PdfPCell();
        text.setAlignment(Element.ALIGN_RIGHT);
        cell.addElement(text);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.setBorder(Rectangle.BOX);
        return cell;
    }

    public static PdfPCell createTextCellParaLeft(Paragraph text) throws DocumentException, IOException {
        PdfPCell cell = new PdfPCell();
        text.setAlignment(Element.ALIGN_LEFT);
        cell.addElement(text);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.setBorder(Rectangle.BOX);
        return cell;
    }

    public static PdfPCell createTextCellBorder(String text, Font font) throws DocumentException, IOException {
        PdfPCell cell = new PdfPCell();
        Paragraph p = new Paragraph(text, font);
        p.setAlignment(Element.ALIGN_CENTER);
        cell.addElement(p);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.setBorder(Rectangle.BOX);
        return cell;
    }

    public static PdfPCell createTextCellBorderLeft(String text, Font font) throws DocumentException, IOException {
        PdfPCell cell = new PdfPCell();
        Paragraph p = new Paragraph(text, font);
        p.setAlignment(Element.ALIGN_LEFT);
        cell.addElement(p);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.setBorder(Rectangle.BOX);
        return cell;
    }

    public static PdfPCell createTextCellBorderRight(String text, Font font) throws DocumentException, IOException {
        PdfPCell cell = new PdfPCell();
        Paragraph p = new Paragraph(text, font);
        p.setAlignment(Element.ALIGN_RIGHT);
        cell.addElement(p);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.setBorder(Rectangle.BOX);
        return cell;
    }
}
