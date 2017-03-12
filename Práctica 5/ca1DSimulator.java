import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.BrokenBarrierException;
import java.lang.Thread;
import static java.lang.Math.*;

public class ca1DSimulator implements ca1DSim, Runnable {
	static int nCel, nGen, k=2;
	static int genAct=0;
	static int[] actual, nueva;
	private int ini, fin;
	private static CyclicBarrier barrera;
	//private static ca1DGraphicSimulation win;
	// Entropia
	static double[] eEspacial;
	static int[] eVectTemporal;
	static double eTemporal;
	// Hamming
	static double[] vHamming;
	// Regla
	static int[] regla;
	// Hilos
	static int nhilos=4;
	static Thread hilos[] = new Thread[nhilos];


	// Copia e impresión de la generación después de que todos los hilos lleguen
	// a la barrera mediante PostBarrera.
	public static class PostBarrera implements Runnable{
		public void run(){
			eEspacial[genAct]=entropia(actual);
			eVectTemporal[genAct]=actual[actual.length/2];

			vHamming[genAct-1]=hamming();

			actual=nueva.clone();
			genAct++;
		}
	}

	public ca1DSimulator(int i, int f){
		this.ini=i;
		this.fin=f;
	}


	public static int hamming(){
		int c=0;
		for(int i=0;i<actual.length;++i){
			if(actual[i]!=nueva[i]){
				c++;
			}
		}
		return c;
	}

	private static double log2(double x) { return (log(x)/log(2));}

	public static double entropia(int[] vector) {
		double p1=0;
		double p0=0;
		double entropia=0;
		double contUnos=0;
		double contCeros=0;

		for(int i=0;i<vector.length;++i) {
			if(vector[i]==1) {
				contUnos++;
			}
			if(vector[i]==0) {
				contCeros++;
			}
		}
		p1=(double)contUnos/vector.length;
		p0=(double)contCeros/vector.length;
		if(p0==0) {
			entropia=-1*(p1*log2(p1));
		}
		else {
			if(p1==0) {
				entropia=-1*(p0*log2(p0));
			}
			else {
				entropia=-1*(p0*log2(p0)+p1*log2(p1));
			}
		}
		return entropia;
	}


	public static void init(int ncel, int ngen, int[] act, int[] regl){
		genAct=0;
		nCel=ncel;
		nGen=ngen;
		actual=act.clone();
		nueva=new int[ncel];
		vHamming=new double[ngen];
		eEspacial=new double[ngen+1];
		eVectTemporal=new int[ngen+1];
		regla=regl.clone();	
	}

	public static void runSimulation() throws InterruptedException{
		genAct++;
		eEspacial[0]=entropia(actual);
		eVectTemporal[0]=actual[actual.length/2];

		for(int i=0;i<nhilos;++i){
			hilos[i] = new Thread(new ca1DSimulator(i*nCel/nhilos,(i+1)*nCel/nhilos));
		}

		/* BARRERA */
		PostBarrera pbar = new PostBarrera();
		barrera = new CyclicBarrier(nhilos,pbar);

		for(int i=0;i<nhilos;++i){
			hilos[i].start();
		}

		for(int i=0;i<nhilos;++i){
			hilos[i].join();
		}

		eTemporal=entropia(eVectTemporal);
				
	}

	public void nextGen(){
		for(int i=ini;i<fin;++i){
			nueva[i]=regla[(actual[(actual.length+i-1)%actual.length]*4+actual[i]*2+actual[(i+1)%actual.length])];	
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
	}


	public void run(){
		caComputation(nGen);
	}

}