import java.util.Random;
import java.util.*;
import java.io.*;

public class calculoReglas{
	private static int nCel=1000;
	private static int nGen=4000;
	private static int k=2;
	private static int[] actual=new int[nCel];
	private static double[][] calcReglas = new double[256][3];
	private static double[][] resReglas = new double[256][2];

	public static void calculoReglas(){
		for(int i=0;i<=255;++i){
			genCelulaInicial();                                   
        	ca1DSimulator.init(nCel,nGen,actual,genRegla(i));
        	try{
        		ca1DSimulator.runSimulation();

        	}catch(InterruptedException e) {}
        	// Media Hamming
        	double sum=0.0;
        	for (int j=0; j < ca1DSimulator.vHamming.length; j++) {
     			sum = sum + ca1DSimulator.vHamming[j];
    		}
    		calcReglas[i][0]=sum/ca1DSimulator.vHamming.length;

    		// Media Entropia Espacial
        	sum=0.0;
        	for (int j=0; j < ca1DSimulator.eEspacial.length; j++) {
     			sum = sum + ca1DSimulator.eEspacial[j];
    		}
    		calcReglas[i][1]=sum/ca1DSimulator.eEspacial.length;

        	if(ca1DSimulator.eTemporal!=-0.0) calcReglas[i][2]=ca1DSimulator.eTemporal;

       	}

    	for(int i=0;i<255;++i){
    		resReglas[i][0]=i+1;
    		resReglas[i][1]=calcReglas[i][0]+calcReglas[i][1]+calcReglas[i][2];
    	}


    	Arrays.sort(resReglas, Comparator.comparing((double[] arr) -> arr[1])
                                      .reversed());;

    	BufferedWriter writer = null;
        try {
	    	File testFile = new File("reglas");
	    	writer = new BufferedWriter(new FileWriter(testFile));
	    	for(int i=0;i<5;++i){
	    		writer.write((int)resReglas[i][0]+" "+calcReglas[(int)resReglas[i][0]-1][0]+" "+calcReglas[(int)resReglas[i][0]-1][1]+" "+calcReglas[(int)resReglas[i][0]-1][2]+"\n");
	    	}
	    	
        } catch (Exception e) {}
        finally {
            try {
                // Close the writer regardless of what happens...
                writer.close();
            } catch (Exception e) {}
        }
        
	}

	private static void genCelulaInicial(){
       	Random rn = new Random();
		for(int i=0;i<actual.length;++i) actual[i]=rn.nextInt(k);
    }

    private static int[] genRegla(int numRegla){
        int[] res = new int[8];
            
        int number = numRegla;
        for(int i=0;i<8;++i){
            if(number==0){
                res[i]=0;
            }
            else{
                res[i]=number%2;
                number/=2;
            }
        }
        return res;
    }

    public static void main(String[] args) {
    	calculoReglas();
        System.out.println("[*] Terminado. Mejores reglas en el archivo reglas.");
    }
}