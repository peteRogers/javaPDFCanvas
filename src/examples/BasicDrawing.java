package examples;

import java.io.FileNotFoundException;

import com.itextpdf.text.DocumentException;

import easel.PDFCanvas;
import easel.SplineFactory;




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
	//canvas.drawCross(100, 100, 50);
	canvas.setColorFill(255,255,255);
	canvas.setFillTransparency(0.9f);
	//canvas.drawTriangle(100, 100, 200);
	//canvas.setFont("myFonts/RaspoutineClassic_TB.otf100", 10);
//	SplineFactory spline = new SplineFactory();
	canvas.setRoundEnd();
	int pointN = 500;
	double[] c = new double[pointN*3];
	for (int i = 0; i < pointN*3; i =i + 3){
		     c[i]= i*pointN;
		     c[i+1]=i*pointN;
		    
		     c[i+2]=0;
		     
		     
	}
	double[] spline3 = SplineFactory.createCatmullRom (c, 50); 
	//canvas.pushMatrix();
	
	canvas.initCurve((float)spline3[0], (float)spline3[1]);
	for (int i = 0; i < spline3.length-3; i =i + 3){
	     canvas.drawCurve((float)spline3[i], (float)spline3[i+1],(float)spline3[i+3], (float)spline3[i+4]);
	     //c[i+1]=Math.random()* 100;
	   //.out.println(spline3[i]+" "+((float)spline3[i+1]-maxHeight));
	     //c[i+2]=0;
	     
	     
}	canvas.endCurve();
for (int i = 0; i < spline3.length-3; i =i + 3){
    canvas.drawCross((float)spline3[i], (float)spline3[i+1],5);
    //c[i+1]=Math.random()* 100;
  //.out.println(spline3[i]+" "+((float)spline3[i+1]-maxHeight));
    //c[i+2]=0;
    
    
}	
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


