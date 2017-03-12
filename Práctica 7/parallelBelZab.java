import java.util.Random;
import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.*;
import java.lang.Math;

public class parallelBelZab implements ca2DSim, Runnable{
	static boolean start = false;
	static belZabGraphicSimulation bzg;
	static int dim;
	static int nGen;
	int ini, fin;

	static float [][][] a;
	static float [][][] b;
	static float [][][] c;
	int p = 0;
	int q = 1;
	static float alfa;
	static float beta;
	static float gamma;

	static Color color[][];

	static ExecutorService ex;
	static CyclicBarrier barrera;

	public parallelBelZab(int ini, int fin){
		this.ini=ini;
		this.fin=fin;
	}

	public void run(){
		caComputation(nGen);
	}


	public static void runSimulation(int d, int ngen, float alf, float bet, float gamm){
		int nHilos=4;
		
		dim=d;
		nGen=ngen;
		alfa=alf;
		beta=bet;
		gamma=gamm;

		setup();

		ex = Executors.newFixedThreadPool(nHilos);

		barrera = new CyclicBarrier(nHilos, 
			new Runnable() {
				public void run() {
                    belZabGraphicSimulation.update();
                }
            }
        );

		parallelBelZab bz;
		for(int i=0;i<nHilos;++i){
			bz = new parallelBelZab(i*parallelBelZab.dim/nHilos,(i+1)*parallelBelZab.dim/nHilos);
			ex.execute(bz);
		}
		ex.shutdown();
	}

	public void nextGen(){
		for (int x = ini; x < fin ; x++){
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

				color[x][y]=new Color(a[x][y][q],b[x][y][q],c[x][y][q]);
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

	public void caComputation(int nGen){
		try{
			while(start){
				nextGen();
				barrera.await();
			}
		}
		
		catch(InterruptedException ie){}
		catch(BrokenBarrierException be){}
	}

	public static float constrain(float valor, float min, float max){
        return Math.min(Math.max(valor, min),max); 
	}

	public static void setup(){
		color = new Color[dim][dim];
		Random r = new Random();
		a = new float [dim][dim][2];
		b = new float [dim][dim][2];
		c = new float [dim][dim][2];
		for (int x = 0; x < dim ; x ++) {
			for (int y = 0; y < dim ; y ++) {
				a[x][y][0] = r.nextFloat();
				b[x][y][0] = r.nextFloat();
				c[x][y][0] = r.nextFloat();
			}
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

