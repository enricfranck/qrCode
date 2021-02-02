
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class Main {
	JFrame main=new JFrame();
	public JLabel qr=new JLabel();
	public Main() {
		main.setTitle("");
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setLocationRelativeTo(null);
		main.setSize(300,300);
		init();
		main.setContentPane(qr);
		main.setVisible(true);
	}

	public void init() {
		String textValue="M001020/Mathématiques et Applications/M.I.S.S/S5/S6/2019-2020/facSciences/.";
	     BitMatrix bitMatrix;
	    	BufferedImage bitmap = null;
	    	QRCodeWriter write=new QRCodeWriter();
	    	try {
	    		
	        	int bitMatrixWidth = main.getWidth();
	        	int bitMatrixHeight= main.getHeight();
				bitMatrix = write.encode(textValue, 
						BarcodeFormat.QR_CODE, bitMatrixWidth, bitMatrixHeight);
				bitmap=MatrixToImageWriter.toBufferedImage(bitMatrix);
				 qr.setIcon(new ImageIcon(bitmap));
	    	} catch (Exception e) {
			}

	}
	
	public static void main(String[] args) {
		new Main();
	}
		public void test() throws WriterException, IOException, NotFoundException {
		String qrCodeData = "M001020/Mathématiques et Applications/M.I.S.S/S5/S6/2019-2020/facSciences/.";
		String filePath = "C:\\Users\\Enric Franck\\Desktop\\QRCode\\QRCode.png";
		String charset = "UTF-8"; // or "ISO-8859-1"
		Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

		createQRCode(qrCodeData, filePath, charset, hintMap, 50, 50);
		System.out.println("QR Code image created successfully!");

		 finit(readQRCode(filePath, charset, hintMap));

	}

	public static void createQRCode(String qrCodeData, String filePath,
			String charset, Map hintMap, int qrCodeheight, int qrCodewidth)
			throws WriterException, IOException {
		BitMatrix matrix = new MultiFormatWriter().encode(
				new String(qrCodeData.getBytes(charset), charset),
				BarcodeFormat.QR_CODE, qrCodewidth, qrCodeheight, hintMap);
		MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
				.lastIndexOf('.') + 1), new File(filePath));
	}

	public static String readQRCode(String filePath, String charset, Map hintMap)
			throws FileNotFoundException, IOException, NotFoundException {
		BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
				new BufferedImageLuminanceSource(
						ImageIO.read(new FileInputStream(filePath)))));
		Result qrCodeResult = new MultiFormatReader().decode(binaryBitmap,
				hintMap);
		return qrCodeResult.getText();
	}
	
	public static  void finit(String text) {
    	String titre[]={"num","mention","parcours","choix_1","choix_2","anne","code"};
		HashMap<String ,String> info=new HashMap<String ,String> ();
		String str = "";
		int l=0;
		try{
		if(!text.equals("")) {
			text=text.trim();
			for (int i=0;i<=text.length()-1; i++) {
				if(text.substring(i,i+1).equals("/")) {
						info.put(titre[l], str);
						str = "";
						l++;
					str+=text.substring(i+1,i+2);
					i=i+1;
				}
				else{
					str+=text.substring(i,i+1);
				}
			}
	}
		System.out.println("text :"+text);
		for(int k=0;k<7;k++)
		System.out.println(titre[k]+" :"+info.get(titre[k]));
		}catch(Exception e){
			e.getMessage();
		}
	}

}
