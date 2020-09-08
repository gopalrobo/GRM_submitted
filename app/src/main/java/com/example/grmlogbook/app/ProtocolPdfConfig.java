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
import java.util.ArrayList;

import androidx.core.content.FileProvider;

public class ProtocolPdfConfig {

    private static Font catFont = new Font(Font.FontFamily.HELVETICA, 12,
            Font.BOLD);
    private static Font subFont = new Font(Font.FontFamily.HELVETICA, 10,
            Font.NORMAL);



    public static void printFunction(Context context, ArrayList<ComplaintLetterbean> complaintLetterbeans) {

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

            document.open();
            ProtocolPdfConfig.addMetaData(document);
            // AppConfig.addTitlePage(document);
            ProtocolPdfConfig.addContent(document,complaintLetterbeans);
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

    public static void addContent(Document document,ArrayList<ComplaintLetterbean> complaintLetterbeans) throws Exception {

        PdfPTable table1 = new PdfPTable(1);
        table1.setWidthPercentage(100);
        table1.setWidths(new int[]{1});
        table1.addCell(createTextCell("PROTOCOL FORM FOR THE GRG MEETING\n", catFont));
        table1.addCell(createTextCell(" \n", catFont));
        table1.setKeepTogether(true);
        document.add(table1);

        PdfPTable table2 = new PdfPTable(1);
        table2.setWidthPercentage(100);
        table2.setWidths(new int[]{1});
        table2.addCell(createTextCellBorderLeft("GRIEVANCE REDRESS COMMITTEE MEETING",catFont));
        Paragraph p = new Paragraph();
        p.add(new Chunk("Project : " , catFont));
        p.add(new Chunk("",subFont));
        table2.addCell(createTextCellParaLeft(p));
        Paragraph p1 = new Paragraph();
        p1.add(new Chunk("Date : " , catFont));
        p1.add(new Chunk(" ",subFont));
        table2.addCell(createTextCellParaLeft(p1));
        Paragraph p2 = new Paragraph();
        p2.add(new Chunk("Venue : " , catFont));
        p2.add(new Chunk("",subFont));
        table2.addCell(createTextCellParaLeft(p2));
        Paragraph p3 = new Paragraph();
        p3.add(new Chunk("GRG Participants : " , catFont));
        p3.add(new Chunk("",subFont));
        table2.addCell(createTextCellParaLeft(p3));
        Paragraph p4 = new Paragraph();
        p4.add(new Chunk("Agenda : " , catFont));
        p4.add(new Chunk("",subFont));
        table2.addCell(createTextCellParaLeft(p4));
        table2.setKeepTogether(true);
        document.add(table2);

        PdfPTable table4 = new PdfPTable(1);
        table4.setWidthPercentage(100);
        table4.setWidths(new int[]{1});
        table4.addCell(createTextCellBorderLeft("\nSummary of discussions: ", catFont));

        table4.setKeepTogether(true);
        document.add(table4);

        PdfPTable table3 = new PdfPTable(5);
        table3.setWidthPercentage(100);
        table3.setWidths(new float[]{0.8f,2,2,3,3});
        table3.addCell(createTextCellBorder("No",catFont));
        table3.addCell(createTextCellBorder("Complainant Name, Location, Phone",catFont));
        table3.addCell(createTextCellBorder("Nature of Complaint ",catFont));
        table3.addCell(createTextCellBorder("Proposals for resolution",catFont));
        table3.addCell(createTextCellBorder("GRG Decision ",catFont));

        PdfPTable table6 = new PdfPTable(5);
        table6.setWidthPercentage(100);
        table6.setWidths(new float[]{0.8f,2,2,3,3});

        int trig=0;
        for (int l = 0; l < complaintLetterbeans.size(); l++) {
            ComplaintLetterbean complaintLetterbean = complaintLetterbeans.get(l);

//            if (!complaintLetterbean.type.equals("Type A")) {


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

            ArrayList<Projectbean> projectbeans = complaintLetterbean.projectbeans;
            StringBuilder natureBuilder = new StringBuilder();
            if (projectbeans != null) {
                for (int j = 0; j < projectbeans.size(); j++) {
                    if (projectbeans.get(j).detail != null && projectbeans.get(j).detail.length() > 0) {
                        natureBuilder.append(projectbeans.get(j).detail).append(" , ");
                    } else {
                        natureBuilder.append("").append("");
                    }
                }
            }

            if (projectbeans != null) {
                for (int m = 0; m < projectbeans.size(); m++) {


                    if (projectbeans.get(m).getType() != null &&
                            !projectbeans.get(m).getType().replace(",", "").equals("Type A - Inquiry clarification suggestion request")) {

                        if (trig <= 4) {
                            table3.addCell(createTextCellBorder(String.valueOf(trig + 1), subFont));
                            table3.addCell(createTextCellBorder(stringBuilder.toString(), subFont));
                            table3.addCell(createTextCellBorder(natureBuilder.toString(), subFont));
                            table3.addCell(createTextCellBorder(" ", subFont));
                            table3.addCell(createTextCellBorder(" ", subFont));
                        } else {
                            table6.addCell(createTextCellBorder(String.valueOf(trig + 1), subFont));
                            table6.addCell(createTextCellBorder(stringBuilder.toString(), subFont));
                            table6.addCell(createTextCellBorder(natureBuilder.toString(), subFont));
                            table6.addCell(createTextCellBorder(" ", subFont));
                            table6.addCell(createTextCellBorder(" ", subFont));
                        }
                        trig = trig + 1;
                    }

                }
            }


        }


//        }

        table3.setKeepTogether(true);
        document.add(table3);

        table6.setKeepTogether(true);
        document.add(table6);

        PdfPTable table5 = new PdfPTable(1);
        table5.setWidthPercentage(100);
        table5.setWidths(new int[]{1});
        table5.addCell(createTextCell("\nGRC Signature", catFont));

        table5.setKeepTogether(true);
        document.add(table5);
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
        cell.setBorder(Rectangle.NO_BORDER);
        return cell;
    }

    public static PdfPCell createTextCellBorder(String text, Font font) throws DocumentException, IOException {
        PdfPCell cell = new PdfPCell();
        Paragraph p = new Paragraph(text, font);
        p.setAlignment(Element.ALIGN_LEFT);
        cell.addElement(p);
        cell.setPadding(5);
        cell.setPaddingLeft(10);
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

    public static PdfPCell createTextCellParaLeft(Paragraph text) throws DocumentException, IOException {
        PdfPCell cell = new PdfPCell();
        text.setAlignment(Element.ALIGN_LEFT);
        cell.addElement(text);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.setBorder(Rectangle.BOX);
        return cell;
    }

}
