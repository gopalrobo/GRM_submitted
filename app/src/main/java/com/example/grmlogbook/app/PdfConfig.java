package com.example.grmlogbook.app;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;

import com.example.grmlogbook.ComplaintLetter.ComplaintLetterbean;
import com.example.grmlogbook.Mailbean;
import com.example.grmlogbook.Projectbean;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class PdfConfig {

    private static Font catFont = new Font(Font.FontFamily.HELVETICA, 12,
            Font.BOLD);
    private static Font subFont = new Font(Font.FontFamily.HELVETICA, 10,
            Font.NORMAL);


    public static void addMetaData(Document document) {
        document.addTitle("My first PDF");
        document.addSubject("Using iText");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("Lars Vogel");
        document.addCreator("Lars Vogel");
    }

    public static void addContent(Document document,
                                  ArrayList<ComplaintLetterbean> complaintLetterbeans,
                                  String name, String position, Handler handler) throws Exception {

        PdfPTable table1 = new PdfPTable(1);
        table1.setWidthPercentage(100);
        table1.setWidths(new int[]{1});
        table1.addCell(createTextCell("GRIEVANCE REGISTRATION LOGBOOK", catFont));
        Paragraph p1 = new Paragraph();
        p1.add(new Chunk("\nProject  ", catFont));
        // p1.add(new Chunk(complaintLetterbean.projectname , subFont));
        p1.add(new Chunk("\n\nGrievance Focal Location  ", catFont));
//        p1.add(new Chunk(complaintLetterbean.projectname , subFont));
        p1.add(new Chunk("\n\nGrievance Focal Person  ", catFont));
        p1.add(new Chunk(name, subFont));
        table1.addCell(createTextCellParaLeft(p1));
        Paragraph p2 = new Paragraph();
        p2.add(new Chunk("\nA    ", catFont));
        p2.add(new Chunk("Inquiry, clarification, suggestion, request\n", subFont));
        p2.add(new Chunk("B    ", catFont));
        p2.add(new Chunk("Complaint regarding alleged breach of the SPS 2009 or Public Communication Policy 2011\n", subFont));
        p2.add(new Chunk("C    ", catFont));
        p2.add(new Chunk("Allegation of fraud or corruption\n", subFont));
        p2.add(new Chunk("IR   ", catFont));
        p2.add(new Chunk("Involuntary Resettlement\n", subFont));
        p2.add(new Chunk("ENV  ", catFont));
        p2.add(new Chunk("Environment\n", subFont));
        table1.addCell(createTextCellParaLeft(p2));
        table1.setKeepTogether(true);
        document.add(table1);

        PdfPTable table3 = new PdfPTable(1);
        table3.setWidthPercentage(100);
        table3.setWidths(new int[]{1});
        table3.addCell(createTextCellCenter("GRIEVANCE REGISTRATION LOG BOOK \n ", catFont));
        table3.setKeepTogether(true);
        document.add(table3);


        PdfPTable table2 = new PdfPTable(6);
        table2.setWidthPercentage(100);
        table2.setWidths(new int[]{1, 1, 1, 1, 1, 1});
        table2.addCell(createTextCellBorder("Grievance No & Date ", catFont));
        table2.addCell(createTextCellBorder("Affected person Name Address, telephone ", catFont));
        table2.addCell(createTextCellBorder("Location of impact (village name, KM Chainage)  Geotag", catFont));
        table2.addCell(createTextCellBorder("Grievance  Summary", catFont));
        table2.addCell(createTextCellBorder("Photos / Videos", catFont));
        table2.addCell(createTextCellBorder("Type (A, B,C) & nature of Grievance (SOC or ENV)", catFont));


        for (int l = 0; l < 15; l++) {
            Message personal = new Message();
            personal.obj = "Processing ( Complaints " + String.valueOf(l+1) + "/" + String.valueOf(
                    complaintLetterbeans.size()) + ")...";
            handler.sendMessage(personal);
            ComplaintLetterbean complaintLetterbean = complaintLetterbeans.get(l);
            ArrayList<Mailbean> mailbeans = complaintLetterbean.mailbeans;
            StringBuilder stringBuilder = new StringBuilder();
            if (mailbeans != null) {
                for (int k = 0; k < mailbeans.size(); k++) {
                    if (mailbeans.get(k).salutation != null) {
                        stringBuilder.append(mailbeans.get(k).salutation).append(". ");
                    } else {
                        stringBuilder.append("").append("");
                    }
                    if (mailbeans.get(k).name != null) {
                        stringBuilder.append(mailbeans.get(k).name).append(", ");
                    } else {
                        stringBuilder.append("").append("");
                    }
                    if (mailbeans.get(k).surname != null) {
                        stringBuilder.append(mailbeans.get(k).surname).append(", ");
                    } else {
                        stringBuilder.append("").append("");
                    }
                    if (mailbeans.get(k).doornumber != null) {
                        stringBuilder.append(mailbeans.get(k).doornumber).append(", ");
                    } else {
                        stringBuilder.append("").append("");
                    }
                    if (mailbeans.get(k).village != null) {
                        stringBuilder.append(mailbeans.get(k).village).append(", ");
                    } else {
                        stringBuilder.append("").append("");
                    }
                    if (mailbeans.get(k).commune != null) {
                        stringBuilder.append(mailbeans.get(k).commune).append(", ");
                    } else {
                        stringBuilder.append("").append("");
                    }
                    if (mailbeans.get(k).district != null) {
                        stringBuilder.append(mailbeans.get(k).district).append(", ");
                    } else {
                        stringBuilder.append("").append("");
                    }
                    if (mailbeans.get(k).Province != null) {
                        stringBuilder.append(mailbeans.get(k).Province).append(". ");
                    } else {
                        stringBuilder.append("").append("");
                    }
                    if (mailbeans.get(k).mobile != null) {
                        stringBuilder.append(mailbeans.get(k).mobile).append(" . ");
                    } else {
                        stringBuilder.append("").append("");
                    }
                }
            }

            ArrayList<Projectbean> projectbeans = complaintLetterbean.projectbeans;
            StringBuilder stringBuilder1 = new StringBuilder();
            if (projectbeans != null) {
                for (int j = 0; j < projectbeans.size(); j++) {
                    if (projectbeans.get(j).geotag != null) {
                        stringBuilder1.append(projectbeans.get(j).geotag).append(" , ");
                    } else {
                        stringBuilder1.append("").append("");
                    }
                }
            }

            StringBuilder stringBuilder2 = new StringBuilder();
            if (projectbeans != null) {
                for (int j = 0; j < projectbeans.size(); j++) {
                    if (projectbeans.get(j).detail != null) {
                        stringBuilder2.append(projectbeans.get(j).detail).append(" , ");
                    } else {
                        stringBuilder2.append("").append("");
                    }
                }
            }


            table2.addCell(createTextCellBorder(complaintLetterbean.projectname + "\n" + complaintLetterbean.date, subFont));
            table2.addCell(createTextCellBorder(stringBuilder.toString(), subFont));
            table2.addCell(createTextCellBorder(stringBuilder1.toString(), subFont));
            table2.addCell(createTextCellBorder(stringBuilder2.toString(), subFont));
            String uriProfile = "";
            if (complaintLetterbean.projectbeans != null && complaintLetterbean.projectbeans.size() > 0
                    && complaintLetterbean.projectbeans.get(0).attachment.length() > 0) {
                uriProfile = complaintLetterbean.projectbeans.get(0).attachment;
            } else if (complaintLetterbean.projectbeans != null && complaintLetterbean.projectbeans.size() > 1
                    && complaintLetterbean.projectbeans.get(1).attachment.length() > 0) {
                uriProfile = complaintLetterbean.projectbeans.get(1).attachment;
            }
            if (uriProfile != null && uriProfile.length() > 0) {
                try {
                    Image image = cretaeImage(uriProfile);
                    if (image != null) {
                        Image img = Image.getInstance(image);
                        PdfPCell cell = new PdfPCell(img, true);
                        cell.setBorder(Rectangle.BOX);
                        cell.setPadding(5);
                        table2.addCell(cell);
                    } else {
                        table2.addCell(createTextCellBorder("", catFont));
                    }
                }catch (OutOfMemoryError E){
                    table2.addCell(createTextCellBorder("", catFont));
                }

            } else {
                table2.addCell(createTextCellBorder("", catFont));
            }

            table2.addCell(createTextCellBorder("", subFont));

        }
        table2.setKeepTogether(true);
        document.add(table2);

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
        p.setAlignment(Element.ALIGN_CENTER);
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
        cell.setBorder(Rectangle.NO_BORDER);
        return cell;
    }

    private static Image cretaeImage(String uri) {

        try {
            URL url = new URL(uri);

            ByteArrayOutputStream stream = new ByteArrayOutputStream();

            Bitmap image =  decodeSampledBitmapFromResource( url, 100, 100);
            image.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();

            Image convertBmp = Image.getInstance(byteArray);
            convertBmp.setAlignment(Element.ALIGN_CENTER);
            convertBmp.scaleToFit(50, 50);
            return convertBmp;
        } catch (Exception e) {
            return null;
        }

    }

    public static Bitmap decodeSampledBitmapFromResource(URL resId,
                                                         int reqWidth, int reqHeight) throws Exception{

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(resId.openConnection().getInputStream(), null, options);
        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeStream(resId.openConnection().getInputStream(), null, options);
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
}
