import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

public class belZabGraphicSimulation{
	
	static JFrame win;
	static int dim;
	static JPanel mainBZ;
	static panelBelZab panelbelzab;
	static panelOptionsBZ panelopt;

	belZabGraphicSimulation(){
		mainBZ = new JPanel();

        panelopt=new panelOptionsBZ();
        mainBZ.setLayout(new BorderLayout());
        mainBZ.add(panelopt,BorderLayout.EAST);
		mainBZ.setVisible(true);		
	}

	public static void addPanelImage(int d){
        dim=d;
        panelbelzab=new panelBelZab(dim); 
		mainBZ.add(panelbelzab,BorderLayout.CENTER);
    }

    public static void restart(){
        parallelBelZab.dim=0;
        parallelBelZab.start=false;
        panelbelzab.setVisible(false);
    }

	public static void update() {
        for(int x=0;x<dim;++x){
        	for(int y=0;y<dim;++y){	
        		panelbelzab.image.setRGB(y, x, parallelBelZab.color[x][y].getRGB());
        	}
        }
        panelbelzab.repaint();
    }
}

class panelBelZab extends JPanel{
	static BufferedImage image;

	panelBelZab(int dim){
		image = new BufferedImage(dim, dim, BufferedImage.TYPE_INT_ARGB);
	}

	@Override
    public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
}

class panelOptionsBZ extends JPanel{
    private JSpinner SpinnerDim = new JSpinner();
    private JLabel lbDim = new JLabel(" Dimension : ");
    JTextField txtAlfa = new JTextField("1.2");
    private JLabel lbAlfa = new JLabel(" Alfa : ");
    JTextField txtBeta = new JTextField("1.0");
    private JLabel lbBeta = new JLabel(" Beta : ");
  	JTextField txtGamma = new JTextField("1.0");
    private JLabel lbGamma = new JLabel(" Gamma : ");

    JButton btnStart = new JButton("Start");

    static GridBagConstraints c;

    panelOptionsBZ(){
        setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.gray));
        setLayout(new GridBagLayout());
        
        c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridx = 0;
        c.gridy = 0;

        add(lbDim,c);

        SpinnerDim.setModel(new javax.swing.SpinnerNumberModel(200, 200, null, 1));

        c.gridx = 1;
        c.gridy = 0;

        add(SpinnerDim);

        c.gridx = 0;
        c.gridy = 1;

        add(lbAlfa,c);

        c.gridx = 1;
        c.gridy = 1;

        add(txtAlfa,c);

        c.gridx = 0;
        c.gridy = 2;

        add(lbBeta,c);

        c.gridx = 1;
        c.gridy = 2;

        add(txtBeta,c);
        c.gridx = 0;
        c.gridy = 3;

        add(lbGamma,c);

        c.gridx = 1;
        c.gridy = 3;

        add(txtGamma,c);
        
        c.gridx = 1;
        c.gridy = 4;

        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if(btnStart.getText().equals("Start")){
                	parallelBelZab.start = true;
                    btnStart.setText("Stop");

                    int dim=(Integer)SpinnerDim.getValue();
                    int ngen=100000;
                    float alf=Float.parseFloat(txtAlfa.getText());
                    float bet=Float.parseFloat(txtBeta.getText());
                    float gamm=Float.parseFloat(txtGamma.getText());
                    belZabGraphicSimulation.addPanelImage(dim);

                    parallelBelZab.runSimulation(dim,ngen,alf,bet,gamm);

                }
                else if(btnStart.getText().equals("Stop")) {
                    belZabGraphicSimulation.restart();
                    btnStart.setText("Start");
                }   
            }
        });
        add(btnStart,c);

    }

}