## Bikr
#### Aplication in java where you can manage the results of a race(Bike/Run)

> There are some screenshots

![alt tag](https://raw.githubusercontent.com/Kitsopappas/bikr/master/screenshots/Screenshot_1.png)
![alt tag](https://raw.githubusercontent.com/Kitsopappas/bikr/master/screenshots/Screenshot_2.png)
![alt tag](https://raw.githubusercontent.com/Kitsopappas/bikr/master/screenshots/Screenshot_3.png)

> Some code

```sh
public class Graph {

    static JInternalFrame frame;

    public void createAndShowGui(JDesktopPane desk) throws SQLException {

        //Δημιουργία tabed pane και εισαγωγή φορμών με εικόνες
        WebLookAndFeel.install();
        frame = new JInternalFrame("PieChart", true, true, true, true);
        frame.setFrameIcon(new ImageIcon("res/br.png"));
        frame.setBounds(530, 5, 520, 350);
        frame.getContentPane().getBackground();
        // This will create the dataset
        PieDataset dataset = createDataset();
        // based on the dataset we create the chart
        JFreeChart chart = createChart(dataset, "PieChart");
        // we put the chart into a panel
        ChartPanel chartPanel = new ChartPanel(chart);
        chart.setBackgroundPaint(Color.GRAY);
        // default size
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        // add it to our application
        frame.add(chartPanel);

        frame.setVisible(true);
        desk.add(frame);

    }

    /**
     * * Creates a sample dataset
     */

    private PieDataset createDataset() throws SQLException {
        DefaultPieDataset result = new DefaultPieDataset();
        int allBikers = MySQLAccess.ageCount(GetRect.ALL);
        int min = MySQLAccess.ageCount(GetRect.AGE_MIN);
        int mid = MySQLAccess.ageCount(GetRect.AGE_MID);
        int max = MySQLAccess.ageCount(GetRect.AGE_MAX);

        result.setValue("...-20", (100*min)/allBikers);
        result.setValue("21-30", (100*mid)/allBikers);
        result.setValue("35-...", (100*max)/allBikers);
        return result;

    }

    /**
     * * Creates a chart
     */
    private JFreeChart createChart(PieDataset dataset, String title) {

        JFreeChart chart = ChartFactory.createPieChart3D(title, // chart title
                dataset, // data
                true, // include legend
                true,
                false);

        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(290);
        plot.setSectionPaint("...-20", new Color(195, 203, 113));
        plot.setSectionPaint("21-30", new Color(174, 90, 65));
        plot.setSectionPaint("35-...", new Color(85, 158, 131));
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        return chart;

    }
}

```
