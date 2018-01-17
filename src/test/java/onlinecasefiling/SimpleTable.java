package onlinecasefiling;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class SimpleTable {
	public static final String DEST = "F:/simple_table2.pdf";
	 
    public static void main(String[] args) throws IOException,
            DocumentException {
    	Date backdate=new Date();
    	Calendar c = Calendar.getInstance(); 
    	c.setTime(new Date()); 
    	c.add(Calendar.DATE, -1);
    	backdate = c.getTime();
    	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		String currentDate = formatter.format(backdate);
		System.out.println("Current Date="+currentDate);
    }
    private static void insertCell(PdfPTable table, String text, int align, int colspan, Font font){
    	  
    	  //create a new cell with the specified Text and Font
    	  PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
    	  //set the cell alignment
    	  cell.setHorizontalAlignment(align);
    	  //set the cell column span in case you want to merge two or more cells
    	  cell.setColspan(colspan);
    	  //in case there is no text and you wan to create an empty row
    	  if(text.trim().equalsIgnoreCase("")){
    	   cell.setMinimumHeight(10f);
    	  }
    	  //add the call to the table
    	  table.addCell(cell);
    	  
    	 }
}
