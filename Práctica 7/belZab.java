import java.util.Random;
import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.lang.Math;

public class belZab {
	static boolean start = false;
	static belZabGraphicSimulation bzg;
	static int dim;
	static float [][][] a;
	static float [][][] b;
	static float [][][] c;
	static int p = 0;
	static int q = 1;
	static final float alfa = 1.2f;
	static final float beta = 1.0f;
	static final float gamma = 1.0f;

	public static float constrain(float valor, float min, float max){
        return Math.min(Math.max(valor, min),max); 
	}

	public static void setup(){
		Random r = new Random();
		a = new float [dim][dim][2];
		b = new float [dim][dim][2];
		c = new float [dim][dim][2];
		for (int x = 0; x < dim ; x ++) {
			for (int y = 0; y < dim ; y ++) {
				a[x][y][p] = r.nextFloat();
				b[x][y][p] = r.nextFloat();
				c[x][y][p] = r.nextFloat();
			}
		}
	}

	public static void compute(){
		for (int x = 0; x < dim ; x++){
			for (int y = 0; y < dim ; y++){
				float c_a = 0.0f;
				float c_b = 0.0f;
				float c_c = 0.0f;
				for (int i = x-1; i <= x+1; i++){
					for (int j = y-1; j <= y+1; j++) {
						c_a += a[(i+ dim)%dim][(j+dim)%dim][p];
						c_b += b[(i+ dim)%dim][(j+dim)%dim][p];
						c_c += c[(i+ dim)%dim][(j+dim)%dim][p];
					}
				}
				c_a /= 9.0f;
				c_b /= 9.0f;
				c_c /= 9.0f;
				a[x][y][q] = constrain(c_a+c_a*(alfa*c_b-gamma*c_c),0.0f,1.0f);
				b[x][y][q] = constrain(c_b+c_b*(beta*c_c-alfa*c_a),0.0f,1.0f);
				c[x][y][q] = constrain(c_c+c_c*(gamma*c_a-beta*c_b),0.0f,1.0f);
			}
		}
		if(p==0){
			p = 1;
			q = 0;
		}
		else {
			p = 0;
			q = 1;
		}

	}

	public static void main(String[] args) throws Exception{

		bzg = new belZabGraphicSimulation();
		JFrame win = new JFrame();
		win.add(bzg.mainBZ);
		win.setVisible(true);
		
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win.setSize(800,600);
		
	}
}


