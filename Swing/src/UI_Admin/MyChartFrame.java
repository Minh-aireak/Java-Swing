package UI_Admin;

import java.text.DecimalFormat;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class MyChartFrame extends javax.swing.JFrame {

    public MyChartFrame() {
        initComponents();
    }
    public MyChartFrame(DefaultCategoryDataset dataset) {
        setTitle("Biểu đồ thống kê số vé theo phim");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JFreeChart chart = ChartFactory.createBarChart(
                "Thống kê số vé đã đặt theo phim", 
                "Tên phim",               
                "Số vé đã đặt",                   
                dataset,                 
                PlotOrientation.VERTICAL,
                false, true, false
        );
        NumberAxis rangeAxis = (NumberAxis) chart.getCategoryPlot().getRangeAxis();
        DecimalFormat decimalFormat = new DecimalFormat("#");
        rangeAxis.setNumberFormatOverride(decimalFormat);
        rangeAxis.setTickUnit(new NumberTickUnit(1));
        rangeAxis.setLowerBound(0);
        ChartPanel chartPanel = new ChartPanel(chart);
        setContentPane(chartPanel);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
