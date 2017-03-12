import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import java.util.concurrent.*;

public class mandelSet extends JFrame implements Runnable {
	
	private final int MAX_ITER = 100000;
	private final double ZOOM = 150;
	private static BufferedImage Imagen;

	private double zx, zy, cX, cY, tmp;

	private static int nHilos=4;
	int min, max;

	public mandelSet() {
		super("Conjunto de Mandelbrot Paralelo");
		setBounds(100, 100, 800, 600);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Imagen = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
	}

	public mandelSet(int mi, int ma) {
		this.min=mi;
		this.max=ma;
	}

	public void run() {

		for (int y = min; y < max; y++) {		//0 a 600
			for (int x = 0; x < 800; x++) {	//0 a 800
				zx = zy = 0;
				cX = (x - 400) / ZOOM;
				cY = (y - 300) / ZOOM;
				int iter = MAX_ITER;
				while (zx * zx + zy * zy < 4 && iter > 0) {
					tmp = zx * zx - zy * zy + cX;
					zy = 2.0 * zx * zy + cY;
					zx = tmp;
					iter--;
				}
				Imagen.setRGB(x, y, iter | (iter << 8));
				repaint();
			}
			
		}


	}

	public void paint(Graphics g) {
		g.drawImage(Imagen, 0, 0, this);
	}

	public static void main(String[] args) throws Exception {
		mandelSet winFrame = new mandelSet();
		
		winFrame.validate();
		winFrame.repaint();
		ExecutorService ex=Executors.newFixedThreadPool(nHilos);

		int dimy=600;

		mandelSet ms;
		for(int i=0;i<nHilos;++i){
			ms = new mandelSet(i*dimy/nHilos,(i+1)*dimy/nHilos);
			ex.execute(ms);
		}

		ex.shutdown();
		while(!ex.isTerminated()) {}
		winFrame.setVisible(true);
	}
}