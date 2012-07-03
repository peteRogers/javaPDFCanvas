package easel;

import java.awt.geom.AffineTransform;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfWriter;



public class PDFCanvas {
	public PdfContentByte canvas;
	private int width;
	private int height;
	public Document document;
	private int padding; 
	
	
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
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
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
	
	public void drawText(String t) {

		 canvas.beginText();                            
	     canvas.moveText(0, 0);                        
	    
	     canvas.showText(t);                   
	     canvas.endText();          

	}
	
	public void drawText(String t, float x, float y ){
		 canvas.beginText();                            
	        
	     canvas.showTextAligned(Element.ALIGN_RIGHT, t, x, height - y,0);
	    
	    
	     canvas.endText();
		
	}
	
	public void setFont(int size){
		com.itextpdf.text.Font font = FontFactory.getFont("Times-Roman");
		System.out.println(FontFactory.getRegisteredFonts());
		canvas.setFontAndSize(font.getBaseFont(), size); 
		
	}
	
	public void setFont(String f){
		com.itextpdf.text.Font font = FontFactory.getFont(f);
		
		
	}
	
	public void drawLine(float x1, float y1, float x2, float y2){
		y1 = height - y1;
		y2 = height - y2;
		canvas.saveState();
		canvas.moveTo(x1, y1);
		canvas.lineTo(x2,y2);
		canvas.stroke();
		canvas.restoreState();	
	}
	
	/**
	 * @param x
	 * @param y
	 * @param dims
	 */
	public void drawCross(float x, float y, float dims){
		canvas.saveState();
		canvas.moveTo(x-(dims/2), (height+(dims/2)) - y );
		canvas.lineTo(x+(dims/2), (height-(dims/2)) - y);
		canvas.stroke();
		canvas.restoreState();	
		canvas.saveState();
		canvas.moveTo(x-(dims/2), (height-(dims/2)) - y );
		canvas.lineTo(x+(dims/2), (height+(dims/2)) - y);
		canvas.stroke();
		canvas.restoreState();	
	}
	
	public void drawCircle(float x, float y, float  diameter){
		canvas.saveState();
		canvas.circle(x, (height-y), diameter);
		canvas.stroke();
		canvas.restoreState();	
	}
	
	public void fillCircle(float x, float y, float  diameter){
		canvas.saveState();
		canvas.circle(x, (height-y), diameter);
		canvas.fill();
		canvas.restoreState();	
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