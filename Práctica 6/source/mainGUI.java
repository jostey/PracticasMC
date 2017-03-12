import java.awt.*;
import javax.swing.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.BrokenBarrierException;

public class mainGUI{
	// Atributos para 2D
	static int dim=0;
	//
	static JFrame frame;
	private JTabbedPane jTab = new javax.swing.JTabbedPane();
	static ca1DGraphicSimulation ca1dg;
	static ca2DGraphicSimulation ca2dg;

	mainGUI(){
		frame = new JFrame();
		frame.setTitle("Simulador Grafico Automata Celular - Francisco Gil A");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ca1dg = new ca1DGraphicSimulation();
		jTab.addTab("CA 1-D", ca1dg.panelca1d);

		ca2dg = new ca2DGraphicSimulation();
		jTab.addTab("CA 2-D", ca2dg.panelca2d);

		frame.getContentPane().setLayout(new BorderLayout ());
		frame.getContentPane().add(jTab, BorderLayout.CENTER);
		frame.pack();

		frame.setSize(800,600);
        frame.setResizable(true);

        frame.setVisible(true);

	}

	public static void main(String[] args) {
		mainGUI main = new mainGUI();		
	}
}