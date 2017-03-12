/**
 * @author Francisco Gil Amorós
 */

import java.awt.*;
import javax.swing.*;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.data.xy.XYDataset; 
import org.jfree.data.xy.XYSeries; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 
import org.jfree.chart.plot.XYPlot; 
import org.jfree.chart.ChartFactory; 
import org.jfree.chart.plot.PlotOrientation; 
import org.jfree.data.xy.XYSeriesCollection; 
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import java.util.Vector;
import java.util.Random;

public class plot2 extends JPanel{

	// Multiples
   	public plot2(String chartTitle, String strX, String strY, double[][] data, int nSeries, String[] namesSeries){
   		JPanel panelChart = new JPanel();
   		panelChart.setPreferredSize(new Dimension(800,300));
   		panelChart.setOpaque(true);
      	JFreeChart xylineChart = ChartFactory.createXYLineChart(chartTitle, strX, strY, createDataset(data, nSeries, namesSeries), PlotOrientation.VERTICAL,
        						 true, true, false);
         
      	ChartPanel chartPanel = new ChartPanel(xylineChart);
      	chartPanel.setPreferredSize( new java.awt.Dimension(800,300));
      	final XYPlot plot = xylineChart.getXYPlot();
      	XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
      	Random rn = new Random();
      	for(int i=0;i<nSeries;++i){
      		renderer.setSeriesPaint(i,new Color(rn.nextInt(200),rn.nextInt(200),rn.nextInt(200)));
      	}
	    plot.setRenderer(renderer); 
	    this.add(chartPanel); 
   	}
   
   	private XYDataset createDataset(double[][] data, int nSeries, String[] namesSeries){
   		Vector<XYSeries> vecSeries = new Vector<XYSeries>(nSeries);

   		for(int i=0;i<nSeries;++i){
   			vecSeries.add(i,new XYSeries(namesSeries[i]));
   			for(int j=0;j<data[i].length;++j){
   				vecSeries.get(i).add(j,data[i][j]);
   			}
   		}
                 
      	XYSeriesCollection dataset = new XYSeriesCollection();
      	for(int i=0;i<nSeries;++i){
      		dataset.addSeries(vecSeries.get(i));
      	}

      	return dataset;
   	}

   	// Individual
   	public plot2(String chartTitle, String strX, String strY, double[] data){
   		JPanel panelChart = new JPanel();
   		panelChart.setPreferredSize(new Dimension(800,300));
   		panelChart.setOpaque(true);
      	JFreeChart xylineChart = ChartFactory.createXYLineChart(chartTitle, strX, strY, createDataset(data, chartTitle), PlotOrientation.VERTICAL,
        						 true, true, false);
         
      	ChartPanel chartPanel = new ChartPanel(xylineChart);
      	chartPanel.setPreferredSize( new java.awt.Dimension(800,300));
      	final XYPlot plot = xylineChart.getXYPlot();
      	XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
      	Random rn = new Random();
      	renderer.setSeriesPaint(0,new Color(rn.nextInt(200),rn.nextInt(200),rn.nextInt(200)));
	    plot.setRenderer(renderer); 
	    this.add(chartPanel); 
   	}
   
   	private XYDataset createDataset(double[] data, String key){

   		
   		XYSeries xys = new XYSeries(key);
   		for(int i=0;i<data.length;++i){
   			xys.add(i,data[i]);
   		}
   		                 
      	XYSeriesCollection dataset = new XYSeriesCollection();
      	dataset.addSeries(xys);

      	return dataset;
   }
}