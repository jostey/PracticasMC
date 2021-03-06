/**
 *
 * @author Francisco Gil Amorós
 */
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.math.BigInteger;
import java.awt.event.*;
import java.util.*;

public class cifradoFlujo extends JFrame{

	/**
     * Creates new form NewJFrame1
     */
    public cifradoFlujo() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtSimpleCifrar = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtSimpleCifrado = new javax.swing.JTextArea();
        btnCifrar1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtRuta = new javax.swing.JTextField();
        btnExaminar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtComplejoCifrado = new javax.swing.JTextArea();
        btnCifrar2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtSimpleCifrar.setColumns(20);
        txtSimpleCifrar.setRows(5);
        jScrollPane1.setViewportView(txtSimpleCifrar);

        jLabel1.setText("Texto simple a cifrar:");

        jLabel2.setText("Texto simple cifrado:");

        txtSimpleCifrado.setColumns(20);
        txtSimpleCifrado.setRows(5);
        jScrollPane2.setViewportView(txtSimpleCifrado);

        btnCifrar1.setText("Cifrar");
        btnCifrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCifrar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnCifrar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCifrar1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Cifrado texto simple", jPanel1);

        jLabel3.setText("Ruta:");

        btnExaminar.setText("Examinar ...");
        btnExaminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExaminarActionPerformed(evt);
            }
        });

        jLabel4.setText("Archivo de texto cifrado:");

        txtComplejoCifrado.setColumns(20);
        txtComplejoCifrado.setRows(5);
        jScrollPane3.setViewportView(txtComplejoCifrado);

        btnCifrar2.setText("Cifrar");
        btnCifrar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCifrar2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRuta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExaminar))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnCifrar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtRuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExaminar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCifrar2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Cifrado archivo texto", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>

    private void btnCifrar1ActionPerformed(java.awt.event.ActionEvent evt) {
        ventanaGenCif.tab=1;                                         
        new ventanaGenCif().setVisible(true);
    }   
    private void btnCifrar2ActionPerformed(java.awt.event.ActionEvent evt) {
        ventanaGenCif.tab=2;                                        
        new ventanaGenCif().setVisible(true);
    }
    private void btnExaminarActionPerformed(java.awt.event.ActionEvent evt) {
    	JFileChooser fileChooser = new JFileChooser();                                       
        if (fileChooser.showOpenDialog(modalToComponent) == JFileChooser.APPROVE_OPTION) {
    		File file = fileChooser.getSelectedFile();
    		lines=openFile(file);
		}
    }

    private String openFile(final File inputFile) {
        int i=0;
        String line;
        String lines = new String();
    	try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            txtRuta.setText(inputFile.getAbsolutePath());       	
            
	        while ((line = reader.readLine()) != null) {
                lines += line + "\n";
                i++;
            }

	    } catch (final IOException e) {
	        e.printStackTrace();
	        // todo: handle exception.
	    }

        return lines;
	}               

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        new cifradoFlujo().setVisible(true);
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton btnCifrar1;
    private javax.swing.JButton btnCifrar2;
    private javax.swing.JButton btnExaminar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    static javax.swing.JTextArea txtComplejoCifrado;
    private javax.swing.JTextField txtRuta;
    static javax.swing.JTextArea txtSimpleCifrado;
   	static javax.swing.JTextArea txtSimpleCifrar;
    private Component modalToComponent;
    static String lines;
    // End of variables declaration              
}


class ventanaGenCif extends JFrame {

    /**
     * Creates new form ventanaGenCif
     */
    public ventanaGenCif() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        btnRun = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtKey = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        listReglas = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        lbHamming = new javax.swing.JLabel();
        lbEEspacial = new javax.swing.JLabel();
        lbETemporal = new javax.swing.JLabel();
                                 
        btnRun.setText("Run");
        btnRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRunActionPerformed(evt);
            }
        });

        jLabel1.setText("Clave:");

        txtKey.setText("1234567890");

        String[] reglas;
        String[][] calcReglas=new String[5][3];

        DefaultListModel listModel = new DefaultListModel();
        try (final BufferedReader reader = new BufferedReader(new FileReader("reglas"))) {
        	String line;
            
            int i=0;
   			
	        while ((line = reader.readLine()) != null) {
                reglas = line.split(" ");
                listModel.addElement(reglas[0]);
                calcReglas[i][0]=reglas[1];
                calcReglas[i][1]=reglas[2];
                calcReglas[i][2]=reglas[3];
                i++;
	        }
    	} catch (final IOException e) {
	        e.printStackTrace();
	        // todo: handle exception.
    	}
    	listReglas = new JList(listModel);
        jScrollPane1.setViewportView(listReglas);

        
		listReglas.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList listReglas = (JList)evt.getSource();
		        if (evt.getClickCount() == 2) {

		            // Double-click detected
		            indexList = listReglas.locationToIndex(evt.getPoint());
                    lbHamming.setText("Distancia Hamming media: "+calcReglas[indexList][0]);
                    lbEEspacial.setText("Entropia espacial media: "+calcReglas[indexList][1]);
                    lbETemporal.setText("Entropia temporal: "+calcReglas[indexList][2]);
                }
		    }
		});


        jLabel2.setText("Reglas:");

        lbHamming.setText("Distancia Hamming media: ");

        lbEEspacial.setText("Entropia espacial media:");

        lbETemporal.setText("Entropia temporal:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtKey)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRun))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbHamming)
                                    .addComponent(lbEEspacial)
                                    .addComponent(lbETemporal))))
                        .addGap(0, 113, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRun)
                    .addComponent(txtKey, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(11, 11, 11)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbHamming)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbEEspacial)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbETemporal)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>

    private static int[] getCelulaInicial(){
        if(txtKey.getText().equals("")){
            return new int[0];
        }
        else{
        	String binary = new BigInteger(txtKey.getText().getBytes()).toString(2);
            int[] res=new int[binary.length()];
            int i=0, j=0;
            while(i<binary.length()){
                
                if(Character.isDigit(binary.charAt(i))){
                    res[j]=Character.getNumericValue(binary.charAt(i));
                
                    j++;
                }
                i++;
            }

            return res;
        }
    }

    private static int[] getTextoSimpleCifrar(){
        if(cifradoFlujo.txtSimpleCifrar.getText().equals("")){
            return new int[0];
        }
        else{
        	String binary = new BigInteger(cifradoFlujo.txtSimpleCifrar.getText().getBytes()).toString(2);
            int[] res=new int[binary.length()];
            int i=0, j=0;
            while(i<binary.length()){
                
                if(Character.isDigit(binary.charAt(i))){
                    res[j]=Character.getNumericValue(binary.charAt(i));
                
                    j++;
                }
                i++;
            }

            return res;
        }
    }

    private static int[] getTextoComplejoCifrar(){
        if(cifradoFlujo.lines.equals("")){
            return new int[0];
        }
        else{
            String binary = new BigInteger(cifradoFlujo.lines.getBytes()).toString(2);
            int[] res=new int[binary.length()];
            int i=0, j=0;
            while(i<binary.length()){
                
                if(Character.isDigit(binary.charAt(i))){
                    res[j]=Character.getNumericValue(binary.charAt(i));
                
                    j++;
                }
                i++;
            }

            return res;
        }
    }

    private static int[] getRegla(int numRegla){
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

    private static void cifrar(){
        int[] vcifrar=null;
        if(tab==1){
            cifradoFlujo.txtSimpleCifrado.setText(null);
            vcifrar=getTextoSimpleCifrar();           
        }else if(tab==2){
            cifradoFlujo.txtComplejoCifrado.setText(null);
            vcifrar=getTextoComplejoCifrar(); 
        }

        vCifrante = ca1DSimulator.eVectTemporal.clone();
        int[] vcifrado = new int[vcifrar.length];

        for(int i=0;i<vcifrar.length;++i){
            vcifrado[i]=vcifrar[i]^vCifrante[i];
        }

        String strCifradobits = Arrays.toString(vcifrado).replaceAll("\\[|\\]|,|\\s", "");
        String strCifrado = new String(new BigInteger(strCifradobits, 2).toByteArray());

        if(tab==1){
            cifradoFlujo.txtSimpleCifrado.append(strCifrado);
        }else if(tab==2){
            cifradoFlujo.txtComplejoCifrado.append(strCifrado);
        }

    }

    private void btnRunActionPerformed(java.awt.event.ActionEvent evt) {
    	celulaInicial=getCelulaInicial();
    	int i_regla=Integer.parseInt(listReglas.getModel().getElementAt(indexList));
    	nCel=celulaInicial.length;                           
     	ca1DSimulator.init(nCel,nGen,celulaInicial,getRegla(i_regla));
     	try{
     		ca1DSimulator.runSimulation();

    	}catch(InterruptedException e) {}

    	this.setVisible(false);
    	cifrar();
    }                                   

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify                     
    private javax.swing.JButton btnRun;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbEEspacial;
    private javax.swing.JLabel lbETemporal;
    private javax.swing.JLabel lbHamming;
    private javax.swing.JList<String> listReglas;
    private static javax.swing.JTextField txtKey;
    private int nCel;
    private int nGen=4000;
    private static int indexList=0;
    private static int[] celulaInicial;
    private static int[] vCifrante;
    private static String txtCifrado;
    static int tab;
    // End of variables declaration                   
}