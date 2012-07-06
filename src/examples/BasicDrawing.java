package examples;

import java.io.FileNotFoundException;

import com.itextpdf.text.DocumentException;

import easel.PDFCanvas;




public class BasicDrawing {
	float maxWidth = 2000f;
	float maxHeight = 2000f;
	private PDFCanvas canvas;
	
private void setup(){
	try {
		initCanvasDims("tester.pdf");
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (DocumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	canvas.drawCross(100, 100, 50);
	canvas.setFont("myFonts/RaspoutineClassic_TB.otf100", 10);
	canvas.save();
}
public BasicDrawing(){
	setup();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new BasicDrawing();

	}
	
	public void initCanvasDims(String fileName) throws FileNotFoundException, DocumentException {

		
		canvas = new PDFCanvas(fileName, Math.round(maxWidth), Math.round(maxHeight));

	}

}


