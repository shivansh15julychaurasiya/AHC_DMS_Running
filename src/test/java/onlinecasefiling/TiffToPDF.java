package onlinecasefiling;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.io.FileChannelRandomAccessSource;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.RandomAccessFileOrArray;
import com.itextpdf.text.pdf.codec.TiffImage;

public class TiffToPDF extends TiffImage {

	public static void main(String args[]){

	String tiffFilename="D:\\tif\\test.tif";
	String pdfFilename="D:\\tif\\test1.pdf";
	
	TiffToPDF tdf= new TiffToPDF();
	tdf.Convert(tiffFilename, pdfFilename);
	
}

public boolean Convert(String tiffFilename, String pdfFilename)
	{
		boolean converted = false;
		try {
			Document pdf = new Document();		
			PdfWriter.getInstance(pdf, new FileOutputStream(pdfFilename));
			pdf.open();
			
				try 
				{
					FileChannelRandomAccessSource source = new FileChannelRandomAccessSource(new FileInputStream(tiffFilename).getChannel());
					RandomAccessFileOrArray file = new RandomAccessFileOrArray(source);
					int pages = TiffImage.getNumberOfPages(file);
					
					for (int page = 1; page <= pages; page++){
						Image img = TiffImage.getTiffImage(file, page);
					     pdf.add(img);
					 }
					
				} catch (IOException ex) {
          try {
			throw ex;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        }	
			
			
			pdf.close();
			
			 if(!pdfFilename.isEmpty())
				 converted=true;
			
		} catch (FileNotFoundException | DocumentException e1) {
      try {
		throw e1;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
		
		return converted;
	}


}
