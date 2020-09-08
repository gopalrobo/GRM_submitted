package com.example.grmlogbook.app;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import com.example.grmlogbook.ComplaintLetter.ComplaintLetterbean;
import com.example.grmlogbook.Mailbean;
import com.example.grmlogbook.Projectbean;
import com.itextpdf.text.Chunk;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import androidx.core.content.FileProvider;

public class SnaglistPdfConfig {

    private static Font catFont = new Font(Font.FontFamily.HELVETICA, 12,
            Font.BOLD);
    private static Font subFont = new Font(Font.FontFamily.HELVETICA, 10,
            Font.NORMAL);


    public static void printFunction(Context context, ArrayList<ComplaintLetterbean> complaintLetterbeans, String name) {

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
            SnaglistPdfConfig.addMetaData(document);
            // AppConfig.addTitlePage(document);
            SnaglistPdfConfig.addContent(document, complaintLetterbeans, name);
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

    public static void addContent(Document document, ArrayList<ComplaintLetterbean> complaintLetterbeans, String name) throws Exception {

        PdfPTable table1 = new PdfPTable(1);
        table1.setWidthPercentage(100);
        table1.setWidths(new int[]{1});
        table1.addCell(createTextCellCenter("SNAG LIST EXAMPLE ", catFont));
        table1.addCell(createTextCell("Please record issues which are not grievances (as yet) such as community request for some improvements, request for temporary access to the field, request for information etc. \n", subFont));
        table1.setKeepTogether(true);
        document.add(table1);

        Date todaysdate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

        PdfPTable table2 = new PdfPTable(1);
        table2.setWidthPercentage(100);
        table2.setWidths(new int[]{1});
        Paragraph p = new Paragraph();
        p.add(new Chunk("PROJECT ", catFont));
        p.add(new Chunk("42285-013-Integrated Urban Environmental Management in the Tonle Sap Basin ", subFont));
        p.add(new Chunk("\nSNAG LIST  as of  (Date) ", catFont));
        p.add(new Chunk(format.format(todaysdate), subFont));
        table2.addCell(createTextCellParaLeft(p));
        Paragraph p1 = new Paragraph();
        p1.add(new Chunk("Person/s handling the issue: ", catFont));
        p1.add(new Chunk(name, subFont));
        table2.addCell(createTextCellParaLeft(p1));
        table2.setKeepTogether(true);
        document.add(table2);

        PdfPTable table3 = new PdfPTable(6);
        table3.setWidthPercentage(100);
        table3.setWidths(new float[]{0.5f, 2, 2, 2, 3f, 3f});
        table3.addCell(createTextCellBorderLeft("No", catFont));
        table3.addCell(createTextCellBorderLeft("DATE", catFont));
        table3.addCell(createTextCellBorderLeft("VILLAGE /AP NAME", catFont));
        table3.addCell(createTextCellBorderLeft("GEOTAGS", catFont));
        table3.addCell(createTextCellBorderLeft("SNAG DESCRIPTION", catFont));
        table3.addCell(createTextCellBorderLeft("SNAG Resolution Decision / Proof", catFont));

        PdfPTable table4 = new PdfPTable(6);
        table4.setWidthPercentage(100);
        table4.setWidths(new float[]{0.5f, 2, 2, 2, 3f, 3f});

        int trig = 0;
        for (int l = 0; l < complaintLetterbeans.size(); l++) {
            ComplaintLetterbean complaintLetterbean = complaintLetterbeans.get(l);
            ArrayList<Projectbean> projectbeans = complaintLetterbean.projectbeans;


            StringBuilder natureBuilder = new StringBuilder();
            if (projectbeans != null) {
                for (int j = 0; j < projectbeans.size(); j++) {
                    if (projectbeans.get(j).geotag != null && projectbeans.get(j).geotag.length() > 0) {
                        natureBuilder.append(projectbeans.get(j).geotag).append(" , ");
                    } else {
                        natureBuilder.append("").append("");
                    }
                }
            }


            ArrayList<Mailbean> mailbeans = complaintLetterbean.mailbeans;
            StringBuilder stringBuilder = new StringBuilder();
            if (mailbeans != null) {
                for (int k = 0; k < mailbeans.size(); k++) {
                    if (mailbeans.get(k).salutation != null && mailbeans.get(k).salutation.length() > 0) {
                        stringBuilder.append(mailbeans.get(k).salutation).append(". ");
                    } else {
                        stringBuilder.append("").append("");
                    }
                    if (mailbeans.get(k).name != null && mailbeans.get(k).name.length() > 0) {
                        stringBuilder.append(mailbeans.get(k).name).append(", ");
                    } else {
                        stringBuilder.append("").append("");
                    }
                    if (mailbeans.get(k).surname != null && mailbeans.get(k).surname.length() > 0) {
                        stringBuilder.append(mailbeans.get(k).surname).append(", ");
                    } else {
                        stringBuilder.append("").append("");
                    }
                    if (mailbeans.get(k).doornumber != null && mailbeans.get(k).doornumber.length() > 0) {
                        stringBuilder.append(mailbeans.get(k).doornumber).append(", ");
                    } else {
                        stringBuilder.append("").append("");
                    }
                    if (mailbeans.get(k).village != null && mailbeans.get(k).village.length() > 0) {
                        stringBuilder.append(mailbeans.get(k).village).append(", ");
                    } else {
                        stringBuilder.append("").append("");
                    }
                    if (mailbeans.get(k).commune != null && mailbeans.get(k).commune.length() > 0) {
                        stringBuilder.append(mailbeans.get(k).commune).append(", ");
                    } else {
                        stringBuilder.append("").append("");
                    }
                    if (mailbeans.get(k).district != null && mailbeans.get(k).district.length() > 0) {
                        stringBuilder.append(mailbeans.get(k).district).append(", ");
                    } else {
                        stringBuilder.append("").append("");
                    }
                    if (mailbeans.get(k).Province != null && mailbeans.get(k).Province.length() > 0) {
                        stringBuilder.append(mailbeans.get(k).Province).append(". ");
                    } else {
                        stringBuilder.append("").append("");
                    }
                    if (mailbeans.get(k).mobile != null && mailbeans.get(k).mobile.length() > 0) {
                        stringBuilder.append(mailbeans.get(k).mobile).append(" . ");
                    } else {
                        stringBuilder.append("").append("");
                    }
                }
            }

            if (projectbeans != null) {
                for (int m = 0; m < projectbeans.size(); m++) {


                    if (projectbeans.get(m).getType() != null &&
                            projectbeans.get(m).getType().replace(",", "").equals("Type A - Inquiry clarification suggestion request")) {

                        if (trig <= 6) {
                            table3.addCell(createTextCellBorder(String.valueOf(trig + 1), subFont));
                            table3.addCell(createTextCellBorder(complaintLetterbean.date, subFont));
                            table3.addCell(createTextCellBorder(stringBuilder.toString(), subFont));
                            table3.addCell(createTextCellBorder(natureBuilder.toString(), subFont));
                            table3.addCell(createTextCellBorder(" ", subFont));
                            table3.addCell(createTextCellBorder(" ", subFont));
                        } else {
                            table4.addCell(createTextCellBorder(String.valueOf(trig + 1), subFont));
                            table4.addCell(createTextCellBorder(complaintLetterbean.date, subFont));
                            table4.addCell(createTextCellBorder(stringBuilder.toString(), subFont));
                            table4.addCell(createTextCellBorder(natureBuilder.toString(), subFont));
                            table4.addCell(createTextCellBorder(" ", subFont));
                            table4.addCell(createTextCellBorder(" ", subFont));

                        }
                        trig = trig + 1;
                    }

                }
            }
        }


        table3.setKeepTogether(true);
        document.add(table3);

        table4.setKeepTogether(true);
        document.add(table4);
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
