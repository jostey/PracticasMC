import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.BrokenBarrierException;

public class ca2DSimulator implements ca2DSim, Runnable{

	static int[][] actual, nueva;
	private int v=0, m=0;
	static int totalVivas=0, totalMuertas=0;
	static double[][] matTotalPob;
	static int nGen, k=2;
	static int genActual=0;
	static int dim;
	static int op;
	private int ini, fin;
	static ExecutorService ex;
	static CyclicBarrier barrera;

	public static class PostBarrera implements Runnable{
		public void run(){
			
			totalVivas=0; totalMuertas=0;
			
			for(int i = 0; i < actual.length; ++i){
    			actual[i] = nueva[i].clone();
    			for(int j=0; j < actual[i].length; ++j){
    				if(actual[i][j]==1) totalVivas++;
    			}
			}
			totalMuertas = dim*dim - totalVivas;

			matTotalPob[0][genActual]=totalVivas;
			matTotalPob[1][genActual]=totalMuertas;

			genActual++;

			ca2DGraphicSimulation.update(nueva);
		}
	}


	public ca2DSimulator(int ini, int fin){
		this.ini=ini;
		this.fin=fin;
	}

	public void run(){
		caComputation(nGen);
	}

	public static void init(int dimen){
		Random rn = new Random();
		dim=dimen;
		actual=new int[dim][dim];
		nueva=new int[dim][dim];
		matTotalPob = new double[2][nGen];

		for(int i=0;i<dim;++i){
			for(int j=0;j<dim;++j){
				actual[i][j]=rn.nextInt(k);
			}
		}
	}

	public static void runSimulation(int d, int ngen, int opt){
		op=opt;
		dim=d;
		nGen=ngen;
		ca2DSimulator.init(dim);
		mainGUI.frame.validate();
		mainGUI.frame.repaint();
		mainGUI.ca2dg.addPanelImage(dim);
		mainGUI.ca2dg.update(ca2DSimulator.actual);

		ex = Executors.newFixedThreadPool(4);
		PostBarrera pb = new ca2DSimulator.PostBarrera();
		barrera = new CyclicBarrier(4, pb);
		ca2DSimulator ca;
		for(int i=0;i<4;++i){
			ca = new ca2DSimulator(i*ca2DSimulator.dim/4,(i+1)*ca2DSimulator.dim/4);
			ex.execute(ca);
		}
		ex.shutdown();
	}

	private void vec_vonNeumann(int i, int j){
		// Vecindad Von Neumann
		if(actual[i][(j-1+dim)%dim]==1) v++;

		if(actual[i][(j+1)%dim]==1) v++;
	
		if(actual[(i-1+dim)%dim][j]==1) v++;
	
		if(actual[(i+1+dim)%dim][j]==1) v++;
	}

	private void vec_Moore(int i, int j){
		// Vecindad Moore
		if(actual[i][(j-1+dim)%dim]==1) v++;

		if(actual[i][(j+1)%dim]==1) v++;

		if(actual[(i-1+dim)%dim][j]==1) v++;

		if(actual[(i+1)%dim][j]==1) v++;

		if(actual[(i-1+dim)%dim][(j-1+dim)%dim]==1) v++;

		if(actual[(i-1+dim)%dim][(j+1)%dim]==1) v++;

		if(actual[(i+1)%dim][(j-1+dim)%dim]==1) v++;

		if(actual[(i+1)%dim][(j+1)%dim]==1) v++;
	}
	
	public void nextGen(){
		for(int i=ini;i<fin;++i){
			for(int j=0;j<dim;++j){
				v=0;
					
				if(op==1) vec_vonNeumann(i,j);
				else if(op==2) vec_Moore(i,j);
				
				if (v == 3 || (actual[i][j]==1 && v == 2)){
                    nueva[i][j] = 1;
                }
                else{
                    nueva[i][j] = 0;
                }

			}
		}		
	}

	public void caComputation(int nGen){
		try{
			for(int i=0;i<nGen;++i){
				nextGen();
				barrera.await();
			}
		}
		
		catch(InterruptedException ie){}
		catch(BrokenBarrierException be){}

		ca2DGraphicSimulation.panelopt.btnStart.setText("Restart");
	}
}