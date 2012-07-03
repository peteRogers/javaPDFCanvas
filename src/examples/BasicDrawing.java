package examples;

import java.io.FileNotFoundException;

import com.itextpdf.text.DocumentException;

import easel.PDFCanvas;




public class BasicDrawing {
	float maxWidth = 2000f;
	float maxHeight = 2000f;
	private PDFCanvas canvas;
	

public BasicDrawing(){
	try {
		initCanvasDims("tester.pdf");
		canvas.drawCross(100, 100, 50);
		canvas.setFont(100);
		canvas.save();
		
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (DocumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new BasicDrawing();

	}
	
	public void initCanvasDims(String fileName) throws FileNotFoundException, DocumentException {

		
		canvas = new PDFCanvas(fileName, Math.round(maxWidth), Math.round(maxHeight));

	}

}


