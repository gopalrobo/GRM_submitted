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

public class ComplaintreceiptPdfConfig {

    private static Font catFont = new Font(Font.FontFamily.HELVETICA, 12,
            Font.BOLD);
    private static Font subFont = new Font(Font.FontFamily.HELVETICA, 10,
            Font.NORMAL);





    public static void printFunction(Context context, ComplaintLetterbean complaintLetterbean, String name, String position) {


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

//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            icon.compress(Bitmap.CompressFormat.PNG, 100, stream);
//            byte[] byteArray = stream.toByteArray();
//
//            ByteArrayOutputStream stream1 = new ByteArrayOutputStream();
//            bu.compress(Bitmap.CompressFormat.PNG, 100, stream1);
//            byte[] byteArray1 = stream1.toByteArray();

//            HeaderFooterPageEvent event = new HeaderFooterPageEvent(Image.getInstance(byteArray),
//                    Image.getInstance(byteArray1));
//            pdfWriter.setPageEvent(event);

            document.open();
            ComplaintreceiptPdfConfig.addMetaData(document);
            // AppConfig.addTitlePage(document);
            ComplaintreceiptPdfConfig.addContent(document,complaintLetterbean,name,position);
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

    public static void addContent(Document document, ComplaintLetterbean complaintLetterbean, String name, String position) throws Exception {

        PdfPTable table1 = new PdfPTable(1);
        table1.setWidthPercentage(100);
        table1.setWidths(new int[]{1});
        table1.addCell(createTextCellCenter("COMPLAINT RECEIPT CONFIRMATION NOTICE", catFont));
        ArrayList<Mailbean> mailbeans=complaintLetterbean.mailbeans;
        StringBuilder stringBuilder=new StringBuilder(1);
        for(int i=0;i<mailbeans.size();i++){
            stringBuilder.append(mailbeans.get(i).name).append(", ");
        }
        StringBuilder addBuilder=new StringBuilder(1);
        for(int i=0;i<mailbeans.size();i++){
            addBuilder.append(mailbeans.get(i).doornumber).append(". ");
            addBuilder.append(mailbeans.get(i).village).append(", ");
            addBuilder.append(mailbeans.get(i).commune).append(", ");
            addBuilder.append(mailbeans.get(i).district).append(", ");
            addBuilder.append(mailbeans.get(i).Province).append(".");
        }
        table1.addCell(createTextCell("\nTo : "+stringBuilder.toString(), catFont));
        table1.addCell(createTextCell(addBuilder.toString(), catFont));
//        table1.addCell(createTextCell("\nFrom :", subFont));
//        table1.addCell(createTextCell(complaintLetterbean.getAdboffice(), catFont));
        table1.addCell(createTextCell("Subject : Complaint Receipt Confirmation Notice", catFont));
        StringBuilder stringBuilder1=new StringBuilder(1);
        for(int k=0;k<mailbeans.size();k++){
            stringBuilder1.append(mailbeans.get(k).name).append(", ");
        }
        Paragraph p1=new Paragraph();
        p1.add(new Chunk("Dear " , subFont));
        p1.add(new Chunk(stringBuilder1.toString() , catFont));
        table1.addCell(createTextCellPara(p1));

        Paragraph p2=new Paragraph();
        p2.add(new Chunk("This is to confirm that your complaint has been received by the Grievance Redress Group and was registered at the logbook under # dated of " , subFont));
        p2.add(new Chunk(complaintLetterbean.getDate() , catFont));
        table1.addCell(createTextCellPara(p2));


//        Paragraph p1 = new Paragraph();
//        p1.add(new Chunk("Dear " , subFont));
//
//        for (int i = 0; i < complaintLetterbean.getMailbeans().size(); i++) {
//            Mailbean fields = complaintLetterbean.getMailbeans().get(i);
//            table1.addCell(createTextCell(fields.name+",", catFont));
//
//        }
//        p1.add(new Chunk("This is to confirm that your complaint has been received by the Grievance Redress Group and was registered at the logbook under # dated of " , subFont));
//        p1.add(new Chunk(complaintLetterbean.getDate() , catFont));
//        table1.addCell(createTextCellPara(p1));

        ArrayList<Projectbean> projectbeans=complaintLetterbean.projectbeans;
        StringBuilder stringBuilder2=new StringBuilder(1);
        for(int i=0;i<projectbeans.size();i++){
            stringBuilder2.append(projectbeans.get(i).detail).append(", ");
        }

        Paragraph p3=new Paragraph();
        p3.add(new Chunk("The nature of the complaint is the following : " , subFont));
        p3.add(new Chunk(stringBuilder2.toString() , catFont));
        table1.addCell(createTextCellPara(p3));

        table1.addCell(createTextCell("\n", subFont));
        document.add(table1);

        PdfPTable table2 = new PdfPTable(5);
        table2.setWidthPercentage(100);
        table2.setWidths(new int[]{1,1,1,1,1});
        table2.addCell(createTextCellBorder("#",catFont));
        table2.addCell(createTextCellBorder("Name of affected persons ",catFont));
        table2.addCell(createTextCellBorder("Geotag",catFont));
        table2.addCell(createTextCellBorder("Description of Complaint",catFont));
        table2.addCell(createTextCellBorder("Evidence Picture /Video / Both ",catFont));

        for (int i = 0; i < complaintLetterbean.getProjectbeans().size(); i++) {
            Projectbean fields = complaintLetterbean.getProjectbeans().get(i);
            table2.addCell(createTextCellBorder(fields.id, subFont));
            table2.addCell(createTextCellBorder(fields.name, subFont));
            table2.addCell(createTextCellBorder(fields.geotag, subFont));
            table2.addCell(createTextCellBorder(fields.detail, subFont));
            table2.addCell(createTextCellBorder(fields.attachment, subFont));

        }
        table2.setKeepTogether(true);
        document.add(table2);

        PdfPTable table3 = new PdfPTable(1);
        table3.setWidthPercentage(100);
        table3.setWidths(new int[]{1});

        Paragraph p4 = new Paragraph();
        p4.add(new Chunk("\nThe Grievance Redress Group shall review and address your complaint within fifteen (15) days upon the date of the receipt of the complaint.  Please note that we will be " , subFont));
        p4.add(new Chunk("", catFont));
        p4.add(new Chunk(" undertaking an investigation on your complaint and conducting GRG meetings to review your complaint and your participation during these meetings will (not) be required as it is a (Minor) major breach.  ",subFont));
        p4.add(new Chunk(" The Local Focal Person  " , subFont));
        p4.add(new Chunk(name,catFont));
        p4.add(new Chunk(" shall contact you with further details. " , subFont));
//        for (int i = 0; i < complaintLetterbean.getRepresentativebeans().size(); i++) {
//            Representativebean fields = complaintLetterbean.getRepresentativebeans().get(i);
//            Paragraph p2 = new Paragraph();
//            table3.addCell(createTextCell(fields.name+" ", catFont));
//            table3.addCell(createTextCell(fields.surname+", ", catFont));
//            table3.addCell(createTextCell(fields.relation+", ", catFont));
//            table3.addCell(createTextCell(fields.parentname+", ", catFont));
//            table3.addCell(createTextCell(fields.mobile+", ", catFont));
//            table3.addCell(createTextCell(fields.doornumber+", " +fields.village+", "+fields.commune+", "+fields.district+", "+fields.Province, catFont));
//            table3.addCell(createTextCell(fields.email, catFont));
//            table3.addCell(createTextCellPara(p2));
//
//        }
       table3.addCell(createTextCellPara(p4));


        table3.addCell(createTextCell("\nSincerely, ", subFont));
        table3.addCell(createTextCell(name, catFont));
        table3.addCell(createTextCell(position, catFont));
        table3.addCell(createTextCell("["+complaintLetterbean.getAdboffice()+"]", catFont));
        table3.setKeepTogether(true);
        document.add(table3);

        PdfPTable table4 = new PdfPTable(1);
        table4.setWidthPercentage(100);
        table4.setWidths(new int[]{1});
        table4.addCell(createTextCellCenter("\n\n", catFont));
        table4.addCell(createTextCellCenter("GRM Investigation format\n ", catFont));
        table4.setKeepTogether(true);
        document.add(table4);

        PdfPTable table5 = new PdfPTable(8);
        table5.setWidthPercentage(100);
        table5.setWidths(new int[]{1,1,1,1,1,1,1,1});
        table5.addCell(createTextCellBorder("#",catFont));
        table5.addCell(createTextCellBorder("Name of affected persons ",catFont));
        table5.addCell(createTextCellBorder("Geotag",catFont));
        table5.addCell(createTextCellBorder("Description of Complaint",catFont));
        table5.addCell(createTextCellBorder("Evidence Picture /Video / Both ",catFont));
        table5.addCell(createTextCellBorder("Minor / Major Environmental /Social /Others",catFont));
        table5.addCell(createTextCellBorder("Field investigation evidence ",catFont));
        table5.addCell(createTextCellBorder("GRM person  & Signature",catFont));

        for (int i = 0; i < complaintLetterbean.getProjectbeans().size(); i++) {
            Projectbean fields = complaintLetterbean.getProjectbeans().get(i);
            table5.addCell(createTextCellBorder(fields.id, subFont));
            table5.addCell(createTextCellBorder("", subFont));
            table5.addCell(createTextCellBorder(fields.geotag, subFont));
            table5.addCell(createTextCellBorder(fields.detail, subFont));
            table5.addCell(createTextCellBorder(fields.attachment, subFont));
            table5.addCell(createTextCellBorder("", subFont));
            table5.addCell(createTextCellBorder("", subFont));
            table5.addCell(createTextCellBorder("", subFont));

        }
        table5.setKeepTogether(true);
        document.add(table5);

        PdfPTable table6 = new PdfPTable(1);
        table6.setWidthPercentage(100);
        table6.setWidths(new int[]{1});
        table6.addCell(createTextCellCenter("\n\nMEETING INVITATION  NOTICE", catFont));
        table6.addCell(createTextCell("\nTo : "+stringBuilder.toString(), catFont));
//        table1.addCell(createTextCell("\nFrom :", subFont));
//        table1.addCell(createTextCell(complaintLetterbean.getAdboffice(), catFont));
        table6.addCell(createTextCell("Subject : MEETING  INVITATION NOTICE", catFont));
        Paragraph p5=new Paragraph();
        p5.add(new Chunk("Dear " , subFont));
        p5.add(new Chunk(stringBuilder1.toString() , catFont));
        table6.addCell(createTextCellPara(p5));

        Paragraph p6=new Paragraph();
        p6.add(new Chunk("This is to confirm that your complaint has been received and was registered at the logbook under " , subFont));
        p6.add(new Chunk(complaintLetterbean.getDate() , catFont));
        p6.add(new Chunk("and" , subFont));
        p6.add(new Chunk(" by the Grievance Redress Group " , subFont));
        table6.addCell(createTextCellPara(p6));


//        Paragraph p1 = new Paragraph();
//        p1.add(new Chunk("Dear " , subFont));
//
//        for (int i = 0; i < complaintLetterbean.getMailbeans().size(); i++) {
//            Mailbean fields = complaintLetterbean.getMailbeans().get(i);
//            table1.addCell(createTextCell(fields.name+",", catFont));
//
//        }
//        p1.add(new Chunk("This is to confirm that your complaint has been received by the Grievance Redress Group and was registered at the logbook under # dated of " , subFont));
//        p1.add(new Chunk(complaintLetterbean.getDate() , catFont));
//        table1.addCell(createTextCellPara(p1));

        Paragraph p7=new Paragraph();
        p7.add(new Chunk("The nature of the complaint is the following : " , subFont));
        p7.add(new Chunk(stringBuilder2.toString() , catFont));
        table6.addCell(createTextCellPara(p7));

        table6.addCell(createTextCell("\n", subFont));
        document.add(table6);

        PdfPTable table7 = new PdfPTable(6);
        table7.setWidthPercentage(100);
        table7.setWidths(new int[]{1,1,1,1,1,1});
        table7.addCell(createTextCellBorder("#",catFont));
        table7.addCell(createTextCellBorder("Name of affected persons ",catFont));
        table7.addCell(createTextCellBorder("Geotag",catFont));
        table7.addCell(createTextCellBorder("Description of Complaint",catFont));
        table7.addCell(createTextCellBorder("Evidence Picture /Video / Both ",catFont));
        table7.addCell(createTextCellBorder("Minor / Major Environmental /Social /Others ",catFont));

        for (int i = 0; i < complaintLetterbean.getProjectbeans().size(); i++) {
            Projectbean fields = complaintLetterbean.getProjectbeans().get(i);
            table7.addCell(createTextCellBorder(fields.id, subFont));
            table7.addCell(createTextCellBorder(fields.name, subFont));
            table7.addCell(createTextCellBorder(fields.geotag, subFont));
            table7.addCell(createTextCellBorder(fields.detail, subFont));
            table7.addCell(createTextCellBorder(fields.attachment, subFont));
            table7.addCell(createTextCellBorder("", subFont));

        }
        table7.setKeepTogether(true);
        document.add(table7);

        PdfPTable table8 = new PdfPTable(1);
        table8.setWidthPercentage(100);
        table8.setWidths(new int[]{1});
        Paragraph p8 = new Paragraph();
        p8.add(new Chunk("\nThe Grievance Redress Group has reviewed your complaint within fifteen (15) days and found to  " , subFont));
        p8.add(new Chunk("", catFont));
        p8.add(new Chunk(" based on our field investigation.   We are organizing the GRG meeting on  " , subFont));
        p8.add(new Chunk(" Please contact the the Local Focal Person " , subFont));
//        for (int i = 0; i < complaintLetterbean.getRepresentativebeans().size(); i++) {
//            Representativebean fields = complaintLetterbean.getRepresentativebeans().get(i);
//            Paragraph p2 = new Paragraph();
//            table3.addCell(createTextCell(fields.name+" ", catFont));
//            table3.addCell(createTextCell(fields.surname+", ", catFont));
//            table3.addCell(createTextCell(fields.relation+", ", catFont));
//            table3.addCell(createTextCell(fields.parentname+", ", catFont));
//            table3.addCell(createTextCell(fields.mobile+", ", catFont));
//            table3.addCell(createTextCell(fields.doornumber+", " +fields.village+", "+fields.commune+", "+fields.district+", "+fields.Province, catFont));
//            table3.addCell(createTextCell(fields.email, catFont));
//            table3.addCell(createTextCellPara(p2));
//
//        }
        table8.addCell(createTextCellPara(p8));


        table8.addCell(createTextCell("\nSincerely, ", subFont));
        table8.addCell(createTextCell(name, catFont));
        table8.addCell(createTextCell(position, catFont));
        table8.addCell(createTextCell("["+complaintLetterbean.getAdboffice()+"]", catFont));
        table8.setKeepTogether(true);
        document.add(table8);
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

    public static PdfPCell createTextCellParaBorder(Paragraph text) throws DocumentException, IOException {
        PdfPCell cell = new PdfPCell();
        text.setAlignment(Element.ALIGN_CENTER);
        cell.addElement(text);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.setBorder(Rectangle.BOX);
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
        cell.setPadding(10);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.setBorder(Rectangle.BOX);
        return cell;
    }
}
