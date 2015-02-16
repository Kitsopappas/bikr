package com.chris.brkopani.gui;

import com.chris.brkopani.logic.MySQLAccess;
import java.awt.Color;
import java.awt.Toolkit;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author xristos
 */
public class RaceManipulation extends JFrame {

    private JTable table;
    //private String[] columnNames = new String[]{"First Name", "Last Name", "Age", "Weight", "Town"};
    //private String[][] rowData = new String[][]{{"", "", "", "", ""}};
    private final Color col = new Color(86, 86, 86);
    private DefaultTableModel dtm;

    public void createAndShowGui() throws SQLException {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1010, 550);

        setTitle("Bikr");
        setLocationRelativeTo(null);
        //set image icon for the frame
        setIconImage(Toolkit.getDefaultToolkit().getImage("res/br.png"));
        this.getClass().getResource("res/br.png");
        getContentPane().setBackground(col);
        dtm = new DefaultTableModel();
        dtm.addColumn("Αριθμός");
        dtm.addColumn("Όνομα");
        dtm.addColumn("Επόνυμο");
        dtm.addColumn("Ηλικία");
        dtm.addColumn("Βάρος");
        dtm.addColumn("Πόλη");

        table = new JTable(dtm);
        MySQLAccess.selectAll(dtm);
        table.revalidate();

        table.setBackground(Color.LIGHT_GRAY);
        add(new JScrollPane(table));
    }

}
