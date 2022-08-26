package com.createpdf;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.stream.Stream;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
//import com.itextpdf.text.pdf.parser.Path;
//import com.itextpdf.text.pdf.parser.clipper.Paths;

public class CreatePdf {

	public static void main(String[] args) throws DocumentException, URISyntaxException, IOException {
		String s = "Hello JAVA in PDF!";
		String s1 = "Hopefully this Document will be created in Mulesoft by Vitaly Zhakun";
		String d = "C:\\Users\\vi\\Desktop\\PDF\\hello.pdf";
		String d1 = "C:\\Users\\vi\\Desktop\\PDF\\hello-table.pdf";
		createPdf(s, d);
		createCustomTable(s, s1, d1);
		String h = "header1, header2, header3";
		System.out.println(Arrays.toString(h.split(", ")));
		System.out.println(h.split(", ")[0] + ", " + h.split(", ")[1] + ", " + h.split(", ")[2]);

	}
	
	public static void createPdf(String text, String fileDestination) throws FileNotFoundException, DocumentException {
		Document doc = new Document();
		PdfWriter.getInstance(doc, new FileOutputStream(fileDestination));
		doc.open();
		Font f = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 19, BaseColor.BLUE);
		Chunk ch = new Chunk(text, f);
		doc.add(ch);
		doc.close();
		System.out.println("The Document was created");
	}
	public static void createCustomTable(String text, String text1, String fileDestination) throws DocumentException, URISyntaxException, IOException {
		Document doc = new Document();
		PdfWriter.getInstance(doc, new FileOutputStream(fileDestination));
		doc.open();
		Font f = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 19, BaseColor.BLUE);
		Chunk ch = new Chunk(text, f);
		doc.add(ch);
		Paragraph pg = new Paragraph();
		Font f1 = FontFactory.getFont(FontFactory.COURIER_BOLD, 16, BaseColor.GREEN);
		pg.setFont(f1);
		pg.add(text1);
		doc.add(pg);
		PdfPTable table = new PdfPTable(3);
		addTableHeader(table);
		addRows(table);
		addCustomRows(table);
		Paragraph pg1 = new Paragraph();
		pg1.add(table);
		doc.add(pg1);
		doc.close();
		System.out.println("The Document was created");
	}
	
	private static void addTableHeader(PdfPTable table) {
	    Stream.of("accountId", "firstName", "dob")
	      .forEach(columnTitle -> {
	        PdfPCell header = new PdfPCell();
	        header.setBackgroundColor(BaseColor.YELLOW);
	        header.setBorderWidth(2);
	        header.setPhrase(new Phrase(columnTitle));
	        table.addCell(header);
	    });
	}
	
	private static void addRows(PdfPTable table) {
	    table.addCell("757");
	    table.addCell("Nelson");
	    table.addCell("2000-05-07");
	}
	
	private static void addCustomRows(PdfPTable table) throws URISyntaxException, BadElementException, IOException {
	    //Path path = Paths.get(ClassLoader.getSystemResource("C:\\Users\\vi\\Desktop\\PDF\\logo-java.png").toURI());
	    //Image img = Image.getInstance(path.toAbsolutePath().toString());
	    //img.scalePercent(10);

	    //PdfPCell imageCell = new PdfPCell(img);
	    //table.addCell(imageCell);

	    PdfPCell horizontalAlignCell = new PdfPCell(new Phrase("row 2, col 2"));
	    horizontalAlignCell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(horizontalAlignCell);

	    PdfPCell verticalAlignCell = new PdfPCell(new Phrase("row 2, col 3"));
	    verticalAlignCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
	    table.addCell(verticalAlignCell);
	}

}
