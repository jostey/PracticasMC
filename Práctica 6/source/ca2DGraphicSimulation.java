import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

public class ca2DGraphicSimulation{
	static int dim;
    static JPanel panelca2d;
	static panelImage2D panelimg;
    static panelOptions2D panelopt;
    static JFrame winGraf;
    static String[] namesSeries = {"Vivas","Muertas"};
 
	ca2DGraphicSimulation(){
        panelca2d = new JPanel();
        panelopt=new panelOptions2D();

		panelca2d.setLayout(new BorderLayout());
        panelca2d.add(panelopt,BorderLayout.EAST);
		panelca2d.setVisible(true);
	}

    public static void dibujarGraficas(){
        
        winGraf = new JFrame("Grafica Poblacion");
        winGraf.setLocationRelativeTo(null);

        plot2 pnlPoblacion = new plot2("Grafica Poblacion","Num Generaciones","Cantidad Pob",ca2DSimulator.matTotalPob,2,namesSeries);

        winGraf.setSize(880,380);
        winGraf.setLocation(0,0);
        winGraf.setResizable(true);

        winGraf.add(pnlPoblacion);

        winGraf.setVisible(true);
    }

	public static void addPanelImage(int d){
        dim=d;
        panelimg=new panelImage2D(dim);
        panelca2d.add(panelimg,BorderLayout.CENTER);       
    }

    public static void restart(){
        ca2DSimulator.dim=0;
        ca2DSimulator.genActual=0;
        panelimg.setVisible(false);
    }

	public static void update(int[][] mat) {
       	
        for(int y=0;y<ca2DSimulator.dim;++y){
        	for(int x=0;x<ca2DSimulator.dim;++x){
        		Color c=null;
        		if(mat[y][x]==1) c=new Color(0,255,0);
        		else if(mat[y][x]==0) c=new Color(20,20,20);

            	panelimg.image.setRGB(x, y, c.getRGB());
            	panelimg.repaint();
        	}
        	
        }

        panelopt.lbVivas.setText(" Vivas: "+ca2DSimulator.totalVivas);
        panelopt.lbMuertas.setText(" Muertas: "+ca2DSimulator.totalMuertas);


        // Muestra la población total generación en generación, pero no es nada eficiente.
        // plot2 pnlPoblacion = new plot2("Grafica Poblacion","Num Generaciones","Cantidad Pob",ca2DSimulator.matTotalPob,2,namesSeries);
        // panelopt.c.gridx = 0;
        // panelopt.c.gridy = 5;
        // panelopt.c.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        // panelopt.c.fill = java.awt.GridBagConstraints.HORIZONTAL;
        // panelopt.c.weightx = 1.0;
        // panelopt.add(pnlPoblacion,panelopt.c);

    }

}

class panelImage2D extends JPanel{
	static BufferedImage image;

	public panelImage2D(int dim){
		image = new BufferedImage(dim, dim, BufferedImage.TYPE_INT_ARGB);
	}

	@Override
    public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
}

class panelOptions2D extends JPanel{
    private JSpinner SpinnerDimRet = new JSpinner();
    private JLabel lbDimRet = new JLabel(" Dimension Reticula: ");
    private JRadioButton op1 = new JRadioButton(" Vecindad von Neumann");
    private JRadioButton op2 = new JRadioButton(" Vecindad Moore");
    private int op=2;
    private JButton btnGrafica = new JButton("Grafica");
    JButton btnStart = new JButton("Start");
    JLabel lbVivas = new JLabel(" Vivas: ");
    JLabel lbMuertas = new JLabel(" Muertas: ");

    static GridBagConstraints c;
    


    panelOptions2D(){
        setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.gray));
        setLayout(new GridBagLayout());
        
        c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridx = 0;
        c.gridy = 0;

        add(lbDimRet,c);

        SpinnerDimRet.setModel(new javax.swing.SpinnerNumberModel(200, 200, null, 1));

        c.gridx = 1;
        c.gridy = 0;

        add(SpinnerDimRet);

        ButtonGroup group = new ButtonGroup();
        group.add(op1);
        group.add(op2);
        op2.setSelected(true);

        op1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                op=1;
            }
        });

        op2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                op=2;
            }
        });

        c.gridx = 0;
        c.gridy = 1;

        add(op1,c);

        c.gridx = 1;
        c.gridy = 1;

        add(op2,c);

        c.gridx = 0;
        c.gridy = 2;
        
        btnGrafica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if(btnStart.getText().equals("Restart")) ca2DGraphicSimulation.dibujarGraficas();
            }
        });
        add(btnGrafica,c);

        c.gridx = 1;
        c.gridy = 2;

        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if(btnStart.getText().equals("Start")){
                    btnStart.setText("Run ... ");

                    ca2DSimulator.dim=(Integer)SpinnerDimRet.getValue();

                    ca2DSimulator.runSimulation(ca2DSimulator.dim, 1000, op);

                }
                else if(btnStart.getText().equals("Restart")) {
                    ca2DGraphicSimulation.restart();
                    btnStart.setText("Start");
                }   
            }
        });
        add(btnStart,c);

        c.gridx = 0;
        c.gridy = 3;

        add(lbVivas,c);

        c.gridx = 0;
        c.gridy = 4;

        add(lbMuertas,c);
    }

}