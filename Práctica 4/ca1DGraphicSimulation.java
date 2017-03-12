import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

public class ca1DGraphicSimulation{
	
	private static int nCel, nGen;
	static panelImage panelimg;
    static panelOptions panelopt=new panelOptions();
    static JFrame frame;
    static JFrame winGraf;

	ca1DGraphicSimulation(){
        frame = new JFrame();
		frame.setTitle("Simulador Grafico Automata Celular");
		frame.setSize(800,600);
        frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setLayout(new BorderLayout());

		frame.add(panelopt,BorderLayout.SOUTH);
        frame.setVisible(true);
	}

    public static void dibujarGraficas(){
        plot objGHamming = new plot();
        plot objGEntropia = new plot();
        JPanel pnlHamming = new JPanel();
        pnlHamming.setOpaque(true);
        pnlHamming.setPreferredSize(new Dimension(800,300));
        JPanel pnlEntropia = new JPanel();
        pnlEntropia.setOpaque(true);
        pnlEntropia.setPreferredSize(new Dimension(800,300));
        ca1DSimulator.eTemporal=ca1DSimulator.entropia(ca1DSimulator.eVectTemporal);
        JLabel lbeTemporal = new JLabel(new String("Entropia temporal: "+ca1DSimulator.eTemporal));
        
        winGraf = new JFrame("Graficas: Curva de Hamming y Entropia");
        winGraf.setLocationRelativeTo(null);

        objGHamming.draw(pnlHamming, ca1DSimulator.vHamming);
        objGEntropia.draw(pnlEntropia, ca1DSimulator.eEspacial);
        pnlEntropia.add(lbeTemporal);

        winGraf.setSize(880,780);
        winGraf.setLocation(0,0);
        winGraf.setResizable(true);

        winGraf.setLayout(new GridLayout(2,1));

        winGraf.add(pnlHamming);
        winGraf.add(pnlEntropia);

        winGraf.setVisible(true);
    }

    public static void addPanelImage(int nCel, int nGen){
        nCel=nCel;
        nGen=nGen;
        panelimg=new panelImage(nCel,nGen+1);
        frame.add(panelimg,BorderLayout.CENTER);
    }

    public static void restart(){
        ca1DSimulator.genAct=0;
        panelimg.setVisible(false);
        winGraf.setVisible(false);
    }

    public static void update(int[] vector, int gen) {
        
        for (int x=0; x<ca1DSimulator.nCel;++x) {
            Color c=new Color((vector[x]*125)%255,(vector[x]*50)%255,(vector[x]*25)%255);
            panelimg.image.setRGB(x, gen, c.getRGB());
        }
        panelimg.repaint();
    }

    public static int[] getCelManual(){
        if(panelopt.txtCel.getText().equals("")){
            return new int[0];
        }
        else{
            int[] res=new int[panelopt.txtCel.getText().length()];
            int i=0, j=0;
            while(i<panelopt.txtCel.getText().length()){
                
                if(Character.isDigit(panelopt.txtCel.getText().charAt(i))){
                    res[j]=Character.getNumericValue(panelopt.txtCel.getText().charAt(i));
                    j++;
                }
                i++;
            }
            return res;
        }
    }

    public static int[] getRegla(){
        int[] res = new int[8];
        if(panelopt.txtRegla.getText().equals("")){
            return res;
        }
            
        else{
            int number = Integer.parseInt(panelopt.txtRegla.getText());
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
    }

}

class panelImage extends JPanel{
	static BufferedImage image;

	public panelImage(int nCel, int nGen){
		image = new BufferedImage(nCel, nGen, BufferedImage.TYPE_INT_ARGB);
	}

	@Override
    public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
}

class panelOptions extends JPanel{
	private JSpinner SpinnerCel = new JSpinner();
    private JSpinner SpinnerGen = new JSpinner();
    private JSpinner SpinnerHilos = new JSpinner();
    //private JSpinner SpinnerK = new JSpinner();
    JTextField txtCel = new JTextField();
    JTextField txtRegla = new JTextField();
    private JButton btnStart = new JButton("Start");
    private JLabel lbCel = new JLabel("Numero de Celulas");
    private JLabel lbGen = new JLabel("Numero de Generaciones");
    //private JLabel lbK = new JLabel("Numero de K");
    private JLabel lbHilos = new JLabel("Numero de Hilos");
    private JLabel lbCelManual = new JLabel("Celula Manual");
    private JLabel lbRegla = new JLabel("Regla");

	panelOptions(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(lbCel);

		SpinnerCel.setModel(new javax.swing.SpinnerNumberModel(2, 2, null, 1));
    	add(SpinnerCel);

    	add(lbGen);

        SpinnerGen.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
    	add(SpinnerGen);

    	//add(lbK);

        //SpinnerK.setModel(new javax.swing.SpinnerNumberModel(2, 2, null, 1));    	
    	//add(SpinnerK);

    	add(lbHilos);

        SpinnerHilos.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
    	add(SpinnerHilos);

        add(lbCelManual);
        add(txtCel);

        add(lbRegla);
        add(txtRegla);

    	btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	if(btnStart.getText().equals("Start")){
            		btnStart.setText("Restart");

            		ca1DSimulator.nCel=(Integer)SpinnerCel.getValue();
            		ca1DSimulator.nGen=(Integer)SpinnerGen.getValue();
            		//ca1DSimulator.k=(Integer)SpinnerK.getValue();
            		int nhilos=(Integer)SpinnerHilos.getValue();

                    if(!txtCel.getText().equals("")) ca1DSimulator.nCel=ca1DGraphicSimulation.getCelManual().length;

                    ca1DGraphicSimulation.addPanelImage(ca1DSimulator.nCel,ca1DSimulator.nGen);

                    ca1DSimulator.init();

                    ca1DSimulator.eEspacial[0]=ca1DSimulator.entropia(ca1DSimulator.actual);

            		ca1DGraphicSimulation.update(ca1DSimulator.actual,ca1DSimulator.genAct++);
                	try{
                		ca1DSimulator.runSimulation(ca1DSimulator.nCel,ca1DSimulator.nGen,ca1DSimulator.k,nhilos);
                	}catch(InterruptedException e){}

                    ca1DGraphicSimulation.dibujarGraficas();

                }
                else if(btnStart.getText().equals("Restart")) {
                    ca1DGraphicSimulation.restart();
                    btnStart.setText("Start");
                } 	
            }
        });
    	add(btnStart);  	    	
    }

}