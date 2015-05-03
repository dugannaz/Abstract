import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;

public class MultiPlot {

    private XYPlot plot;
    private ChartPanel chartPanel;
    private int datasetIndex = 0;
    private List<XYSeriesCollection> seriesArrayList = new ArrayList<XYSeriesCollection>();

    public MultiPlot(String title, String xAxis) {
        super();
        String yAxis = title;
        XYSeriesCollection dataset = createDataset("Series");
        JFreeChart chart = ChartFactory.createXYLineChart("", xAxis, yAxis,
            dataset, PlotOrientation.VERTICAL, false, false, false);
        chart.setBackgroundPaint(Color.white);
        plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        ValueAxis axis = plot.getDomainAxis();
        axis.setAutoRange(true);
        NumberAxis rangeAxis2 = new NumberAxis("Range Axis 2");
        rangeAxis2.setAutoRangeIncludesZero(false);
        JPanel content = new JPanel(new BorderLayout());
        
        chartPanel = new ChartPanel(chart);
        content.add(chartPanel);
      
    }

    private XYSeriesCollection createDataset(String name) {
        XYSeries series = new XYSeries(name);
        return new XYSeriesCollection(series);
    }

    public ChartPanel getChart() {
        return chartPanel;
    }

    public void createAdditionalDataset() {
        seriesArrayList.add(createDataset("S" + this.datasetIndex));
        this.plot.setDataset(this.datasetIndex, seriesArrayList.get(datasetIndex));
        this.plot.setRenderer(this.datasetIndex, new StandardXYItemRenderer());
        this.datasetIndex++;
    }

    public XYSeriesCollection getXYSeries(int datasetIndex) {
        return seriesArrayList.get(datasetIndex);
    }

    public int getDatasetCount() {
        return this.plot.getDatasetCount();
    }

    public static MultiPlot newMultiPlot() {
    	MultiPlot mcf = new MultiPlot("Irr", "x");
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.add(mcf.getChart());
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        return mcf;
    }
    
    public void addDataSet(int[] data) {
    	
    	createAdditionalDataset();
    	for (int i=0; i<data.length; i++)
    		getXYSeries(datasetIndex-1).getSeries(0).add(i, data[i]);
    }
    
public void addDataSet(double[] data) {
    	
    	createAdditionalDataset();
    	for (int i=0; i<data.length; i++)
    		getXYSeries(datasetIndex-1).getSeries(0).add(i, data[i]);
    }
    
    public static void main(String[] args) throws InterruptedException {
        MultiPlot mcf = new MultiPlot("Battery", "Time");
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.add(mcf.getChart());
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        
        mcf.createAdditionalDataset();
        mcf.createAdditionalDataset();
        mcf.getXYSeries(0).getSeries(0).add(1, 10);
        mcf.getXYSeries(0).getSeries(0).add(2, 100);
        System.out.println("first");
        mcf.getXYSeries(1).getSeries(0).add(1, 20);
        mcf.getXYSeries(1).getSeries(0).add(2, 200);
        System.out.println("second");
        mcf.getXYSeries(2).getSeries(0).add(1, 30);
        mcf.getXYSeries(2).getSeries(0).add(2, 300);
    }
}