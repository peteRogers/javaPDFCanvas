package easel;

import java.awt.geom.AffineTransform;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfWriter;



public class PDFCanvas {
	public PdfContentByte canvas;
	public int width;
	public int height;
	public Document document;
	
	public PDFCanvas(String fileName, int width, int height) throws FileNotFoundException, DocumentException{
		this.width = width;
		this.height = height;
		
		Rectangle rect = new Rectangle(0, 0, width, height);
	    document = new Document(rect); 
	    PdfWriter writer = PdfWriter.getInstance(document,
	                new FileOutputStream(fileName));
	    document.open();
	    canvas = writer.getDirectContent();
	}
	
	public void drawImage(String url){
		try {
			Image img = Image.getInstance(url);
			try {
				img.setAbsolutePosition(0,0);
				canvas.addImage(img);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (BadElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void drawImage(String url, float x, float y){
		try {
			Image img = Image.getInstance(url);
			try {
				img.setAbsolutePosition(x,y);
				canvas.addImage(img);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (BadElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public void drawScaledImage(String url, int scale){
		try {
			Image img = Image.getInstance(url);
			try {
				img.scalePercent(scale);
				img.setAbsolutePosition(0,0);
				canvas.addImage(img);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (BadElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void drawScaledImage(String url, int scale, float x, float y){
		try {
			Image img = Image.getInstance(url);
			try {
				img.scalePercent(scale);
				
				float scaleWidth =  (img.getWidth()*(scale/100f));
				
				float scaleHeight =  (img.getHeight()*(scale/100f));
				img.setAbsolutePosition(x-scaleWidth/2,y-scaleHeight/2);
				canvas.addImage(img);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (BadElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void pushMatrix(){
		canvas.saveState();
	}
	
	public void popMatrix(){
		canvas.restoreState();
	}
	
	public void rotate(double r){
		AffineTransform af = new AffineTransform();
		af.rotate(r);
		canvas.transform(af);
	}
	
	public void save(){
		document.close();
	}
	
	public void translate(double x, double y){
		AffineTransform af = new AffineTransform();
		y = height - y;
		af.translate(x, y);
		canvas.transform(af);
	}
	
	public void setTransparency(float t){
		PdfGState gs1 = new PdfGState();
		gs1.setFillOpacity(t);
		canvas.setGState(gs1);
	}
	
	public void drawText(String t, int fontSize) throws DocumentException, IOException{

		 canvas.beginText();                            
	     canvas.moveText(0, 0);                        
	     canvas.setFontAndSize(BaseFont.createFont(), fontSize); 
	     canvas.showText(t);                   
	     canvas.endText();          

	}
	
	public void setFont(){
		com.itextpdf.text.Font font = FontFactory.getFont("Times-Roman");
	
		System.out.println(FontFactory.getRegisteredFamilies());
		
		
	}
	
	public void makeGrid(int gridExtent, int blockSize){
		for (int y = 0; y < gridExtent; y++){
		
			for(int x = 0; x < gridExtent; x++){
				int x1 = blockSize*x;
				int x2 = x1+blockSize;
				int y1 = blockSize*y;
				int y2 = y1+blockSize;
				canvas.saveState();
				
				canvas.rectangle(x1, y1, blockSize, blockSize);
				
				canvas.stroke();
				canvas.restoreState();
				//System.out.println(x1+" "+y1+" "+x2+" "+y2);
			}
			//myX = myX + 1;
		}
		
	}
}
