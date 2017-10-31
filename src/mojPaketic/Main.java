package mojPaketic;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import rafgfxlib.ImageViewer;
import rafgfxlib.Util;

public class Main {

	public static void main(String[] args) {
		BufferedImage image = Util.loadImage("doge.png");
		
		if(image == null) { System.out.println("Nema slike!"); return; }
		
		WritableRaster source = image.getRaster();
		// Posto sliku okrecemo na stranu, sirina rezultata je visina originala,
		// a visina mu je sirina originala
		WritableRaster target = Util.createRaster(source.getHeight(), source.getWidth(), false);

		int rgb[] = new int[3];
		
		for(int y = 0; y < source.getHeight(); y++)
		{
			for(int x = 0; x < source.getWidth(); x++)
			{
				source.getPixel(x, y, rgb);
			
				// Najprostiji nacin da dobijemo grayscale nijansu od boje je
				// da nadjemo prosjek od sve tri komponente i onda ih sve
				// izjednacimo na taj prosjek.
				int i = (rgb[0] + rgb[1] + rgb[2]) / 3;
				
				rgb[0] = i;
				rgb[1] = i;
				rgb[2] = i;
				
				target.setPixel(x, y, rgb);
			}
		}
		// Konverzija rastera u BufferedImage i prikaz u prozoru
		ImageViewer.showImageWindow(Util.rasterToImage(target), "RAF Racunarska Grafika");
	}
	
	
	
}
