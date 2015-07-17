package com.chris.brkopani.gui;

import com.chris.brkopani.gui.analytics.DesktopAnalytics;
import com.chris.brkopani.logic.MySQLAccess;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author xristos pappas
 */
public class RaceIsUp {

    JFrame f;
    private JTable table;
    //private String[] columnNames = new String[]{"First Name", "Last Name", "Age", "Weight", "Town"};
    //private String[][] rowData = new String[][]{{"", "", "", "", ""}};
    private final Color col = new Color(86, 86, 86);
    private DefaultTableModel dtm;
    private int ingame = 1;
    private int lap = 1;
    private JLabel lapNumber;

    JTextField getNum;
    JButton addTime, addLap, bikerOut, raceEnd;

    public void init() throws SQLException {
        f = new JFrame("Bikr");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1010, 650);
        f.setLocationRelativeTo(null);
        //set image icon for the frame
        f.setIconImage(Toolkit.getDefaultToolkit().getImage("res/br.png"));
        this.getClass().getResource("res/br.png");

        f.getContentPane().setBackground(GetRect.col);
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
        f.add(new JScrollPane(table));
        f.add(timePanel(), BorderLayout.SOUTH);

        //listeners
        addTime.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int number = Integer.parseInt(getNum.getText());
                long time = System.currentTimeMillis();

                MySQLAccess.insertTime(number, time, ingame, lap);
                SimpleDateFormat dateFormat = new SimpleDateFormat("hh.mm.ss.S aa");
                String formattedDate = dateFormat.format(time);
                System.out.println(formattedDate);

            }
        });

        addLap.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lap += 1;
            }
        });
        
        raceEnd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    f.dispose();
                    DesktopAnalytics da = new DesktopAnalytics();
                    da.createAndShowgui();
                } catch (SQLException ex) {
                    Logger.getLogger(RaceIsUp.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        bikerOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ingame = 0;
                int number = Integer.parseInt(getNum.getText());
                long time = System.currentTimeMillis();

                MySQLAccess.insertTime(number, time, ingame, lap);
                SimpleDateFormat dateFormat = new SimpleDateFormat("hh.mm.ss.S aa");
                String formattedDate = dateFormat.format(time);
                System.out.println(formattedDate);
            }
        });

        f.setVisible(true);
    }

    private JPanel timePanel() {
        JPanel p = new JPanel();
        getNum = new JTextField(20);
        lapNumber = new JLabel("Lap: " + lap);
        lapNumber.setForeground(Color.LIGHT_GRAY);
        addTime = new JButton("Add time to biker");
        addLap = new JButton("Add Lap");
        bikerOut = new JButton("Abandonment Match");
        raceEnd = new JButton("Race End");
        p.setBackground(GetRect.col);
        p.add(lapNumber);
        p.add(getNum);
        p.add(addTime);
        p.add(bikerOut);
        p.add(addLap);
        p.add(raceEnd);
        return p;
    }

}
