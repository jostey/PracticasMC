import java.util.Random;
import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.*;

public class parallelTumoralGrowth implements Runnable{
	static Random rn = new Random();
	static boolean start = false;
	static int[][] actual, nueva;

	private static float ps, pd, pm, pp, pq, NP;
	private static float rr, rrm, rrp;
	private static int[][] PH;
	static float p1,p2,p3,p4;

	static int genActual=0;
	static int dim;

	static JFrame frame = new JFrame();
	static panelImage panelimg;

	static ExecutorService ex;
	static CyclicBarrier barrera;
	int ini, fin;
	static int nHilos = 4;

	public parallelTumoralGrowth(int ini, int fin){
		this.ini=ini;
		this.fin=fin;
	}

	public void run(){
		caComputation();
	}


	public static void init(){
		actual=new int[dim][dim];
		nueva=new int[dim][dim];
		PH=new int[dim][dim];

		// Inicial.
		actual[dim/2][dim/2]=1;

	}

	public static void update(){
		for (int x=0; x<dim;++x) {
			for(int y=0; y<dim;++y){
	            Color c=new Color((actual[x][y]*125)%255,(actual[x][y]*50)%255,(actual[x][y]*25)%255);
	            panelimg.image.setRGB(x, y, c.getRGB());
	        }
	        
        }

		panelimg.repaint();
	}

	public static void restart(){
        start=false;
        panelimg.setVisible(false);
    }

	public static void runSimulation(int dim_, float ps_, float pm_, float pp_, int NP_){

		dim = dim_;
		init();

		ps=ps_;
		pm=pm_;
		pp=pp_;
		NP=NP_;

		panelimg = new panelImage(dim);
		panelimg.setVisible(true);
		frame.add(panelimg,BorderLayout.CENTER);

		frame.validate();
		frame.repaint();

		ex = Executors.newFixedThreadPool(nHilos);

		barrera = new CyclicBarrier(nHilos, 
			new Runnable() {
				public void run() {
                    update();
                    for(int i = 0; i < actual.length; ++i){
						actual[i] = nueva[i].clone();
					}
                }
            }
        );

		parallelTumoralGrowth tum;
		for(int i=0;i<nHilos;++i){
			tum = new parallelTumoralGrowth(i*dim/nHilos,(i+1)*dim/nHilos);
			ex.execute(tum);
		}
		ex.shutdown();
	}
	
	public void nextGen(){


		for(int i=ini;i<fin;++i){
			if(i!=0 && i!=dim){

				for(int j=1;j<dim-1;++j){
					
					if (rn.nextFloat() < ps){

						if (rn.nextFloat() < pp){
							synchronized(barrera){
								PH[i][j]++;
								if(PH[i][j] >= NP) proliferation(i,j);
								else migration(i,j);
							}
							
						}
						else{
							migration(i,j);
						}
						
					}
					else{
						synchronized(barrera){
							nueva[i][j]=0;
							PH[i][j]=0;
						}
						
					}
				}

			}
		}
	}

	static void proliferation(int i, int j){
		if(actual[i][j]==1){
			updateCell(i,j,probs(i,j));
			PH[i][j]=0;
		}
		
	}

	static void migration(int i, int j){
		if(rn.nextFloat() < pm){
			if(actual[i][j]==1){
				updateCell(i,j,probs(i,j));
				nueva[i][j]=0;
				PH[i][j]=0;
			}
		}
		
	}

	static float[] probs(int i, int j){
		float denominador = (4-actual[(i+1)%dim][j]-actual[(i-1+dim)%dim][j]-actual[i][(j+1)%dim]-actual[i][(j-1+dim)%dim]);

		p1 = (1-actual[(i-1+dim)%dim][j])/denominador;
		p2 = (1-actual[(i+1)%dim][j])/denominador;
		p3 = (1-actual[i][(j-1+dim)%dim])/denominador;
		p4 = (1-actual[i][(j+1)%dim])/denominador;

		float[] res={p1,p2,p3,p4};
		return res;
	}

	static void updateCell(int i, int j, float[] p){
		if (rn.nextFloat() <= p[0]){
			if(nueva[(i-1+dim)%dim][j]==0){
				nueva[(i-1+dim)%dim][j]=1;
				PH[(i-1+dim)%dim][j]=0;
			}
		}
		else if (rn.nextFloat() <= p[0]+p[1]){
			if(nueva[(i+1)%dim][j]==0){
				nueva[(i+1)%dim][j]=1;
				PH[(i+1)%dim][j]=0;
			}
		}
		else if (rn.nextFloat() <= p[0]+p[1]+p[2]){
			if(nueva[i][(j-1+dim)%dim]==0){
				nueva[i][(j-1+dim)%dim]=1;
				PH[i][(j-1+dim)%dim]=0;

			}
		}
		else if (rn.nextFloat() <= p[0]+p[1]+p[2]+p[3]){
			if(nueva[i][(j+1)%dim]==0){
				nueva[i][(j+1)%dim]=1;
				PH[i][(j+1)%dim]=0;
			}
		}

	}

	public void caComputation(){
		try{
			while(start){
				nextGen();
				barrera.await();		        
			}
		}
		
		catch(InterruptedException ie){}
		catch(BrokenBarrierException be){}
	}

	public static void main(String[] args) throws Exception{

		
		panelOptions panelopt = new panelOptions();

		frame.setLayout(new BorderLayout());
        frame.add(panelopt,BorderLayout.EAST);
		
		frame.setVisible(true);
		frame.setTitle("Simulador de Tumores");
		frame.setSize(800,600);
        frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
	}
}

class panelImage extends JPanel{
	static BufferedImage image;

	public panelImage(int dim){
		image = new BufferedImage(dim, dim, BufferedImage.TYPE_INT_ARGB);
	}

	@Override
    public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
}

class panelOptions extends JPanel{
    private JSpinner SpinnerDim = new JSpinner();
    private JLabel lbDim = new JLabel(" Dimension: ");
    private JSpinner SpinnerPS = new JSpinner();
    private JLabel lbPS = new JLabel(" Prob. Survival (%): ");
    private JSpinner SpinnerPM = new JSpinner();
    private JLabel lbPM = new JLabel(" Prob. Migration (%): ");
    private JSpinner SpinnerPP = new JSpinner();
    private JLabel lbPP = new JLabel(" Prob. Proliferation (%): ");
    private JSpinner SpinnerNP = new JSpinner();
    private JLabel lbNP = new JLabel(" NP: ");
    JButton btnStart = new JButton("Start");

    static GridBagConstraints c;
    


    panelOptions(){
        setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.gray));
        setLayout(new GridBagLayout());
        
        c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridx = 0;
        c.gridy = 0;

        add(lbDim,c);

        SpinnerDim.setModel(new javax.swing.SpinnerNumberModel(512, 32, null, 6));

        c.gridx = 1;
        c.gridy = 0;

        add(SpinnerDim,c);

        c.gridx = 0;
        c.gridy = 1;

        add(lbPS,c);

        SpinnerPS.setModel(new javax.swing.SpinnerNumberModel(100, 0, 100, 10));

        c.gridx = 1;
        c.gridy = 1;

        add(SpinnerPS,c);

        c.gridx = 0;
        c.gridy = 2;

        add(lbPM,c);

        SpinnerPM.setModel(new javax.swing.SpinnerNumberModel(25, 0, 100, 10));

        c.gridx = 1;
        c.gridy = 2;

        add(SpinnerPM,c);

 		c.gridx = 0;
        c.gridy = 3;

        add(lbPP,c);

        SpinnerPP.setModel(new javax.swing.SpinnerNumberModel(90, 0, 100, 10));

        c.gridx = 1;
        c.gridy = 3;

        add(SpinnerPP,c);

        c.gridx = 0;
        c.gridy = 4;

        add(lbNP,c);

        SpinnerNP.setModel(new javax.swing.SpinnerNumberModel(1, 0, 2, 1));

        c.gridx = 1;
        c.gridy = 4;

        add(SpinnerNP,c);

        
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	Thread hiloSim;
                if(btnStart.getText().equals("Start")){
                	parallelTumoralGrowth.start=true;
                    btnStart.setText("Stop");

                    parallelTumoralGrowth.runSimulation( (Integer)SpinnerDim.getValue(),
                    							 (float)((Integer)SpinnerPS.getValue())*0.01f, 
                    							 (float)((Integer)SpinnerPM.getValue())*0.01f,
                    							 (float)((Integer)SpinnerPP.getValue())*0.01f,
                    							 (Integer)SpinnerNP.getValue()
                    							);
                    

                }
                else if(btnStart.getText().equals("Stop")) {
                    parallelTumoralGrowth.restart();
                    btnStart.setText("Start");
                }   
            }
        });
        c.gridx = 1;
        c.gridy = 5;
        add(btnStart,c);
    }

}