package examples;

import java.io.FileNotFoundException;
import java.util.Random;

import com.itextpdf.text.DocumentException;

import easel.PDFCanvas;
import easel.SplineFactory;




public class SawTooth {
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
	//canvas.drawCross(100, 100, 50);
	canvas.setColorFill(255,255,255);
	//canvas.setFillTransparency(0.9f);
	//canvas.drawTriangle(100, 100, 200);
	canvas.setFont("myFonts/RaspoutineClassic_TB.otf", 60);
//	SplineFactory spline = new SplineFactory();
	canvas.setRoundEnd();
	//int pointN = 500;

	float x = 100;
	float y = maxHeight/2;
	canvas.drawText("foofo", 100, 100);
	 Random generator = new Random();
	for (int i = 0; i < 50; i ++){
			float tY = y-generator.nextInt(500);
			canvas.drawLine(x, y, x+20, tY);
			canvas.drawLine(x+20, tY, x+40, y);
		   
		    
		    x = x + 40;
	}
	
	//canvas.pushMatrix();
	
	
	
	canvas.save();
}
public SawTooth(){
	setup();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new SawTooth();

	}
	
	public void initCanvasDims(String fileName) throws FileNotFoundException, DocumentException {

		
		canvas = new PDFCanvas(fileName, Math.round(maxWidth), Math.round(maxHeight));

	}

}


