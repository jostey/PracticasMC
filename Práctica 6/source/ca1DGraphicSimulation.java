import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

public class ca1DGraphicSimulation{
	
	private static int nCel, nGen;
    static JPanel panelca1d;
	static panelImage1D panelimg;
    static panelOptions1D panelopt=new panelOptions1D();
    static JFrame winGraf;

	ca1DGraphicSimulation(){
		panelca1d = new JPanel();
        panelopt=new panelOptions1D();

        panelca1d.setLayout(new BorderLayout());
        panelca1d.add(panelopt,BorderLayout.EAST);
        panelca1d.setVisible(true);
	}

    public static void dibujarGraficas(){
        ca1DSimulator.eTemporal=ca1DSimulator.entropia(ca1DSimulator.eVectTemporal);
        JLabel lbeTemporal = new JLabel(new String("Entropia temporal: "+ca1DSimulator.eTemporal));
        
        winGraf = new JFrame("Graficas: Curva de Hamming y Entropia");
        winGraf.setLocationRelativeTo(null);

        ca1DSimulator.eTemporal=ca1DSimulator.entropia(ca1DSimulator.eVectTemporal);
        plot2 pnlHamming = new plot2("Grafica Curva de Hamming","X","Y",ca1DSimulator.vHamming);
        plot2 pnlEntropia = new plot2("Grafica Entropia Espacial","X","Y",ca1DSimulator.eEspacial);

        winGraf.setSize(880,780);
        winGraf.setLocation(0,0);
        winGraf.setResizable(true);

        winGraf.setLayout(new GridLayout(2,1));

        winGraf.add(pnlHamming);
        winGraf.add(pnlEntropia);
        pnlEntropia.add(lbeTemporal);

        winGraf.setVisible(true);
    }

    public static void addpanelImage1D(int nCel, int nGen){
        nCel=nCel;
        nGen=nGen;
        panelimg=new panelImage1D(nCel,nGen+1);
        panelca1d.add(panelimg,BorderLayout.CENTER);
    }

    public static void restart(){
        ca1DSimulator.genAct=0;
        panelimg.setVisible(false);
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

class panelImage1D extends JPanel{
	static BufferedImage image;

	public panelImage1D(int nCel, int nGen){
		image = new BufferedImage(nCel, nGen, BufferedImage.TYPE_INT_ARGB);
	}

	@Override
    public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
}

class panelOptions1D extends JPanel{
	private JSpinner SpinnerCel = new JSpinner();
    private JSpinner SpinnerGen = new JSpinner();
    //private JSpinner SpinnerHilos = new JSpinner();
    //private JSpinner SpinnerK = new JSpinner();
    JTextField txtCel = new JTextField();
    JTextField txtRegla = new JTextField("110");
    private JButton btnStart = new JButton("Start");
    private JButton btnGrafica = new JButton("Grafica");
    private JLabel lbCel = new JLabel(" Numero de Celulas: ");
    private JLabel lbGen = new JLabel(" Numero de Generaciones: ");
    //private JLabel lbK = new JLabel("Numero de K");
    //private JLabel lbHilos = new JLabel("Numero de Hilos");
    private JLabel lbCelManual = new JLabel(" Celula Manual: ");
    private JLabel lbRegla = new JLabel(" Regla: ");

	panelOptions1D(){
        setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.gray));
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridx = 0;
        c.gridy = 0;

		add(lbCel,c);

        c.gridx = 1;
        c.gridy = 0;

		SpinnerCel.setModel(new javax.swing.SpinnerNumberModel(200, 2, null, 1));
    	add(SpinnerCel,c);

        c.gridx = 0;
        c.gridy = 1;

    	add(lbGen,c);

        c.gridx = 1;
        c.gridy = 1;

        SpinnerGen.setModel(new javax.swing.SpinnerNumberModel(200, 1, null, 1));
    	add(SpinnerGen,c);

    	//add(lbK);

        //SpinnerK.setModel(new javax.swing.SpinnerNumberModel(2, 2, null, 1));    	
    	//add(SpinnerK);

    	//add(lbHilos);

        //SpinnerHilos.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
    	//add(SpinnerHilos);

        c.gridx = 0;
        c.gridy = 2;

        add(lbCelManual,c);

        c.gridx = 1;
        c.gridy = 2;

        txtCel.setPreferredSize( new Dimension( 100, 20 ) );
        add(txtCel,c);

        c.gridx = 0;
        c.gridy = 3;
        
        add(lbRegla,c);

        c.gridx = 1;
        c.gridy = 3;
        add(txtRegla,c);

        btnGrafica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if(btnStart.getText().equals("Restart")) ca1DGraphicSimulation.dibujarGraficas();
            }
        });
        c.gridx = 0;
        c.gridy = 4;
        add(btnGrafica,c);

    	btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	if(btnStart.getText().equals("Start")){
            		btnStart.setText("Restart");

            		ca1DSimulator.nCel=(Integer)SpinnerCel.getValue();
            		ca1DSimulator.nGen=(Integer)SpinnerGen.getValue();
            		//ca1DSimulator.k=(Integer)SpinnerK.getValue();
            		//int nhilos=(Integer)SpinnerHilos.getValue();
                    int nhilos=4;

                    if(!txtCel.getText().equals("")) ca1DSimulator.nCel=ca1DGraphicSimulation.getCelManual().length;

                    ca1DGraphicSimulation.addpanelImage1D(ca1DSimulator.nCel,ca1DSimulator.nGen);

                    ca1DSimulator.init();

                    ca1DSimulator.eEspacial[0]=ca1DSimulator.entropia(ca1DSimulator.actual);

            		ca1DGraphicSimulation.update(ca1DSimulator.actual,ca1DSimulator.genAct++);
                	try{
                		ca1DSimulator.runSimulation(ca1DSimulator.nCel,ca1DSimulator.nGen,ca1DSimulator.k,nhilos);
                	}catch(InterruptedException e){}

                }
                else if(btnStart.getText().equals("Restart")) {
                    ca1DGraphicSimulation.restart();
                    btnStart.setText("Start");
                } 	
            }
        });
        c.gridx = 1;
        c.gridy = 4;
    	add(btnStart,c);


    }

}